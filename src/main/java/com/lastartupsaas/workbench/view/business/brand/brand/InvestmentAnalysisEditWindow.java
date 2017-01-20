package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.widgets.FormWindow;

public class InvestmentAnalysisEditWindow extends FormWindow {

	private static final long serialVersionUID = 6292367946463922058L;

	public InvestmentAnalysisEditWindow(String caption) {
		super(caption);
	}

	@Override
	protected boolean doDoneActione(FormAgent formAgent) {
//		PasswordEditDTO dto = new PasswordEditDTO();
//		formAgent.loadFormDataToBean(dto);

		boolean ret = this.saveObject(null);
		if (ret) {
			this.showNotification("操作成功", "保存成功");
			return true;
		} else {
			this.showNotification("操作失败", "保存失败");
			return false;
		}
	}

	@Override
	protected void setupFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("加盟类型", "joinType", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("预算店面面积(㎡)", "storeArea", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("加盟费(万)", "joiningFee", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("保证金(万)", "deposit", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("设备费用(万)", "equipmentFee", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("首批原物料(万)", "firstBatchRawMaterial", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("装修费(万)", "renovationFee", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("广告费(万)", "advertisingFee", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("其他费用(万)", "otherFee", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("合计(万)", "total", InputFieldEditor.class, true, null, true));
		formAgent.addFieldListToMap("投资分析", base_message);
	}

	protected boolean saveObject(Object data) {
		return true;
	}
}
