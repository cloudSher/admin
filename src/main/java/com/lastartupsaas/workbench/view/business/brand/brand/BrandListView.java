package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.domain.KeyValueObject;
import com.lastartupsaas.workbench.view.BaseWorkBenchListWithSearchView;
import com.lastartupsaas.workbench.view.business.brand.supplier.JoinContractViewWindow;
import com.lastartupsaas.workbench.view.business.brand.supplier.ServiceContractEditView;
import com.lastartupsaas.workbench.view.business.brand.supplier.ServiceContractViewWindow;
import com.lastartupsaas.workbench.view.business.brand.supplier.SupplierViewWindow;
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
import com.lastartupsaas.workbench.view.form.impl.SelectFieldEditor;
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog;
import com.lastartupsaas.workbench.widgets.ModalWindow;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 品牌列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = BrandListView.VIEW_NAME)
public class BrandListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 8782207053101461308L;
	public static final String VIEW_NAME = "brand.view";

	private FormAgent searchAgent;

	private String searchName;
	private String pageFlag;// 页面标识：1所有品牌、2导入

	public BrandListView() {
	}

	public BrandListView(String pageFlag) {
		this.pageFlag = pageFlag;
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			// 新增品牌
			this.navigateToView("brand_edit.view");

		} else if (command.isActionId("export")) {
			// 导出数据
			Notification.show("提示", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);

		} else if (command.isActionId("batch_review")) {
			// 批量提交审核
			if (parameters.length == 0) {
				Notification.show("提示", "请至少选择一条数据", Notification.Type.ERROR_MESSAGE);
			} else {
				showConfirmDialog("提示", "确定要批量审核选中的品牌吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
					@Override
					public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
						if (event.isConfirm()) {
							Notification.show("提示", "品牌批量审核成功", Notification.Type.HUMANIZED_MESSAGE);
						} else {
						}
					}
				});
			}

		} else if (command.isActionId("edit")) {
			// 编辑
			this.navigateToView("brand_edit.view/id=" + parameters[0]);

		} else if (command.isActionId("shelve")) {
			// 上架/下架
			showConfirmDialog("提示", "确定要上架该品牌吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "品牌上架成功", Notification.Type.HUMANIZED_MESSAGE);
					} else {
					}
				}
			});

		} else if (command.isActionId("add_service_contract")) {
			// 新增服务合同
			ServiceContractEditView firstView = new ServiceContractEditView();
			firstView.initView();
			ModalWindow formWindow = new ModalWindow("", firstView, "90%");
			UI.getCurrent().addWindow(formWindow);

		} else if (command.isActionId("freeze")) {
			// 冻结/解冻
			showConfirmDialog("提示", "确定要冻结该品牌吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "品牌冻结成功", Notification.Type.HUMANIZED_MESSAGE);
					} else {
					}
				}
			});

		} else if (command.isActionId("send_sms")) {
			// 发送短信

		} else if (command.isActionId("reset_password")) {
			// 重置密码

		} else if (command.isActionId("del")) {
			// 删除
			showConfirmDialog("提示", "确定要删除该品牌吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "品牌删除成功", Notification.Type.HUMANIZED_MESSAGE);
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
		searchAgent.setFieldColumnCount(5);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);

		searchAgent.addField(new FormField("品牌名称", "brand_name", InputFieldEditor.class, false, null, false).setInputDescr("品牌名称"));

		List<KeyValueObject> accountState = new ArrayList<>();
		accountState.add(new KeyValueObject("1", "正常"));
		accountState.add(new KeyValueObject("2", "冻结"));
		searchAgent.addField(new FormField("品牌账户状态", "accountState", new SelectFieldEditor(accountState, "key", "value"), true, null, true)
				.setInputDescr("选择品牌账户状态"));

		searchAgent.addField(new FormField("所属分类", "classify", InputFieldEditor.class, false, null, false).setInputDescr("所属分类"));
		searchAgent.addField(new FormField("品牌商名称", "brand_supplier_name", InputFieldEditor.class, false, null, false).setInputDescr("品牌商名称"));
		searchAgent.addField(new FormField("品牌商编号", "brand_supplier_no", InputFieldEditor.class, false, null, false).setInputDescr("品牌商编号"));
		searchAgent.addField(new FormField("系统服务合同编号", "service_contract_no", InputFieldEditor.class, false, null, false).setInputDescr("系统服务合同编号"));

		List<KeyValueObject> brand_state = new ArrayList<>();
		brand_state.add(new KeyValueObject("1", "已上线"));
		brand_state.add(new KeyValueObject("2", "强制下线"));
		brand_state.add(new KeyValueObject("3", "待上线"));
		searchAgent.addField(
				new FormField("品牌状态", "brand_state", new SelectFieldEditor(brand_state, "key", "value"), true, null, true).setInputDescr("选择品牌状态"));
		searchAgent.addField(new FormField("上线时间", "signBeginTime", DateFieldEditor.class, false, null, false).setInputDescr("开始上线时间"));
		searchAgent.addField(new FormField("~", "signEndTime", DateFieldEditor.class, false, null, false).setInputDescr("结束上线时间"));

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

		Button view_account = new Button("432.00", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				JoinContractViewWindow formWindow = new JoinContractViewWindow("");
				UI.getCurrent().addWindow(formWindow);
			}
		});
		view_account.addStyleName(ValoTheme.BUTTON_LINK);
		Button view_supplier = new Button("呷哺呷哺股份有限公司", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				SupplierViewWindow formWindow = new SupplierViewWindow("");
				UI.getCurrent().addWindow(formWindow);
			}
		});
		view_supplier.addStyleName(ValoTheme.BUTTON_LINK);
		Button view_contract = new Button("00000001", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				ServiceContractViewWindow formWindow = new ServiceContractViewWindow("");
				UI.getCurrent().addWindow(formWindow);
			}
		});
		view_contract.addStyleName(ValoTheme.BUTTON_LINK);
		return new DataGridRow("00000001", new Object[] { "00000001", "呷哺呷哺", "已上线", "正常", view_account, "餐饮", view_supplier, view_contract,
				"100000322555555", "是", "北京分部", "2020-10-11 12:41:25", "2016-10-11 12:41:25", "审核通过", "备注信息" });
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

		gridModel.addColumn(new DataGridColumn("品牌编号", String.class));
		gridModel.addColumn(new DataGridColumn("品牌名称", String.class));
		gridModel.addColumn(new DataGridColumn("品牌状态", String.class));
		gridModel.addColumn(new DataGridColumn("品牌账户状态", String.class));
		gridModel.addColumn(new DataGridColumn("账户余额", Button.class));
		gridModel.addColumn(new DataGridColumn("所属分类", String.class));
		gridModel.addColumn(new DataGridColumn("品牌商名称", Button.class));
		gridModel.addColumn(new DataGridColumn("系统服务合同编号", Button.class));
		gridModel.addColumn(new DataGridColumn("品牌账号", String.class));
		gridModel.addColumn(new DataGridColumn("已发开户短信", String.class));
		gridModel.addColumn(new DataGridColumn("所属销售", String.class));
		gridModel.addColumn(new DataGridColumn("合同到期时间", String.class));
		gridModel.addColumn(new DataGridColumn("上线时间", String.class));
		gridModel.addColumn(new DataGridColumn("审核状态", String.class));
		gridModel.addColumn(new DataGridColumn("备注", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增品牌"));
		gridModel.addCommonAction(new ActionCommand("export", "导出数据"));
		gridModel.addListAction(new ActionCommand("batch_review", "批量提交审核"));

		gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
		gridModel.addItemAction(new ActionCommand("shelve", "上架/下架"));
		gridModel.addItemAction(new ActionCommand("add_service_contract", "新增服务合同"));
		gridModel.addItemAction(new ActionCommand("freeze", "冻结/解冻"));
		gridModel.addItemAction(new ActionCommand("send_sms", "发送短信"));
		gridModel.addItemAction(new ActionCommand("reset_password", "重置密码"));
	}
}
