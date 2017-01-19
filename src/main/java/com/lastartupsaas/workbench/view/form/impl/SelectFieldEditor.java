package com.lastartupsaas.workbench.view.form.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;

/**
 * @author shixin
 *
 */
public class SelectFieldEditor extends BaseFormFieldEditor {

	private ComboBox comboBox;
	private List<?> items;
	private String valuePropertyName;
	private String displayPropertyName;
	private String defaultValue;
	private String width;

	public SelectFieldEditor() {
		this.width = "100%";
	}

	public SelectFieldEditor(List<?> items, String valuePropertyName, String displayPropertyName) {
		super();
		this.items = items;
		this.valuePropertyName = valuePropertyName;
		this.displayPropertyName = displayPropertyName;
		this.width = "100%";
	}

	public SelectFieldEditor(List<?> items, String valuePropertyName, String displayPropertyName, String width) {
		super();
		this.items = items;
		this.valuePropertyName = valuePropertyName;
		this.displayPropertyName = displayPropertyName;
		this.width = width;
	}

	public SelectFieldEditor(List<?> items, String valuePropertyName, String displayPropertyName, String defaultValue, String width) {
		super();
		this.items = items;
		this.valuePropertyName = valuePropertyName;
		this.displayPropertyName = displayPropertyName;
		this.defaultValue = defaultValue;
		this.width = width;
	}

	@Override
	public void resetValue() {
		if (this.comboBox != null)
			this.comboBox.setValue(null);
	}

	@Override
	public void setValue(Object value) {
		if (value != null) {
			Object oid = this.getFormAgent().getDataHelper().getProperty(value, valuePropertyName);
			this.comboBox.select(oid);
		}
	}

	@Override
	public Object getValue() {
		return this.comboBox.getValue() != null ? comboBox.getValue() : null;
	}

	@Override
	protected Component createComponent() {
		this.comboBox = new ComboBox();
		this.comboBox.setWidth(this.width);
		if (this.field.getInputDescr() != null) {
			this.comboBox.setInputPrompt(this.field.getInputDescr());
			this.comboBox.setDescription(this.field.getInputDescr());
		}

		if (field.getDictType() != null && field.getDictType().length() > 0) {
			comboBox.setNewItemsAllowed(false);
			comboBox.setNullSelectionAllowed(this.agent.isSearchMode());

			// if(this.agent.getDictionaryManager()!=null){
			// List<ODictionaryItem> items = this.agent.getDictionaryManager().getDictionaryItemListByType(field.getDictType());
			// if(items!=null)for(ODictionaryItem item : items) {
			// comboBox.addItem(item.getItemKey());
			// comboBox.setItemCaption(item.getItemKey(), item.getItemName());
			// }
			// }
		} else {
			if (this.items != null) {
				comboBox.setNewItemsAllowed(false);
				comboBox.setNullSelectionAllowed(this.agent.isSearchMode());
				for (Object item : this.items) {
					Object oid = this.getFormAgent().getDataHelper().getProperty(item, valuePropertyName);
					Object cap = this.getFormAgent().getDataHelper().getProperty(item, displayPropertyName);
					if (oid != null && cap != null) {
						comboBox.addItem(oid);
						comboBox.setItemCaption(oid, cap.toString());
					}
				}
			}
		}
		if (StringUtils.isNotBlank(defaultValue)) {
			comboBox.setValue(defaultValue);
		}

		return this.comboBox;
	}

}