package com.lastartupsaas.workbench.view.business.marketing.consult;

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
 * 留言管理列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = LeaveMessageListView.VIEW_NAME)
public class LeaveMessageListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = -5076203701751136786L;
	public static final String VIEW_NAME = "leave_message_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public LeaveMessageListView() {
		this("当前位置：社区运营 > 留言咨询 > 留言管理");
	}

	public LeaveMessageListView(String caption) {
		this.setViewCaption(caption);
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("del")) {
			showConfirmDialog("提示", "确定要删除该留言吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "留言删除成功", Notification.Type.HUMANIZED_MESSAGE);
					} else {
					}
				}
			});
		}
		if (command.isActionId("batch_delete")) {
			if (parameters.length == 0) {
				Notification.show("提示", "至少需要选中一条数据", Notification.Type.ERROR_MESSAGE);
			} else {
				showConfirmDialog("提示", "确定要删除选中的留言吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
					@Override
					public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
						if (event.isConfirm()) {
							Notification.show("提示", "留言批量删除成功", Notification.Type.HUMANIZED_MESSAGE);
						} else {
						}
					}
				});
			}
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
		Label content_label = new Label("留言内容...");
		content_label.setDescription("留言内容故事案例之加盟肯德基月入千万");
		Label reply_label = new Label("回复内容...");
		reply_label.setDescription("回复内容故事案例之加盟肯德基月入千万");
		return new DataGridRow("00000001",
				new Object[] { "00000001", "鸟叔", "2017-03-03 10:22:11", content_label, reply_label, "2017-03-03 10:22:11", "4234244", "黄焖鸡米饭" });
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

		gridModel.addColumn(new DataGridColumn("留言用户ID", String.class));
		gridModel.addColumn(new DataGridColumn("留言用户昵称", String.class));
		gridModel.addColumn(new DataGridColumn("留言时间", String.class));
		gridModel.addColumn(new DataGridColumn("留言内容", Label.class));
		gridModel.addColumn(new DataGridColumn("回复内容", Label.class));
		gridModel.addColumn(new DataGridColumn("回复时间", String.class));
		gridModel.addColumn(new DataGridColumn("品牌商ID", String.class));
		gridModel.addColumn(new DataGridColumn("品牌商名称", String.class));

		gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addListAction(new ActionCommand("batch_delete", "批量删除"));
	}
}
