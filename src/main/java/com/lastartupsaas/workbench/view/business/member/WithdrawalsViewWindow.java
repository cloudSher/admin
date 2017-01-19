package com.lastartupsaas.workbench.view.business.member;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.lastartupsaas.workbench.widgets.FormWindow;

public class WithdrawalsViewWindow extends FormWindow {

	private static final long serialVersionUID = 4859776739814760327L;

	public WithdrawalsViewWindow(String caption) {
		super(caption, "1");
	}

	@Override
	protected boolean doDoneActione(FormAgent formAgent) {
        return true;
	}

	@Override
	protected void setupFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("提现ID:", "oldrd",new LabelFieldEditor("10000001", "100%"), false, null,  true));
		base_message.add(new FormField("会员昵称:", "nerd", new LabelFieldEditor("马云", "100%"), false, null,  true));
		base_message.add(new FormField("提现金额:", "newPrm", new LabelFieldEditor("300", "100%"), false, null, true));
		base_message.add(new FormField("收款银行:", "newfirm1", new LabelFieldEditor("招商银行", "100%"), false, null, true));
		base_message.add(new FormField("收款账号:", "nm2", new LabelFieldEditor("6215***********6793", "100%"), false, null, true));
		base_message.add(new FormField("开户名称:", "nerm3", new LabelFieldEditor("马云", "100%"), false, null, true));
		formAgent.addFieldListToMap("详细信息", base_message);
		
		List<FormField> pay_message = new ArrayList<FormField>();
		pay_message.add(new FormField("支付渠道:", "oldrd3",new LabelFieldEditor("招商银行", "100%"), false, null,  true));
		pay_message.add(new FormField("交易单号:", "nerd2", new LabelFieldEditor("12380091", "100%"), false, null,  true));
		pay_message.add(new FormField("付款时间:", "newPrm6", new LabelFieldEditor("2017-02-03 17:00", "100%"), false, null, true));
		pay_message.add(new FormField("备注信息:", "newfirm16", new LabelFieldEditor("付款已成功", "100%"), false, null, true));
		
		formAgent.addFieldListToMap("付款信息", pay_message);
	}
}
