package com.lastartupsaas.workbench.view.business.marketing.messagepush;

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
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

/**
 * push管理列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = MessagePushListView.VIEW_NAME)
public class MessagePushListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = -1874068477634669367L;

	public static final String VIEW_NAME = "message_push_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public MessagePushListView() {
		this("当前位置：社区运营 > 消息推送 > push管理");
	}

	public MessagePushListView(String caption) {
		this.setViewCaption(caption);
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("message_push_edit.view");
		}
		if (command.isActionId("edit")) {
			this.navigateToView("message_push_edit.view/id=" + parameters[0]);
		}
		if (command.isActionId("del")) {
			showConfirmDialog("提示", "确定要删除该推送消息吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "推送消息删除成功", Notification.Type.HUMANIZED_MESSAGE);
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
		searchAgent.addField(new FormField("内容", "search_value", InputFieldEditor.class, false, null, false).setInputDescr("通过内容关键字进行模糊搜索"));

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

		Label title_label = new Label("标题标题...");
		title_label.setDescription("标题标题故事案例之加盟肯德基月入千万");
		Label content_label = new Label("内容内容...");
		content_label.setDescription("内容内容故事案例之加盟肯德基月入千万");
		return new DataGridRow("00000001",
				new Object[] { "00000001", title_label, content_label, "系统通知", "IOS", "1.0", "2017-03-03 10:22:11", "已推送" });
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

		gridModel.addColumn(new DataGridColumn("ID", String.class));
		gridModel.addColumn(new DataGridColumn("标题", Label.class));
		gridModel.addColumn(new DataGridColumn("内容", Label.class));
		gridModel.addColumn(new DataGridColumn("类型", String.class));
		gridModel.addColumn(new DataGridColumn("平台", String.class));
		gridModel.addColumn(new DataGridColumn("版本", String.class));
		gridModel.addColumn(new DataGridColumn("推送时间", String.class));
		gridModel.addColumn(new DataGridColumn("状态", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增"));
		gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
	}
}
