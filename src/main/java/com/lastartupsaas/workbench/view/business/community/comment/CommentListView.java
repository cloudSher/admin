package com.lastartupsaas.workbench.view.business.community.comment;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.BaseWorkBenchListWithSearchView;
import com.lastartupsaas.workbench.view.business.community.dynamic.DynamicViewWindow;
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
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

/**
 * 评论管理列表页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = CommentListView.VIEW_NAME)
public class CommentListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = -3834010700573200006L;
	public static final String VIEW_NAME = "comment_list.view";

	private FormAgent searchAgent;
	private String searchName;
	private int totalCount;

	public CommentListView() {
		this("当前位置：社区运营 > 评论 > 评论管理");
	}

	public CommentListView(String caption) {
		this.setViewCaption(caption);
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("batch_delete")) {
			if (parameters.length == 0) {
				Notification.show("提示", "至少需要选中一条数据", Notification.Type.ERROR_MESSAGE);
			} else {
				showConfirmDialog("提示", "确定要删除选中的评论吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
					@Override
					public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
						if (event.isConfirm()) {
							Notification.show("提示", "评论批量删除成功", Notification.Type.HUMANIZED_MESSAGE);
						} else {
						}
					}
				});
			}
		}
		if (command.isActionId("del")) {
			showConfirmDialog("提示", "确定要删除此条评论吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "评论删除成功", Notification.Type.HUMANIZED_MESSAGE);
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
		searchAgent.setFieldColumnCount(3);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
		searchAgent.addField(new FormField("评论时间", "start_time", DateFieldEditor.class, false, null, false));
		searchAgent.addField(new FormField("~", "end_time", DateFieldEditor.class, false, null, false));
		searchAgent.addField(new FormField("评论用户", "enterpriseName", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的评论用户"));

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
		Label content_label = new Label("我来评论一...");
		content_label.setDescription("我来评论一下，楼主说的真不错");

		return new DataGridRow("100000001", new Object[] { content_label, "张三", "2340000014", "12200001", "2016-10-11 12:41:25" });
	}

	@Override
	public int getDataCount() {
		return totalCount;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {
		List<String> list = new ArrayList<>();
		list.add("1");
		return list;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		gridModel.addColumn(new DataGridColumn("评论内容", Label.class));
		gridModel.addColumn(new DataGridColumn("评论用户", String.class));
		gridModel.addColumn(new DataGridColumn("评论用户ID", String.class));
		gridModel.addColumn(new DataGridColumn("动态ID", String.class));
		gridModel.addColumn(new DataGridColumn("评论时间", String.class));

		gridModel.addListAction(new ActionCommand("batch_delete", "批量删除"));
		gridModel.addItemAction(new ActionCommand("del", "删除"));
	}
}
