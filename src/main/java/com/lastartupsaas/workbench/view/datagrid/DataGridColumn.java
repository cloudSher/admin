package com.lastartupsaas.workbench.view.datagrid;

/**
 * @author shixin
 *
 */
public class DataGridColumn {

    private String id;
    private Class<?> type;
    private String caption;
    private String iconUrl;
    private Object defaultValue;




    public DataGridColumn(String id, Class<?> type) {
        super();
        this.id = id;
        this.type = type;
    }

    public DataGridColumn(String id, Class<?> type, String caption,	String iconUrl, Object defaultValue) {
        super();
        this.id = id;
        this.type = type;
        this.caption = caption;
        this.iconUrl = iconUrl;
        this.defaultValue = defaultValue;
    }
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getIconUrl() {
        return iconUrl;
    }
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    public Object getDefaultValue() {
        return defaultValue;
    }
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
    public Class<?> getType() {
        return type;
    }
    public void setType(Class<?> type) {
        this.type = type;
    }



}
