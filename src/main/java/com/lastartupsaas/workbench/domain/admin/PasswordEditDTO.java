package com.lastartupsaas.workbench.domain.admin;

import com.lastartupsaas.workbench.domain.BaseObject;

/**
 * 修改密码DTO
 * 
 * @author lifeilong
 * @date 2017-01-03
 */
public class PasswordEditDTO extends BaseObject {
	/** 旧密码 */
	private String oldPassword;
	/** 新密码 */
	private String newPassword;
	/** 确认新密码 */
	private String newPasswordConfirm;

	public PasswordEditDTO() {
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

	@Override
	public String toString() {
		return "PasswordEditDTO [oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", newPasswordConfirm=" + newPasswordConfirm + "]";
	}
}
