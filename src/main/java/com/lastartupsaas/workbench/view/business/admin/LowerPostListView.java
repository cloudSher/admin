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
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;

/**
 * 岗位列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = LowerPostListView.VIEW_NAME)
public class LowerPostListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 2714143026768026023L;
	public static final String VIEW_NAME = "lower_post_list.view";
	private FormAgent searchAgent;

	private String searchName;

	public LowerPostListView() {
		this.setViewCaption("");
		this.withFilterSection = false;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
	}

	@Override
	protected void createFilterForm(HorizontalLayout layout) {
	}

	@Override
	protected void doSearchCancelAction() {
	}

	@Override
	protected void doSearchAction() {
	}

	@Override
	public DataGridRow convertRowData(Object item) {
		Post post = (Post) item;
		return new DataGridRow(post.getId(), new Object[] { post.getId(), post.getPostName(), post.getSuperiorPost().getPostName() });
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
		gridModel.addColumn(new DataGridColumn("所属上级", String.class));
	}
}
