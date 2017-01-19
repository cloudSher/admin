package com.lastartupsaas.workbench.view.business.member;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.domain.KeyValueObject;
import com.lastartupsaas.workbench.domain.admin.Post;
import com.lastartupsaas.workbench.domain.admin.User;
import com.lastartupsaas.workbench.view.BaseWorkBenchListWithSearchView;
import com.lastartupsaas.workbench.view.business.admin.PasswordEditWindow;
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
import com.vaadin.ui.UI;

/**
 * 管理员列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = WithdrawalsListView.VIEW_NAME)
public class WithdrawalsListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 1842229739731945391L;
	public static final String VIEW_NAME = "withdrawals_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public WithdrawalsListView() {
		this("当前位置：系统管理 > 权限管理 > 管理员");
	}

	public WithdrawalsListView(String caption) {
		this.setViewCaption(caption);
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("view")) {
			WithdrawalsViewWindow formWindow = new WithdrawalsViewWindow("");
			UI.getCurrent().addWindow(formWindow);
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
		list.add(new KeyValueObject("1", "会员ID"));
		list.add(new KeyValueObject("2", "手机号"));

		searchAgent.addField(
				new FormField("", "member_type", new SelectFieldEditor(list, "key", "value"), false, null, false).setInputDescr("请选择搜索条件类型"));
		searchAgent.addField(new FormField("", "param", InputFieldEditor.class, false, null, false).setInputDescr("请输入要搜索的内容"));

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
		this.searchName = (String) this.searchAgent.getFieldValue("name");
		this.dataGrid.reloadDatas();
	}

	@Override
	public DataGridRow convertRowData(Object item) {
		return new DataGridRow("10000001",
				new Object[] { "10000001", "12380091", "马云", "18688888888", "300", "2017-02-03 17:00", "招商银行", "6215***********6793", "马云", "已支付" });
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

		gridModel.addColumn(new DataGridColumn("提现ID", String.class));
		gridModel.addColumn(new DataGridColumn("会员ID", String.class));
		gridModel.addColumn(new DataGridColumn("会员昵称", String.class));
		gridModel.addColumn(new DataGridColumn("会员手机", String.class));
		gridModel.addColumn(new DataGridColumn("提现金额(元)", String.class));
		gridModel.addColumn(new DataGridColumn("申请时间", String.class));
		gridModel.addColumn(new DataGridColumn("收款银行", String.class));
		gridModel.addColumn(new DataGridColumn("收款账号", String.class));
		gridModel.addColumn(new DataGridColumn("开户名称", String.class));
		gridModel.addColumn(new DataGridColumn("支付状态", String.class));

		gridModel.addItemAction(new ActionCommand("view", "查看"));
	}
}
