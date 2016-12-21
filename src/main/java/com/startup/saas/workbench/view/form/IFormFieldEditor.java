package com.startup.saas.workbench.view.form;


import com.vaadin.ui.Component;

/**
 * @author shixin
 *
 */
public interface IFormFieldEditor {

    public void showValidateMessage(String msg);

    public void setValue(Object value);

    public Object getValue();

    public void resetValue();

    public String validate();

    public void setField(FormField field);

    public FormField getField();

    public void setFormAgent(FormAgent agent);

    public Component getEditorComponent();

}