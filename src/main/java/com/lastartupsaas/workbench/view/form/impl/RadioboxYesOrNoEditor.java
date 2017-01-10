package com.lastartupsaas.workbench.view.form.impl;

import com.lastartupsaas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;

/**
 * 是与否选择
 * 
 * @author lifeilong
 * @date 2016-12-23 14:04
 */
public class RadioboxYesOrNoEditor extends BaseFormFieldEditor {

	private OptionGroup optionGroup;
	private String itemYesCaption;
	private String itemNoCaption;

	public RadioboxYesOrNoEditor() {
		this.itemYesCaption = "是";
		this.itemNoCaption = "否";
	}

	public RadioboxYesOrNoEditor(String itemYesCaption, String itemNoCaption) {
		super();
		this.itemYesCaption = itemYesCaption;
		this.itemNoCaption = itemNoCaption;
		this.optionGroup = new OptionGroup();
	}

	@Override
	public void setValue(Object value) {
		this.resetValue();
		if (this.optionGroup != null) {
			this.optionGroup.setValue(value);
		}
	}

	@Override
	public Object getValue() {
		if (this.optionGroup == null)
			return null;
		return this.optionGroup.getValue();
	}

	@Override
	public String validate() {
		if (this.optionGroup == null)
			return this.getRequiredErrorMessage();
		Object value = this.getValue();
		if (this.field.isRequired() && value == null)
			return this.getRequiredErrorMessage();
		return null;
	}

	@Override
	public void resetValue() {
		if (this.optionGroup != null) {
			this.optionGroup.setValue(null);
		}
	}

	@Override
	protected Component createComponent() {
		VerticalLayout vl = new VerticalLayout();
		vl.setSizeUndefined();
		vl.setSpacing(true);
		this.optionGroup.setStyleName("horizontal");

		this.optionGroup.addItem("1");
		this.optionGroup.setItemCaption("1", itemYesCaption);
		this.optionGroup.addItem("0");
		this.optionGroup.setItemCaption("0", itemNoCaption);
		vl.addComponent(optionGroup);

		if (this.field.getInputDescr() != null && this.field.getInputDescr().trim().length() > 0) {
			Label desc = new Label(this.field.getInputDescr(), ContentMode.HTML);
			desc.setSizeUndefined();
			vl.addComponent(desc);
		}
		return vl;
	}

	public void addValueChangeListener(Property.ValueChangeListener listener) {
		if (this.optionGroup != null) {
			this.optionGroup.addValueChangeListener(listener);
		}
	}
}
