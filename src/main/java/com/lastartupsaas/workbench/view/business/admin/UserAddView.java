package com.lastartupsaas.workbench.view.business.admin;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.domain.admin.Post;
import com.lastartupsaas.workbench.domain.admin.User;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.SelectFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 管理员添加页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = UserAddView.VIEW_NAME)
public class UserAddView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = 9024889963716430540L;
	public static final String VIEW_NAME = "user_add.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：系统管理 > 权限管理 > 管理员";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("登录名", "loginName", InputFieldEditor.class, true, null, true).setInputDescr("3-20位字符，可由中文、英文及数字”组成"));
		base_message.add(new FormField("员工ID", "jobNumber", InputFieldEditor.class, true, null, true).setInputDescr("6-10位数字"));
		base_message.add(new FormField("姓名", "realName", InputFieldEditor.class, true, null, true).setInputDescr("2-4位汉字组成"));
		base_message.add(new FormField("登录密码", "password", InputFieldEditor.class, true, null, true).setInputDescr("6-20位字符，可由英文、数字及标点符号组成"));
		base_message.add(new FormField("确认密码", "", InputFieldEditor.class, true, null, true).setInputDescr("请再次输入您的密码"));

		List<Post> postList = new ArrayList<>();
		postList.add(new Post(1L, "执行总裁", null, null, null, null, null, null));
		postList.add(new Post(2L, "运营总监", null, null, null, null, null, null));
		postList.add(new Post(3L, "财务总监", null, null, null, null, null, null));
		base_message.add(new FormField("岗位", "post", new SelectFieldEditor(postList, "id", "postName"), true, null, true)
				.setInputDescr("请选择一个权限组，如果还未设置，请先建立权限组后再添加管理员"));
		
		formAgent.addFieldListToMap("管理员信息", base_message);
	}

	@Override
	protected Object createVirginObject() {
		return new User();
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
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return "user_list.view";
	}
}
