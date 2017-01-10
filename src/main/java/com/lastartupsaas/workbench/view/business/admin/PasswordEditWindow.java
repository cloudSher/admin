package com.lastartupsaas.workbench.view.business.admin;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.domain.admin.PasswordEditDTO;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.PasswordFieldEditor;
import com.lastartupsaas.workbench.widgets.FormWindow;

public class PasswordEditWindow extends FormWindow {

	private static final long serialVersionUID = -8075369615789073010L;

	public PasswordEditWindow(String caption) {
		super(caption);
	}

	@Override
	protected boolean doDoneActione(FormAgent formAgent) {
		PasswordEditDTO dto = new PasswordEditDTO();
		formAgent.loadFormDataToBean(dto);
		
		boolean ret = this.saveObject(dto);
        if(ret){
        	this.showNotification("操作成功", "密码修改已成功");
        	return true;
        }else{
            this.showNotification("操作失败", "密码修改失败, 请检查数据是否符合要求, 然后再试");
            return false;
        }
	}

	@Override
	protected void setupFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("旧密码", "oldPassword", PasswordFieldEditor.class, true, null, true).setInputDescr("请输入当前密码"));
		base_message.add(new FormField("新密码", "newPassword", PasswordFieldEditor.class, true, null, true).setInputDescr("6-20位字符，可由英文、数字及标点符号组成"));
		base_message.add(new FormField("确认新密码", "newPasswordConfirm", PasswordFieldEditor.class, true, null, true).setInputDescr("请再次输入您的密码"));
		
		formAgent.addFieldListToMap("密码修改", base_message);
	}
	
	protected boolean saveObject(Object data) {
		return false;
	}
}
