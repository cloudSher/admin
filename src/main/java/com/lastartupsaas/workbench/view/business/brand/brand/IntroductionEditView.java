package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.RichTextAreaEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌 > 品牌 > 品牌编辑页面 > 品牌介绍
 * 
 * @author lifeilong
 * @date: 2016-12-13
 */
@SpringView(name = IntroductionEditView.VIEW_NAME)
public class IntroductionEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = -7264884986713794566L;
	public static final String VIEW_NAME = "introduction_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("品牌介绍", "firstParty", RichTextAreaEditor.class, true, null, true));
		base_message.add(new FormField("产品介绍", "contractNo", RichTextAreaEditor.class, false, null, true));
		base_message.add(new FormField("商机亮点", "firstParty", RichTextAreaEditor.class, true, null, true));
		formAgent.addFieldListToMap("品牌介绍", base_message);
	}

	@Override
	protected Object createVirginObject() {
		return null;
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
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return "brand_list.view";
	}
}
