package com.lastartupsaas.workbench.view.form.impl;


import java.util.UUID;

import com.lastartupsaas.workbench.view.form.BaseFormFieldEditor;
import com.lastartupsaas.workbench.widgets.FormButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

/**
 * Author: alvin
 * Date: 2016-08-23
 */
public class UUIDFieldEditor extends BaseFormFieldEditor {

    private TextField textField;

    @Override
    public void setValue(Object value) {
        if(this.textField!=null){
            this.textField.setValue(value!=null?value.toString():"");
        }
    }

    @Override
    public String validate() {
        if(super.validate()!=null) return super.validate();
        String v = this.textField.getValue();
        if(this.field.isRequired() && (v==null || v.trim().length()==0)) return this.getRequiredErrorMessage();
        return null;
    }

    @Override
    public void resetValue() {
        if(this.textField!=null) this.textField.setValue("");
    }

    @Override
    public Object getValue() {
        return this.textField!=null?this.textField.getValue().trim():null;
    }

    @Override
    protected Component createComponent() {
        HorizontalLayout hl = new HorizontalLayout();
        hl.setWidth("100%");
        hl.setSpacing(true);
        this.textField = new TextField();
        this.textField.setWidth("100%");
        if(this.field.getInputDescr()!=null){
            this.textField.setInputPrompt(this.field.getInputDescr());
        }

        hl.addComponent(textField);
        hl.setExpandRatio(textField, 1);

        hl.addComponent(new FormButton("自动生成", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String uuid = UUID.randomUUID().toString();
                textField.setValue(uuid);
            }
        }));

        return hl;
    }

}
