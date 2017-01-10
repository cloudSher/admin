package com.lastartupsaas.workbench.view.form;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author shixin
 *
 */
public class FormBuildLayout extends VerticalLayout {

	private static final long serialVersionUID = 2599914033475946016L;

	private VerticalLayout formVerLayout;
	private HorizontalLayout btns;

	public FormBuildLayout() {
		this("100%");
	}

	public FormBuildLayout(String width) {
		
		this.setSpacing(true);
		this.formVerLayout = new VerticalLayout();
		this.formVerLayout.setSpacing(true);
		this.formVerLayout.setWidth(width);
		this.addComponent(this.formVerLayout, 0);
	}

	public void addActionComponent(Component component) {
		if (this.btns == null) {
			this.btns = new HorizontalLayout();
			this.btns.setSpacing(true);
			this.addComponent(this.btns);
		}
		this.btns.addComponent(component);
	}
	
	public void addFormFieldComponent(Component component,int index) {
		this.formVerLayout.addComponent(component, index);
	}
}
