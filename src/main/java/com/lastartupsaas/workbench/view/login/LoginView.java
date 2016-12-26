package com.lastartupsaas.workbench.view.login;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.lastartupsaas.workbench.util.CaptchaUtil;
import com.lastartupsaas.workbench.util.SessionUtil;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * UI content when the user is not logged in yet.
 */
public class LoginView extends VerticalLayout {

	private LoginListener loginListener;
	private AccessControl accessControl;

	private TextField userName;
	private PasswordField password;
	private Button forgotPassword;
	private Button loginBtn;
	private Image captchaImg;
	private TextField captcha;
	
	private String ETERNIT_WORKBENCH_USERNAME = "ETERNIT_WORKBENCH_USERNAME";
	private String ETERNIT_WORKBENCH_PASSWORD = "ETERNIT_WORKBENCH_PASSWORD";
	private CheckBox remberPassword;

	// 构建登录页面
	public LoginView(AccessControl accessControl, LoginListener loginListener) {

		this.loginListener = loginListener;
		this.accessControl = accessControl;

		// 构建登录页
		buildLoginView();
		// 设置事件监听
		setListeners();

		userName.setValue(SessionUtil.getFromCookie(ETERNIT_WORKBENCH_USERNAME));
		password.setValue(SessionUtil.getFromCookie(ETERNIT_WORKBENCH_PASSWORD));
//		phone.setValue("18688888888");
//		password.setValue("18688888888");
		userName.focus();
	}

