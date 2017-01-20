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
@SpringView(name = BrandTypeListTabView.VIEW_NAME)
public class BrandTypeListTabView extends BaseWorkbenchTabView {

	private static final long serialVersionUID = 2630424548213219997L;
	public static final String VIEW_NAME = "brand_type_list.view";

	public BrandTypeListTabView() {
		this.setViewCaption("当前位置：品牌 > 品牌 > 品牌类型");
	}
	
	@Override
	protected void setupViews(Map<String, BaseWorkbenchView> viewsMap) {

		BrandTypeListView firstView = new BrandTypeListView("1");
		firstView.initView();
		BrandTypeImportView secondView = new BrandTypeImportView();
		secondView.initView();

		viewsMap.put("所有类型", firstView);
		viewsMap.put("导入", secondView);
	}
}
