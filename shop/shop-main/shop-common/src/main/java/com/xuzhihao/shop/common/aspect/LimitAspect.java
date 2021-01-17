package com.xuzhihao.shop.common.aspect;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xuzhihao.shop.common.annotation.Limit;
import com.xuzhihao.shop.common.api.CommonResult;
import com.xuzhihao.shop.common.api.LimitType;
import com.xuzhihao.shop.common.constant.AuthConstant;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 限流切面实现
 * 
 * @author Administrator
 *
 */
@Aspect
@Component
@Slf4j
public class LimitAspect {
	private final static String UNKNOWN = "unknown";
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private DefaultRedisScript<Long> redisScript;

	@Around("@annotation(limit)")
	public Object around(ProceedingJoinPoint joinPoint, Limit limit) throws Throwable {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		Object[] args = joinPoint.getArgs();
		String key;
		long now = Instant.now().toEpochMilli();
		TimeUnit timeUnit = limit.timeUnit();
		long max = limit.max();
		long timeout = limit.timeout();
		long ttl = timeUnit.toMillis(timeout);
		long expired = now - ttl;
		LimitType limitType = limit.limitType();
		String jwtToken = request.getHeader(AuthConstant.JWT_TOKEN_HEADER);
		switch (limitType) {
		case IP:
			key = getIpAddr();
			break;
		case CUSTOMER:
			key = limit.key();
			break;
		default:
			key = SecureUtil.md5(jwtToken + "-" + request.getRequestURL().toString() + "-" + Arrays.asList(args));
			break;
		}
		Long executeTimes = stringRedisTemplate.execute(redisScript, Collections.singletonList(limit.prefix() + key),
				now + "", ttl + "", expired + "", max + "");
		if (executeTimes != null) {
			if (executeTimes == 0) {
				log.error("【{}】在单位时间 {} 毫秒内已达到访问上限，当前接口上限 {}", key, ttl, max);
				return CommonResult.validateRepeat();
			} else {
				log.info("【{}】在单位时间 {} 毫秒内访问 {} 次", key, ttl, executeTimes);

			}
		}
		return joinPoint.proceed();
	}

	/**
	 * 获取IP地址 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddr() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String ip = null;
		try {
			ip = request.getHeader("x-forwarded-for");
			if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} catch (Exception e) {
			log.error("IPUtils ERROR ", e);
		}
		// 使用代理，则获取第一个IP地址
		if (!StrUtil.isEmpty(ip) && ip.length() > 15) {
			if (ip.indexOf(StrUtil.COMMA) > 0) {
				ip = ip.substring(0, ip.indexOf(StrUtil.COMMA));
			}
		}
		return ip;
	}

}