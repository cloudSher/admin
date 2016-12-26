package com.lastartupsaas.workbench.view.form.impl;


import com.lastartupsaas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;

/**
 * @author shixin
 *
 */
public class InputFieldEditor extends BaseFormFieldEditor {

    private TextField textField;

    private String width;

    public InputFieldEditor() {
        this("100%");
    }

    public InputFieldEditor(String width) {
        super();
        this.width = width;
    }



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
        this.textField = new TextField();
        this.textField.setWidth(this.width);
        if(this.field.getInputDescr()!=null){
            this.textField.setInputPrompt(this.field.getInputDescr());
        }
        return this.textField;
    }

}