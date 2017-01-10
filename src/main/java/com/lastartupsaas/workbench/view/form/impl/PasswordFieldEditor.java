package com.lastartupsaas.workbench.view.form.impl;

import com.lastartupsaas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author shixin
 *
 */
public class PasswordFieldEditor extends BaseFormFieldEditor {

	private PasswordField passwordField;

	public PasswordFieldEditor() {
	}

	@Override
	public void setValue(Object value) {
		if (this.passwordField != null) {
			this.passwordField.setValue(value != null ? value.toString() : "");
		}
	}

	@Override
	public String validate() {
		if (super.validate() != null)
			return super.validate();
		String v = this.passwordField.getValue();
		if (this.field.isRequired() && (v == null || v.trim().length() == 0))
			return this.getRequiredErrorMessage();
		return null;
	}

	@Override
	public void resetValue() {
		if (this.passwordField != null)
			this.passwordField.setValue("");
	}

	@Override
	public Object getValue() {
		return this.passwordField != null ? this.passwordField.getValue().trim() : null;
	}

	@Override
	protected Component createComponent() {
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSizeUndefined();
		hLayout.setSpacing(true);

		this.passwordField = new PasswordField();
		hLayout.addComponent(passwordField);

		if (this.field.getInputDescr() != null && this.field.getInputDescr().trim().length() > 0) {
			Label desc = new Label(this.field.getInputDescr(), ContentMode.HTML);
			desc.setSizeUndefined();
			desc.addStyleName(ValoTheme.LABEL_TINY);
			desc.addStyleName(ValoTheme.LABEL_COLORED);
			hLayout.addComponent(desc);
		}
		return hLayout;
	}

}