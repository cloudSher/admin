package com.lastartupsaas.workbench.view;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.MainFrameUI;
import com.lastartupsaas.workbench.domain.admin.Resource;
import com.lastartupsaas.workbench.domain.admin.Role;
import com.lastartupsaas.workbench.util.MenuDataTest;
import com.lastartupsaas.workbench.view.menu.MenuBars;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 登录后的主页面
 */
public class MainView extends VerticalLayout {

	private Navigator navigator;

	public MainView(MainFrameUI mainFrameUI, ViewProvider provider) {

		// root布局,vertical
		this.setWidth("100%");
		this.setMargin(true);

		// header布局
		HorizontalLayout headPart = this.createHead();
		addComponent(headPart);

		// menu布局, horizontal
		HorizontalLayout menuPart = this.createMenu();
		addComponent(menuPart);

		// body布局
		VerticalLayout body = new VerticalLayout();
		body.setWidth("100%");
		// 组装布局
		addComponent(body);
		setExpandRatio(body, 1);

		// 初始化navigator
		navigator = new Navigator(mainFrameUI, body);
		navigator.addProvider(provider);
		navigator.addViewChangeListener(viewChangeListener);

		mainFrameUI.setNavigator(navigator);
		navigator.navigateTo("brand_business_list.view");
	}

	/**
	 * 创建header布局
	 * 
	 * @return
	 */
	private HorizontalLayout createHead() {
		HorizontalLayout headLayout = new HorizontalLayout();

		// 页面头部信息，包括登录用户信息和退出按钮
		headLayout.setSpacing(true);
		headLayout.setWidth("100%");

		Image headerImg = new Image(null, new ThemeResource("img/logo.png"));// 头部logo
		Label headerTitle = new Label("|　运营管理后台");// 头部标题
		headerTitle.addStyleName(ValoTheme.LABEL_H2);

		headLayout.addComponents(headerImg, headerTitle);
		headLayout.setComponentAlignment(headerImg, Alignment.MIDDLE_LEFT);
		headLayout.setComponentAlignment(headerTitle, Alignment.BOTTOM_LEFT);
		headLayout.setExpandRatio(headerTitle, 1);
		
		// logout menu item
		MenuBar logoutMenu = new MenuBar();
		logoutMenu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		logoutMenu.addItem("账号设置", FontAwesome.USER, new Command() {
			@Override
			public void menuSelected(com.vaadin.ui.MenuBar.MenuItem selectedItem) {
				Notification.show("账号设置", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
			}
		});
		logoutMenu.addItem("退出", FontAwesome.SIGN_OUT, new Command() {
			@Override
			public void menuSelected(com.vaadin.ui.MenuBar.MenuItem selectedItem) {
				VaadinSession.getCurrent().getSession().invalidate();
				Page.getCurrent().reload();
			}
		});
		headLayout.addComponent(logoutMenu);
		headLayout.setComponentAlignment(logoutMenu, Alignment.TOP_RIGHT);

		return headLayout;
	}

	/**
	 * 创建menu布局
	 * 
	 * @return
	 */
	private HorizontalLayout createMenu() {
		HorizontalLayout menuLayout = new HorizontalLayout();
		menuLayout.setWidth("100%");

//		User user = SystemInitialization.getDatastore().createQuery(User.class).filter("id", 1L).get();

		// 查询获取菜单数据
		List<Resource> resources = MenuDataTest.getInstance().getMenuData();
//		for (Role r : user.getRoles()) {
//			resources.addAll(r.getResources());
//		}

		// 组装菜单数据
		MenuBars menuBars = new MenuBars(ValoTheme.MENUBAR_BORDERLESS, resources, navigator);
		MenuBar menuBar = menuBars.getMenuBar();
		menuLayout.addComponent(menuBar);
		return menuLayout;
	}

	// notify the view menu about view changes so that it can display which view is currently active
	ViewChangeListener viewChangeListener = new ViewChangeListener() {
		@Override
		public boolean beforeViewChange(ViewChangeEvent event) {
			return true;
		}

		@Override
		public void afterViewChange(ViewChangeEvent event) {
			System.out.println("==================afterViewChange==================");
			System.out.println(event.getViewName());
			System.out.println(event.getParameters());
		}
	};
}
