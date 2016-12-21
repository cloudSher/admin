package com.startup.saas.workbench.widgets;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SubPanel extends VerticalLayout {

    private static final long serialVersionUID = 6982560967977527629L;
    private Component content;
    private Label headCaption;
    private HorizontalLayout head;

    public SubPanel() {
        this("Title");
    }

    public SubPanel(String caption) {
        this.setSizeUndefined();
        this.setWidth("100%");
        this.setStyleName("SubPanel");
//		HorizontalLayout head = new HorizontalLayout();
//		head.setWidth("100%");
//		head.setHeight("30px");
//		head.setStyleName("SubPanel-Head");
//		this.addComponent(head);

        headCaption = new Label(caption);
        headCaption.setWidth("100%");
//        headCaption.setHeight("30px");
        headCaption.setStyleName("SubPanel-HeadCaption");
        this.addComponent(headCaption);
        //head.setComponentAlignment(headCaption, Alignment.MIDDLE_LEFT);

//		panel = new Panel();
//		panel.setSizeFull();
//		this.addComponent(panel);
//		this.setExpandRatio(panel, 1);
    }


//	public void addActionComponent(Component component) {
//		if(this.head==null){
//			this.head = new HorizontalLayout();
//
//		}
//	}

    public void setPanelCaption(String caption){
        this.headCaption.setValue(caption);
    }

    public String getPanelCaption() {
        return this.headCaption.getValue();
    }

    public void setContent(Component component) {
        if(this.content!=null){
            this.removeComponent(content);
        }
        this.addComponent(component);
        this.setExpandRatio(component, 1);
        this.content = component;
    }

}
