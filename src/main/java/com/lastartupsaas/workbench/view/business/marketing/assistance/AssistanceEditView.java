package com.lastartupsaas.workbench.view.business.marketing.assistance;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.domain.admin.User;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 助力管理编辑页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = AssistanceEditView.VIEW_NAME)
public class AssistanceEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = -8132068792327940859L;
	public static final String VIEW_NAME = "assistance_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：社区运营 > 创业助力 > 助力管理";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("选择品类", "name", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("能力值", "image", InputFieldEditor.class, true, null, true).setInputDescr("能力值的范围为0~100之间，且后面的数值必须大于前面的数值"));
		base_message.add(new FormField("助力金", "assistance", InputFieldEditor.class, true, null, true).setInputDescr("助力金额为大于0的整数"));
		base_message.add(new FormField("有效期", "validity", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("库存", "stock", InputFieldEditor.class, true, null, true).setInputDescr("可发放的最大数量"));

		formAgent.addFieldListToMap("助力信息", base_message);
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
		return "assistance_list.view";
	}
}
