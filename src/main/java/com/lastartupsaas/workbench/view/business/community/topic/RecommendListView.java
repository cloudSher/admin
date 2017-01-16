package com.lastartupsaas.workbench.view.business.community.topic;

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
 * 会员列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = RecommendListView.VIEW_NAME)
public class RecommendListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 8782207053101461308L;
	public static final String VIEW_NAME = "recommend_topic.view";

	private FormAgent searchAgent;

	private String searchName;
	private String recommendFlag;// 推荐标识：1首页、2热门

	public RecommendListView() {
	}
	
	public RecommendListView(String processFlag) {
		this.recommendFlag = processFlag;
		this.withFilterSection = false;
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

		gridModel.addColumn(new DataGridColumn("排序", String.class));
		gridModel.addColumn(new DataGridColumn("话题ID", String.class));
		gridModel.addColumn(new DataGridColumn("话题名称", String.class));
		gridModel.addColumn(new DataGridColumn("话题图片", String.class));
		gridModel.addColumn(new DataGridColumn("二级标签", String.class));
		gridModel.addColumn(new DataGridColumn("品牌话题", String.class));
		gridModel.addColumn(new DataGridColumn("创建用户", String.class));
		gridModel.addColumn(new DataGridColumn("创建时间", String.class));
		gridModel.addColumn(new DataGridColumn("关注人数", String.class));
		gridModel.addColumn(new DataGridColumn("动态数", String.class));
		gridModel.addColumn(new DataGridColumn("状态", String.class));
		gridModel.addColumn(new DataGridColumn("备注", String.class));

		gridModel.addItemAction(new ActionCommand("del", "删除"));
//		if ("1".equals(recommendFlag)) {
//			gridModel.addItemAction(new ActionCommand("cancel", "取消加盟"));
//		}
	}
}
