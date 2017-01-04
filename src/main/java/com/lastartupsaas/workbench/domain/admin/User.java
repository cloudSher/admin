package com.lastartupsaas.workbench.domain.admin;

import com.lastartupsaas.workbench.domain.BaseObject;

/**
 * Author: alvin
 * Date: 2016-08-26
 */
public class User extends BaseObject {
	/** ID */
	private Long id;
	/** 员工ID */
	private String jobNumber;
	/** 登录名 */
	private String loginName;
	/** 真实姓名 */
	private String realName;
	/** 密码 */
	private String password;
	
	/** 创建者 */
	private String createUser;
	/** 创建时间 */
	private String createTime;
	/** 更新者 */
	private String updateUser;
	/** 更新时间 */
	private String updateTime;
	/** 上次登录时间 */
	private String lastLoadTime;
	/** 岗位 */
	private Post post;

	public User() {
	}

	public User(Long id, String jobNumber, String loginName, String realName, String password, String createUser, String createTime,
			String updateUser, String updateTime, String lastLoadTime, Post post) {
		super();
		this.id = id;
		this.jobNumber = jobNumber;
		this.loginName = loginName;
		this.realName = realName;
		this.password = password;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.lastLoadTime = lastLoadTime;
		this.post = post;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getLastLoadTime() {
		return lastLoadTime;
	}

	public void setLastLoadTime(String lastLoadTime) {
		this.lastLoadTime = lastLoadTime;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
