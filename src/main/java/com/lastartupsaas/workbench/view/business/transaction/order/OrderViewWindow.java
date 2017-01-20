package com.lastartupsaas.workbench.view.business.transaction.order;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.ButtonFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog;
import com.lastartupsaas.workbench.widgets.FormWindow;
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog.ConfirmEvent;
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog.ConfirmListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class OrderViewWindow extends FormWindow {

	private static final long serialVersionUID = 4062409983143002055L;

	public OrderViewWindow(String caption) {
		super(caption, "1", "95%");
	}

	@Override
	protected boolean doDoneActione(FormAgent formAgent) {
        return true;
	}

	@Override
	protected void setupFormAgent(FormAgent formAgent) {
		formAgent.setFieldColumnCount(4);
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("订单号:", "oldrd",new LabelFieldEditor("7000000000015901", "100%"), false, null,  true));
		base_message.add(new FormField("申请时间:", "nerd", new LabelFieldEditor("2015-06-12 09:39:59", "100%"), false, null,  true));
		base_message.add(new FormField("订单状态:", "newPrm", new LabelFieldEditor("交易关闭", "100%"), false, null, true));
		base_message.add(new FormField("", "space01", new LabelFieldEditor("", ""), false, null, true));
		formAgent.addFieldListToMap("下单支付", base_message);
		
		List<FormField> starting_gold = new ArrayList<FormField>();
		starting_gold.add(new FormField("启动金:", "newfirm1", new LabelFieldEditor("300", "100%"), false, null, true));
		starting_gold.add(new FormField("实付款:", "nm2", new LabelFieldEditor("300", "100%"), false, null, true));
		starting_gold.add(new FormField("", "space02", new LabelFieldEditor("", "100%"), false, null, true));
		starting_gold.add(new FormField("支付时间:", "nerm3", new LabelFieldEditor("2015-06-12 10:19:48", "100%"), false, null, true));
		starting_gold.add(new FormField("支付单号:", "nnm2", new LabelFieldEditor("580487417199481007", "100%"), false, null, true));
		starting_gold.add(new FormField("支付方式:", "nnnm2", new LabelFieldEditor("微信支付[扫码]", "100%"), false, null, true));
		starting_gold.add(new FormField("支付状态:", "nnnnm2", new LabelFieldEditor("已接收", "100%"), false, null, true));
		
		ButtonFieldEditor button1 = new ButtonFieldEditor("获取扣款信息", "");
		button1.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				DebitViewWindow formWindow = new DebitViewWindow("");
				UI.getCurrent().addWindow(formWindow);
			}
		});
		
		starting_gold.add(new FormField("", "button01", button1, false, null, true));
		formAgent.addFieldListToMap("启动金", starting_gold);
		
		List<FormField> final_payment = new ArrayList<FormField>();
		final_payment.add(new FormField("尾款:", "newfirm11", new LabelFieldEditor("50000", "100%"), false, null, true));
		final_payment.add(new FormField("实付款:", "nm21", new LabelFieldEditor("48000", "100%"), false, null, true));
		final_payment.add(new FormField("助力金:", "nm21", new LabelFieldEditor("2000", "100%"), false, null, true));
		final_payment.add(new FormField("支付时间:", "nerm31", new LabelFieldEditor("2015-06-12 10:19:48", "100%"), false, null, true));
		final_payment.add(new FormField("支付单号:", "nnm21", new LabelFieldEditor("580487417199481007", "100%"), false, null, true));
		final_payment.add(new FormField("支付方式:", "nnnm21", new LabelFieldEditor("微信支付[扫码]", "100%"), false, null, true));
		final_payment.add(new FormField("支付状态:", "nnnnm21", new LabelFieldEditor("已接收", "100%"), false, null, true));
		ButtonFieldEditor button2 = new ButtonFieldEditor("获取扣款信息", "");
		button2.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				DebitViewWindow formWindow = new DebitViewWindow("");
				UI.getCurrent().addWindow(formWindow);
			}
		});
		final_payment.add(new FormField("", "button02", button2, false, null, true));
		formAgent.addFieldListToMap("尾款", final_payment);
		
		List<FormField> user_message = new ArrayList<FormField>();
		user_message.add(new FormField("用户ID:", "oldrd3",new LabelFieldEditor("423423442342", "100%"), false, null,  true));
		user_message.add(new FormField("用户昵称:", "nerd2", new LabelFieldEditor("王石", "100%"), false, null,  true));
		user_message.add(new FormField("联系方式:", "newPrm6", new LabelFieldEditor("13222222222", "100%"), false, null, true));
		user_message.add(new FormField("", "space03", new LabelFieldEditor("", "100%"), false, null, true));
		formAgent.addFieldListToMap("用户信息", user_message);
		
		List<FormField> contract_message = new ArrayList<FormField>();
		contract_message.add(new FormField("合同号:", "oldrd37",new LabelFieldEditor("4324234234234", "100%"), false, null,  true));
		contract_message.add(new FormField("合同金额:", "nerd27", new LabelFieldEditor("500000", "100%"), false, null,  true));
		contract_message.add(new FormField("定金:", "newPrm67", new LabelFieldEditor("300", "100%"), false, null, true));
		contract_message.add(new FormField("尾款:", "newPrm617", new LabelFieldEditor("389232", "100%"), false, null, true));
		formAgent.addFieldListToMap("加盟合同信息", contract_message);
		
		List<FormField> pay_message = new ArrayList<FormField>();
		pay_message.add(new FormField("交易单编号:", "oldrd378",new LabelFieldEditor("1000000", "100%"), false, null,  true));
		pay_message.add(new FormField("前期款:", "nerd278", new LabelFieldEditor("1000000", "100%"), false, null,  true));
		pay_message.add(new FormField("用户确认时间:", "newPrm678", new LabelFieldEditor("2013-04-23 19:00", "100%"), false, null, true));
		pay_message.add(new FormField("服务费:", "newPrm6178", new LabelFieldEditor("1000", "100%"), false, null, true));
		pay_message.add(new FormField("实付款:", "newPrm61178", new LabelFieldEditor("3891", "100%"), false, null, true));
		pay_message.add(new FormField("状态:", "newPrm61278", new LabelFieldEditor("未确认", "100%"), false, null, true));
		ButtonFieldEditor button3 = new ButtonFieldEditor("服务确认", "");
		button3.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				showConfirmDialog("提示", "确认该服务已完成了吗 ?",  new ConfirmYesNoDialog.ConfirmListener() {
                    @Override
                    public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
                        if(event.isConfirm()){
                        	Notification.show("恭喜你", "服务确认成功", Notification.Type.HUMANIZED_MESSAGE);
                        }else{
                        }
                    }
                });
			}
		});
		
		pay_message.add(new FormField("", "button03", button3, false, null, true));
		pay_message.add(new FormField("", "space05", new LabelFieldEditor("", "100%"), false, null, true));
		
		pay_message.add(new FormField("交易单编号:", "oldrd3789",new LabelFieldEditor("1000000", "100%"), false, null,  true));
		pay_message.add(new FormField("中期款:", "nerd2789", new LabelFieldEditor("1000000", "100%"), false, null,  true));
		pay_message.add(new FormField("用户确认时间:", "newPrm6789", new LabelFieldEditor("2013-04-23 19:00", "100%"), false, null, true));
		pay_message.add(new FormField("", "space01", new LabelFieldEditor("", "100%"), false, null, true));
		pay_message.add(new FormField("实付款:", "newPrm611789", new LabelFieldEditor("3891", "100%"), false, null, true));
		pay_message.add(new FormField("状态:", "newPrm612789", new LabelFieldEditor("未确认", "100%"), false, null, true));
		pay_message.add(new FormField("", "space06", new LabelFieldEditor("", "100%"), false, null, true));
		pay_message.add(new FormField("", "space07", new LabelFieldEditor("", "100%"), false, null, true));
		
		pay_message.add(new FormField("交易单编号:", "oldrd3780",new LabelFieldEditor("1000000", "100%"), false, null,  true));
		pay_message.add(new FormField("后期款:", "nerd2780", new LabelFieldEditor("1000000", "100%"), false, null,  true));
		pay_message.add(new FormField("用户确认时间:", "newPrm6780", new LabelFieldEditor("2013-04-23 19:00", "100%"), false, null, true));
		pay_message.add(new FormField("保证金:", "newPrm61780", new LabelFieldEditor("1000", "100%"), false, null, true));
		pay_message.add(new FormField("实付款:", "newPrm611780", new LabelFieldEditor("3891", "100%"), false, null, true));
		pay_message.add(new FormField("状态:", "newPrm612780", new LabelFieldEditor("未确认", "100%"), false, null, true));
		formAgent.addFieldListToMap("付款信息", pay_message);
	}
}
