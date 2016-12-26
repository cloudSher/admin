package com.lastartupsaas.workbench.view.form;

import com.vaadin.server.UserError;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;

/**
 * @author shixin
 *
 */
public abstract class BaseFormFieldEditor implements IFormFieldEditor {

    protected FormField field;

    protected Component component;

    protected FormAgent agent;

    @Override
    public void showValidateMessage(String msg) {
        if(component instanceof AbstractComponent){
            AbstractComponent ac = (AbstractComponent) component;
            if(msg!=null){
                ac.setComponentError(new UserError(msg));
            }else{
                ac.setComponentError(null);
            }
        }
    }

    @Override
    public String validate() {
        Object v = this.getValue();
        if(this.field.isRequired() && v == null) return this.getRequiredErrorMessage();
        return null;
    }

    @Override
    public void setField(FormField field) {
        this.field = field;
    }

    @Override
    public FormField getField() {
        return this.field;
    }

    @Override
    public void setFormAgent(FormAgent agent) {
        this.agent = agent;
    }

    @Override
    public Component getEditorComponent() {
        if(component!=null) return this.component;
        this.component = this.createComponent();
        if(this.field.getWidth()!=null){
            this.component.setWidth(this.field.getWidth());
        }
        if(this.field.getHeight()!=null){
            this.component.setHeight(this.field.getHeight());
        }
        return this.component;
    }

    protected String getRequiredErrorMessage(){
        return "[" + this.field.getTitle() + "] 为必填项.";
    }

    protected FormAgent getFormAgent(){
        return this.agent;
    }

    protected abstract Component createComponent();


}