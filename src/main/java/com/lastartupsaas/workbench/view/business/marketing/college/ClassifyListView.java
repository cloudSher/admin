package com.lastartupsaas.workbench.view.business.marketing.college;

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
 * 分类管理列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = ClassifyListView.VIEW_NAME)
public class ClassifyListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 6246096481671879440L;
	public static final String VIEW_NAME = "classify_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public ClassifyListView() {
		this("当前位置：社区运营 > 创业学院 > 分类管理");
	}
	
	public ClassifyListView(String caption) {
		this.setViewCaption(caption);
		this.withFilterSection = false;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("classify_edit.view");
		}
		if (command.isActionId("edit")) {
			this.navigateToView("classify_edit.view/id=" + parameters[0]);
		}
		if (command.isActionId("del")) {
			showConfirmDialog("提示", "确定要删除该分类吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "分类删除成功", Notification.Type.HUMANIZED_MESSAGE);
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
		searchAgent.setFieldColumnCount(2);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
		searchAgent.addField(new FormField("", "name", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的登录名或姓名"));

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
		this.searchName = (String) this.searchAgent.getFieldValue("name");
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
				new Object[] { layout, "", "加盟知识" });
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
		gridModel.addColumn(new DataGridColumn("图片", String.class));
		gridModel.addColumn(new DataGridColumn("分类名称", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增分类"));
		gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
	}
}
