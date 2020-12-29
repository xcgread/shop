package com.xuzhihao.domain;

import java.util.List;

public class SessionInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6797153522222826199L;
	private Long id;// 用户ID
	private String loginname;// 登录名
	private String nickname;// 昵称
	private String name;// 姓名
	private String skinid;// 皮肤
	private String openid;// openid
	private String ip;// 用户IP
	private String siteid;// CMS站点ID

	private List<String> resourceList;// 用户可以访问的资源地址列表

	public List<String> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public String getSkinid() {
		return skinid;
	}

	public void setSkinid(String skinid) {
		this.skinid = skinid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

}
