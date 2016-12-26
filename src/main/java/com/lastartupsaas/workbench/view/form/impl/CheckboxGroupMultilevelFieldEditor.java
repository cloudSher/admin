/**
 * 
 */
package com.lastartupsaas.workbench.view.form.impl;

import java.util.List;

import com.lastartupsaas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;

/**
 * Multilevel Checkbox Group
 * 
 * @author lifeilong
 */
public class CheckboxGroupMultilevelFieldEditor extends BaseFormFieldEditor {

	private OptionGroup optionGroup;
	private List<?> items;
	private String valuePropertyName;
	private String displayPropertyName;

	public CheckboxGroupMultilevelFieldEditor() {
	}

	public CheckboxGroupMultilevelFieldEditor(List<?> items, String valuePropertyName, String displayPropertyName) {
		super();
		this.items = items;
		this.valuePropertyName = valuePropertyName;
		this.displayPropertyName = displayPropertyName;
	}

	public List<?> getItems() {
		return items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}

	public String getValuePropertyName() {
		return valuePropertyName;
	}

	public void setValuePropertyName(String valuePropertyName) {
		this.valuePropertyName = valuePropertyName;
	}

	public String getDisplayPropertyName() {
		return displayPropertyName;
	}

	public void setDisplayPropertyName(String displayPropertyName) {
		this.displayPropertyName = displayPropertyName;
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
		// vl.setWidth("100%");
		vl.setSizeUndefined();
		vl.setSpacing(true);
		this.optionGroup = new OptionGroup();
		// this.optionGroup.setWidth("100%");
		this.optionGroup.setStyleName("horizontal");
		this.optionGroup.setMultiSelect(true);
		if (field.getDictType() != null && field.getDictType().length() > 0) {

			// if(this.agent.getDictionaryManager()!=null){
			// List<ODictionaryItem> items = this.agent.getDictionaryManager().getDictionaryItemListByType(field.getDictType());
			// if(items!=null)for(ODictionaryItem item : items) {
			// CheckBox ckb = new CheckBox(item.getItemName(), false);
			// ckb.setData(item.getItemKey());
			// this.checkBoxs.add(ckb);
			// hl.addComponent(ckb);
			// }
			// }
		} else {
			if (this.items != null) {
				for (Object item : this.items) {
					Object oid = this.getFormAgent().getDataHelper().getProperty(item, valuePropertyName);
					Object cap = this.getFormAgent().getDataHelper().getProperty(item, displayPropertyName);
					if (oid != null && cap != null) {
						this.optionGroup.addItem(oid);
						this.optionGroup.setItemCaption(oid, cap.toString());
					}
				}
			}
		}
		vl.addComponent(optionGroup);
		if (this.field.getInputDescr() != null && this.field.getInputDescr().trim().length() > 0) {
			Label desc = new Label(this.field.getInputDescr(), ContentMode.HTML);
			desc.setSizeUndefined();
			vl.addComponent(desc);
		}

		return vl;
	}

}
