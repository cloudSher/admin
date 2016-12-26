package com.lastartupsaas.workbench.view.business.brand;

import java.util.ArrayList;
import java.util.List;

//import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

//import com.lastartupsaas.api.client.resource.TopicsResource;
//import com.lastartupsaas.api.client.resource.TopicsResource.XLaFormat;
//import com.lastartupsaas.api.client.resource.TopicsResource.XLaSignMethod;
//import com.lastartupsaas.api.model.Topic;
import com.lastartupsaas.workbench.domain.brand.ServiceContract;
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
 * 品牌 > 品牌商 > 服务合同列表页面
 * 
 * @author lifeilong
 * @date: 2016-12-13
 */
@SpringView(name = ServiceContractListView.VIEW_NAME)
public class ServiceContractListView extends BaseWorkBenchListWithSearchView {

	public static final String VIEW_NAME = "service_contract_list.view";

	private FormAgent searchAgent;

	private String searchName;
	
//	@Autowired
//	private TopicsResource topicsResource;

	public ServiceContractListView() {
		this.setViewCaption("当前位置：品牌 > 品牌商 > 服务合同");
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
		if (command.isActionId("create")) {
			this.navigateToView("service_contract_edit.view");
		}
		if (command.isActionId("edit")) {
			this.navigateToView("service_contract_edit.view/id=" + parameters[0]);
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
		searchAgent.setFieldColumnCount(3);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
		searchAgent.addField(new FormField("合同编号", "contractNo", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的合同编号"));
		searchAgent.addField(new FormField("开始签约时间", "signBeginTime", InputFieldEditor.class, false, null, false));
		searchAgent.addField(new FormField("结束签约时间", "signEndTime", InputFieldEditor.class, false, null, false));

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
		this.searchName = (String) this.searchAgent.getFieldValue("contractNo");
		this.dataGrid.reloadDatas();
	}

	@Override
	public DataGridRow convertRowData(Object item) {
		ServiceContract serviceContract = (ServiceContract) item;
		return new DataGridRow(serviceContract.getId(), new Object[] { serviceContract.getContractNo(), serviceContract.getFirstParty(),
				serviceContract.getSignTime(), serviceContract.getSplitFlag(), serviceContract.getLastOperateTime(), serviceContract.getOperator() });
	}

	@Override
	public int getDataCount() {
		return 3;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {
		
		try {
//			GetTopicsByTopicIdResponse response = topicsResource.getTopicsByTopicId("1", "oauth2.0", XLaFormat.json, "123456", XLaSignMethod.MD5, "C71F538BC1243D2903D3AB935949379B");
//			Response response = topicsResource.getTopicsByTopicId("10", "oauth2.0", XLaFormat.json, "123456", XLaSignMethod.MD5, "C71F538BC1243D2903D3AB935949379B");
//			response.getEntity();
//			System.out.println(response.readEntity(Topic.class).getTitle());
//			
//			Response resp = topicsResource.getTopicsByFieldSelectors(":()", "oauth2.0", XLaFormat.json, "123456", XLaSignMethod.MD5, "","", 10, 1, "C71F538BC1243D2903D3AB935949379B");
//			System.out.println(resp.readEntity(Topic.class));
			
//			System.out.println(topicsResource.getTopicsByFieldSelectors("title", "xLaAuthorization", XLaFormat.json, "xLaAppKey", XLaSignMethod.MD5, "keywords", "tags", 10, 1, "sign"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<ServiceContract> serviceContractList = new ArrayList<ServiceContract>();
		serviceContractList.add(new ServiceContract(1L, "00000001", "周黑鸭", "刘翔", "2016-12-09 16:26:22", "3年", "100201612090011", "100201612090011",
				"张三", "张三", "62258888888888888", "招商银行", "1年", "年", "是", "businessLicenceImg00001.jpg", "organizationCodeImg00001.jpg",
				"contractImg.jpg", "2016-12-09 16:22:22", "系统管理员"));
		serviceContractList.add(new ServiceContract(2L, "00000002", "庆丰包子", "张三", "2016-12-09 16:26:22", "3年", "100201612090011", "100201612090011",
				"张三", "张三", "62258888888888888", "招商银行", "1年", "年", "是", "businessLicenceImg00001.jpg", "organizationCodeImg00001.jpg",
				"contractImg.jpg", "2016-12-09 16:22:22", "系统管理员"));
		serviceContractList.add(new ServiceContract(3L, "00000003", "肯德基", "李四", "2016-12-09 16:26:22", "3年", "100201612090011", "100201612090011",
				"张三", "张三", "62258888888888888", "招商银行", "1年", "年", "是", "businessLicenceImg00001.jpg", "organizationCodeImg00001.jpg",
				"contractImg.jpg", "2016-12-09 16:22:22", "系统管理员"));
		return serviceContractList;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		gridModel.addColumn(new DataGridColumn("服务合同编号", String.class));
		gridModel.addColumn(new DataGridColumn("企业名称", String.class));
		gridModel.addColumn(new DataGridColumn("合同签约时间", String.class));
		gridModel.addColumn(new DataGridColumn("合同状态", String.class));
		gridModel.addColumn(new DataGridColumn("最后操作时间", String.class));
		gridModel.addColumn(new DataGridColumn("合同录入人", String.class));

		gridModel.addCommonAction(new ActionCommand("create", "新增服务合同"));
		gridModel.addItemAction(new ActionCommand("del", "删除"));
		gridModel.addItemAction(new ActionCommand("edit", "编辑"));
	}
}
