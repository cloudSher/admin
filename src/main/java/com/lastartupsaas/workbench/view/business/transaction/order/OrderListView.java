package com.lastartupsaas.workbench.view.business.transaction.order;

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
import com.vaadin.ui.UI;

/**
 * 交易管理列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = OrderListView.VIEW_NAME)
public class OrderListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 3830671701433487097L;
	public static final String VIEW_NAME = "order.view";

	private FormAgent searchAgent;

	private String searchName;
	private String processFlag;// 流程标识：1加盟流程、2服务流程、3服务完成

	public OrderListView() {
	}
	
	public OrderListView(String processFlag) {
		this.processFlag = processFlag;
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("view")) {
			OrderViewWindow formWindow = new OrderViewWindow("");
			UI.getCurrent().addWindow(formWindow);
		}
		if (command.isActionId("cancel")) {
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
		
		List<KeyValueObject> list = new ArrayList<>();
		list.add(new KeyValueObject("1", "订单编号"));
		list.add(new KeyValueObject("2", "用户手机号"));

		searchAgent.addField(
				new FormField("", "member_type", new SelectFieldEditor(list, "key", "value"), false, null, false).setInputDescr("请选择搜索条件类型"));
		searchAgent.addField(new FormField("", "param", InputFieldEditor.class, false, null, false).setInputDescr("请输入要搜索的内容"));

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
		if ("1".equals(processFlag)) {
			return new DataGridRow("00000001", new Object[] { "00000001","2016-10-11 12:41:25","1000000001", "雷军", "18612345678",  "30000.00",
					"10001", "黄焖鸡米饭", "待支付启动金"});
		} else if ("2".equals(processFlag)) {
			return new DataGridRow("00000002", new Object[] { "00000002","2017-02-01 12:41:25","1000000002", "马化腾", "18612345678",  "30000.00",
					"10001", "肯德基", "后期款项未确认"});
		} else {
			return new DataGridRow("00000003", new Object[] { "00000003","2016-10-11 12:41:25","1000000001", "周宏文", "18612345678",  "30000.00",
					"10001", "德克士", "加盟成功"});
		}
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

		gridModel.addColumn(new DataGridColumn("订单编号", String.class));
		gridModel.addColumn(new DataGridColumn("申请时间", String.class));
		gridModel.addColumn(new DataGridColumn("用户id", String.class));
		gridModel.addColumn(new DataGridColumn("申请用户", String.class));
		gridModel.addColumn(new DataGridColumn("用户手机号", String.class));
		gridModel.addColumn(new DataGridColumn("启动金额(元)", String.class));
		gridModel.addColumn(new DataGridColumn("品牌编号", String.class));
		gridModel.addColumn(new DataGridColumn("品牌商名称", String.class));
		gridModel.addColumn(new DataGridColumn("订单状态", String.class));

		gridModel.addItemAction(new ActionCommand("view", "查看"));
		if ("1".equals(processFlag)) {
			gridModel.addItemAction(new ActionCommand("cancel", "取消加盟"));
		}
	}
}
