package com.lastartupsaas.workbench.view.business.brand.supplier;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.ImageUploadEditor;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.lastartupsaas.workbench.widgets.FormWindow;

/**
 * 品牌 > 品牌商 > 加盟合同查看页面
 * 
 * @author lifeilong
 * @date: 2016-12-13
 */
public class JoinContractViewWindow extends FormWindow {

	private static final long serialVersionUID = 7513651376497414749L;

	public JoinContractViewWindow(String caption) {
		super(caption, "1", "70%");
	}

	@Override
	protected boolean doDoneActione(FormAgent formAgent) {
		return true;
	}

	@Override
	protected void setupFormAgent(FormAgent formAgent) {
		formAgent.setFieldColumnCount(2);

		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("系统加盟合同编号:", "oldrd", new LabelFieldEditor("10000001", "100%"), false, null, true));
		base_message.add(new FormField("线下加盟合同编号:", "nerd", new LabelFieldEditor("132232000035431", "100%"), false, null, true));
		base_message.add(new FormField("品牌名称:", "newPrm", new LabelFieldEditor("呷哺呷哺", "100%"), false, null, true));
		base_message.add(new FormField("订单编号:", "newfirm1", new LabelFieldEditor("12300041", "100%"), false, null, true));
		base_message.add(new FormField("特许方:", "nm2", new LabelFieldEditor("呷哺呷哺股份有限公司", "100%"), false, null, true));
		base_message.add(new FormField("加盟方:", "nm21", new LabelFieldEditor("张三", "100%"), false, null, true));
		base_message.add(new FormField("签约时间:", "nerm3", new LabelFieldEditor("2016-10-11 12:41:25", "100%"), false, null, true));
		base_message.add(new FormField("合作有效期:", "oldrd3", new LabelFieldEditor("3年", "100%"), false, null, true));
		formAgent.addFieldListToMap("基本信息", base_message);
		
		List<FormField> firstParty_message = new ArrayList<FormField>();
		firstParty_message.add(new FormField("加盟总费用(元)", "businessLicenseNo1", new LabelFieldEditor("300,0000.00", "100%"), true, null, true));
		firstParty_message.add(new FormField("加盟费(元)", "taxNo", new LabelFieldEditor("100,0000.00", "100%"), true, null, true));
		firstParty_message.add(new FormField("保证金(元)", "legalPerson", new LabelFieldEditor("200,0000.00", "100%"), true, null, true));
		formAgent.addFieldListToMap("加盟费用", firstParty_message);
		
		List<FormField> account_message = new ArrayList<FormField>();
		account_message.add(new FormField("法定授权人:", "oldrd311ds", new LabelFieldEditor("李四", "100%"), false, null, true));
		account_message.add(new FormField("法定地址:", "oldrd312s", new LabelFieldEditor("北京市望京东路1号摩托罗拉大厦", "100%"), false, null, true));
		account_message.add(new FormField("联系方式:", "oldrd322s", new LabelFieldEditor("18612345678", "100%"), false, null, true));
		formAgent.addFieldListToMap("品牌商基本信息", account_message);
		
		List<FormField> settlement_message = new ArrayList<FormField>();
		settlement_message.add(new FormField("法定代表人", "settlementPeriod", new LabelFieldEditor("王五", "100%"), false, null, true));
		settlement_message.add(new FormField("身份证号", "settlementDimension", new LabelFieldEditor("110231196502031101", "100%"), false, null, true));
		settlement_message.add(new FormField("法定地址", "accountName", new LabelFieldEditor("北京市望京东路1号", "100%"), false, null, true));
		settlement_message.add(new FormField("联系方式", "accountNo", new LabelFieldEditor("13212345678", "100%"), false, null, true));
		formAgent.addFieldListToMap("加盟者基本信息", settlement_message);
		
		List<FormField> img_message = new ArrayList<FormField>();
		img_message.add(new FormField("合同照片", "businessLicenceImg", ImageUploadEditor.class, false, null, true));
		formAgent.addFieldListToMap("附件", img_message);
	}
}
