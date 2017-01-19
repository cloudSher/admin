package com.lastartupsaas.workbench.view.business.task;

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
 * 绩效统计列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = PerformanceListView.VIEW_NAME)
public class PerformanceListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = -6491258389312587412L;
	public static final String VIEW_NAME = "performance_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public PerformanceListView() {
		this("当前位置：待办任务 > 待办任务 > 绩效统计");
	}

	public PerformanceListView(String caption) {
		this.setViewCaption(caption);
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("export")) {
			// this.navigateToView("user_add.view");
			Notification.show("提示", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
		}
	}

	@Override
	protected void createFilterForm(HorizontalLayout layout) {
		searchAgent = new FormAgent();
		searchAgent.setDataHelper(new FormDataHelper());
		searchAgent.setSearchMode(true);
		searchAgent.setFieldColumnCount(4);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
		searchAgent.addField(new FormField("统计时间", "start_time", DateFieldEditor.class, false, null, false).setInputDescr("开始统计时间"));
		searchAgent.addField(new FormField("~", "end_time", DateFieldEditor.class, false, null, false).setInputDescr("结束统计时间"));
		searchAgent.addField(new FormField("角色名称", "role_name", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的角色名称"));
		searchAgent.addField(new FormField("员工姓名", "name", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的员工姓名"));

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

		gridModel.addColumn(new DataGridColumn("员工ID", String.class));
		gridModel.addColumn(new DataGridColumn("员工姓名", String.class));
		gridModel.addColumn(new DataGridColumn("角色名称", String.class));

		gridModel.addColumn(new DataGridColumn("社区发文章数", String.class));
		gridModel.addColumn(new DataGridColumn("发话题数", String.class));
		gridModel.addColumn(new DataGridColumn("话题文章点赞数", String.class));
		gridModel.addColumn(new DataGridColumn("话题文章评论数", String.class));
		gridModel.addColumn(new DataGridColumn("话题文章被举报数", String.class));

		gridModel.addColumn(new DataGridColumn("创业学院发文章数", String.class));

		gridModel.addColumn(new DataGridColumn("话题审核数", String.class));

		gridModel.addColumn(new DataGridColumn("举报处理数", String.class));
		gridModel.addColumn(new DataGridColumn("重复投诉数", String.class));

		gridModel.addColumn(new DataGridColumn("潜能报告审核数", String.class));

		gridModel.addColumn(new DataGridColumn("投诉处理数", String.class));
		gridModel.addColumn(new DataGridColumn("日遗留总数", String.class));

		gridModel.addColumn(new DataGridColumn("合同录入数", String.class));
		gridModel.addColumn(new DataGridColumn("合同一次通过率", String.class));
		gridModel.addColumn(new DataGridColumn("合同二次通过率", String.class));

		gridModel.addColumn(new DataGridColumn("品牌录入数", String.class));
		gridModel.addColumn(new DataGridColumn("品牌一次通过率", String.class));
		gridModel.addColumn(new DataGridColumn("品牌二次通过率", String.class));

		gridModel.addColumn(new DataGridColumn("合同录入审核数", String.class));
		gridModel.addColumn(new DataGridColumn("品牌录入审核数", String.class));

		gridModel.addColumn(new DataGridColumn("拉新用户数", String.class));
		gridModel.addColumn(new DataGridColumn("拉新品牌数", String.class));
		gridModel.addColumn(new DataGridColumn("成交量", String.class));
		gridModel.addColumn(new DataGridColumn("用户发文数", String.class));

		gridModel.addCommonAction(new ActionCommand("export", "导出数据"));
	}
}
