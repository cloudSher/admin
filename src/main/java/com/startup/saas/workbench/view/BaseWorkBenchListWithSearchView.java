package com.startup.saas.workbench.view;

import com.startup.saas.workbench.widgets.FormActionButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

/**
 * Author: alvin
 * Date: 2016-08-30
 */
public abstract class BaseWorkBenchListWithSearchView extends BaseWorkbenchListView {

    private static final long serialVersionUID = 2728090134159112412L;

    protected void createComponentsBeforeGrid() {
        if(this.withFilterSection){
            HorizontalLayout layout = new HorizontalLayout();
            layout.setWidth("100%");
            layout.setSpacing(true);
            this.getViewRoot().addComponent(layout);
            this.createFilterForm(layout);
            layout.addComponent(new FormActionButton("搜索内容", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    doSearchAction();
                }
            }));
            layout.addComponent(new FormActionButton("重置搜索", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    doSearchCancelAction();
                }
            }));
        }
    }

    /**
     * 设置搜索表单
     * @param layout
     */
    protected abstract void createFilterForm(HorizontalLayout layout);

    /**
     * 清除搜索内容动作
     */
    protected abstract void doSearchCancelAction();

    /**
     * 执行搜索动作
     */
    protected abstract void doSearchAction();


}
