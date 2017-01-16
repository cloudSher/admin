package com.lastartupsaas.workbench.view.business.transaction.order;

import java.util.Map;

import com.lastartupsaas.workbench.view.BaseWorkbenchTabView;
import com.lastartupsaas.workbench.view.BaseWorkbenchView;
import com.lastartupsaas.workbench.view.business.admin.RoleListView;
import com.lastartupsaas.workbench.view.business.admin.UserEditView;
import com.lastartupsaas.workbench.view.business.admin.UserListView;
import com.vaadin.spring.annotation.SpringView;

/**
 * 交易管理列表页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = OrderTabView.VIEW_NAME)
public class OrderTabView extends BaseWorkbenchTabView {

	private static final long serialVersionUID = -6100770582135594555L;

	public static final String VIEW_NAME = "order_list.view";

	public OrderTabView() {
		this.setViewCaption("当前位置：交易 > 交易订单 > 交易管理");
	}
	
	@Override
	protected void setupViews(Map<String, BaseWorkbenchView> viewsMap) {

		OrderListView firstView = new OrderListView("1");
		firstView.initView();
		OrderListView secondView = new OrderListView("2");
		secondView.initView();
		OrderListView thirdView = new OrderListView("3");
		thirdView.initView();

		viewsMap.put("加盟流程", firstView);
		viewsMap.put("服务中", secondView);
		viewsMap.put("服务完成", thirdView);
	}
}
