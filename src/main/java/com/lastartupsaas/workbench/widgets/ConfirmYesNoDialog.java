package com.lastartupsaas.workbench.widgets;

import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.util.ReflectTools;

import java.lang.reflect.Method;

/**
 * @author shixin
 *
 */
public class ConfirmYesNoDialog extends Window implements ClickListener {

    private static final long serialVersionUID = 5099844493889527587L;

    public ConfirmYesNoDialog(String caption, String text) {
        super(caption);
        this.center();
        this.setModal(true);
//		this.setDraggable(false);
//		this.setResizable(false);
        VerticalLayout vl = new VerticalLayout();
        vl.setWidth("450px");
        vl.setSpacing(true);
        vl.setMargin(true);
        this.setContent(vl);

        Label txt = new Label(text);
        vl.addComponent(txt);

        HorizontalLayout hl = new HorizontalLayout();
        hl.setWidth("100%");
        hl.setSpacing(true);
        vl.addComponent(hl);

        Button yesBtn = new Button("是");
        yesBtn.setData(true);
        yesBtn.setStyleName("m-button-normal");
        yesBtn.addClickListener(this);
        hl.addComponent(yesBtn);
        hl.setComponentAlignment(yesBtn, Alignment.TOP_RIGHT);

        Button noBtn = new Button("否");
        noBtn.setData(false);
        noBtn.setStyleName("m-button-normal");
        noBtn.addClickListener(this);
        hl.addComponent(noBtn);

    }

    @Override
    public void buttonClick(ClickEvent event) {
        this.close();
        boolean b = (Boolean) event.getButton().getData();
        this.fireEvent(new ConfirmEvent(event.getButton(), b));
    }

    public void addConfirmListener(ConfirmListener confirmListener){
        this.addListener(ConfirmEvent.class, confirmListener, ConfirmListener.CONFIRM_CLICK_METHOD);
    }

    public class ConfirmEvent extends Event {
        private boolean confirm;
        public ConfirmEvent(Component comp, boolean confirm) {
            super(comp);
            this.confirm = confirm;
        }
        public boolean isConfirm() {
            return confirm;
        }
    }

    public interface ConfirmListener {
        public static final Method CONFIRM_CLICK_METHOD = ReflectTools.findMethod(ConfirmListener.class, "confirmClick", ConfirmEvent.class);
        public void confirmClick(ConfirmEvent event);
    }



}