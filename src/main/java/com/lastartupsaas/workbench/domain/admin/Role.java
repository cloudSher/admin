package com.lastartupsaas.workbench.domain.admin;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.domain.BaseObject;

/**
 * Author: alvin Date: 2016-08-26
 */
public class Role extends BaseObject {
	private Long id;
	private String roleName;
	private String state;
	private List<Resource> resources;

	public Role() {
	}

	public Role(Long id, String roleName) {
		this.id = id;
		this.roleName = roleName;
		this.resources = new ArrayList<Resource>();
	}

	public Role(Long id, String roleName, String state) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.state = state;
		this.resources = new ArrayList<Resource>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void appenMenuItem(Resource resource) {
		this.resources.add(resource);
	}

}
