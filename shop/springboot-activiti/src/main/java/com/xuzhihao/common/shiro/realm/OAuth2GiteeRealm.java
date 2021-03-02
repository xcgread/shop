package com.xuzhihao.common.shiro.realm;

import org.springframework.stereotype.Component;

import com.xuzhihao.common.api.AuthcTypeEnum;

/**
 * Gitee OAuth2 Realm
 */
@Component
public class OAuth2GiteeRealm extends OAuth2Realm {

    @Override
    public AuthcTypeEnum getAuthcTypeEnum() {
        return AuthcTypeEnum.GITEE;
    }
}