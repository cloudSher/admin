package com.lastartupsaas.workbench.domain.admin;

import com.lastartupsaas.workbench.domain.BaseObject;

/**
 * Author: alvin
 * Date: 2016-08-26
 */
public class User extends BaseObject {
	private Long id;
	private String name;
	private String password;
	private String createTime;
	private String updateTime;
	private String lastLoadTime;
	private Role role;

	public User() {
	}

	public User(Long id, String name, String password, String createTime, String updateTime, String lastLoadTime,Role role) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.lastLoadTime = lastLoadTime;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
