package com.startup.saas.workbench.view.login;

import com.startup.saas.workbench.util.SessionUtil;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a password, and considers the user "admin" as the only
 * administrator.
 */
public class BasicAccessControl implements AccessControl {

	private static final long serialVersionUID = 2654394363430481103L;
	
	public static final String CURRENT_LOGIN_USER = "CURRENT_LOGIN_USER";

	@Override
	public boolean signIn(String username, String password) {
		if (username == null || username.isEmpty())
			return false;

		SessionUtil.setToSession(CURRENT_LOGIN_USER, username);
		return true;
	}

	@Override
	public boolean isUserSignedIn() {
		return SessionUtil.getFromSession(CURRENT_LOGIN_USER) != null;
	}

	@Override
	public boolean isUserInRole(String role) {
		if ("admin".equals(role)) {
			// Only the "admin" user is in the "admin" role
			return getPrincipalName().equals("admin");
		}
		// All users are in all non-admin roles
		return true;
	}

	@Override
	public String getPrincipalName() {
		return (String) SessionUtil.getFromSession(CURRENT_LOGIN_USER);
	}
}