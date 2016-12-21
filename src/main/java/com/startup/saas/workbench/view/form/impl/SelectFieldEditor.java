package com.startup.saas.workbench.view.form.impl;

import java.util.List;

import com.startup.saas.workbench.view.form.BaseFormFieldEditor;
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

    public SelectFieldEditor() {
    }

    public SelectFieldEditor(List<?> items, String valuePropertyName, String displayPropertyName) {
        super();
        this.items = items;
        this.valuePropertyName = valuePropertyName;
        this.displayPropertyName = displayPropertyName;
    }

    @Override
    public void resetValue() {
        if(this.comboBox!=null) this.comboBox.setValue(null);
    }

    @Override
    public void setValue(Object value) {
        if(value!=null){
            this.comboBox.setValue(value);
        }
    }

    @Override
    public Object getValue() {
        return this.comboBox.getValue()!=null?comboBox.getValue():null;
    }

    @Override
    protected Component createComponent() {
        this.comboBox = new ComboBox();
        this.comboBox.setWidth("100%");

        if(field.getDictType()!=null && field.getDictType().length()>0){
            comboBox.setNewItemsAllowed(false);
            comboBox.setNullSelectionAllowed(this.agent.isSearchMode());

//			if(this.agent.getDictionaryManager()!=null){
//				List<ODictionaryItem> items = this.agent.getDictionaryManager().getDictionaryItemListByType(field.getDictType());
//				if(items!=null)for(ODictionaryItem item : items) {
//					comboBox.addItem(item.getItemKey());
//					comboBox.setItemCaption(item.getItemKey(), item.getItemName());
//				}
//			}
        }else{
            if(this.items!=null){
                comboBox.setNewItemsAllowed(false);
                comboBox.setNullSelectionAllowed(this.agent.isSearchMode());
                for(Object item : this.items){
                    Object oid = this.getFormAgent().getDataHelper().getProperty(item, valuePropertyName);
                    Object cap = this.getFormAgent().getDataHelper().getProperty(item, displayPropertyName);
                    if(oid!=null && cap!=null){
                        comboBox.addItem(oid);
                        comboBox.setItemCaption(oid, cap.toString());
                    }
                }
            }
        }

        return this.comboBox;
    }

}