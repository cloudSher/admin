package com.lastartupsaas.workbench.view.business.member;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.api.model.Member;
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
 * 会员列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = MemberListView.VIEW_NAME)
public class MemberListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 3179536072154522556L;

	public static final String VIEW_NAME = "member_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public MemberListView() {
		this.setViewCaption("当前位置：会员 > 会员管理 > 会员列表");
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("viewPort")) {
			// this.navigateToView("Member_edit.view/id=" + parameters[0]);
			Notification.show("账号设置", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
		}
		if (command.isActionId("disable")) {
			Notification.show("账号设置", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
		}
	}

	@Override
	protected void createFilterForm(HorizontalLayout layout) {
		searchAgent = new FormAgent();
		searchAgent.setDataHelper(new FormDataHelper());
		searchAgent.setSearchMode(true);
		searchAgent.setFieldColumnCount(2);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
		searchAgent.addField(new FormField("", "param", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的ID或手机号"));

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
		Member member = (Member) item;
		return new DataGridRow(member.getId(), new Object[] { member.getId(), member.getNickName(), "男", "18612345678", "2016-10-11 12:41:25", "是",
				"正常", "2016-10-11 12:41:25", "12", "32", "3", "32", "3", "50000", "10000", "40000" });
	}

	@Override
	public int getDataCount() {
		return 1;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {

		List<Member> members = new ArrayList<Member>();
		Member member = new Member();
		member.setId("2017011000001");
		member.setNickName("张三");
		member.setType("1");
		member.setEmail("test001@lashou-inc.com");
		member.setHeadImg("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
		member.setStatus("1");
		member.setAdditionalProperty("测试", "111111111");
		members.add(member);
		return members;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		gridModel.addColumn(new DataGridColumn("会员ID", String.class));
		gridModel.addColumn(new DataGridColumn("昵称", String.class));
		gridModel.addColumn(new DataGridColumn("会员性别", String.class));
		gridModel.addColumn(new DataGridColumn("会员手机", String.class));
		gridModel.addColumn(new DataGridColumn("注册时间", String.class));
		gridModel.addColumn(new DataGridColumn("潜能报告", String.class));
		gridModel.addColumn(new DataGridColumn("状态", String.class));
		gridModel.addColumn(new DataGridColumn("最后登录时间", String.class));
		gridModel.addColumn(new DataGridColumn("发布动态", String.class));
		gridModel.addColumn(new DataGridColumn("关注人数", String.class));
		gridModel.addColumn(new DataGridColumn("关注品牌数", String.class));
		gridModel.addColumn(new DataGridColumn("粉丝数", String.class));
		gridModel.addColumn(new DataGridColumn("申请数", String.class));
		gridModel.addColumn(new DataGridColumn("启动金账户(元)", String.class));
		gridModel.addColumn(new DataGridColumn("可提现金额(元)", String.class));
		gridModel.addColumn(new DataGridColumn("冻结金额(元)", String.class));

		gridModel.addItemAction(new ActionCommand("disable", "禁/启用"));
		gridModel.addItemAction(new ActionCommand("viewPort", "查看潜能报告"));
	}
}
