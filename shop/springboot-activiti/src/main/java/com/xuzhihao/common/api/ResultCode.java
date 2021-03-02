package com.xuzhihao.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultCode {

	public static final ResultCode LOGIN_SUCCESS = new ResultCode(Constast.OK, "登陆成功");
	public static final ResultCode LOGIN_ERROR_PASS = new ResultCode(Constast.ERROR, "登陆失败,用户名或密码不正确");

	public static final ResultCode UPDATE_SUCCESS = new ResultCode(Constast.OK, "更新成功");
	public static final ResultCode UPDATE_ERROR = new ResultCode(Constast.ERROR, "更新失败");

	public static final ResultCode ADD_SUCCESS = new ResultCode(Constast.OK, "添加成功");
	public static final ResultCode ADD_ERROR = new ResultCode(Constast.ERROR, "添加失败");

	public static final ResultCode DELETE_SUCCESS = new ResultCode(Constast.OK, "删除成功");
	public static final ResultCode DELETE_ERROR = new ResultCode(Constast.ERROR, "删除失败");

	public static final ResultCode RESET_SUCCESS = new ResultCode(Constast.OK, "重置成功");
	public static final ResultCode RESET_ERROR = new ResultCode(Constast.ERROR, "重置失败");

	public static final ResultCode DISPATCH_SUCCESS = new ResultCode(Constast.OK, "分配成功");
	public static final ResultCode DISPATCH_ERROR = new ResultCode(Constast.ERROR, "分配失败");

	public static final ResultCode OPERATE_SUCCESS = new ResultCode(Constast.OK, "操作成功");
	public static final ResultCode OPERATE_ERROR = new ResultCode(Constast.ERROR, "操作失败");

	public static final ResultCode DEPLOY_SUCCESS = new ResultCode(Constast.OK, "发布成功");
	public static final ResultCode DEPLOY_ERROR = new ResultCode(Constast.ERROR, "发布失败,请检查流程是否正确");

	public static final ResultCode START_PROCESS_SUCCESS = new ResultCode(Constast.OK, "流程启动成功");
	public static final ResultCode START_PROCESS_ERROR = new ResultCode(Constast.ERROR, "流程启动失败");

	public static final ResultCode MISSION_SUCCESS = new ResultCode(Constast.OK, "任务完成");
	public static final ResultCode MISSION_FAILURE = new ResultCode(Constast.ERROR, "任务完成失败");
	private Integer code;
	private String msg;

}
