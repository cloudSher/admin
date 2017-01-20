package com.lastartupsaas.workbench.view.business.community.topic;

import java.util.ArrayList;
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
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 话题推荐列表页
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
		if (command.isActionId("del")) {
			showConfirmDialog("提示", "确定要删除该话题吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "话题删除成功", Notification.Type.HUMANIZED_MESSAGE);
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

		HorizontalLayout layout = new HorizontalLayout();
		TextField field = new TextField();
		field.setValue("1");
		field.addStyleName(ValoTheme.TEXTFIELD_SMALL);
		field.addStyleName(ValoTheme.TEXTFIELD_ALIGN_CENTER);
		field.setWidth("50px");
		Button button = new Button("编辑", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				showConfirmDialog("提示", "确定要修改顺序 ?",  new ConfirmYesNoDialog.ConfirmListener() {
                    @Override
                    public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
                        if(event.isConfirm()){
                        	Notification.show("恭喜你", "顺序设置成功", Notification.Type.HUMANIZED_MESSAGE);
                        }
                    }
                });
			}
		});
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		button.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		layout.addComponent(field, 0);
		layout.addComponent(button, 1);
		return new DataGridRow("00000003",
				new Object[] { layout, "00000003", "成功故事", "", "否", "是", "1000000001", "2016-10-11 12:41:25", "1678", "768", "未审核", "成功故事话题" });
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

		gridModel.addColumn(new DataGridColumn("排序", HorizontalLayout.class));
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
		// if ("1".equals(recommendFlag)) {
		// gridModel.addItemAction(new ActionCommand("cancel", "取消加盟"));
		// }
	}
}
