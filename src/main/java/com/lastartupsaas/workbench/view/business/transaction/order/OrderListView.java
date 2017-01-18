package com.lastartupsaas.workbench.view.business.transaction.order;

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
			// this.navigateToView("Member_edit.view/id=" + parameters[0]);
			Notification.show("提示", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
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
		
		searchAgent.addField(new FormField("", "param", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的订单编号或手机号"));

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
//		Member member = (Member) item;
//		return new DataGridRow(member.getId(), new Object[] { member.getId(),"2016-10-11 12:41:25","1000000001", member.getNickName(), "18612345678",  "30000",
//				"10001", "黄焖鸡米饭", "待支付启动金"});
		return null;
	}

	@Override
	public int getDataCount() {
		return 1;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {

//		List<Member> members = new ArrayList<Member>();
//		Member member = new Member();
//		member.setId("2017011000001");
//		member.setNickName("张三");
//		member.setType("1");
//		member.setEmail("test001@lashou-inc.com");
//		member.setHeadImg("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
//		member.setStatus("1");
//		member.setAdditionalProperty("测试", "111111111");
//		members.add(member);
//		return members;
		return null;
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
