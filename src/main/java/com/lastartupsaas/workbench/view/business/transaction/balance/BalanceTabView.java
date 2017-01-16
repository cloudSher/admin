package com.lastartupsaas.workbench.view.business.transaction.balance;

import java.util.Map;

import com.lastartupsaas.workbench.view.BaseWorkbenchTabView;
import com.lastartupsaas.workbench.view.BaseWorkbenchView;
import com.vaadin.spring.annotation.SpringView;

/**
 * 交易管理列表页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = BalanceTabView.VIEW_NAME)
public class BalanceTabView extends BaseWorkbenchTabView {

	private static final long serialVersionUID = 7970477494361274404L;
	public static final String VIEW_NAME = "balance_list.view";

	public BalanceTabView() {
		this.setViewCaption("当前位置：交易 > 结算管理 > 结算单管理");
	}
	
	@Override
	protected void setupViews(Map<String, BaseWorkbenchView> viewsMap) {

		BalanceListView firstView = new BalanceListView("1");
		firstView.initView();
		BalanceListView secondView = new BalanceListView("2");
		secondView.initView();

		viewsMap.put("未结算查询", firstView);
		viewsMap.put("已结算查询", secondView);
	}
}
