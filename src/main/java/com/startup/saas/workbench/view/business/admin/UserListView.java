package com.startup.saas.workbench.view.business.admin;

import java.util.ArrayList;
import java.util.List;

import com.startup.saas.workbench.domain.admin.Role;
import com.startup.saas.workbench.domain.admin.User;
import com.startup.saas.workbench.view.BaseWorkBenchListWithSearchView;
import com.startup.saas.workbench.view.datagrid.ActionCommand;
import com.startup.saas.workbench.view.datagrid.DataGridColumn;
import com.startup.saas.workbench.view.datagrid.DataGridModel;
import com.startup.saas.workbench.view.datagrid.DataGridRow;
import com.startup.saas.workbench.view.datagrid.DataListRequest;
import com.startup.saas.workbench.view.form.FormAgent;
import com.startup.saas.workbench.view.form.FormBuildLayout;
import com.startup.saas.workbench.view.form.FormDataHelper;
import com.startup.saas.workbench.view.form.FormField;
import com.startup.saas.workbench.view.form.impl.InputFieldEditor;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;

/**
 * Author: alvin Date: 2016-08-26
 */
@SpringView(name = UserListView.VIEW_NAME)
public class UserListView extends BaseWorkBenchListWithSearchView {

	public static final String VIEW_NAME = "user_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public UserListView() {
		this.setViewCaption("当前位置：系统管理 > 权限管理 > 管理员");
		this.withFilterSection = false;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("user_edit.view");
		}
		if (command.isActionId("edit")) {
			this.navigateToView("user_edit.view/id=" + parameters[0]);
		}
		if (command.isActionId("del")) {
			System.out.println("del");
		}
	}

	@Override
	protected void createFilterForm(HorizontalLayout layout) {
//		searchAgent = new FormAgent();
//		searchAgent.setDataHelper(new FormDataHelper());
//		searchAgent.setSearchMode(true);
//		searchAgent.setFieldColumnCount(2);
//		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
//		searchAgent.addField(new FormField("登录名", "name", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的名称"));
//
//		FormBuildLayout form = searchAgent.buildForm();
//		form.setWidth("100%");
//		form.setSpacing(true);
//
//		layout.addComponent(form);
//		layout.setExpandRatio(form, 1);
	}

	@Override
	protected void doSearchCancelAction() {
//		this.searchAgent.resetFormData();
//		this.doSearchAction();
	}

	@Override
	protected void doSearchAction() {
//		this.searchName = (String) this.searchAgent.getFieldValue("name");
//		this.dataGrid.reloadDatas();
	}

	@Override
	public DataGridRow convertRowData(Object item) {
		User user = (User) item;
		return new DataGridRow(user.getId(), new Object[] { user.getId(), user.getName(), user.getLastLoadTime(), user.getRole() == null ? "" : user.getRole().getRoleName()});
	}

	@Override
	public int getDataCount() {
		return 1;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {
		List<User> users = new ArrayList<User>();
		User user = new User(1L, "admin", "admin", "2016-12-07 14:30:23", "2016-12-07 14:30:45", "2016-12-07 14:31:15",new Role(1L, "超级管理员"));
		users.add(user);
		return users;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		gridModel.addColumn(new DataGridColumn("ID", Long.class));
		gridModel.addColumn(new DataGridColumn("登录名", String.class));
		gridModel.addColumn(new DataGridColumn("上次登录时间", String.class));
		gridModel.addColumn(new DataGridColumn("权限组", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增管理员"));
		gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
	}
}
