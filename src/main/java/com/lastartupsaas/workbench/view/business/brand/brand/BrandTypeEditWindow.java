package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.widgets.FormWindow;

public class BrandTypeEditWindow extends FormWindow {

	private static final long serialVersionUID = 6816262981543230913L;

	public BrandTypeEditWindow(String caption) {
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
		base_message.add(new FormField("类型名称", "joinType", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("上级分类", "storeArea", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("排序", "joiningFee", InputFieldEditor.class, true, null, true).setInputDescr("数字范围为0~255，数字越小越靠前"));
		formAgent.addFieldListToMap("品牌类型", base_message);
	}

	protected boolean saveObject(Object data) {
		return true;
	}
}
