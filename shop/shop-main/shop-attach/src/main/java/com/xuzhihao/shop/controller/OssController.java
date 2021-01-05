package com.xuzhihao.shop.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuzhihao.shop.common.api.CommonResult;
import com.xuzhihao.shop.dto.OssCallbackResult;
import com.xuzhihao.shop.dto.OssPolicyResult;
import com.xuzhihao.shop.service.impl.OssServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * OSS相关操作接口
 */
@Controller
@Api(tags = "OssController", description = "OSS管理")
@RequestMapping("/aliyun/oss")
public class OssController {
    @Autowired
    private OssServiceImpl ossService;

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OssPolicyResult> policy() {
        OssPolicyResult result = ossService.policy();
        return CommonResult.success(result);
    }

    @ApiOperation(value = "oss上传成功回调")
    @RequestMapping(value = "callback", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return CommonResult.success(ossCallbackResult);
    }

}
