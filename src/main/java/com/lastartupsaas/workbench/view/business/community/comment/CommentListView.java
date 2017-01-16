package com.lastartupsaas.workbench.view.business.community.comment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
import com.lastartupsaas.workbench.view.form.impl.DateFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

/**
 * 品牌商列表页面
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

	@Autowired
//	private TopicsResource topicsResource;
	private String xLaAuthorization = "oauth2.0";
//	private XLaFormat xLaFormat = XLaFormat.json;
	private String xLaAppKey = "123456";
//	private XLaSignMethod xLaSignMethod = XLaSignMethod.MD5;
	private String sign = "C71F538BC1243D2903D3AB935949379B";

	public CommentListView() {
		this.setViewCaption("当前位置：社区运营 > 评论 > 评论管理");
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("multi_del")) {
			Notification.show("提示", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
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
//		Topic topic = (Topic) item;
//		return new DataGridRow(topic.getId(),
//				new Object[] { topic.getId(), // 话题ID
//						topic.getTitle(), // 话题名称
//						topic.getTags(), // 二级标签
//						topic.getTags(), // 品牌话题
//						topic.getPublisher().getName(), // 创建用户
//						topic.getPublishTime(), // 创建时间
//						topic.getFans().getTotalCount(), // 关注人数
//						topic.getPosters().getTotalCount(), // 动态数
//						topic.getStatus(), // 状态
//						topic.getPublisher().getName(), // 审核人
//						topic.getPublishTime(), // 审核时间
//						topic.getDesc() });// 备注
		return null;

	}

	@Override
	public int getDataCount() {
		return totalCount;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {
		try {
//			Response response = topicsResource.getTopicsByFieldSelectors(":()", xLaAuthorization, xLaFormat, xLaAppKey, xLaSignMethod, "", "", 10, 1,
//					sign);
//			logger.info("查询话题列表结束,返回状态码[{}]", response.getStatus());
//			if (response.getStatus() == 200) {
//				Topics topics = (Topics) response.getEntity();
//				if (topics != null) {
//					totalCount = topics.getTotalCount().intValue();
//					return topics.getTopics();
//				}
//			}
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		gridModel.addColumn(new DataGridColumn("评论内容", Long.class));
		gridModel.addColumn(new DataGridColumn("评论用户", String.class));
		gridModel.addColumn(new DataGridColumn("评论用户ID", String.class));
		gridModel.addColumn(new DataGridColumn("动态ID", String.class));
		gridModel.addColumn(new DataGridColumn("评论时间", String.class));

		gridModel.addListAction(new ActionCommand("multi_del", "批量删除"));
		gridModel.addItemAction(new ActionCommand("del", "删除"));
	}
}
