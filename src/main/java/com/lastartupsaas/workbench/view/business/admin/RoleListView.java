package com.lastartupsaas.workbench.view.business.admin;


import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.domain.admin.Role;
import com.lastartupsaas.workbench.view.BaseWorkBenchListWithSearchView;
import com.lastartupsaas.workbench.view.datagrid.ActionCommand;
import com.lastartupsaas.workbench.view.datagrid.DataGridColumn;
import com.lastartupsaas.workbench.view.datagrid.DataGridModel;
import com.lastartupsaas.workbench.view.datagrid.DataGridRow;
import com.lastartupsaas.workbench.view.datagrid.DataListRequest;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormBuildLayout;
import com.lastartupsaas.workbench.view.form.FormDataHelper;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

/**
 * Author: alvin
 * Date: 2016-08-30
 */
@SpringView(name = RoleListView.VIEW_NAME)
public class RoleListView extends BaseWorkBenchListWithSearchView {

    public static final String VIEW_NAME = "role_list.view";
    private FormAgent searchAgent;

    private String searchName;


    public RoleListView(){
        this.setViewCaption("当前位置：系统管理 > 权限管理 > 权限组");
        this.withFilterSection = false;
    }
    @Override
    public void performAction(ActionCommand command, Object... parameters) {
        if(command.isActionId("create")){
            this.navigateToView("role_edit.view");
        }
        if(command.isActionId("edit")){
            this.navigateToView("role_edit.view/id="+parameters[0]);
        }
        if(command.isActionId("del")){
            System.out.println("del");
        }
    }

    @Override
    protected void createFilterForm(HorizontalLayout layout) {
//        searchAgent = new FormAgent();
//        searchAgent.setDataHelper(new FormDataHelper());
//        searchAgent.setSearchMode(true);
//        searchAgent.setFieldColumnCount(2);
//        searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
//        searchAgent.addField(new FormField("权限组", "roleName", InputFieldEditor.class, false, null, false).setInputDescr("输入要搜索的权限组"));
//
//        FormBuildLayout form = searchAgent.buildForm();
//        form.setWidth("100%");
//        form.setSpacing(true);
//
//        layout.addComponent(form);
//        layout.setExpandRatio(form, 1);
    }

    @Override
    protected void doSearchCancelAction() {
//        this.searchAgent.resetFormData();
//        this.doSearchAction();
    }

    @Override
    protected void doSearchAction() {
//        this.searchName = (String)this.searchAgent.getFieldValue("name");
//        this.dataGrid.reloadDatas();
    }

    @Override
    public DataGridRow convertRowData(Object item) {
        Role role = (Role)item;
        return new DataGridRow(role.getId(), new Object[]{
                role.getId(),
                role.getRoleName()
        });
    }

    @Override
    public int getDataCount() {
        return 1;
    }

    @Override
    public List<?> getDataList(DataListRequest request) {
        List<Role> roles = new ArrayList<>();
        Role role = new Role(1L, "超级管理员");
        roles.add(role);
        return roles;
    }

    @Override
    protected void setupGridModel(DataGridModel gridModel) {
        gridModel.addColumn(new DataGridColumn("ID", Long.class));
        gridModel.addColumn(new DataGridColumn("权限组", String.class));

        gridModel.addCommonAction(new ActionCommand("create", "新增权限组"));
        gridModel.addItemAction(new ActionCommand("del", "删除"));
        gridModel.addItemAction(new ActionCommand("edit", "编辑"));
    }
}