package com.startup.saas.workbench.view.menu;

import java.util.List;

import com.startup.saas.workbench.domain.admin.Resource;
import com.vaadin.event.MouseEvents;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

/**
 * 菜单Bar
 * 只允许3级菜单
 * Author: alvin
 * Date: 2016-08-19
 */
public class MenuBars extends VerticalLayout implements View, MouseEvents.ClickListener {

    private MenuBar menuBar;
    private List<Resource> resources;
    private Navigator navigator;


    public MenuBars(String styleName, List<Resource> resources, Navigator navigator){
        this.setMargin(true);
        this.setSpacing(true);
        this.navigator = navigator;
        this.resources = resources;

        menuBar = new MenuBar();
        menuBar.setWidth("100%");
        menuBar.addStyleName(styleName);
    }

    public MenuBar getMenuBar() {
        for (Resource first : resources){
            MenuBar.MenuItem item = menuBar.addItem(first.getName(), (first.getActive()!= null)?new MenuBarCommand(first.getActive()):null);
            item.setCheckable(first.isCheckable());
            if (first.getResourceList()!=null){
                for (Resource second : first.getResourceList()){
                    if (second == null)
                        continue;
                    MenuBar.MenuItem childItem = item.addItem(second.getName(), (second.getActive()!= null)?new MenuBarCommand(second.getActive()):null);
                    childItem.setCheckable(second.isCheckable());
                    if (second.getResourceList()!=null) {
                        for (Resource third : second.getResourceList()) {
                            if (third == null)
                                continue;
                            MenuBar.MenuItem thirdItem = childItem.addItem(third.getName(),
                                    (third.getActive() != null) ? new MenuBarCommand(third.getActive()) : null);
                            thirdItem.setCheckable(third.isCheckable());
                        }
                    }
                }
            }
        }
        return menuBar;
    }

    public Navigator getNavigator(){
        return  this.navigator;
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    @Override
    public void click(MouseEvents.ClickEvent event) {
        System.out.println(event.getButtonName());
    }
}
