package com.lastartupsaas.workbench.view.business.community.dynamic;

import java.util.Map;

import com.lastartupsaas.workbench.view.BaseWorkbenchTabView;
import com.lastartupsaas.workbench.view.BaseWorkbenchView;
import com.vaadin.spring.annotation.SpringView;

/**
 * 动态管理页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = DynamicTabView.VIEW_NAME)
public class DynamicTabView extends BaseWorkbenchTabView {

	private static final long serialVersionUID = -16635591629209575L;
	public static final String VIEW_NAME = "dynamic_list.view";

	public DynamicTabView() {
		this.setViewCaption("当前位置：社区运营 > 动态 > 动态管理");
	}
	
	@Override
	protected void setupViews(Map<String, BaseWorkbenchView> viewsMap) {

		DynamicListView firstView = new DynamicListView("1");
		firstView.initView();
		DynamicListView secondView = new DynamicListView("2");
		secondView.initView();

		viewsMap.put("列表", firstView);
		viewsMap.put("举报", secondView);
	}
}
