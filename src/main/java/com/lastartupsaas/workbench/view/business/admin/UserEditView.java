package com.lastartupsaas.workbench.view.business.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

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
 * 管理员编辑页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = UserEditView.VIEW_NAME)
public class UserEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = -3699172478466125818L;
	public static final String VIEW_NAME = "user_edit.view";

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
		String id = vc.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			User user = new User(1L, "102299", "admin", "张三", "123456", "admin", "", "admin", "", "2016-12-30 16:00:54",
					new Post(1L, "运营总监", null, null, null, null, null, null));
			return user;
		}
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return "user_list.view";
	}
}
