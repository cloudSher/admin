package com.lastartupsaas.workbench.view.business.brand;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.domain.brand.BrandBusiness;
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

/**
 * 品牌商列表页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = BrandBusinessListView.VIEW_NAME)
public class BrandBusinessListView extends BaseWorkBenchListWithSearchView {

	public static final String VIEW_NAME = "brand_business_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public BrandBusinessListView() {
		this.setViewCaption("当前位置：品牌 > 品牌商 > 品牌商列表");
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("brand_business_edit.view");
		}
		if (command.isActionId("edit")) {
			this.navigateToView("brand_business_edit.view/id=" + parameters[0]);
		}
		if (command.isActionId("del")) {
			System.out.println("del");
		}
	}

	@Override
	protected void createFilterForm(HorizontalLayout layout) {
		searchAgent = new FormAgent();
		searchAgent.setDataHelper(new FormDataHelper());
		searchAgent.setSearchMode(true);
		searchAgent.setFieldColumnCount(2);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
		searchAgent.addField(new FormField("企业名称", "enterpriseName", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的企业名称"));

		FormBuildLayout form = searchAgent.buildForm();
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
		BrandBusiness brandBusiness = (BrandBusiness) item;
		return new DataGridRow(brandBusiness.getId(),
				new Object[] { brandBusiness.getId(), brandBusiness.getEnterpriseName(), brandBusiness.getBusinessLicenseNo(),
						brandBusiness.getEnterpriseAddress(), brandBusiness.getReviewState(), brandBusiness.getLastOperateTime(),
						brandBusiness.getSmsFlag() });
	}

	@Override
	public int getDataCount() {
		return 2;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {
		List<BrandBusiness> brandBusinessList = new ArrayList<BrandBusiness>();
		brandBusinessList
				.add(new BrandBusiness(1L, "久久鸭", "100201612090011", "北京市朝阳区望京东路1号摩托罗拉大厦8层", "张三", "18612345678", "通过", "是", "2016-12-09 16:26:22"));
		brandBusinessList
				.add(new BrandBusiness(2L, "庆丰包子", "202201612090012", "北京市朝阳区望京东路1号", "李四", "18612345678", "拒绝", "否", "2016-12-09 16:26:22"));
		return brandBusinessList;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		gridModel.addColumn(new DataGridColumn("品牌商编号", Long.class));
		gridModel.addColumn(new DataGridColumn("品牌商名称", String.class));
		gridModel.addColumn(new DataGridColumn("工商执照注册号", String.class));
		gridModel.addColumn(new DataGridColumn("品牌商地址", String.class));
		gridModel.addColumn(new DataGridColumn("审核状态", String.class));
		gridModel.addColumn(new DataGridColumn("最后操作时间", String.class));
		gridModel.addColumn(new DataGridColumn("备注", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增品牌商"));
		gridModel.addCommonAction(new ActionCommand("export", "导出数据"));
		gridModel.addItemAction(new ActionCommand("del", "品牌列表"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
		gridModel.addItemAction(new ActionCommand("contract", "查看合同"));
		gridModel.addItemAction(new ActionCommand("freeze", "冻结/解冻"));
		gridModel.addItemAction(new ActionCommand("sendSms", "发送开户短信"));
	}
}
