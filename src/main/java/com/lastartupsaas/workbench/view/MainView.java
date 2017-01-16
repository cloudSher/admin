package com.lastartupsaas.workbench.view;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.lastartupsaas.workbench.MainFrameUI;
import com.lastartupsaas.workbench.domain.admin.Resource;
import com.lastartupsaas.workbench.util.MenuDataTest;
import com.lastartupsaas.workbench.view.business.admin.PasswordEditWindow;
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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 登录后的主页面
 */
public class MainView extends VerticalLayout {

	private static final long serialVersionUID = -1064859646213580974L;
	private Navigator navigator;

	public MainView(MainFrameUI mainFrameUI, ViewProvider provider) {

		// root布局,vertical
		this.setSizeFull();
		this.setMargin(true);

		// header布局
		HorizontalLayout headPart = this.createHead();
		addComponent(headPart);

		// menu布局, horizontal
		HorizontalLayout menuPart = this.createMenu();
		addComponent(menuPart);

		// body布局
		VerticalLayout body = new VerticalLayout();
		body.setSizeFull();
		// 组装布局
		addComponent(body);
		setExpandRatio(body, 1);

		// 初始化navigator
		navigator = new Navigator(mainFrameUI, body);
		navigator.addProvider(provider);
		navigator.addViewChangeListener(viewChangeListener);

		mainFrameUI.setNavigator(navigator);
		navigator.navigateTo("brand_business_list.view");
		navigator.setErrorView(ErrorView.class);
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
		MenuBar.MenuItem account = logoutMenu.addItem("admin", FontAwesome.USER, null);
		account.addItem("个人信息修改", FontAwesome.WRENCH, new Command() {
			@Override
			public void menuSelected(com.vaadin.ui.MenuBar.MenuItem selectedItem) {
				Notification.show("账号设置", "功能正在建设中。。。", Notification.Type.HUMANIZED_MESSAGE);
			}
		});
		account.addItem("密码修改", FontAwesome.LOCK, new Command() {
			@Override
			public void menuSelected(com.vaadin.ui.MenuBar.MenuItem selectedItem) {
				PasswordEditWindow formWindow = new PasswordEditWindow("");
				UI.getCurrent().addWindow(formWindow);
			}
		});
		logoutMenu.addItem("退出", FontAwesome.SIGN_OUT, new Command() {
			@Override
			public void menuSelected(com.vaadin.ui.MenuBar.MenuItem selectedItem) {
				
				SecurityUtils.getSubject().logout();
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

		// User user = SystemInitialization.getDatastore().createQuery(User.class).filter("id", 1L).get();

		// 查询获取菜单数据
		List<Resource> resources = MenuDataTest.getInstance().getMenuData();
		// for (Role r : user.getRoles()) {
		// resources.addAll(r.getResources());
		// }

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
			System.out.println("==================beforeViewChange==================");
			System.out.println(event.getViewName());
			System.out.println(event.getParameters());
			
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser.isPermitted(event.getViewName())) {
				return true;
			} else {
				UI.getCurrent().getNavigator().navigateTo("error");
				return false;
			}
		}

		@Override
		public void afterViewChange(ViewChangeEvent event) {
		}
	};
}
