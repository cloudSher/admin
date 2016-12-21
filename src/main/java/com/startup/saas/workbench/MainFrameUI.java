package com.startup.saas.workbench;

import org.springframework.beans.factory.annotation.Autowired;

import com.startup.saas.workbench.view.MainView;
import com.startup.saas.workbench.view.login.AccessControl;
import com.startup.saas.workbench.view.login.BasicAccessControl;
import com.startup.saas.workbench.view.login.LoginView;
import com.startup.saas.workbench.view.login.LoginView.LoginListener;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;

/**
 * Author: alvin Date: 2016-09-21
 */
@Theme("saas")
@Title("运营管理后台")
@SpringUI(path = "")
@SuppressWarnings("serial")
public class MainFrameUI extends UI {
	
	@Autowired
	private SpringViewProvider viewProvider;

	/** 登录验证 */
	private AccessControl accessControl = new BasicAccessControl();

	@Override
	protected void init(VaadinRequest request) {
		// 判断是否登录
//		if (!accessControl.isUserSignedIn()) {
//			setContent(new LoginView(accessControl, new LoginListener() {
//				@Override
//				public void loginSuccessful() {
//					showMainView();
//				}
//			}));
//		} else {
//			showMainView();
//		}
		showMainView();
	}

	private void showMainView() {
		setContent(new MainView(MainFrameUI.this, viewProvider));
	}
}
