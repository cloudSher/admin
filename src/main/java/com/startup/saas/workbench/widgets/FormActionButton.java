package com.startup.saas.workbench.widgets;

import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author shixin
 *
 */
public class FormActionButton extends Button {

    private static final long serialVersionUID = -7046926362723007231L;

    public FormActionButton() {
        super();
        setup();
    }

    public FormActionButton(String caption, ClickListener listener) {
        super(caption, listener);
        setup();
    }

    public FormActionButton(String caption) {
        super(caption);
        setup();
    }

    private void setup(){
        this.addStyleName(ValoTheme.BUTTON_SMALL);
        this.addStyleName(ValoTheme.BUTTON_QUIET);
    }
}
