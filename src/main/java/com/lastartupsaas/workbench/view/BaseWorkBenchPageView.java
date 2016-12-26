package com.lastartupsaas.workbench.view;

import com.vaadin.ui.VerticalLayout;

/**
 * @author shixin
 *
 */
public abstract class BaseWorkBenchPageView extends VerticalLayout {

    private static final long serialVersionUID = -571717251330333330L;

    public BaseWorkBenchPageView() {
        this.setWidth("100%");
        this.setSpacing(true);
        this.setMargin(true);
        this.createUI();
    }

    protected void createUI() {
    }

    public abstract void loadDataObject(Object data);

    public abstract String validateData();

    public abstract void saveDataObject(Object data);

}
