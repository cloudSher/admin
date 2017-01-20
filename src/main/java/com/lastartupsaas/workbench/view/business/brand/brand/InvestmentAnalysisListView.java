package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.BaseWorkBenchListWithSearchView;
import com.lastartupsaas.workbench.view.datagrid.ActionCommand;
import com.lastartupsaas.workbench.view.datagrid.DataGridColumn;
import com.lastartupsaas.workbench.view.datagrid.DataGridModel;
import com.lastartupsaas.workbench.view.datagrid.DataGridRow;
import com.lastartupsaas.workbench.view.datagrid.DataListRequest;
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * 投资分析列表页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = InvestmentAnalysisListView.VIEW_NAME)
public class InvestmentAnalysisListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 7335209933992943259L;
	public static final String VIEW_NAME = "investment_analysis_list.view";

	public InvestmentAnalysisListView() {
		this.setViewCaption("");
		this.withFilterSection = false;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			// 新增
			InvestmentAnalysisEditWindow formWindow = new InvestmentAnalysisEditWindow("");
			UI.getCurrent().addWindow(formWindow);

		} else if (command.isActionId("edit")) {
			// 编辑
			InvestmentAnalysisEditWindow formWindow = new InvestmentAnalysisEditWindow("");
			UI.getCurrent().addWindow(formWindow);

		} else if (command.isActionId("del")) {
			// 删除
			showConfirmDialog("提示", "确定要删除该投资分析吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "投资分析删除成功", Notification.Type.HUMANIZED_MESSAGE);
					} else {
					}
				}
			});

		}
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
		return new DataGridRow("00000001", new Object[] { "合伙经营", "300", "10,000.00", "5,000.00", "30,000.00", "50,000.00", "30,000.00", "14,000.00",
				"13,000.00", "132,000.00" });
	}

	@Override
	public int getDataCount() {
		return 1;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {
		List<String> list = new ArrayList<>();
		list.add("1");
		return list;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		gridModel.addColumn(new DataGridColumn("加盟类型", String.class));
		gridModel.addColumn(new DataGridColumn("预算店面面积(㎡)", String.class));
		gridModel.addColumn(new DataGridColumn("加盟费(万)", String.class));
		gridModel.addColumn(new DataGridColumn("保证金(万)", String.class));
		gridModel.addColumn(new DataGridColumn("设备费用(万)", String.class));
		gridModel.addColumn(new DataGridColumn("首批原物料(万)", String.class));
		gridModel.addColumn(new DataGridColumn("装修费(万)", String.class));
		gridModel.addColumn(new DataGridColumn("广告费(万)", String.class));
		gridModel.addColumn(new DataGridColumn("其他费用(万)", String.class));
		gridModel.addColumn(new DataGridColumn("合计(万)", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增投资分析"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
		gridModel.addItemAction(new ActionCommand("del", "删除"));
	}
}
