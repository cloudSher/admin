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
import com.lastartupsaas.workbench.view.form.impl.CheckboxMultilevelGroupEditor;
import com.lastartupsaas.workbench.view.form.impl.DateFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.RadioboxYesOrNoEditor;
import com.lastartupsaas.workbench.view.form.impl.SelectFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.TextFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * push管理编辑页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = MessagePushEditView.VIEW_NAME)
public class MessagePushEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = 988625965892133622L;
	public static final String VIEW_NAME = "message_push_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：社区运营 > 消息推送 > push管理";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		
		List<KeyValueObject> typeList = new ArrayList<>();
		typeList.add(new KeyValueObject("1", "消息"));
		typeList.add(new KeyValueObject("2", "品牌"));
		typeList.add(new KeyValueObject("3", "专题"));
		base_message.add(new FormField("推送类型", "type", new SelectFieldEditor(typeList, "key", "value", "1", "100%"), true, null, true));
		base_message.add(new FormField("推送链接", "url", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("参数ID", "param_id", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("推送标题", "title", InputFieldEditor.class, true, null, true));
		
		List<KeyValueObject> platformList = new ArrayList<>();
		platformList.add(new KeyValueObject("1", "IOS"));
		platformList.add(new KeyValueObject("2", "Android"));
		base_message.add(new FormField("平台", "platform",new CheckboxMultilevelGroupEditor(platformList, "key", "value"), true, null, true));
		base_message.add(new FormField("IOS推送内容", "ios_content", TextFieldEditor.class, true, null, true));
		base_message.add(new FormField("Android推送内容", "android_content", TextFieldEditor.class, true, null, true));
		base_message.add(new FormField("客户端版本", "version", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("推送时间", "send_time", DateFieldEditor.class, true, null, true));
		base_message.add(new FormField("发送分类", "send_type", new RadioboxYesOrNoEditor("个人用户", "分类用户"), true, null, true));
		base_message.add(new FormField("用户ID", "user_id", InputFieldEditor.class, true, null, true));

		formAgent.addFieldListToMap("推送信息", base_message);
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
		return "message_push_list.view";
	}
}
