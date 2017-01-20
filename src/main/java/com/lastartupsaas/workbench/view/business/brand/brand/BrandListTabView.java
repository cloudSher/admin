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
@SpringView(name = BrandListTabView.VIEW_NAME)
public class BrandListTabView extends BaseWorkbenchTabView {

	private static final long serialVersionUID = -6324125283540388369L;
	public static final String VIEW_NAME = "brand_list.view";

	public BrandListTabView() {
		this.setViewCaption("当前位置：品牌 > 品牌 > 品牌列表");
	}
	
	@Override
	protected void setupViews(Map<String, BaseWorkbenchView> viewsMap) {

		BrandListView firstView = new BrandListView("1");
		firstView.initView();
		BrandImportView secondView = new BrandImportView();
		secondView.initView();

		viewsMap.put("所有品牌", firstView);
		viewsMap.put("导入", secondView);
	}
}
