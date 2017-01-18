package com.lastartupsaas.workbench.view.business.community.topic;

import java.util.Map;

import com.lastartupsaas.workbench.view.BaseWorkbenchTabView;
import com.lastartupsaas.workbench.view.BaseWorkbenchView;
import com.vaadin.spring.annotation.SpringView;

/**
 * 话题推荐页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = RecommendTabView.VIEW_NAME)
public class RecommendTabView extends BaseWorkbenchTabView {

	private static final long serialVersionUID = -6100770582135594555L;

	public static final String VIEW_NAME = "recommend_topic_list.view";

	public RecommendTabView() {
		this.setViewCaption("当前位置：社区运营 > 话题 > 话题推荐");
	}
	
	@Override
	protected void setupViews(Map<String, BaseWorkbenchView> viewsMap) {

		RecommendListView firstView = new RecommendListView("1");
		firstView.initView();
		RecommendListView secondView = new RecommendListView("2");
		secondView.initView();

		viewsMap.put("首页", firstView);
		viewsMap.put("热门", secondView);
	}
}
