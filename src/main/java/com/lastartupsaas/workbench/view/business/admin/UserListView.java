package com.lastartupsaas.workbench.view.business.admin;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.domain.admin.Post;
import com.lastartupsaas.workbench.domain.admin.User;
import com.lastartupsaas.workbench.view.BaseWorkBenchListWithSearchView;
import com.lastartupsaas.workbench.view.datagrid.ActionCommand;
import com.lastartupsaas.workbench.view.datagrid.DataGridColumn;
import com.lastartupsaas.workbench.view.datagrid.DataGridModel;
import com.lastartupsaas.workbench.view.datagrid.DataGridRow;
import com.lastartupsaas.workbench.view.datagrid.DataListRequest;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormBuildLayout;
import com.lastartupsaas.workbench.view.form.FormDataHelper;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;

/**
 * 管理员列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = UserListView.VIEW_NAME)
public class UserListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 6074628817603712546L;

	public static final String VIEW_NAME = "user_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public UserListView() {
		this("当前位置：系统管理 > 权限管理 > 管理员");
	}
	
	public UserListView(String caption) {
		this.setViewCaption(caption);
		this.withFilterSection = false;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("user_add.view");
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
		searchAgent = new FormAgent();
		searchAgent.setDataHelper(new FormDataHelper());
		searchAgent.setSearchMode(true);
		searchAgent.setFieldColumnCount(2);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
		searchAgent.addField(new FormField("", "name", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的登录名或姓名"));

		FormBuildLayout form = searchAgent.buildSearchForm();
		form.setWidth("100%");
		form.setSpacing(true);

		layout.addComponent(form);
		layout.setExpandRatio(form, 1);
	}

	@Override
	protected void doSearchCancelAction() {
		this.searchAgent.resetFormData();
		this.doSearchAction();
	}

	@Override
	protected void doSearchAction() {
		this.searchName = (String) this.searchAgent.getFieldValue("name");
		this.dataGrid.reloadDatas();
	}

	@Override
	public DataGridRow convertRowData(Object item) {
		User user = (User) item;
		return new DataGridRow(user.getId(), new Object[] { user.getLoginName(), user.getJobNumber(), user.getRealName(),
				user.getPost() == null ? "" : user.getPost().getPostName(), user.getLastLoadTime() });
	}

	@Override
	public int getDataCount() {
		return 1;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {
		List<User> users = new ArrayList<User>();
		User user = new User(1L, "102299", "admin", "张三", "123456", "admin", "", "admin", "", "2016-12-30 16:00:54",
				new Post(1L, "运营总监", null, null, null, null, null, null));
		User user1 = new User(2L, "102124", "admin", "李四", "123456", "admin", "", "admin", "", "2016-12-21 11:30:23",
				new Post(1L, "财务总监", null, null, null, null, null, null));
		User user2 = new User(3L, "102890", "admin", "王武", "123456", "admin", "", "admin", "", "2016-11-13 09:02:51",
				new Post(1L, "人力总监", null, null, null, null, null, null));
		users.add(user);
		users.add(user1);
		users.add(user2);
		return users;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		gridModel.addColumn(new DataGridColumn("登录名", String.class));
		gridModel.addColumn(new DataGridColumn("员工号", String.class));
		gridModel.addColumn(new DataGridColumn("姓名", String.class));
		gridModel.addColumn(new DataGridColumn("岗位", String.class));
		gridModel.addColumn(new DataGridColumn("上次登录时间", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增管理员"));
		gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
	}
}
