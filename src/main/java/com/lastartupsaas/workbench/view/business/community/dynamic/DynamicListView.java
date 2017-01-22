package com.lastartupsaas.workbench.view.business.community.dynamic;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.BaseWorkBenchListWithSearchView;
import com.lastartupsaas.workbench.view.business.member.WithdrawalsViewWindow;
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
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * 动态管理列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = DynamicListView.VIEW_NAME)
public class DynamicListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 8782207053101461308L;
	public static final String VIEW_NAME = "dynamic.view";

	private FormAgent searchAgent;

	private String searchName;
	private String dynamicFlag;// 推荐标识：1首页、2热门

	public DynamicListView() {
	}

	public DynamicListView(String processFlag) {
		this.dynamicFlag = processFlag;
		if ("1".equals(dynamicFlag)) {
			this.withFilterSection = true;
		}
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("dynamic_edit.view");
		}
		if (command.isActionId("view")) {
			DynamicViewWindow formWindow = new DynamicViewWindow("");
			UI.getCurrent().addWindow(formWindow);
		}
		if (command.isActionId("del")) {
			showConfirmDialog("提示", "确定要删除此动态吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "动态删除成功", Notification.Type.HUMANIZED_MESSAGE);
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
		searchAgent.setFieldColumnCount(4);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);

		searchAgent.addField(new FormField("发布者", "param", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的发布者"));

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

		Label title_label = new Label("成功故事案例...");
		title_label.setDescription("成功故事案例之加盟肯德基月入千万");

		if ("2".equals(dynamicFlag)) {
			return new DataGridRow("100000001",
					new Object[] { "100000001", title_label, "成功故事", "张三", "2016-10-11 12:41:25", "23", "233", "4234", "未处理", "备注信息" });
		} else {
			return new DataGridRow("100000001", new Object[] { "100000001", title_label, "成功故事", "张三", "2016-10-11 12:41:25", "23", "233", "4234" });
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

		gridModel.addColumn(new DataGridColumn("动态ID", String.class));
		gridModel.addColumn(new DataGridColumn("动态标题", Label.class));
		gridModel.addColumn(new DataGridColumn("所属话题", String.class));
		gridModel.addColumn(new DataGridColumn("发布者", String.class));
		gridModel.addColumn(new DataGridColumn("发布时间", String.class));
		gridModel.addColumn(new DataGridColumn("点赞数", String.class));
		gridModel.addColumn(new DataGridColumn("分享数", String.class));
		gridModel.addColumn(new DataGridColumn("评论数", String.class));

		if ("2".equals(dynamicFlag)) {
			gridModel.addColumn(new DataGridColumn("状态", String.class));
			gridModel.addColumn(new DataGridColumn("备注", String.class));
		} else if ("1".equals(dynamicFlag)) {
			gridModel.addCommonAction(new ActionCommand("create", "创建动态"));
			gridModel.addItemAction(new ActionCommand("del", "删除"));
			gridModel.addItemAction(new ActionCommand("view", "查看"));
		}
	}
}
