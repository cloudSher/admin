package com.startup.saas.workbench.view.business.demo;

import com.startup.saas.workbench.view.BaseWorkbenchView;
import com.startup.saas.workbench.view.ViewContext;
import com.startup.saas.workbench.widgets.MultiPages;

/**
 * Author: alvin
 * Date: 2016-08-24
 */
//@EternityDeclare(value = "multiPages")
public class DemoPagesView extends BaseWorkbenchView {


    private static final long serialVersionUID = 5836244647762368364L;

    @Override
    protected void initView() {

        MultiPages multiPages = new MultiPages();
        multiPages.setSizeFull();
//        DemoCustomeView demoCustomeView = new DemoCustomeView();
//        demoCustomeView.initView();
//        multiPages.addPage(demoCustomeView, demoCustomeView.getViewCaption());
//
//        DemoEditorView firstView = new DemoEditorView();
//        firstView.initView();
//        multiPages.addPage(firstView, firstView.getViewCaption());

        this.getViewRoot().addComponent(multiPages);
        this.getViewRoot().setExpandRatio(multiPages, 1);
    }

    @Override
    protected void showView(ViewContext vc) {

    }
}
