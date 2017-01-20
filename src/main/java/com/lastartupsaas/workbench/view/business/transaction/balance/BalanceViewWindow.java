package com.lastartupsaas.workbench.view.business.transaction.balance;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.business.transaction.order.OrderListView;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormBuildLayout;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.lastartupsaas.workbench.widgets.FormWindow;
import com.vaadin.ui.VerticalLayout;

public class BalanceViewWindow extends FormWindow {

	private static final long serialVersionUID = -6169028229535431524L;

	public BalanceViewWindow(String caption) {
		super(caption, "1", "80%");
	}

	@Override
	protected boolean doDoneActione(FormAgent formAgent) {
		return true;
	}

	@Override
	protected void setupFormAgent(FormAgent formAgent) {
		formAgent.setFieldColumnCount(1);
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("品牌名称:", "oldrd", new LabelFieldEditor("重庆小面", "100%"), false, null, true));
		base_message.add(new FormField("结算单编号:", "nerd", new LabelFieldEditor("00000002", "100%"), false, null, true));
		base_message.add(new FormField("出账日期:", "newPrm", new LabelFieldEditor("2016-10-11 12:41:25", "100%"), false, null, true));
		base_message.add(new FormField("平台应结金额:", "newPrm1", new LabelFieldEditor("30.00", "100%"), false, null, true));
		base_message.add(new FormField("结算状态:", "newPrm2", new LabelFieldEditor("已结算", "100%"), false, null, true));
		base_message.add(new FormField("支付渠道:", "newPrm3", new LabelFieldEditor("支付宝", "100%"), false, null, true));
		base_message.add(new FormField("支付交易单号:", "newPrm4", new LabelFieldEditor("222111102222205567", "100%"), false, null, true));
		base_message.add(new FormField("打款时间:", "newPrm5", new LabelFieldEditor("2016-10-11 12:41:25", "100%"), false, null, true));
		base_message.add(new FormField("品牌收款账号:", "newPrm6", new LabelFieldEditor("42352532453245345", "100%"), false, null, true));
		base_message.add(new FormField("备注:", "newPrm7", new LabelFieldEditor("结算完成", "100%"), false, null, true));
		formAgent.addFieldListToMap("结算单明细", base_message);
	}

	@Override
	protected void addComponentBefore(FormBuildLayout layout) {
		super.addComponentBefore(layout);
	}

	@Override
	protected void addComponentAfter(FormBuildLayout layout) {
		super.addComponentAfter(layout);
		OrderListView orderList = new OrderListView();
		orderList.initView();
		layout.addFormFieldComponent(orderList, layout.getComponentCount());
	}
	
}
