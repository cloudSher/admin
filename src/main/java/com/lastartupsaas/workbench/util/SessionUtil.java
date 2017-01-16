package com.lastartupsaas.workbench.util;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;

/**
 * Session管理工具类
 * 
 * @author lifeilong
 */
public class SessionUtil {

	public static final Logger logger = LoggerFactory.getLogger(CaptchaUtil.class);

	/**
	 * 获取request
	 * 
	 * @return
	 */
	public static VaadinRequest getCurrentRequest() {
		VaadinRequest request = VaadinService.getCurrentRequest();
		if (request == null) {
			throw new IllegalStateException("No request bound to current thread");
		}
		return request;
	}

	/**
	 * 获取response
	 * 
	 * @return
	 */
	public static VaadinResponse getCurrentResponse() {
		VaadinResponse response = VaadinService.getCurrentResponse();
		if (response == null) {
			throw new IllegalStateException("No response bound to current thread");
		}
		return response;
	}

	/**
	 * 获取Session
	 * 
	 * @return
	 */
	public static WrappedSession getSession() {
		return getCurrentRequest().getWrappedSession();
	}

	/**
	 * 往Session中存值
	 * 
	 * @return
	 */
	public static void setToSession(String key, Object value) {
		logger.info("往Session中存值,key:{},value:{},SessionId:{}", key, value, getSession().getId());
		getSession().setAttribute(key, value);
	}

	/**
	 * 获取Session中的值
	 * 
	 * @return
	 */
	public static Object getFromSession(String key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 往Cookies中存值
	 * 
	 * @return
	 */
	public static void setToCookie(String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(1209600);
		if (StringUtils.isBlank(getCurrentRequest().getContextPath())) {
			cookie.setPath("/");
		} else {
			cookie.setPath(getCurrentRequest().getContextPath());
		}
		logger.info("往Cookies中存值,key:{},value:{},path:{}", key, value, cookie.getPath());
		getCurrentResponse().addCookie(cookie);
	}

	/**
	 * 获取Cookies中的值
	 * 
	 * @return
	 */
	public static String getFromCookie(String key) {
		VaadinRequest request = VaadinService.getCurrentRequest();
		if (request != null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (key.equals(cookie.getName())) {
						return cookie.getValue();
					}
				}
			}
		}
		return "";
	}
}