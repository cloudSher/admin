package com.lastartupsaas.workbench.view.business.marketing.messagepush;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.domain.KeyValueObject;
import com.lastartupsaas.workbench.domain.admin.User;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.SelectFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 消息用户管理编辑页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = MessageUserEditView.VIEW_NAME)
public class MessageUserEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = -6553600325165000639L;
	public static final String VIEW_NAME = "message_user_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：社区运营 > 消息推送 > 消息用户管理";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		
		List<KeyValueObject> typeList = new ArrayList<>();
		typeList.add(new KeyValueObject("1", "版本1"));
		typeList.add(new KeyValueObject("2", "版本2"));
		base_message.add(new FormField("用户分类名称", "type", new SelectFieldEditor(typeList, "key", "value"), true, null, true).setInputDescr("选择版本"));
		base_message.add(new FormField("选择用户文件", "url", InputFieldEditor.class, true, null, true));
		
		formAgent.addFieldListToMap("消息用户信息", base_message);
	}

	@Override
	protected Object createVirginObject() {
		return new User();
	}

	@Override
	protected boolean saveObject(Object data) {

		return true;
	}

	@Override
	protected boolean updateObject(Object data) {
		return true;
	}

	@Override
	protected Object loadEdittingDataFromContext(ViewContext vc) {
		String id = vc.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
		}
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return "message_user_list.view";
	}
}
