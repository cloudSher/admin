package com.lastartupsaas.workbench.view.business.marketing.messagepush;

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
 * 消息用户管理列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = MessageUserListView.VIEW_NAME)
public class MessageUserListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 5327409628409266664L;
	public static final String VIEW_NAME = "message_user_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public MessageUserListView() {
		this("当前位置：社区运营 > 消息推送 > 消息用户管理");
	}
	
	public MessageUserListView(String caption) {
		this.setViewCaption(caption);
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("message_user_edit.view");
		}
		if (command.isActionId("edit")) {
			this.navigateToView("message_user_edit.view/id=" + parameters[0]);
		}
		if (command.isActionId("del")) {
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
		searchAgent.addField(new FormField("用户分类查询", "search_value", InputFieldEditor.class, false, null, false));

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
		this.searchName = (String) this.searchAgent.getFieldValue("search_value");
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

		gridModel.addColumn(new DataGridColumn("用户分类ID", String.class));
		gridModel.addColumn(new DataGridColumn("用户分类名称", String.class));
		gridModel.addColumn(new DataGridColumn("推送用户文件地址", String.class));
		gridModel.addColumn(new DataGridColumn("数据量", String.class));
		gridModel.addColumn(new DataGridColumn("上传时间", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增"));
		gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
	}
}
