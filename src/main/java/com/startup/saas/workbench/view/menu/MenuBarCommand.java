package com.startup.saas.workbench.view.menu;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;

/**
 * Author: alvin
 * Date: 2016-08-22
 */
public class MenuBarCommand implements MenuBar.Command {

    private String active;

    public MenuBarCommand(String active){
        this.active = active;
    }

    @Override
    public void menuSelected(MenuBar.MenuItem selectedItem) {
        UI.getCurrent().getNavigator().navigateTo(active);
    }
}
