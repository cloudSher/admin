package com.startup.saas.workbench.view.business.admin;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.startup.saas.workbench.domain.admin.Resource;
import com.startup.saas.workbench.domain.admin.Role;
import com.startup.saas.workbench.util.MenuDataTest;
import com.startup.saas.workbench.view.BaseWorkBenchEditorView;
import com.startup.saas.workbench.view.ViewContext;
import com.startup.saas.workbench.view.form.FormAgent;
import com.startup.saas.workbench.view.form.FormField;
import com.startup.saas.workbench.view.form.impl.InputFieldEditor;
import com.startup.saas.workbench.view.form.impl.TreeTableFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * Author: alvin Date: 2016-08-26
 */
@SpringView(name = RoleEditView.VIEW_NAME)
public class RoleEditView extends BaseWorkBenchEditorView {

	public static final String VIEW_NAME = "role_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：系统管理 > 权限管理 > 权限组";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		formAgent.addField(new FormField("权限组名", "roleName", InputFieldEditor.class, true, null, true).setInputDescr("为权限组设置特定名称，便于添加管理员时选择使用"));
		List<Resource> resources = MenuDataTest.getInstance().getMenuData();
		formAgent.addField(new FormField("权限设置", "resources", new TreeTableFieldEditor(resources, "id", "name", "resourceList"), true, null, true));
	}

	@Override
	protected Object createVirginObject() {
		return new Role();
	}

	@Override
	protected boolean saveObject(Object data) {
		return true;
	}

	@Override
	protected boolean updateObject(Object data) {
		return true;
	}

	@Override
	protected Object loadEdittingDataFromContext(ViewContext vc) {
		String id = vc.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			Role role = new Role(1L, "超级管理员");

			Resource system_manage = new Resource(6000L, "系统管理", false, false, null, null, null);
			Resource authority_manage = new Resource(6001L, "权限管理", false, false, null, null, null);
			system_manage.appenMenuItem(authority_manage);
			authority_manage.appenMenuItem(new Resource(600101L, "管理员", false, false, null, null, "user_list.view"));

			role.appenMenuItem(system_manage);
			return role;
		}
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return "role_list.view";
	}
}
