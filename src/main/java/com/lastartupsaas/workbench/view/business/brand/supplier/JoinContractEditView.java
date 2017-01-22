package com.lastartupsaas.workbench.view.business.brand.supplier;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

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
 * 品牌 > 品牌商 > 加盟合同编辑页面
 * 
 * @author lifeilong
 * @date: 2016-12-13
 */
@SpringView(name = JoinContractEditView.VIEW_NAME)
public class JoinContractEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = 4822510543195967145L;
	public static final String VIEW_NAME = "join_contract_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：品牌 > 品牌商 > 加盟合同";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("系统加盟合同编号", "businessLicenseNo", new LabelFieldEditor("系统自动生成", "100%"), false, null, true));
		base_message.add(new FormField("线下加盟合同编号", "contractNo", InputFieldEditor.class, false, null, true).setInputDescr("线下合同编号，手动填写"));
		base_message.add(new FormField("订单编号", "firstParty", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("品牌名称", "firstParty1", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("特许方", "secondParty", InputFieldEditor.class, true, null, true).setInputDescr("品牌商名称"));
		base_message.add(new FormField("加盟方", "secondParty1", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("签约时间", "signTime", DateFieldEditor.class, true, null, true));
		base_message.add(new FormField("合作有效期", "cooperateEffeDate", InputFieldEditor.class, true, null, true));
		formAgent.addFieldListToMap("基本信息", base_message);
		
		List<FormField> firstParty_message = new ArrayList<FormField>();
		firstParty_message.add(new FormField("加盟总费用(元)", "businessLicenseNo1", InputFieldEditor.class, true, null, true));
		firstParty_message.add(new FormField("加盟费(元)", "taxNo", InputFieldEditor.class, true, null, true));
		firstParty_message.add(new FormField("保证金(元)", "legalPerson", InputFieldEditor.class, true, null, true));
		formAgent.addFieldListToMap("加盟费用", firstParty_message);
		
		List<FormField> settlement_message = new ArrayList<FormField>();
		settlement_message.add(new FormField("法定代表人", "settlementPeriod", InputFieldEditor.class, true, null, true));
		settlement_message.add(new FormField("身份证号", "settlementDimension", InputFieldEditor.class, true, null, true));
		settlement_message.add(new FormField("法定地址", "accountName", InputFieldEditor.class, true, null, true));
		settlement_message.add(new FormField("联系方式", "accountNo", InputFieldEditor.class, true, null, true));
		formAgent.addFieldListToMap("加盟者基本信息", settlement_message);
		
		List<FormField> img_message = new ArrayList<FormField>();
		img_message.add(new FormField("合同照片", "businessLicenceImg", ImageUploadEditor.class, true, null, true));
		formAgent.addFieldListToMap("附件", img_message);
		
	}

	@Override
	protected Object createVirginObject() {
		return new ServiceContract();
	}

	@Override
	protected boolean saveObject(Object data) {
		return true;
	}

	@Override
	protected boolean updateObject(Object data) {
		return true;
	}

	@Override
	protected Object loadEdittingDataFromContext(ViewContext vc) {
		String id = vc.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
		}
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return "join_contract_list.view";
	}
}
