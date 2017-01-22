package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌导入页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = BrandImportView.VIEW_NAME)
public class BrandImportView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = -4040381457221942218L;
	public static final String VIEW_NAME = "brand_import.view";

	@Override
	protected String getObjectName(Object obj) {
		return "";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> fields = new ArrayList<FormField>();

		fields.add(new FormField("基本信息", "id", InputFieldEditor.class, false, null, true));
		fields.add(new FormField("品牌介绍", "enterpriseName", InputFieldEditor.class, true, null, true));
		fields.add(new FormField("加盟流程", "contactPhone", InputFieldEditor.class, true, null, true));
		fields.add(new FormField("利润分析", "enterpriseAddress", InputFieldEditor.class, true, null, false));
		fields.add(new FormField("投资分析", "businessLicenseNo", InputFieldEditor.class, true, null, true));

		formAgent.addFieldListToMap("品牌导入", fields);
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
