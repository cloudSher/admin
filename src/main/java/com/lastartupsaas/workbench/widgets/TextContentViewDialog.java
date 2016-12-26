package com.lastartupsaas.workbench.widgets;

import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * @author shixin
 *
 */
public class TextContentViewDialog extends Window {

    private static final long serialVersionUID = 4413189249293340550L;
    private String textContent;

    public TextContentViewDialog(String caption, String textContent) {
        super(caption);
        this.textContent = textContent;
        this.setModal(true);
        this.center();
        this.setWidth("750px");
        this.setHeight("500px");
        this.setupUI();
    }

    private void setupUI() {
        VerticalLayout vl = new VerticalLayout();
        vl.setSizeFull();
        vl.setMargin(true);
        this.setContent(vl);

        TextArea textField = new TextArea("文本内容:");
        textField.setSizeFull();
        vl.addComponent(textField);
        textField.setValue(textContent);
    }

    public void prettyMaximize() {
        int width = UI.getCurrent().getPage().getBrowserWindowWidth() - 40;
        int height = UI.getCurrent().getPage().getBrowserWindowHeight() - 40;
        this.setWidth(width + "px");
        this.setHeight(height+"px");
    }

}