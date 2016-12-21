package com.startup.saas.workbench.widgets;

import com.startup.saas.workbench.view.form.FormAgent;
import com.startup.saas.workbench.view.form.FormBuildLayout;
import com.startup.saas.workbench.view.form.FormDataHelper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * @author shixin
 *
 */
public abstract class FormWindow extends Window {

    private static final long serialVersionUID = 1190213018808378729L;
    private FormBuildLayout form;

    public FormWindow(String caption) {
        super(caption);

        this.setModal(true);
        this.center();
        this.setWidth("750px");

        this.setup();
    }

    private void setup() {

        final FormAgent fa = new FormAgent();
        fa.setDataHelper(new FormDataHelper());
        this.setupFormAgent(fa);

        form = fa.buildForm();
        form.setMargin(true);
        form.setSpacing(true);
        form.setWidth("100%");

        this.setupFormData(fa);

        form.addActionComponent(new FormActionButton("确定", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if(!fa.validateForm()){
                    Notification.show("数据填写不完整!", "请检查字段右侧的提示信息.", Notification.Type.WARNING_MESSAGE);
                    return;
                }
                boolean ret = doDoneActione(fa);
                if(ret) {
                    FormWindow.this.close();
                }
                postDoneAction(fa, ret);
            }
        }));
        form.addActionComponent(new FormActionButton("取消", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                FormWindow.this.close();
            }
        }));

        this.setContent(form);

    }


    public void prettyMaximize() {
        int width = UI.getCurrent().getPage().getBrowserWindowWidth() - 40;
        int height = UI.getCurrent().getPage().getBrowserWindowHeight() - 40;
        this.setWidth(width + "px");
        this.setHeight(height+"px");
    }

    protected FormBuildLayout getForm(){
        return this.form;
    }

    protected abstract boolean doDoneActione(FormAgent fa);

    protected void postDoneAction(FormAgent fa, boolean success) {
        //NOOP
    }

    protected abstract void setupFormAgent(FormAgent fa);

    protected void setupFormData(FormAgent fa) {

    }


}