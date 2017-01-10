package com.lastartupsaas.workbench.view.business.admin;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.domain.admin.Post;
import com.lastartupsaas.workbench.domain.admin.Role;
import com.lastartupsaas.workbench.view.BaseWorkBenchListWithSearchView;
import com.lastartupsaas.workbench.view.datagrid.ActionCommand;
import com.lastartupsaas.workbench.view.datagrid.DataGridColumn;
import com.lastartupsaas.workbench.view.datagrid.DataGridModel;
import com.lastartupsaas.workbench.view.datagrid.DataGridRow;
import com.lastartupsaas.workbench.view.datagrid.DataListRequest;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.widgets.ModalWindow;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

/**
 * 岗位列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = PostListView.VIEW_NAME)
public class PostListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = -1191121791880909757L;
	public static final String VIEW_NAME = "post_list.view";
	private FormAgent searchAgent;

	private String searchName;

	public PostListView() {
		this.setViewCaption("当前位置：系统管理 > 权限管理 > 岗位");
		this.withFilterSection = false;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("post_edit.view");
		}
		if (command.isActionId("edit")) {
			this.navigateToView("post_edit.view/id=" + parameters[0]);
		}
		if (command.isActionId("del")) {
			System.out.println("del");
		}
		if (command.isActionId("lowerPost")) {
			LowerPostListView lowerPostListView = new LowerPostListView();
			lowerPostListView.initView();
			ModalWindow formWindow = new ModalWindow("查看下级岗位", lowerPostListView);
			UI.getCurrent().addWindow(formWindow);
		}
	}

	@Override
	protected void createFilterForm(HorizontalLayout layout) {
		// searchAgent = new FormAgent();
		// searchAgent.setDataHelper(new FormDataHelper());
		// searchAgent.setSearchMode(true);
		// searchAgent.setFieldColumnCount(2);
		// searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
		// searchAgent.addField(new FormField("岗位", "roleName", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的岗位"));
		//
		// FormBuildLayout form = searchAgent.buildSearchForm();
		// form.setWidth("100%");
		// form.setSpacing(true);
		//
		// layout.addComponent(form);
		// layout.setExpandRatio(form, 1);
	}

	@Override
	protected void doSearchCancelAction() {
		// this.searchAgent.resetFormData();
		// this.doSearchAction();
	}

	@Override
	protected void doSearchAction() {
		// this.searchName = (String)this.searchAgent.getFieldValue("name");
		// this.dataGrid.reloadDatas();
	}

	@Override
	public DataGridRow convertRowData(Object item) {
		Post post = (Post) item;
		return new DataGridRow(post.getId(), new Object[] { post.getId(), post.getPostName() });
	}

	@Override
	public int getDataCount() {
		return 1;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {
		List<Post> posts = new ArrayList<>();
		List<Role> roles = new ArrayList<>();
		roles.add(new Role(1L, "系统管理员"));
		roles.add(new Role(2L, "运营人员"));

		Post post = new Post(1L, "运营总监", new Post(2L, "执行总裁", null, null, null, null, null, null), roles, null, null, null, null);
		Post post1 = new Post(2L, "财务总监", new Post(2L, "执行总裁", null, null, null, null, null, null), roles, null, null, null, null);
		posts.add(post);
		posts.add(post1);
		return posts;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {
		gridModel.addColumn(new DataGridColumn("岗位ID", Long.class));
		gridModel.addColumn(new DataGridColumn("岗位名称", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增岗位"));
		gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
		gridModel.addItemAction(new ActionCommand("lowerPost", "查看下级岗位"));
	}
}
