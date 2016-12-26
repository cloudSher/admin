package com.lastartupsaas.workbench.view.form.impl;

import com.lastartupsaas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextArea;

/**
 * @author shixin
 *
 */
public class TextFieldEditor extends BaseFormFieldEditor {

	private TextArea textArea;

	@Override
	public void setValue(Object value) {
		if (this.textArea != null) {
			this.textArea.setValue(value != null ? value.toString() : "");
		}
	}

	@Override
	public void resetValue() {
		if (this.textArea != null)
			this.textArea.setValue("");
	}

	@Override
	public Object getValue() {
		return this.textArea != null ? this.textArea.getValue() : null;
	}

	@Override
	public String validate() {
		if (super.validate() != null)
			return super.validate();
		String v = this.textArea.getValue();
		if (this.field.isRequired() && (v == null || v.trim().length() == 0))
			return this.getRequiredErrorMessage();
		return null;
	}

	@Override
	protected Component createComponent() {
		this.textArea = new TextArea();
		this.textArea.setWidth("100%");
		this.textArea.setHeight("100px");
		if (this.field.getInputDescr() != null) {
			this.textArea.setInputPrompt(this.field.getInputDescr());
		}
		return this.textArea;
	}

}
