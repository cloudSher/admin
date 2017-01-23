package com.lastartupsaas.workbench.view.business.brand.supplier;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.BaseWorkBenchListWithSearchView;
import com.lastartupsaas.workbench.view.business.brand.brand.BrandTabView;
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
import com.lastartupsaas.workbench.widgets.ModalWindow;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 品牌 > 品牌商 > 加盟合同列表页面
 * 
 * @author lifeilong
 * @date: 2016-12-13
 */
@SpringView(name = JoinContractListView.VIEW_NAME)
public class JoinContractListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 8764924054431034367L;
	public static final String VIEW_NAME = "join_contract_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public JoinContractListView() {
		this.setViewCaption("当前位置：品牌 > 品牌商 > 加盟合同");
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("join_contract_edit.view");
		} else if (command.isActionId("edit")) {
			this.navigateToView("join_contract_edit.view/id=" + parameters[0]);
		}
	}

	@Override
	protected void createFilterForm(HorizontalLayout layout) {
		searchAgent = new FormAgent();
		searchAgent.setDataHelper(new FormDataHelper());
		searchAgent.setSearchMode(true);
		searchAgent.setFieldColumnCount(4);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
		searchAgent.addField(new FormField("线下加盟合同编号", "contractNo", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的线下服务合同编号"));
		searchAgent.addField(new FormField("品牌商名称", "brandName", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的品牌商名称"));
		searchAgent.addField(new FormField("品牌名称", "phone1", InputFieldEditor.class, false, null, false).setInputDescr("品牌名称"));
		searchAgent.addField(new FormField("加盟者联系方式", "phone", InputFieldEditor.class, false, null, false).setInputDescr("联系方式"));
		searchAgent.addField(new FormField("开始签约时间", "signBeginTime", DateFieldEditor.class, false, null, false));
		searchAgent.addField(new FormField("结束签约时间", "signEndTime", DateFieldEditor.class, false, null, false));
		searchAgent.addField(new FormField("合同录入人", "inputPerson", InputFieldEditor.class, false, null, false).setInputDescr("合同录入人"));

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
		this.searchName = (String) this.searchAgent.getFieldValue("contractNo");
		this.dataGrid.reloadDatas();
	}

	@Override
	public DataGridRow convertRowData(Object item) {
		Button view_contract = new Button("00000010", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				JoinContractViewWindow formWindow = new JoinContractViewWindow("");
				UI.getCurrent().addWindow(formWindow);
			}
		});
		view_contract.addStyleName(ValoTheme.BUTTON_LINK);
		Button view_supplier = new Button("呷哺呷哺股份有限公司", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				SupplierViewWindow formWindow = new SupplierViewWindow("");
				UI.getCurrent().addWindow(formWindow);
			}
		});
		view_supplier.addStyleName(ValoTheme.BUTTON_LINK);
		Button view_brand = new Button("周黑鸭", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				BrandTabView firstView = new BrandTabView();
				firstView.initView();
				ModalWindow formWindow = new ModalWindow("品牌信息查看", firstView, "80%");
				UI.getCurrent().addWindow(formWindow);
			}
		});
		view_brand.addStyleName(ValoTheme.BUTTON_LINK);

		return new DataGridRow("00000010", new Object[] { view_contract, "00000001", view_supplier, "张三", "13212345678", view_brand, "100,0000.00", "300,0000.00",
				"2016-12-09 16:26:22", "5年", "2016-12-09 16:26:22", "李四", "审核通过", "备注信息" });
	}

	@Override
	public int getDataCount() {
		return 3;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {

		List<String> list = new ArrayList<>();
		list.add("1");
		return list;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		gridModel.addColumn(new DataGridColumn("系统加盟合同编号", Button.class));
		gridModel.addColumn(new DataGridColumn("线下加盟合同编号", String.class));
		gridModel.addColumn(new DataGridColumn("品牌商名称", Button.class));
		gridModel.addColumn(new DataGridColumn("加盟者", String.class));
		gridModel.addColumn(new DataGridColumn("加盟者联系方式", String.class));
		gridModel.addColumn(new DataGridColumn("品牌名称", Button.class));
		gridModel.addColumn(new DataGridColumn("加盟费(元)", String.class));
		gridModel.addColumn(new DataGridColumn("加盟总费用(元)", String.class));
		gridModel.addColumn(new DataGridColumn("合同签约时间", String.class));
		gridModel.addColumn(new DataGridColumn("合作有效期", String.class));
		gridModel.addColumn(new DataGridColumn("最后操作时间", String.class));
		gridModel.addColumn(new DataGridColumn("合同录入人", String.class));
		gridModel.addColumn(new DataGridColumn("审核状态", String.class));
		gridModel.addColumn(new DataGridColumn("备注", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增加盟合同"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
	}
}
