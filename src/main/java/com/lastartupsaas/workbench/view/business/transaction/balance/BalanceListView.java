package com.lastartupsaas.workbench.view.business.transaction.balance;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.BaseWorkBenchListWithSearchView;
import com.lastartupsaas.workbench.view.business.transaction.order.OrderViewWindow;
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
import com.vaadin.shared.ui.BorderStyle;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Button.ClickEvent;

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
	private String processFlag;// 结算标识：1未结算查询、2已结算查询

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

		searchAgent.addField(new FormField("1".equals(processFlag) ? "出账时间" : "划账时间", "start_time", DateFieldEditor.class, false, null, false)
				.setInputDescr("1".equals(processFlag) ? "开始出账时间" : "开始划账时间"));
		searchAgent.addField(new FormField("~", "end_time", DateFieldEditor.class, false, null, false)
				.setInputDescr("1".equals(processFlag) ? "结束出账时间" : "结束划账时间"));
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
		if ("1".equals(processFlag)) {
			Button button = new Button("00000001", new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					BalanceViewWindow formWindow = new BalanceViewWindow("");
					UI.getCurrent().addWindow(formWindow);
				}
			});
			button.addStyleName(ValoTheme.BUTTON_LINK);
			return new DataGridRow("00000001", new Object[] { button, "2016-10-11 12:41:25", "30.00", "2.40", "27.60", "真功夫", "未结算", "待未结算中" });
		} else {
			Button button = new Button("00000002", new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					BalanceViewWindow formWindow = new BalanceViewWindow("");
					UI.getCurrent().addWindow(formWindow);
				}
			});
			button.addStyleName(ValoTheme.BUTTON_LINK);
			return new DataGridRow("00000002", new Object[] { "2016-10-11 12:41:25", button, "2016-10-11 12:41:25", "30.00", "2.40", "27.60",
					"重庆小面", "支付宝", "222111102222205567", "李四", "结算完成" });
		}
	}

	@Override
	public int getDataCount() {
		return 0;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {
		List<String> list = new ArrayList<>();
		list.add("1");
		return list;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		if ("1".equals(processFlag)) {
			gridModel.addColumn(new DataGridColumn("结算单编号", Button.class));
			gridModel.addColumn(new DataGridColumn("出账日期", String.class));
			gridModel.addColumn(new DataGridColumn("应结金额(元)", String.class));
			gridModel.addColumn(new DataGridColumn("服务费(元)", String.class));
			gridModel.addColumn(new DataGridColumn("划账金额(元)", String.class));
			gridModel.addColumn(new DataGridColumn("品牌名称", String.class));
			gridModel.addColumn(new DataGridColumn("状态", String.class));
			gridModel.addColumn(new DataGridColumn("备注", String.class));

		} else {
			gridModel.addColumn(new DataGridColumn("划账日期", String.class));
			gridModel.addColumn(new DataGridColumn("结算单编号", Button.class));
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
