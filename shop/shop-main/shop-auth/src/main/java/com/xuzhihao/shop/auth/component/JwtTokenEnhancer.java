package com.xuzhihao.shop.auth.component;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.xuzhihao.shop.auth.domain.SecurityUser;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT内容增强器
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
		Map<String, Object> info = new HashMap<>();
		// 把用户ID设置到JWT中
		info.put("id", securityUser.getId());
		info.put("client_id", securityUser.getClientId());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}
}
