package com.lastartupsaas.workbench.widgets;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;

/**
 * @author shixin
 *
 */
public class OrderByButton extends Button {

    private static final long serialVersionUID = -4884642290764396872L;
    private String fieldName;
    private Boolean aesc;

    public OrderByButton(String caption, String fieldName, Boolean aesc) {
        super(caption);
        this.setStyleName("m-button-orderby");
        this.fieldName = fieldName;
        this.aesc = aesc;
        this.updateStatus();
    }

    public void setStatus(Boolean aesc) {
        this.aesc = aesc;
        this.updateStatus();
    }

    public void toggleStatus(){
        if(this.aesc!=null){
            if(aesc){
                this.setStatus(null);
            }else{
                this.setStatus(true);
            }
        }else{
            this.setStatus(false);
        }
    }

    public Boolean getStatus(){
        return aesc;
    }

    public String getFieldName() {
        return fieldName;
    }

    private void updateStatus(){
        if(this.aesc!=null){
            if(this.aesc){
                this.setIcon(new ThemeResource("images/icon_arrow_up.png"));
            }else{
                this.setIcon(new ThemeResource("images/icon_arrow_down.png"));
            }
            this.addStyleName("m-link-focus");
        }else{
            this.setIcon(null);
            this.removeStyleName("m-link-focus");
        }
    }
}