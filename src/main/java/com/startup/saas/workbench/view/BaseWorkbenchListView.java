package com.startup.saas.workbench.view;

import com.startup.saas.workbench.view.datagrid.DataGrid;
import com.startup.saas.workbench.view.datagrid.DataGridModel;
import com.startup.saas.workbench.view.datagrid.IActionCommandHandler;
import com.startup.saas.workbench.view.datagrid.IDataGridRowConverter;
import com.startup.saas.workbench.view.datagrid.IDataProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * Author: alvin
 * Date: 2016-08-22
 */
public abstract class BaseWorkbenchListView extends BaseWorkbenchView implements IDataProvider,
        IActionCommandHandler, IDataGridRowConverter {


    private static final long serialVersionUID = 2728090134159112412L;
    protected boolean withFilterSection;

    protected DataGridModel gridModel;

    protected DataGrid dataGrid;

    @Override
    public void initView() {
        this.createComponentsBeforeGrid();
        gridModel = new DataGridModel();
        gridModel.setCommandHandler(this);
        gridModel.setDataProvider(this);
        gridModel.setGridRowConverter(this);
        gridModel.setWithInfoSection(true);

        this.setupGridModel(this.gridModel);

        dataGrid = new DataGrid(gridModel);
        dataGrid.setSizeFull();
        this.getViewRoot().addComponent(dataGrid);
        this.getViewRoot().setExpandRatio(dataGrid, 1);
        dataGrid.reloadDatas();
    }

    protected abstract void setupGridModel(DataGridModel gridModel);


    protected void createComponentsBeforeGrid() {

    }


//    protected void createFilterForm(HorizontalLayout layout){
//
//
//    }
//
//    protected void doSearchCancelAction() {
//
//    }
//
//    protected void doSearchAction() {
//
//    }

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

}
