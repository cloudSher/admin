package com.startup.saas.workbench.util;

import javax.servlet.http.Cookie;

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
//		cookie.setPath(getCurrentRequest().getContextPath());
		cookie.setPath("/");
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