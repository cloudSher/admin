package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.Map;

import com.lastartupsaas.workbench.view.BaseWorkbenchTabView;
import com.lastartupsaas.workbench.view.BaseWorkbenchView;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = BrandEditTabView.VIEW_NAME)
public class BrandEditTabView extends BaseWorkbenchTabView {

	private static final long serialVersionUID = -3703538365554722112L;
	public static final String VIEW_NAME = "brand_edit.view";

	public BrandEditTabView() {
		this.setViewCaption("当前位置：品牌 > 品牌 > 品牌列表");
	}

	@Override
	protected void setupViews(Map<String, BaseWorkbenchView> viewsMap) {

		BaseMessageEditView firstView = new BaseMessageEditView();
		firstView.initView();
		IntroductionEditView secondView = new IntroductionEditView();
		secondView.initView();
		ProcessEditView thirdView = new ProcessEditView();
		thirdView.initView();
		ProfitEditView fourthView = new ProfitEditView();
		fourthView.initView();
		InvestmentAnalysisListView fifthView = new InvestmentAnalysisListView();
		fifthView.initView();

		viewsMap.put("基本信息", firstView);
		viewsMap.put("品牌介绍", secondView);
		viewsMap.put("加盟流程", thirdView);
		viewsMap.put("利润分析", fourthView);
		viewsMap.put("投资分析", fifthView);
	}
}
