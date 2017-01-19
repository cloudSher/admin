package com.lastartupsaas.workbench.view.business.marketing.consult;

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
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
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
			Notification.show("提示", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
		}
		if (command.isActionId("batch_delete")) {
			Notification.show("提示", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
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
//		User user = (User) item;
//		return new DataGridRow(user.getId(), new Object[] { user.getLoginName(), user.getJobNumber(), user.getRealName(),
//				user.getPost() == null ? "" : user.getPost().getPostName(), user.getLastLoadTime() });
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

		gridModel.addColumn(new DataGridColumn("留言用户ID", String.class));
		gridModel.addColumn(new DataGridColumn("留言用户昵称", String.class));
		gridModel.addColumn(new DataGridColumn("留言时间", String.class));
		gridModel.addColumn(new DataGridColumn("留言内容", String.class));
		gridModel.addColumn(new DataGridColumn("回复内容", String.class));
		gridModel.addColumn(new DataGridColumn("回复时间", String.class));
		gridModel.addColumn(new DataGridColumn("品牌商ID", String.class));
		gridModel.addColumn(new DataGridColumn("品牌商名称", String.class));
		
		gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addListAction(new ActionCommand("batch_delete", "批量删除"));
	}
}