	/**
	 * 构建登录页
	 */
	private void buildLoginView() {
		
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setWidth("80%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		// 页面头部
		HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setSpacing(true);
		headerLayout.setWidth("100%");

		Image headerImg = new Image(null, new ThemeResource("img/logo.png"));// 头部logo
		Label headerTitle = new Label("|　运营管理后台");// 头部标题
		headerTitle.addStyleName(ValoTheme.LABEL_H2);

		headerLayout.addComponents(headerImg, headerTitle);
		headerLayout.setComponentAlignment(headerImg, Alignment.MIDDLE_LEFT);
		headerLayout.setComponentAlignment(headerTitle, Alignment.BOTTOM_LEFT);
		headerLayout.setExpandRatio(headerTitle, 1);

		// 页面中部
		HorizontalLayout centerLayout = new HorizontalLayout();
		centerLayout.setMargin(true);
		centerLayout.setWidth("100%");
		centerLayout.setHeight("500px");
		centerLayout.addStyleName(ValoTheme.LAYOUT_CARD);

		// 登录Form
		FormLayout loginForm = bulidLoginForm();
		// 中部图片
		Image centerImg = new Image(null, new ThemeResource("img/login/centerImg.jpg"));
		centerImg.setWidth("80%");
		centerImg.setHeight("80%");

		centerLayout.addComponents(loginForm, centerImg);
		centerLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
		centerLayout.setComponentAlignment(centerImg, Alignment.MIDDLE_LEFT);

		mainLayout.addComponents(headerLayout, centerLayout);
		addComponent(mainLayout);
		setComponentAlignment(mainLayout, Alignment.TOP_CENTER);
	}

	/**
	 * 构建登录FormLayout
	 */
	private FormLayout bulidLoginForm() {
		// 创建FormLayout
		FormLayout loginForm = new FormLayout();
		loginForm.setMargin(true);
		loginForm.setSpacing(true);
		loginForm.setWidth("50%");

		// 手机号输入框
		userName = new TextField("用户名：");
		userName.setWidth("100%");
		userName.setMaxLength(20);
		userName.setImmediate(true);
		userName.addValidator(new RegexpValidator("^[\u4e00-\u9fa5a-zA-Z0-9]{3,20}$", "用户名输入有误"));

		// 密码输入框
		password = new PasswordField("密　码：");
		password.setWidth("100%");
		password.setMaxLength(20);
		userName.setImmediate(true);
		password.addValidator(new RegexpValidator("^[a-zA-Z0-9~!@#$%^&*()-=_+\\|`,./<>?;':\"]{6,20}$", "密码输入有误"));

		// 验证码
		HorizontalLayout captchLayout = new HorizontalLayout();
		captchLayout.setCaption("验证码：");
		captchLayout.setWidth("100%");
		captchLayout.setSpacing(true);

		// 验证码输入框
		captcha = new TextField();
		captcha.setWidth("100%");
		captcha.setMaxLength(4);

		// 验证码图片
		captchaImg = new Image(null, CaptchaUtil.makeCaptchaImg());
		captchaImg.setWidth("100%");
		captchaImg.setHeight("37px");
		captchaImg.setAlternateText("点击刷新");
		captchaImg.setDescription("点击换一张");

		captchLayout.addComponents(captcha, captchaImg);
		captchLayout.setComponentAlignment(captcha, Alignment.MIDDLE_LEFT);
		captchLayout.setComponentAlignment(captchaImg, Alignment.MIDDLE_RIGHT);

		// 记住密码和忘记密码
		HorizontalLayout passwordBtns = new HorizontalLayout();
		passwordBtns.setWidth("100%");
		passwordBtns.setHeight("35px");
		passwordBtns.setSpacing(true);

		// 记住密码
		remberPassword = new CheckBox("记住登录密码");
		remberPassword.addStyleName(ValoTheme.LABEL_SMALL);

		// 忘记密码
		forgotPassword = new Button("忘记密码？");
		forgotPassword.addStyleName(ValoTheme.LABEL_SMALL);
		forgotPassword.addStyleName(ValoTheme.BUTTON_BORDERLESS);

		passwordBtns.addComponents(remberPassword, forgotPassword);
		passwordBtns.setComponentAlignment(remberPassword, Alignment.MIDDLE_LEFT);
		passwordBtns.setComponentAlignment(forgotPassword, Alignment.MIDDLE_RIGHT);

		// 登录按钮
		loginBtn = new Button("登 录");
		loginBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		loginBtn.setWidth("100%");

		loginForm.addComponents(userName, password, captchLayout, passwordBtns, loginBtn);

		return loginForm;
	}

	/**
	 * 设置事件
	 */
	private void setListeners() {

		userName.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				userName.setComponentError(null);
			}
		});
		password.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				password.setComponentError(null);
			}
		});
		captcha.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				captcha.setComponentError(null);
				if (!CaptchaUtil.validateCaptcha(captcha.getValue())) {
					captcha.setComponentError(new UserError("请输入正确的验证码"));
				}
			}
		});

		loginBtn.setDisableOnClick(true);
		loginBtn.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				try {
					login();
				} finally {
					loginBtn.setEnabled(true);
				}
			}
		});

		captchaImg.addClickListener(new ClickListener() {
			@Override
			public void click(ClickEvent event) {
				captchaImg.setSource(CaptchaUtil.makeCaptchaImg());
			}
		});

		forgotPassword.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				showNotification(new Notification("Hint: Try anything"));
			}
		});
	}

	/**
	 * 登陆操作
	 */
	private void login() {
		if (StringUtils.isBlank(userName.getValue())) {
			userName.setComponentError(new UserError("用户名不能为空"));
		}
		if (StringUtils.isBlank(password.getValue())) {
			password.setComponentError(new UserError("密码不能为空"));
		}
		if (StringUtils.isBlank(captcha.getValue()) || !CaptchaUtil.validateCaptcha(captcha.getValue())) {
			captcha.setComponentError(new UserError("请输入正确的验证码"));
		}
		if (userName.getErrorMessage() != null || password.getErrorMessage() != null || captcha.getErrorMessage() != null) {
			return;
		}
		if (accessControl.signIn(userName.getValue(), password.getValue())) {
			loginListener.loginSuccessful();

			if (remberPassword.getValue()) {
				SessionUtil.setToCookie(ETERNIT_WORKBENCH_USERNAME, userName.getValue());
				SessionUtil.setToCookie(ETERNIT_WORKBENCH_PASSWORD, password.getValue());
			}
		} else {
			showNotification(new Notification("登录失败", "您输入的帐号或密码有误", Notification.Type.WARNING_MESSAGE));
			userName.focus();
		}
	}

	/**
	 * 弹出框提示
	 * 
	 * @param notification
	 */
	private void showNotification(Notification notification) {
		// keep the notification visible a little while after moving the mouse, or until clicked
		notification.setDelayMsec(2000);
		notification.show(Page.getCurrent());
	}

	/**
	 * 登陆监听
	 */
	public interface LoginListener extends Serializable {
		void loginSuccessful();
	}
}
