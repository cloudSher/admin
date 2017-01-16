package com.lastartupsaas.workbench.view.business.transaction.balance;

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
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.SelectFieldEditor;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

/**
 * 岗位列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = NotOutAccountListView.VIEW_NAME)
public class NotOutAccountListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = -8642461705265612627L;
	public static final String VIEW_NAME = "not_out_account_list.view";
	private FormAgent searchAgent;
	private String searchName;

	public NotOutAccountListView() {
		this.setViewCaption("当前位置：交易 > 结算管理 > 未出账交易单");
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("export")) {
			// this.navigateToView("post_edit.view");
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

		List<KeyValueObject> list = new ArrayList<>();
		list.add(new KeyValueObject("1", "品牌名称"));
		list.add(new KeyValueObject("2", "品牌编号"));
		list.add(new KeyValueObject("3", "交易单号"));
		list.add(new KeyValueObject("4", "订单编号"));
		searchAgent.addField(new FormField("", "search_model", new SelectFieldEditor(list, "key", "value", "1", "100%"), false, null, false));
		searchAgent.addField(new FormField("", "search_value", InputFieldEditor.class, false, null, false));

		FormBuildLayout form = searchAgent.buildSearchForm();
		form.setWidth("50%");
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
		// Post post = (Post) item;
		// return new DataGridRow(post.getId(), new Object[] { post.getId(), post.getPostName() });
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
		gridModel.addColumn(new DataGridColumn("品牌名称", Long.class));
		gridModel.addColumn(new DataGridColumn("品牌编号", String.class));
		gridModel.addColumn(new DataGridColumn("交易单号", String.class));
		gridModel.addColumn(new DataGridColumn("用户确认时间", String.class));
		gridModel.addColumn(new DataGridColumn("用户ID", String.class));
		gridModel.addColumn(new DataGridColumn("用户手机号", String.class));
		gridModel.addColumn(new DataGridColumn("交易单金额", String.class));
		gridModel.addColumn(new DataGridColumn("平台分成(元)", String.class));
		gridModel.addColumn(new DataGridColumn("划账金额(元)", String.class));
		gridModel.addColumn(new DataGridColumn("订单编号", String.class));

		gridModel.addCommonAction(new ActionCommand("export", "导出数据"));
	}
}
