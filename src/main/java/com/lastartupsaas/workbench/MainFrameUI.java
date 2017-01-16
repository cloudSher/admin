package com.lastartupsaas.workbench;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lastartupsaas.workbench.view.LoginView;
import com.lastartupsaas.workbench.view.MainView;
import com.lastartupsaas.workbench.view.LoginView.LoginListener;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;

/**
 * 主界面
 * 
 * @author lifeilong
 * @date 2016-12-12
 */
@Theme("saas")
@Title("运营管理后台")
@SpringUI(path = "")
@SuppressWarnings("serial")
public class MainFrameUI extends UI {

	@Autowired
	private SpringViewProvider viewProvider;

	@Override
	protected void init(VaadinRequest request) {
		// 判断是否登录
		if (SecurityUtils.getSubject().isAuthenticated()) {
			showMainView();
        } else {
        	setContent(new LoginView(new LoginListener() {
				@Override
				public void loginSuccessful() {
					showMainView();
				}
			}));
        }
	}

	private void showMainView() {
		setContent(new MainView(MainFrameUI.this, viewProvider));
	}
}
