package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.Map;

import com.lastartupsaas.workbench.view.BaseWorkbenchTabView;
import com.lastartupsaas.workbench.view.BaseWorkbenchView;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌查看页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = BrandTabView.VIEW_NAME)
public class BrandTabView extends BaseWorkbenchTabView {

	private static final long serialVersionUID = -6324125283540388369L;
	public static final String VIEW_NAME = "brand.view";

	public BrandTabView() {
		this.setViewCaption("");
	}

	@Override
	protected void setupViews(Map<String, BaseWorkbenchView> viewsMap) {

		BaseMessageView firstView = new BaseMessageView();
		firstView.initView();
		IntroductionView secondView = new IntroductionView();
		secondView.initView();
		ProcessView thirdView = new ProcessView();
		thirdView.initView();
		ProfitView fourthView = new ProfitView();
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
