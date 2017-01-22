package com.lastartupsaas.workbench.view.form.impl;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * 用于查看页面的按钮
 * 
 * @author lifeilong
 * @date 2016-12-09
 */
public class ButtonFieldEditor extends BaseFormFieldEditor {

	private Button button;

	public ButtonFieldEditor() {
		this("100%");
	}
	
	public ButtonFieldEditor(String width) {
		this("按钮", width);
	}

	public ButtonFieldEditor(String caption, String width) {
		super();
		this.button = new Button(caption);
		if (StringUtils.isNotBlank(width)) {
			this.button.setWidth(width);
		}
	}

	@Override
	public void setValue(Object value) {
	}

	@Override
	public String validate() {
		return null;
	}

	@Override
	public void resetValue() {
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	protected Component createComponent() {
		return this.button;
	}
	
	public Button getButton() {
		if (this.button != null) {
			return this.button;
		}
		return null;
	}
	
	public void addClickListener(Button.ClickListener listener) {
		if (this.button != null) {
			this.button.addClickListener(listener);
		}
	}
}