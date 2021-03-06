package com.lastartupsaas.workbench.view.business.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.domain.admin.Resource;
import com.lastartupsaas.workbench.domain.admin.Role;
import com.lastartupsaas.workbench.util.MenuDataTest;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.RadioboxYesOrNoEditor;
import com.lastartupsaas.workbench.view.form.impl.TreeTableFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 角色编辑页
 * 
 * @author lifeilong
 * @date 2016-12-29
 */
@SpringView(name = RoleEditView.VIEW_NAME)
public class RoleEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = -8563519246837149689L;
	public static final String VIEW_NAME = "role_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：系统管理 > 权限管理 > 角色";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("角色名称", "roleName", InputFieldEditor.class, true, null, true).setInputDescr("为权限组设置特定名称，便于添加管理员时选择使用"));
		base_message.add(new FormField("状态", "state", new RadioboxYesOrNoEditor("正常", "禁用"), true, null, true));
		List<Resource> resources = MenuDataTest.getInstance().getMenuData();
		base_message.add(new FormField("权限设置", "resources", new TreeTableFieldEditor(resources, "id", "name", "resourceList"), true, null, true));
		
		formAgent.addFieldListToMap("角色信息", base_message);
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
			Role role = new Role(1L, "超级管理员", "0");

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
