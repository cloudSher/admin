package com.lastartupsaas.workbench.view.business.member;

import java.util.Map;

import com.lastartupsaas.workbench.view.BaseWorkbenchTabView;
import com.lastartupsaas.workbench.view.BaseWorkbenchView;
import com.vaadin.spring.annotation.SpringView;

/**
 * 交易管理页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = MemberTabView.VIEW_NAME)
public class MemberTabView extends BaseWorkbenchTabView {

	private static final long serialVersionUID = 1329298605029356144L;
	public static final String VIEW_NAME = "member_list.view";

	public MemberTabView() {
		this.setViewCaption("当前位置：会员 > 会员管理 > 会员列表");
	}

	@Override
	protected void setupViews(Map<String, BaseWorkbenchView> viewsMap) {
		MemberListView firstView = new MemberListView("1");
		firstView.initView();
		MemberListView secondView = new MemberListView("2");
		secondView.initView();
		
		viewsMap.put("真实会员", firstView);
		viewsMap.put("运营会员", secondView);
	}
}
