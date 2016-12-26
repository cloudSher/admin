package com.lastartupsaas.workbench.view.datagrid;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shixin
 *
 */
public class ActionCommand implements Serializable {

    public static Map<String, String> DEFAULT_ICONS_MAPPINGS = new ConcurrentHashMap<String, String>();
    static {
        DEFAULT_ICONS_MAPPINGS.put("编辑", "media/images/icon_acts/icon_edit_edit.png");
        DEFAULT_ICONS_MAPPINGS.put("删除", "media/images/icon_acts/icon_edit_delete.png");
        DEFAULT_ICONS_MAPPINGS.put("查看", "media/images/icon_acts/icon_edit_export.png");
        DEFAULT_ICONS_MAPPINGS.put("摘要", "media/images/icon_acts/icon_edit_summary.png");
        DEFAULT_ICONS_MAPPINGS.put("舆情", "media/images/icon_acts/icon_edit_list.png");
        DEFAULT_ICONS_MAPPINGS.put("列表", "media/images/icon_acts/icon_edit_list.png");
        DEFAULT_ICONS_MAPPINGS.put("图表", "media/images/icon_acts/icon_edit_table.png");
        DEFAULT_ICONS_MAPPINGS.put("趋势", "media/images/icon_acts/icon_edit_trend.png");
    }

    private String id;
    private String caption;
    private String description;
    private String category;
    private String icon;


    public ActionCommand() {
    }

    public ActionCommand(String id, String caption, String description, String category) {
        super();
        this.id = id;
        this.caption = caption;
        this.description = description;
        this.category = category;
    }



    public ActionCommand(String id, String caption, String category) {
        super();
        this.id = id;
        this.caption = caption;
        this.category = category;
    }

    public ActionCommand(String id, String caption) {
        super();
        this.id = id;
        this.caption = caption;
    }
    public boolean isActionId(String actionId){
        if(this.id==null) return false;
        return this.id.equals(actionId);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCaption() {
        return caption;
    }
    public ActionCommand setCaption(String caption) {
        this.caption = caption;
        return this;
    }
    public String getDescription() {
        return description;
    }
    public ActionCommand setDescription(String description) {
        this.description = description;
        return this;
    }
    public String getCategory() {
        return category;
    }
    public ActionCommand setCategory(String category) {
        this.category = category;
        return this;
    }
    public String getIcon() {
        if(this.icon!=null) return this.icon;
        String dicon = DEFAULT_ICONS_MAPPINGS.get(this.caption);
        return dicon;
    }
    public ActionCommand setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ActionCommand){
            ActionCommand cmd = (ActionCommand) obj;
            return this.id.equals(cmd.getId());
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }



}