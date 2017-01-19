package com.lastartupsaas.workbench.view.business.admin;

import java.util.List;

import com.lastartupsaas.workbench.view.BaseWorkBenchListWithSearchView;
import com.lastartupsaas.workbench.view.datagrid.ActionCommand;
import com.lastartupsaas.workbench.view.datagrid.DataGridColumn;
import com.lastartupsaas.workbench.view.datagrid.DataGridModel;
import com.lastartupsaas.workbench.view.datagrid.DataGridRow;
import com.lastartupsaas.workbench.view.datagrid.DataListRequest;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

/**
 * KIP数据项列表页
 * 
 * @author lifeilong
 * @date 2016-12-29
 */
@SpringView(name = KpiTargetListView.VIEW_NAME)
public class KpiTargetListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 5153716034804657673L;
	public static final String VIEW_NAME = "kpi_config_list.view";

	public KpiTargetListView() {
		this("当前位置：系统管理 > KIP管理 > KIP数据项");
	}
	
	public KpiTargetListView(String caption) {
		this.setViewCaption(caption);
		this.withFilterSection = false;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("kpi_config_edit.view");
		}
		if (command.isActionId("edit")) {
			this.navigateToView("kpi_config_edit.view/id=" + parameters[0]);
		}
		if (command.isActionId("del")) {
			Notification.show("提示", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
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
		gridModel.addColumn(new DataGridColumn("模块", String.class));
		gridModel.addColumn(new DataGridColumn("资源包含KPI指标", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增配置"));
		gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
	}
}
