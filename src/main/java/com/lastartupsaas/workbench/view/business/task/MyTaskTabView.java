package com.lastartupsaas.workbench.view.business.task;

import java.util.Map;

import com.lastartupsaas.workbench.view.BaseWorkbenchTabView;
import com.lastartupsaas.workbench.view.BaseWorkbenchView;
import com.vaadin.spring.annotation.SpringView;

/**
 * 我的任务列表页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = MyTaskTabView.VIEW_NAME)
public class MyTaskTabView extends BaseWorkbenchTabView {

	private static final long serialVersionUID = 2914253308819823932L;
	public static final String VIEW_NAME = "my_task_list.view";

	public MyTaskTabView() {
		this.setViewCaption("当前位置：待办任务 > 我的任务");
	}
	
	@Override
	protected void setupViews(Map<String, BaseWorkbenchView> viewsMap) {

		MyTaskListView firstView = new MyTaskListView("1");
		firstView.initView();
		MyTaskListView secondView = new MyTaskListView("2");
		secondView.initView();

		viewsMap.put("待处理", firstView);
		viewsMap.put("已处理", secondView);
	}
}
