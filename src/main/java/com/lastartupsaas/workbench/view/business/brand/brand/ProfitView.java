package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.view.BaseWorkBenchViewView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.ImageViewComponent;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌 > 品牌 > 品牌查看页面 > 基本信息
 * 
 * @author lifeilong
 * @date: 2016-12-13
 */
@SpringView(name = ProfitView.VIEW_NAME)
public class ProfitView extends BaseWorkBenchViewView {

	private static final long serialVersionUID = 2471217168010693261L;
	public static final String VIEW_NAME = "profit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> operating_expenses = new ArrayList<FormField>();
		operating_expenses.add(new FormField("经营税金", "contractNo", new LabelFieldEditor("40,000.00", "100%"), false, null, true));
		operating_expenses.add(new FormField("原材料消耗", "firstParty", new LabelFieldEditor("10,000.00", "100%"), false, null, true));
		operating_expenses.add(new FormField("物料用品", "firstParty", ImageViewComponent.class, false, null, true));
		operating_expenses.add(new FormField("日常费用支出", "secondParty", new LabelFieldEditor("20,000.00", "100%"), false, null, true));
		operating_expenses.add(new FormField("其他费用", "signTime", new LabelFieldEditor("30,000.00", "100%"), false, null, true));
		operating_expenses.add(new FormField("合计", "cooperateEffeDate", new LabelFieldEditor("100,000.00", "100%"), false, null, true));
		formAgent.addFieldListToMap("经营费用(元/月)", operating_expenses);

		List<FormField> profit_analysis = new ArrayList<FormField>();
		profit_analysis.add(new FormField("月销售额", "businessLicenseNo1", new LabelFieldEditor("100,000.00", "100%"), false, null, true));
		profit_analysis.add(new FormField("预期净利润", "taxNo", new LabelFieldEditor("100,000.00", "100%"), false, null, true));
		profit_analysis.add(new FormField("投资回收期(月)", "legalPerson", new LabelFieldEditor("8", "100%"), false, null, true));
		formAgent.addFieldListToMap("利润分析(元/月)", profit_analysis);
	}

	@Override
	protected Object loadFormDataByContext(ViewContext vc) {
		String id = vc.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
		}
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return null;
	}
}
