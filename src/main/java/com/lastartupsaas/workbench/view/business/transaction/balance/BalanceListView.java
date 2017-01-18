package com.lastartupsaas.workbench.view.business.transaction.balance;

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
import com.lastartupsaas.workbench.view.form.impl.DateFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

/**
 * 结算单管理列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = BalanceListView.VIEW_NAME)
public class BalanceListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = -189774808812077698L;

	public static final String VIEW_NAME = "balance.view";

	private FormAgent searchAgent;
	private String searchName;
	private String processFlag;// 流程标识：1加盟流程、2服务流程、3服务完成

	public BalanceListView() {
	}

	public BalanceListView(String processFlag) {
		this.processFlag = processFlag;
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("export")) {
			// this.navigateToView("Member_edit.view/id=" + parameters[0]);
			Notification.show("提示", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
		}
		if (command.isActionId("remark")) {
			Notification.show("提示", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
		}
	}

	@Override
	protected void createFilterForm(HorizontalLayout layout) {
		searchAgent = new FormAgent();
		searchAgent.setDataHelper(new FormDataHelper());
		searchAgent.setSearchMode(true);
		searchAgent.setFieldColumnCount(5);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);

		searchAgent.addField(new FormField("1".equals(processFlag)? "出账时间" : "划账时间", "start_time", DateFieldEditor.class, false, null, false).setInputDescr("1".equals(processFlag)? "开始出账时间" : "开始划账时间"));
		searchAgent.addField(new FormField("~", "end_time", DateFieldEditor.class, false, null, false).setInputDescr("1".equals(processFlag)? "结束出账时间" : "结束划账时间"));
		searchAgent.addField(new FormField("划账金额", "amount_min", InputFieldEditor.class, false, null, false).setInputDescr("划账金额(起)"));
		searchAgent.addField(new FormField("~", "amount_max", InputFieldEditor.class, false, null, false).setInputDescr("划账金额(止)"));
		searchAgent.addField(new FormField("品牌名称", "brand_name", InputFieldEditor.class, false, null, false).setInputDescr("品牌名称"));

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
		this.searchName = (String) this.searchAgent.getFieldValue("param");
		this.dataGrid.reloadDatas();
	}

	@Override
	public DataGridRow convertRowData(Object item) {
		return null;
	}

	@Override
	public int getDataCount() {
		return 0;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {
		return null;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		if ("1".equals(processFlag)) {
			gridModel.addColumn(new DataGridColumn("结算单编号", String.class));
			gridModel.addColumn(new DataGridColumn("出账日期", String.class));
			gridModel.addColumn(new DataGridColumn("应结金额(元)", String.class));
			gridModel.addColumn(new DataGridColumn("服务费(元)", String.class));
			gridModel.addColumn(new DataGridColumn("划账金额(元)", String.class));
			gridModel.addColumn(new DataGridColumn("品牌名称", String.class));
			gridModel.addColumn(new DataGridColumn("状态", String.class));
			gridModel.addColumn(new DataGridColumn("备注", String.class));
			
		} else {
			gridModel.addColumn(new DataGridColumn("划账日期", String.class));
			gridModel.addColumn(new DataGridColumn("结算单编号", String.class));
			gridModel.addColumn(new DataGridColumn("出账日期", String.class));
			gridModel.addColumn(new DataGridColumn("应结金额(元)", String.class));
			gridModel.addColumn(new DataGridColumn("平台分成(元)", String.class));
			gridModel.addColumn(new DataGridColumn("划账金额(元)", String.class));
			gridModel.addColumn(new DataGridColumn("品牌名称", String.class));
			gridModel.addColumn(new DataGridColumn("支付渠道", String.class));
			gridModel.addColumn(new DataGridColumn("支付渠道交易单号", String.class));
			gridModel.addColumn(new DataGridColumn("操作人", String.class));
			gridModel.addColumn(new DataGridColumn("备注", String.class));
			
			gridModel.addItemAction(new ActionCommand("remark", "备注"));
		}
		gridModel.addCommonAction(new ActionCommand("export", "导出数据"));
	}
}
