package com.lastartupsaas.workbench.view.form.impl;

import java.util.LinkedList;
import java.util.List;

import com.lastartupsaas.workbench.view.form.BaseFormFieldEditor;
import com.lastartupsaas.workbench.view.form.IFormDataHelper;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

/**
 * @author shixin
 *
 */
public class SelectFieldGroupEditor extends BaseFormFieldEditor {

	private HorizontalLayout layou;
	private List<?> items;
	private String valuePropertyName;
	private String displayPropertyName;
	private String listPropertyName;
	private Object value;
	private IFormDataHelper dataHelper;
	
	protected LinkedList<Component> components = new LinkedList<Component>();

	public SelectFieldGroupEditor() {
	}

	public SelectFieldGroupEditor(List<?> items, String valuePropertyName, String displayPropertyName, String listPropertyName) {
		super();
		this.items = items;
		this.valuePropertyName = valuePropertyName;
		this.displayPropertyName = displayPropertyName;
		this.listPropertyName = listPropertyName;
	}

	@Override
	public void resetValue() {
		value = null;
		if (layou != null) {
			layou.removeAllComponents();
			if (this.items != null) {
				addComboBox(items);
			}
		}
	}

	@Override
	public void setValue(Object value) {
		if (value != null) {
			Object oid = this.getFormAgent().getDataHelper().getProperty(value, valuePropertyName);
//			this.comboBox.select(oid);
		}
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	protected Component createComponent() {
		layou = new HorizontalLayout();
		layou.setSpacing(true);

		this.dataHelper = this.getFormAgent().getDataHelper();
		if (this.items != null) {
			addComboBox(items);
		}
		return layou;
	}
	
	protected void addComboBox(List<?> itemList) {
		final ComboBox comboBox = new ComboBox();
		comboBox.setNewItemsAllowed(false);
		comboBox.setNullSelectionAllowed(this.agent.isSearchMode());
		for (Object item : itemList) {
			Object oid = this.getFormAgent().getDataHelper().getProperty(item, valuePropertyName);
			Object cap = this.getFormAgent().getDataHelper().getProperty(item, displayPropertyName);
			if (oid != null && cap != null) {
				comboBox.addItem(item);
				comboBox.setItemCaption(item, cap.toString());
			}
		}
		layou.addComponent(comboBox);
		components.add(comboBox);
		comboBox.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				value = event.getProperty().getValue();
				if (components != null && components.size() > 0) {
					for (int i = 1; i < components.size(); i++) {
						if (i > components.indexOf(comboBox)) {
							layou.removeComponent(components.get(i));
						}
					}
				}
				if (value != null) {
					List<?> childItemList = (List<?>) dataHelper.getProperty(event.getProperty().getValue(), listPropertyName);
					if (childItemList != null && childItemList.size() > 0) {
						addComboBox(childItemList);
					}
				}
			}
		});
	}
}