package com.lastartupsaas.workbench.view.business.transaction.order;

import java.util.List;

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
import com.vaadin.ui.Notification;

/**
 * 会员列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = ComplainListView.VIEW_NAME)
public class ComplainListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 1639094224696258904L;

	public static final String VIEW_NAME = "complain_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public ComplainListView() {
		this.setViewCaption("当前位置：交易 > 交易订单 > 投诉管理");
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("member_edit.view");
		}
		if (command.isActionId("disable")) {
			Notification.show("账号设置", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
		}
	}

	@Override
	protected void createFilterForm(HorizontalLayout layout) {
		searchAgent = new FormAgent();
		searchAgent.setDataHelper(new FormDataHelper());
		searchAgent.setSearchMode(true);
		searchAgent.setFieldColumnCount(4);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
		
		
		searchAgent.addField(new FormField("订单编号", "order_no", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的订单编号"));

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
		this.searchName = (String) this.searchAgent.getFieldValue("order_no");
		this.dataGrid.reloadDatas();
	}

	@Override
	public DataGridRow convertRowData(Object item) {
		return null;
	}

	@Override
	public int getDataCount() {
		return 1;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {

		return null;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		gridModel.addColumn(new DataGridColumn("订单ID", String.class));
		gridModel.addColumn(new DataGridColumn("投诉用户ID", String.class));
		gridModel.addColumn(new DataGridColumn("投诉用户昵称", String.class));
		gridModel.addColumn(new DataGridColumn("投诉内容", String.class));
		gridModel.addColumn(new DataGridColumn("投诉时间", String.class));
		gridModel.addColumn(new DataGridColumn("被投诉品牌", String.class));
		gridModel.addColumn(new DataGridColumn("被投诉品牌ID", String.class));
		gridModel.addColumn(new DataGridColumn("状态", String.class));
		gridModel.addColumn(new DataGridColumn("处理人", String.class));
		gridModel.addColumn(new DataGridColumn("处理时间", String.class));
		gridModel.addColumn(new DataGridColumn("备注", String.class));
	}
}
