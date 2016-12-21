package com.startup.saas.workbench.view.form;

import com.startup.saas.workbench.util.DataHelper;

/**
 * @author shixin
 *
 */
public class FormDataHelper implements IFormDataHelper {

    @Override
    public void setProperty(Object bean, String propertyName, Object value) {
        DataHelper.setProperty(bean, propertyName, value);
    }

    @Override
    public Object getProperty(Object bean, String propertyName) {
        return DataHelper.getProperty(bean, propertyName);
    }

}
