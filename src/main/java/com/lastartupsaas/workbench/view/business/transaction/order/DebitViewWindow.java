package com.lastartupsaas.workbench.view.business.transaction.order;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.lastartupsaas.workbench.widgets.FormWindow;

public class DebitViewWindow extends FormWindow {

	private static final long serialVersionUID = -6169028229535431524L;

	public DebitViewWindow(String caption) {
		super(caption, "1");
	}

	@Override
	protected boolean doDoneActione(FormAgent formAgent) {
		return true;
	}

	@Override
	protected void setupFormAgent(FormAgent formAgent) {
		formAgent.setFieldColumnCount(1);
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("渠道:", "oldrd", new LabelFieldEditor("招商银行", "100%"), false, null, true));
		base_message.add(new FormField("时间:", "nerd", new LabelFieldEditor("2015-06-12 09:39:59", "100%"), false, null, true));
		base_message.add(new FormField("流水号:", "newPrm", new LabelFieldEditor("42352532453245345", "100%"), false, null, true));
		formAgent.addFieldListToMap("扣款信息", base_message);
	}
}
