package com.lastartupsaas.workbench.view.business.admin;

import java.util.List;

import com.lastartupsaas.workbench.domain.admin.Resource;
import com.lastartupsaas.workbench.util.MenuDataTest;
import com.lastartupsaas.workbench.view.BaseWorkbenchTreeListView;
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
import com.vaadin.ui.Notification;

/**
 * 资源列表页
 * 
 * @author lifeilong
 * @date 2016-12-29
 */
@SpringView(name = ResourceListView.VIEW_NAME)
public class ResourceListView extends BaseWorkbenchTreeListView {

	private static final long serialVersionUID = 1946274487874257014L;
	public static final String VIEW_NAME = "resource_list.view";
	private FormAgent searchAgent;

	private String searchName;

	public ResourceListView() {
		this.setViewCaption("当前位置：系统管理 > 权限管理 > 资源");
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("resource_edit.view");
		}
		if (command.isActionId("edit")) {
			this.navigateToView("resource_edit.view/id=" + parameters[0]);
		}
		if (command.isActionId("del")) {
			Notification.show("提示", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
		}
	}
	@Override
	protected void createFilterForm(HorizontalLayout layout) {
		searchAgent = new FormAgent();
		searchAgent.setDataHelper(new FormDataHelper());
		searchAgent.setSearchMode(true);
		searchAgent.setFieldColumnCount(2);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
		searchAgent.addField(new FormField("资源名称", "ResourceName", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的资源名称"));

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
		Resource resource = (Resource) item;
		return new DataGridRow(resource.getId(), new Object[] { resource.getId().toString(), resource.getName(), "0".equals(resource.getState()) ? "禁用" : "正常" }, resource.getResourceList());
	}

	@Override
	public int getDataCount() {
		return 10;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {
		return MenuDataTest.getInstance().getMenuData();
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {
		gridModel.addColumn(new DataGridColumn("资源编码", String.class));
		gridModel.addColumn(new DataGridColumn("资源名称", String.class));
		gridModel.addColumn(new DataGridColumn("状态", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增资源"));
		gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
	}
}
