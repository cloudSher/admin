package com.lastartupsaas.workbench.view.business.demo;


import com.lastartupsaas.workbench.view.BaseWorkbenchView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.widgets.ModalWindow;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Author: alvin
 * Date: 2016-08-19
 */
//@EternityDeclare(value = "test")
public class DemoCustomeView extends BaseWorkbenchView {

    private static final long serialVersionUID = -2108590661526048021L;
    private int clickCounter = 0;
    private Label clickCounterLabel;

    @Override
    protected void initView() {
        this.setViewCaption("测试页面");
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.addComponent(new Label("Hello World!"));
        layout.addComponent(new Label("Greetings from server."));
        layout.addComponent(new Label("I have "
                + Runtime.getRuntime().availableProcessors()
                + " processors and "
                + (Runtime.getRuntime().totalMemory() / 1000000)
                + " MB total memory."));

        Button button = new Button("Click Me");
        button.setStyleName("v-button-primary");
        button.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                clickCounter++;
                clickCounterLabel.setValue("Clicks: " + clickCounter);
                Notification.show("Thank you for clicking.");
            }
        });



        Button button1 = new Button("Show Dialog Modal");
        button1.setStyleName("v-button-primary");
        button1.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
//                GroupListView firstView = new GroupListView();
//                firstView.initView();
//                ModalWindow formWindow = new ModalWindow("弹出Modal",firstView);
//                UI.getCurrent().addWindow(formWindow);
            }
        });
        layout.addComponent(button1);
        layout.addComponent(button);
        layout.addComponent(clickCounterLabel = new Label("Clicks: 0"));
        layout.addComponent(new Label("test"));
        this.getViewRoot().addComponent(layout);
    }

    @Override
    protected void showView(ViewContext vc) {

    }


}
