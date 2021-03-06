package com.lastartupsaas.workbench.view.business.community.setup;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.domain.admin.User;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 关键词屏蔽编辑页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = KeywordEditView.VIEW_NAME)
public class KeywordEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = -3699172478466125818L;
	public static final String VIEW_NAME = "keyword_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：社区运营 > 设置 > 关键词屏蔽";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("关键词ID", "id", new LabelFieldEditor("系统自动生成", "100%"), false, null, true));
		base_message.add(new FormField("关键词", "loginName", InputFieldEditor.class, true, null, true));

		formAgent.addFieldListToMap("关键词信息", base_message);
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
		return "keyword_list.view";
	}
}
