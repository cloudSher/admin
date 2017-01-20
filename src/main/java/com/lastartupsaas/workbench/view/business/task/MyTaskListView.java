package com.lastartupsaas.workbench.view.business.task;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.domain.KeyValueObject;
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
import com.lastartupsaas.workbench.view.form.impl.SelectFieldEditor;
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

/**
 * 我的任务列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = MyTaskListView.VIEW_NAME)
public class MyTaskListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 6834992791154072035L;
	public static final String VIEW_NAME = "my_task.view";

	private FormAgent searchAgent;

	private String searchName;
	private String taskType;// 任务标识：1待处理、2已处理

	public MyTaskListView() {
	}

	public MyTaskListView(String taskType) {
		this.taskType = taskType;
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("operation")) {
			Notification.show("提示", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
		}
		if (command.isActionId("del")) {
			showConfirmDialog("提示", "确定要删除该任务吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "任务删除成功", Notification.Type.HUMANIZED_MESSAGE);
					} else {
					}
				}
			});
		}
	}

	@Override
	protected void createFilterForm(HorizontalLayout layout) {
		searchAgent = new FormAgent();
		searchAgent.setDataHelper(new FormDataHelper());
		searchAgent.setSearchMode(true);
		searchAgent.setFieldColumnCount(4);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);

		List<KeyValueObject> typeList = new ArrayList<>();
		typeList.add(new KeyValueObject("1", "全部类型"));
		typeList.add(new KeyValueObject("2", "话题审核"));
		typeList.add(new KeyValueObject("3", "品牌审核"));
		searchAgent.addField(new FormField("任务类型", "type", new SelectFieldEditor(typeList, "key", "value", "1", "100%"), true, null, true)
				.setInputDescr("选择任务类型"));
		List<KeyValueObject> dealTypeList = new ArrayList<>();
		dealTypeList.add(new KeyValueObject("1", "全部未处理"));
		dealTypeList.add(new KeyValueObject("2", "超过8小时未处理"));
		dealTypeList.add(new KeyValueObject("3", "超过12小时未处理"));
		dealTypeList.add(new KeyValueObject("4", "超过24小时未处理"));
		searchAgent.addField(new FormField("任务类型", "dealType", new SelectFieldEditor(typeList, "key", "value", "1", "100%"), true, null, true));

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

		return new DataGridRow("00000003", new Object[] { "话题审核", "领取待完成", "待处理", "张三", "2016-10-11 12:41:25", "2016-10-11 12:41:25", "详情" });
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

		gridModel.addColumn(new DataGridColumn("任务类型", String.class));
		gridModel.addColumn(new DataGridColumn("任务状态", String.class));
		gridModel.addColumn(new DataGridColumn("处理状态", String.class));
		gridModel.addColumn(new DataGridColumn("处理人", String.class));
		gridModel.addColumn(new DataGridColumn("任务领取时间", String.class));
		gridModel.addColumn(new DataGridColumn("处理时间", String.class));
		gridModel.addColumn(new DataGridColumn("详情", String.class));

		gridModel.addItemAction(new ActionCommand("view", "查看"));
		if ("1".equals(taskType)) {
			gridModel.addItemAction(new ActionCommand("operation", "处理"));
			gridModel.addItemAction(new ActionCommand("del", "删除"));
		}
	}
}
