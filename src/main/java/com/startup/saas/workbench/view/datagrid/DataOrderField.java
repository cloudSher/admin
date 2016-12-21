package com.startup.saas.workbench.view.datagrid;

/**
 * @author shixin
 *
 */
public class DataOrderField {

    private String caption;
    private String fieldName;
    private Boolean ascending;

    public DataOrderField(String caption, String fieldName, Boolean ascending) {
        super();
        this.caption = caption;
        this.fieldName = fieldName;
        this.ascending = ascending;
    }

    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public Boolean getAscending() {
        return ascending;
    }
    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }



}