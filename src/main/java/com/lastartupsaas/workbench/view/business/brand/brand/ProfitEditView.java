package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.ImageUploadEditor;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌 > 品牌 > 品牌编辑页面 > 利润分析
 * 
 * @author lifeilong
 * @date: 2016-12-13
 */
@SpringView(name = ProfitEditView.VIEW_NAME)
public class ProfitEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = -8730649216358358854L;
	public static final String VIEW_NAME = "profit_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> operating_expenses = new ArrayList<FormField>();
		operating_expenses.add(new FormField("经营税金", "contractNo", InputFieldEditor.class, false, null, true));
		operating_expenses.add(new FormField("原材料消耗", "firstParty", InputFieldEditor.class, true, null, true));
		operating_expenses.add(new FormField("物料用品", "firstParty", ImageUploadEditor.class, true, null, true));
		operating_expenses.add(new FormField("日常费用支出", "secondParty", InputFieldEditor.class, true, null, true));
		operating_expenses.add(new FormField("其他费用", "signTime", InputFieldEditor.class, true, null, true));
		operating_expenses.add(new FormField("合计", "cooperateEffeDate", InputFieldEditor.class, true, null, true));
		formAgent.addFieldListToMap("经营费用(元/月)", operating_expenses);

		List<FormField> profit_analysis = new ArrayList<FormField>();
		profit_analysis.add(new FormField("月销售额", "businessLicenseNo1", InputFieldEditor.class, true, null, true));
		profit_analysis.add(new FormField("预期净利润", "taxNo", InputFieldEditor.class, true, null, true));
		profit_analysis.add(new FormField("投资回收期(月)", "legalPerson", InputFieldEditor.class, true, null, true));
		formAgent.addFieldListToMap("利润分析(元/月)", profit_analysis);
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
