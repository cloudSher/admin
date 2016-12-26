package com.lastartupsaas.workbench.widgets;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author shixin
 *
 */
public class LinkButton extends Button {

    private static final long serialVersionUID = -1782900856689034890L;

    public LinkButton() {
        super();
        setup();
    }

    public LinkButton(String caption, ClickListener listener) {
        super(caption, listener);
        setup();
    }

    public LinkButton(String caption) {
        super(caption);
        setup();
    }

    private void setup(){
        this.setIcon(FontAwesome.ADJUST);
        this.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
    }


}
