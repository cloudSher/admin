package com.lastartupsaas.workbench.view;

import com.lastartupsaas.workbench.view.datagrid.DataGridModel;
import com.lastartupsaas.workbench.view.datagrid.DataGridTreeTable;
import com.lastartupsaas.workbench.view.datagrid.IActionCommandHandler;
import com.lastartupsaas.workbench.view.datagrid.IDataGridRowConverter;
import com.lastartupsaas.workbench.view.datagrid.IDataProvider;
import com.lastartupsaas.workbench.widgets.FormActionButton;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * Author: alvin
 * Date: 2016-08-22
 */
public abstract class BaseWorkbenchTreeListView extends BaseWorkbenchView implements IDataProvider,
        IActionCommandHandler, IDataGridRowConverter {

    protected boolean withFilterSection;

    protected DataGridModel gridModel;

    protected DataGridTreeTable dataGrid;

    @Override
    public void initView() {
        this.createComponentsBeforeGrid();
        gridModel = new DataGridModel();
        gridModel.setCommandHandler(this);
        gridModel.setDataProvider(this);
        gridModel.setGridRowConverter(this);
        gridModel.setWithInfoSection(true);

        this.setupGridModel(this.gridModel);

        dataGrid = new DataGridTreeTable(gridModel);
        dataGrid.setSizeFull();
        this.getViewRoot().addComponent(dataGrid);
        this.getViewRoot().setExpandRatio(dataGrid, 1);
        dataGrid.reloadDatas();
    }

    protected abstract void setupGridModel(DataGridModel gridModel);

    protected Label addLabel(String caption, HorizontalLayout layout){
        Label lb = new Label(caption);
        //lb.setWidth("60px");
        lb.setSizeUndefined();
        layout.addComponent(lb);
        layout.setComponentAlignment(lb, Alignment.MIDDLE_CENTER);
        return lb;
    }

    @Override
    protected void showView(ViewContext vc) {
//        this.dataGrid.reloadDatas();
    }
    
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
