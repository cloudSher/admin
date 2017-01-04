package com.lastartupsaas.workbench.domain.admin;

import java.util.List;

import com.lastartupsaas.workbench.domain.BaseObject;

/**
 * 岗位实体类
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
public class Post extends BaseObject {
	/** 岗位ID */
	private Long id;
	/** 岗位名称 */
	private String postName;
	/** 上级岗位 */
	private Post superiorPost;
	/** 角色 */
	private List<Role> roles;
	
	/** 创建者 */
	private String createUser;
	/** 创建时间 */
	private String createTime;
	/** 更新者 */
	private String updateUser;
	/** 更新时间 */
	private String updateTime;

	public Post() {
	}

	public Post(Long id, String postName, Post superiorPost, List<Role> roles, String createUser, String createTime, String updateUser,
			String updateTime) {
		super();
		this.id = id;
		this.postName = postName;
		this.superiorPost = superiorPost;
		this.roles = roles;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public Post getSuperiorPost() {
		return superiorPost;
	}

	public void setSuperiorPost(Post superiorPost) {
		this.superiorPost = superiorPost;
	}

	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
}
