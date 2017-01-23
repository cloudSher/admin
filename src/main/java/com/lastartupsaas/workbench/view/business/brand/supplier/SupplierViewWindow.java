package com.lastartupsaas.workbench.view.business.brand.supplier;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.lastartupsaas.workbench.widgets.FormWindow;

public class SupplierViewWindow extends FormWindow {

	private static final long serialVersionUID = 2686898529905522381L;

	public SupplierViewWindow(String caption) {
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
		base_message.add(new FormField("品牌商编号:", "id", new LabelFieldEditor("100000001", "100%"), false, null, true));
		base_message.add(new FormField("品牌商名称:", "enterpriseName", new LabelFieldEditor("久久鸭", "100%"), false, null, true));
		base_message.add(new FormField("工商执照注册号:", "businessLicenseNo", new LabelFieldEditor("100201612090011", "100%"), false, null, true));
		base_message.add(new FormField("品牌商企业规模(人):", "newfirm1", new LabelFieldEditor("5000", "100%"), false, null, true));
		base_message.add(new FormField("品牌商地址:", "enterpriseAddress", new LabelFieldEditor("北京市朝阳区望京东路1号摩托罗拉大厦8层", "100%"), false, null, true));
		base_message.add(new FormField("审核状态:", "nerm3", new LabelFieldEditor("通过", "100%"), false, null, true));
		base_message.add(new FormField("最后操作时间:", "lastOperateTime", new LabelFieldEditor("2016-12-09 16:26:22", "100%"), false, null, true));
		base_message.add(new FormField("备注:", "oldrd3", new LabelFieldEditor("备注信息", "100%"), false, null, true));
		formAgent.addFieldListToMap("品牌商信息", base_message);
	}
}
