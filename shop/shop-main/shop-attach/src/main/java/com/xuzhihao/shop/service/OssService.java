package com.xuzhihao.shop.service;

import javax.servlet.http.HttpServletRequest;

import com.xuzhihao.shop.dto.OssCallbackResult;
import com.xuzhihao.shop.dto.OssPolicyResult;

/**
 * OSS上传管理Service
 */
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();
    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
