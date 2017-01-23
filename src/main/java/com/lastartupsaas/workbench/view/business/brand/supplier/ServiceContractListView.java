package com.lastartupsaas.workbench.view.business.brand.supplier;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.domain.KeyValueObject;
//import com.lastartupsaas.api.client.resource.TopicsResource;
//import com.lastartupsaas.api.client.resource.TopicsResource.XLaFormat;
//import com.lastartupsaas.api.client.resource.TopicsResource.XLaSignMethod;
//import com.lastartupsaas.api.model.Topic;
import com.lastartupsaas.workbench.domain.brand.ServiceContract;
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
 * 品牌 > 品牌商 > 服务合同列表页面
 * 
 * @author lifeilong
 * @date: 2016-12-13
 */
@SpringView(name = ServiceContractListView.VIEW_NAME)
public class ServiceContractListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = 3881039585232874853L;

	public static final String VIEW_NAME = "service_contract_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public ServiceContractListView() {
		this.setViewCaption("当前位置：品牌 > 品牌商 > 服务合同");
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("service_contract_edit.view");
		} else if (command.isActionId("edit")) {
			this.navigateToView("service_contract_edit.view/id=" + parameters[0]);
		} else if (command.isActionId("del")) {
			showConfirmDialog("提示", "确定要删除该服务合同吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
				@Override
				public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
					if (event.isConfirm()) {
						Notification.show("提示", "服务合同删除成功", Notification.Type.HUMANIZED_MESSAGE);
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
		searchAgent.addField(new FormField("线下服务合同编号", "contractNo", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的线下服务合同编号"));
		searchAgent.addField(new FormField("品牌商名称", "brandName", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的品牌商名称"));
		List<KeyValueObject> stateList = new ArrayList<>();
		stateList.add(new KeyValueObject("1", "未开始服务"));
		stateList.add(new KeyValueObject("2", "服务中"));
		stateList.add(new KeyValueObject("3", "过期"));
		searchAgent.addField(
				new FormField("合同状态", "contractState", new SelectFieldEditor(stateList, "key", "value"), true, null, true).setInputDescr("选择合同状态"));
		searchAgent.addField(new FormField("开始签约时间", "signBeginTime", InputFieldEditor.class, false, null, false));
		searchAgent.addField(new FormField("结束签约时间", "signEndTime", InputFieldEditor.class, false, null, false));
		searchAgent.addField(new FormField("合同录入人", "inputPerson", InputFieldEditor.class, false, null, false).setInputDescr("合同录入人"));
		searchAgent.addField(new FormField("联系方式", "phone", InputFieldEditor.class, false, null, false).setInputDescr("联系方式"));

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
		ServiceContract serviceContract = (ServiceContract) item;

		Button button = new Button(serviceContract.getBusinessLicenseNo(), new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				ServiceContractViewWindow formWindow = new ServiceContractViewWindow("");
				UI.getCurrent().addWindow(formWindow);
			}
		});
		button.addStyleName(ValoTheme.BUTTON_LINK);
		Button view_supplier = new Button(serviceContract.getSecondParty(), new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				SupplierViewWindow formWindow = new SupplierViewWindow("");
				UI.getCurrent().addWindow(formWindow);
			}
		});
		view_supplier.addStyleName(ValoTheme.BUTTON_LINK);
		Button view_brand = new Button(serviceContract.getFirstParty(), new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				BrandTabView firstView = new BrandTabView();
				firstView.initView();
				ModalWindow formWindow = new ModalWindow("品牌信息查看", firstView, "80%");
				UI.getCurrent().addWindow(formWindow);
			}
		});
		view_brand.addStyleName(ValoTheme.BUTTON_LINK);

		return new DataGridRow(serviceContract.getId(),
				new Object[] { button, serviceContract.getContractNo(), view_brand, view_supplier,
						serviceContract.getSignTime(), serviceContract.getSplitFlag(), serviceContract.getLegalPerson(),
						serviceContract.getAccountNo(), serviceContract.getLastOperateTime(), serviceContract.getOperator(), "审核通过", "备注信息" });
	}

	@Override
	public int getDataCount() {
		return 3;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {

		List<ServiceContract> serviceContractList = new ArrayList<ServiceContract>();
		serviceContractList.add(new ServiceContract(1L, "00000001", "周黑鸭", "拉手餐饮", "2016-12-09 16:26:22", "3年", "100201612090011", "100201612090011",
				"张三", "张三", "18612345678", "招商银行", "1年", "年", "未开始服务", "businessLicenceImg00001.jpg", "organizationCodeImg00001.jpg",
				"contractImg.jpg", "2016-12-09 16:22:22", "系统管理员"));
		serviceContractList.add(new ServiceContract(2L, "00000002", "庆丰包子", "拉手餐饮", "2016-12-09 16:26:22", "3年", "100201612090012", "100201612090012",
				"张三", "张三", "18600001111", "招商银行", "1年", "年", "服务中", "businessLicenceImg00001.jpg", "organizationCodeImg00001.jpg", "contractImg.jpg",
				"2016-12-09 16:22:22", "系统管理员"));
		serviceContractList.add(new ServiceContract(3L, "00000003", "肯德基", "拉手餐饮", "2016-12-09 16:26:22", "3年", "100201612090013", "100201612090013",
				"张三", "张三", "18612345678", "招商银行", "1年", "年", "过期", "businessLicenceImg00001.jpg", "organizationCodeImg00001.jpg", "contractImg.jpg",
				"2016-12-09 16:22:22", "系统管理员"));
		return serviceContractList;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		gridModel.addColumn(new DataGridColumn("系统服务合同编号", Button.class));
		gridModel.addColumn(new DataGridColumn("线下服务合同编号", String.class));
		gridModel.addColumn(new DataGridColumn("品牌名称", Button.class));
		gridModel.addColumn(new DataGridColumn("品牌商名称", Button.class));
		gridModel.addColumn(new DataGridColumn("合同签约时间", String.class));
		gridModel.addColumn(new DataGridColumn("合同状态", String.class));
		gridModel.addColumn(new DataGridColumn("联系人", String.class));
		gridModel.addColumn(new DataGridColumn("联系方式", String.class));
		gridModel.addColumn(new DataGridColumn("最后操作时间", String.class));
		gridModel.addColumn(new DataGridColumn("合同录入人", String.class));
		gridModel.addColumn(new DataGridColumn("审核状态", String.class));
		gridModel.addColumn(new DataGridColumn("备注", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增服务合同"));
		// gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
	}
}
