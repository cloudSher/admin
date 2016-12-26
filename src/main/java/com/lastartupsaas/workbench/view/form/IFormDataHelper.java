package com.lastartupsaas.workbench.view.form;


/**
 * @author shixin
 *
 */
public interface IFormDataHelper {

    public void setProperty(Object bean, String propertyName, Object value);

    public Object getProperty(Object bean, String propertyName);

}
