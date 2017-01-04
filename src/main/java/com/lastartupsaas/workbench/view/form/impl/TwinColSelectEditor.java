package com.lastartupsaas.workbench.view.form.impl;

import java.util.List;

import com.lastartupsaas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.ui.Component;
import com.vaadin.ui.TwinColSelect;

/**
 * 双列选择器
 * 
 * @author lifeilong
 * @date 2016-12-28
 */
public class TwinColSelectEditor extends BaseFormFieldEditor {

	private TwinColSelect twinColSelect;
	private List<?> items;
	private String valuePropertyName;
	private String displayPropertyName;

	public TwinColSelectEditor() {
	}

	public TwinColSelectEditor(List<?> items, String valuePropertyName, String displayPropertyName) {
        super();
        this.items = items;
        this.valuePropertyName = valuePropertyName;
        this.displayPropertyName = displayPropertyName;
    }

	@Override
	public void resetValue() {
		if (this.twinColSelect != null)
			this.twinColSelect.setValue(null);
	}

	@Override
	public void setValue(Object value) {
		if (value != null) {
			this.resetValue();
			List<?> values = (List<?>) value;
			if (values != null && values.size() > 0) {
				for (Object obj : values) {
					Object oid = this.getFormAgent().getDataHelper().getProperty(obj, valuePropertyName);
					this.twinColSelect.select(oid);
				}
			}
		}
	}

	@Override
	public Object getValue() {
		return this.twinColSelect.getValue();
	}

	@Override
	public String validate() {
		if (this.twinColSelect == null)
			return this.getRequiredErrorMessage();
		Object value = this.getValue();
		if (this.field.isRequired() && value == null)
			return this.getRequiredErrorMessage();
		return null;
	}

	@Override
	protected Component createComponent() {
		this.twinColSelect = new TwinColSelect();
		this.twinColSelect.setWidth("100%");
		if (this.field.getInputDescr() != null) {
			this.twinColSelect.setDescription(this.field.getInputDescr());
		}

		if (this.items != null) {
			this.twinColSelect.setRows(8);
			this.twinColSelect.setMultiSelect(true);
			this.twinColSelect.setNewItemsAllowed(false);
			this.twinColSelect.setNullSelectionAllowed(this.agent.isSearchMode());
			this.twinColSelect.setLeftColumnCaption("全部列表");
			this.twinColSelect.setRightColumnCaption("已选列表");
			this.twinColSelect.setImmediate(true);
			for (Object item : this.items) {
				Object oid = this.getFormAgent().getDataHelper().getProperty(item, valuePropertyName);
				Object cap = this.getFormAgent().getDataHelper().getProperty(item, displayPropertyName);
				if (cap != null) {
					twinColSelect.addItem(oid);
					twinColSelect.setItemCaption(oid, cap.toString());
				}
			}
		}
		return this.twinColSelect;
	}
}