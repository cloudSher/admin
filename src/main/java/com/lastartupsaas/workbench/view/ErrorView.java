package com.lastartupsaas.workbench.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = ErrorView.VIEW_NAME)
public class ErrorView extends VerticalLayout implements View {
	private static final long serialVersionUID = 2580231512604297100L;
	public static final String VIEW_NAME = "error";

	public ErrorView() {
		Label label = new Label("温馨提示：您访问的页面不存在或您无权限访问！");
		label.addStyleName(ValoTheme.LABEL_FAILURE);
		label.addStyleName(ValoTheme.LABEL_LARGE);
		addComponent(label);
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
}
