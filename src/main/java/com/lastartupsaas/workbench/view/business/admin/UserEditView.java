package com.lastartupsaas.workbench.view.business.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lastartupsaas.workbench.domain.admin.Role;
import com.lastartupsaas.workbench.domain.admin.User;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.SelectFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * Author: alvin
 * Date: 2016-08-26
 */
@SpringView(name = UserEditView.VIEW_NAME)
public class UserEditView extends BaseWorkBenchEditorView {

    public static final String VIEW_NAME = "user_edit.view";

    @Override
    protected String getObjectName(Object obj) {
        return "当前位置：系统管理 > 权限管理 > 管理员";
    }

    @Override
    protected void declareFormAgent(FormAgent formAgent) {
        formAgent.addField(new FormField("登录名", "name", InputFieldEditor.class, true, null, true).setInputDescr("3-20位字符，可由中文、英文及数字”组成"));
        formAgent.addField(new FormField("登录密码", "password", InputFieldEditor.class, true, null, true).setInputDescr("6-20位字符，可由英文、数字及标点符号组成"));
        formAgent.addField(new FormField("确认密码", "", InputFieldEditor.class, true, null, true).setInputDescr("请再次输入您的密码"));
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(1L, "系统管理员"));
        roleList.add(new Role(2L, "测试人员"));
        roleList.add(new Role(3L, "游客"));
        formAgent.addField(new FormField("用户角色", "roles", new SelectFieldEditor(roleList,"id","roleName"), true, null, true));
    }

    @Override
    protected Object createVirginObject() {
        return new User();
    }

    @Override
    protected boolean saveObject(Object data) {

        return true;
    }

    @Override
    protected boolean updateObject(Object data) {
        return true;
    }

    @Override
    protected Object loadEdittingDataFromContext(ViewContext vc) {
    	String id = vc.getParameter("id");
    	if (StringUtils.isNotBlank(id)) {
    		User user = new User(1L, "admin", "admin", "2016-12-07 14:30:23", "2016-12-07 14:30:45", "2016-12-07 14:31:15",new Role(1L, "超级管理员"));
    		return user;
		}
    	return null;
    }

    @Override
    protected String getReturnViewUrl() {
        return "user_list.view";
    }
}
