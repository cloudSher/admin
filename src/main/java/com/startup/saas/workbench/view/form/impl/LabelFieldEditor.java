package com.startup.saas.workbench.view.form.impl;

import com.startup.saas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 * @author lifeilong
 * @date 2016-12-09
 */
public class LabelFieldEditor extends BaseFormFieldEditor {

	private Label label;

	private String width;

	public LabelFieldEditor() {
		this("100%");
	}

	public LabelFieldEditor(String width) {
		super();
		this.width = width;
	}

	@Override
	public void setValue(Object value) {
		if (this.label != null) {
			this.label.setValue(value != null ? value.toString() : "");
		}
	}

	@Override
	public String validate() {
		if (super.validate() != null)
			return super.validate();
		String v = this.label.getValue();
		if (this.field.isRequired() && (v == null || v.trim().length() == 0))
			return this.getRequiredErrorMessage();
		return null;
	}

	@Override
	public void resetValue() {
		// if (this.label != null)
		// this.label.setValue("");
	}

	@Override
	public Object getValue() {
		return this.label != null ? this.label.getValue().trim() : null;
	}

	@Override
	protected Component createComponent() {
		this.label = new Label();
		this.label.setWidth(this.width);
		return this.label;
	}
}