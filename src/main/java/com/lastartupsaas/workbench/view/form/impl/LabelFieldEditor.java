package com.lastartupsaas.workbench.view.form.impl;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author lifeilong
 * @date 2016-12-09
 */
public class LabelFieldEditor extends BaseFormFieldEditor {

	private Label label;

	private String width;
	private String defaultValue;

	public LabelFieldEditor() {
		this("100%");
	}

	public LabelFieldEditor(String width) {
		super();
		this.width = width;
	}

	public LabelFieldEditor(String defaultValue, String width) {
		super();
		this.defaultValue = defaultValue;
		this.width = width;
	}

	@Override
	public void setValue(Object value) {
		if (this.label != null) {
			this.label.setValue(value == null ? defaultValue : String.valueOf(value));
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
	}

	@Override
	public Object getValue() {
		return this.label != null ? this.label.getValue().trim() : null;
	}

	@Override
	protected Component createComponent() {
		this.label = new Label();
		this.label.addStyleName(ValoTheme.LABEL_SMALL);
		this.label.addStyleName(ValoTheme.LABEL_LIGHT);
		this.label.setWidth(this.width);
		if (StringUtils.isNotBlank(defaultValue)) {
			this.label.setValue(defaultValue);
		}
		return this.label;
	}
}