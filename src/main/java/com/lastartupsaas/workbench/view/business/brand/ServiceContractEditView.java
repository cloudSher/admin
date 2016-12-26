package com.lastartupsaas.workbench.view.business.brand;

import org.apache.commons.lang.StringUtils;

import com.lastartupsaas.workbench.domain.brand.ServiceContract;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.DateFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.ImageUploadEditor;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌 > 品牌商 > 服务合同编辑页面
 * 
 * @author lifeilong
 * @date: 2016-12-13
 */
@SpringView(name = ServiceContractEditView.VIEW_NAME)
public class ServiceContractEditView extends BaseWorkBenchEditorView {

	public static final String VIEW_NAME = "service_contract_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：品牌 > 品牌商 > 服务合同";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		formAgent.addField(new FormField("服务合同编号", "contractNo", InputFieldEditor.class, false, null, true));
		formAgent.addField(new FormField("甲方", "firstParty", InputFieldEditor.class, true, null, true));
		formAgent.addField(new FormField("乙方", "secondParty", InputFieldEditor.class, true, null, true));
		formAgent.addField(new FormField("签约时间", "signTime", DateFieldEditor.class, true, null, true));
		formAgent.addField(new FormField("合作有效期", "cooperateEffeDate", InputFieldEditor.class, true, null, true));
		formAgent.addField(new FormField("工商执照注册号", "businessLicenseNo", InputFieldEditor.class, true, null, true));
		formAgent.addField(new FormField("税务登记证号", "taxNo", InputFieldEditor.class, true, null, true));
		formAgent.addField(new FormField("法定代表人", "legalPerson", InputFieldEditor.class, true, null, true));
		formAgent.addField(new FormField("户名", "accountName", InputFieldEditor.class, true, null, true));
		formAgent.addField(new FormField("银行账号", "accountNo", InputFieldEditor.class, true, null, true));
		formAgent.addField(new FormField("开户行", "bankName", InputFieldEditor.class, true, null, true));
		formAgent.addField(new FormField("结算周期", "settlementPeriod", InputFieldEditor.class, true, null, true));
		formAgent.addField(new FormField("结算维度", "settlementDimension", InputFieldEditor.class, true, null, true));
		formAgent.addField(new FormField("是否分账", "splitFlag", InputFieldEditor.class, true, null, true));
		formAgent.addField(new FormField("营业执照照片", "businessLicenceImg", ImageUploadEditor.class, true, null, true));
		formAgent.addField(new FormField("组织机构代码照片", "organizationCodeImg", ImageUploadEditor.class, true, null, true));
		formAgent.addField(new FormField("合同照片", "contractImg", new ImageUploadEditor(true), true, null, true));
	}

	@Override
	protected Object createVirginObject() {
		return new ServiceContract();
	}

	@Override
	protected boolean saveObject(Object data) {
		System.out.println("==========================ServiceContractEditView saveObject===================================");
		System.out.println(data);
		return true;
	}

	@Override
	protected boolean updateObject(Object data) {
		System.out.println("==========================ServiceContractEditView updateObject===================================");
		System.out.println(data);
		return true;
	}

	@Override
	protected Object loadEdittingDataFromContext(ViewContext vc) {
		String id = vc.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			ServiceContract serviceContract = new ServiceContract(1L, "00000001", "周黑鸭", "刘翔", "2016-12-09 16:26:22", "3年", "100201612090011", "100201612090011",
					"张三", "张三", "62258888888888888", "招商银行", "1年", "年", "是", "businessLicenceImg00001.jpg", "organizationCodeImg00001.jpg",
					"contractImg.jpg", "2016-12-09 16:22:22", "系统管理员");
			return serviceContract;
		}
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return "service_contract_list.view";
	}
}
