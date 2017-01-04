package com.lastartupsaas.workbench.view.business.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lastartupsaas.workbench.domain.admin.Post;
import com.lastartupsaas.workbench.domain.admin.Resource;
import com.lastartupsaas.workbench.domain.admin.Role;
import com.lastartupsaas.workbench.util.MenuDataTest;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.SelectFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.TreeTableFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.TwinColSelectEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 岗位编辑页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = PostEditView.VIEW_NAME)
public class PostEditView extends BaseWorkBenchEditorView {

	public static final String VIEW_NAME = "post_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：系统管理 > 权限管理 > 岗位";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		formAgent.addField(new FormField("岗位名称", "postName", InputFieldEditor.class, true, null, true).setInputDescr("2-10位汉字组成"));

		List<Post> postList = new ArrayList<>();
		postList.add(new Post(1L, "执行总裁", null, null, null, null, null, null));
		postList.add(new Post(2L, "运营总监", null, null, null, null, null, null));
		postList.add(new Post(3L, "财务总监", null, null, null, null, null, null));
		formAgent.addField(
				new FormField("所属上级", "superiorPost", new SelectFieldEditor(postList, "id", "postName"), true, null, true).setInputDescr("选择所属上级岗位"));

		List<Role> roleList = new ArrayList<>();
		roleList.add(new Role(1L, "系统管理员"));
		roleList.add(new Role(2L, "运营人员"));
		roleList.add(new Role(3L, "测试人员"));
		roleList.add(new Role(4L, "测试1"));
		roleList.add(new Role(5L, "测试2"));
		roleList.add(new Role(6L, "测试3"));
		roleList.add(new Role(7L, "测试4"));
		roleList.add(new Role(8L, "测试5"));
		roleList.add(new Role(9L, "测试6"));
		roleList.add(new Role(10L, "测试7"));
		roleList.add(new Role(11L, "测试8"));
		roleList.add(new Role(12L, "测试9"));
		roleList.add(new Role(13L, "测试10"));
		formAgent.addField(new FormField("角色", "roles", new TwinColSelectEditor(roleList,"id", "roleName"), true, null, true).setInputDescr("选择角色"));
	}

	@Override
	protected Object createVirginObject() {
		return new Post();
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
			List<Role> roles = new ArrayList<>();
			roles.add(new Role(1L, "系统管理员"));
			roles.add(new Role(2L, "运营人员"));
			Post post = new Post(1L, "运营总监", new Post(1L, "执行总裁", null, null, null, null, null, null), roles, null, null, null, null);
			return post;
		}
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return "post_list.view";
	}
}
