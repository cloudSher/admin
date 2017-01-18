package com.lastartupsaas.workbench.view.business.task;

import java.util.Map;

import com.lastartupsaas.workbench.view.BaseWorkbenchTabView;
import com.lastartupsaas.workbench.view.BaseWorkbenchView;
import com.vaadin.spring.annotation.SpringView;

/**
 * 任务库列表页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = TaskLibraryTabView.VIEW_NAME)
public class TaskLibraryTabView extends BaseWorkbenchTabView {

	private static final long serialVersionUID = -2949124775663705345L;
	public static final String VIEW_NAME = "task_library_list.view";

	public TaskLibraryTabView() {
		this.setViewCaption("当前位置：待办任务 > 待办任务 > 任务库");
	}
	
	@Override
	protected void setupViews(Map<String, BaseWorkbenchView> viewsMap) {

		TaskLibraryListView firstView = new TaskLibraryListView("1");
		firstView.initView();
		TaskLibraryListView secondView = new TaskLibraryListView("2");
		secondView.initView();
		TaskLibraryListView thirdView = new TaskLibraryListView("3");
		thirdView.initView();

		viewsMap.put("待领取", firstView);
		viewsMap.put("领取待完成", secondView);
		viewsMap.put("已完成", thirdView);
	}
}
