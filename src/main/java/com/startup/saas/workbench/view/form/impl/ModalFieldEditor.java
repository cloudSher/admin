package com.startup.saas.workbench.view.form.impl;


import com.startup.saas.workbench.view.form.BaseFormFieldEditor;
import com.startup.saas.workbench.widgets.FormButton;
import com.startup.saas.workbench.widgets.ModalWindow;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 * Author: alvin
 * Date: 2016-08-23
 */
public class ModalFieldEditor extends BaseFormFieldEditor {

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

        hl.addComponent(new FormButton("组列表", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
//                GroupListView firstView = new GroupListView();
////                firstView.initView();
//                ModalWindow formWindow = new ModalWindow("弹出Modal",firstView);
//                UI.getCurrent().addWindow(formWindow);
            }
        })
        );

        return hl;
    }

}
