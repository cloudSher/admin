package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.BaseWorkBenchListWithSearchView;
import com.lastartupsaas.workbench.view.business.brand.supplier.ServiceContractEditView;
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
import com.lastartupsaas.workbench.widgets.ModalWindow;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * 品牌列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = BrandTypeListView.VIEW_NAME)
public class BrandTypeListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 3527647342244841781L;

	public static final String VIEW_NAME = "brand_type.view";

	private FormAgent searchAgent;

	private String searchName;
	private String pageFlag;// 页面标识：1所有品牌、2导入

	public BrandTypeListView() {
	}

	public BrandTypeListView(String pageFlag) {
		this.pageFlag = pageFlag;
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			// 新增
			BrandTypeEditWindow formWindow = new BrandTypeEditWindow("");
			UI.getCurrent().addWindow(formWindow);

		} else if (command.isActionId("export")) {
			// 导出数据
			Notification.show("提示", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);

		} else if (command.isActionId("edit")) {
			// 编辑
			BrandTypeEditWindow formWindow = new BrandTypeEditWindow("");
			UI.getCurrent().addWindow(formWindow);

		} else if (command.isActionId("add_lower_level")) {
			// 新增下级分类
			BrandTypeEditWindow formWindow = new BrandTypeEditWindow("");
			UI.getCurrent().addWindow(formWindow);

		} else if (command.isActionId("view_lower_level")) {
			// 查看下级分类
			BrandTypeListView firstView = new BrandTypeListView();
			firstView.initView();
			ModalWindow formWindow = new ModalWindow("", firstView);
			UI.getCurrent().addWindow(formWindow);

		} else if (command.isActionId("del")) {
			// 删除
			showConfirmDialog("提示", "确定要删除该品牌类型吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "品牌类型删除成功", Notification.Type.HUMANIZED_MESSAGE);
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

		searchAgent.addField(new FormField("类型名称", "brand_type_name", InputFieldEditor.class, false, null, false).setInputDescr("类型名称"));

		FormBuildLayout form = searchAgent.buildSearchForm();
		form.setWidth("30%");
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
		return new DataGridRow("00000001", new Object[] { "一级分类", "餐饮类", "1", });
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

		gridModel.addColumn(new DataGridColumn("分类级别", String.class));
		gridModel.addColumn(new DataGridColumn("类型名称", String.class));
		gridModel.addColumn(new DataGridColumn("排序", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增品牌"));
		gridModel.addCommonAction(new ActionCommand("export", "导出数据"));

		gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
		gridModel.addItemAction(new ActionCommand("add_lower_level", "新增下级分类"));
		gridModel.addItemAction(new ActionCommand("view_lower_level", "查看下级分类"));
	}
}
