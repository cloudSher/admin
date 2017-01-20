package com.lastartupsaas.workbench.view.business.community.setup;

import java.util.ArrayList;
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
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

/**
 * 关键词屏蔽列表页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = KeywordListView.VIEW_NAME)
public class KeywordListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = -7763777633023836230L;
	public static final String VIEW_NAME = "keyword_list.view";

	private FormAgent searchAgent;
	private String searchName;
	private int totalCount;

	public KeywordListView() {
		this.setViewCaption("当前位置：社区运营 > 设置 > 关键词屏蔽");
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("batch_delete")) {
			if (parameters.length == 0) {
				Notification.show("提示", "至少需要选中一条数据", Notification.Type.ERROR_MESSAGE);
			} else {
				showConfirmDialog("提示", "确定要删除选中的关键词吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
					@Override
					public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
						if (event.isConfirm()) {
							Notification.show("提示", "关键词批量删除成功", Notification.Type.HUMANIZED_MESSAGE);
						} else {
						}
					}
				});
			}
		}
		if (command.isActionId("del")) {
			showConfirmDialog("提示", "确定要删除该关键词吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "关键词删除成功", Notification.Type.HUMANIZED_MESSAGE);
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
		searchAgent.setFieldColumnCount(2);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
		searchAgent.addField(new FormField("关键词", "enterpriseName", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的关键词"));

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
		this.searchName = (String) this.searchAgent.getFieldValue("enterpriseName");
		this.dataGrid.reloadDatas();
	}

	@Override
	public DataGridRow convertRowData(Object item) {
		return new DataGridRow("100000001", new Object[] { "100000001", "不良", "2016-10-11 12:41:25", "张三" });
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

		gridModel.addColumn(new DataGridColumn("关键词ID", String.class));
		gridModel.addColumn(new DataGridColumn("关键词", String.class));
		gridModel.addColumn(new DataGridColumn("添加时间", String.class));
		gridModel.addColumn(new DataGridColumn("添加者", String.class));

		gridModel.addListAction(new ActionCommand("batch_delete", "批量删除"));
		gridModel.addItemAction(new ActionCommand("del", "删除"));
	}
}
