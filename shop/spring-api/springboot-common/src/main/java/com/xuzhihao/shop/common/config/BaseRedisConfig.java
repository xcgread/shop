package com.xuzhihao.shop.common.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

/**
 * 基础redis配置
 */
public class BaseRedisConfig {

	/**
	 * 为RedisTemplate原生设置中文序列化器 (对象操作并且序列化可见)
	 * 
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisSerializer<Object> serializer = redisSerializer();
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(serializer);
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(serializer);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	/**
	 * 原生redisSerializer序列化
	 * 
	 * @return
	 */
	@Bean
	public RedisSerializer<Object> redisSerializer() {
		// 创建JSON序列化器
		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		// 必须设置，否则无法将JSON转化为对象，会转化成Map类型
		objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
		serializer.setObjectMapper(objectMapper);
		return serializer;
	}

	/**
	 * 扩展redisTemplate 自定义redis缓存管理器配置不同失效时间
	 * 
	 * @author Administrator
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
				this.getRedisCacheConfigurationWithTtl(-1), // 默认策略，未配置的 key 会使用这个
				this.getRedisCacheConfigurationMap() // 指定 key 策略
		);
	}

	/**
	 * 结合@Cacheable设置默认有效期
	 * 
	 * @return
	 */
	private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
		Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
		// SsoCache和BasicDataCache进行过期时间配置
		redisCacheConfigurationMap.put("GROUP30M", this.getRedisCacheConfigurationWithTtl(30 * 60));
		// 自定义设置缓存时间
		redisCacheConfigurationMap.put("GROUP1M", this.getRedisCacheConfigurationWithTtl(60));
		redisCacheConfigurationMap.put("-DEFAULT", this.getRedisCacheConfigurationWithTtl(-1));
		return redisCacheConfigurationMap;
	}

	@SuppressWarnings("deprecation")
	private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		redisCacheConfiguration = redisCacheConfiguration
				.serializeValuesWith(
						RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
				.entryTtl(Duration.ofSeconds(seconds));

		return redisCacheConfiguration;
	}

	/**
	 * 序列化redis对象serializeRedisTemplate
	 * 
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean("serializeRedisTemplate")
	public RedisTemplate<String, Object> serializeRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}

}
