package com.startup.saas.workbench.domain.admin;

//import org.mongodb.morphia.annotations.Embedded;
//import org.mongodb.morphia.annotations.Entity;
//import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.List;

import com.startup.saas.workbench.domain.BaseObject;

/**
 * Author: alvin
 * Date: 2016-08-19
 */
//@Entity
public class Resource extends BaseObject{

//    @Id
    private Long id;
    private String name;
    private boolean checkable = false;
    private boolean checked = false;
    private String icon;
    private String styleName;
    private String active;
//    @Embedded
    private List<Resource> resourceList;

    public Resource(){}

    public Resource(Long id, String name, boolean checkable, boolean checked, String icon, String styleName,
                    String active) {
        this.id = id;
        this.name = name;
        this.checkable = checkable;
        this.checked = checked;
        this.icon = icon;
        this.styleName = styleName;
        this.active = active;
        this.resourceList = new ArrayList<Resource>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheckable() {
        return checkable;
    }

    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void appenMenuItem(Resource resource){
        this.resourceList.add(resource);
    }
}
