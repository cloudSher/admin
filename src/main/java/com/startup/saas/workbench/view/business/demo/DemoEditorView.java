package com.startup.saas.workbench.view.business.demo;

import com.startup.saas.workbench.view.BaseWorkBenchEditorView;
import com.startup.saas.workbench.view.ViewContext;
import com.startup.saas.workbench.view.form.FormAgent;
import com.startup.saas.workbench.view.form.FormField;
import com.startup.saas.workbench.view.form.impl.InputFieldEditor;
import com.startup.saas.workbench.view.form.impl.UUIDFieldEditor;

/**
 * Author: alvin
 * Date: 2016-08-23
 */
//@EternityDeclare(value = "demoeditor")
public class DemoEditorView extends BaseWorkBenchEditorView {


    private static final long serialVersionUID = 4375747312085965803L;

    @Override
    protected String getObjectName(Object obj) {
        return "系列";
    }

    @Override
    protected void declareFormAgent(FormAgent formAgent) {
        formAgent.addField(new FormField("系列名称", "name", InputFieldEditor.class, true, null, true).setInputDescr("输入分类名称"));
        formAgent.addField(new FormField("系列ID", "code", UUIDFieldEditor.class, true, null, true).setWidth("300px"));
    }

    @Override
    protected Object createVirginObject() {
        return null;
    }

    @Override
    protected boolean saveObject(Object data) {
        return false;
    }

    @Override
    protected boolean updateObject(Object edittingData2) {
        return false;
    }

    @Override
    protected Object loadEdittingDataFromContext(ViewContext vc) {
        return null;
    }

    @Override
    protected String getReturnViewUrl() {
        return null;
    }
}
