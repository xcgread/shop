package com.xuzhihao.shop.portal.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户注册参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsRegisterParam {
	@ApiModelProperty(value = "用户名", required = true)
	@NotEmpty(message = "用户名不能为空")
	private String username;
	@ApiModelProperty(value = "密码", required = true)
	@NotEmpty(message = "密码不能为空")
	private String password;
	@ApiModelProperty(value = "联系电话", required = true)
	@NotEmpty(message = "联系电话不能为空")
	private String telephone;
	@ApiModelProperty(value = "验证码", required = true)
	@NotEmpty(message = "验证码不能为空")
	private String authCode;
}
