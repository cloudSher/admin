package com.lastartupsaas.workbench.view.business.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.domain.admin.Role;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * KIP数据项编辑页
 * 
 * @author lifeilong
 * @date 2016-12-29
 */
@SpringView(name = KpiTargetEditView.VIEW_NAME)
public class KpiTargetEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = 5136178009412812213L;
	public static final String VIEW_NAME = "kpi_config_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：系统管理 > KIP管理 > KIP数据项";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("选择模块", "roleName", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("操作", "roleName1", InputFieldEditor.class, true, null, true));
		
		formAgent.addFieldListToMap("配置信息", base_message);
	}

	@Override
	protected Object createVirginObject() {
		return new Role();
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
		return "kpi_config_list.view";
	}
}
