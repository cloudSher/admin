package com.lastartupsaas.workbench.view.business.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lastartupsaas.workbench.domain.admin.Resource;
import com.lastartupsaas.workbench.util.CaptchaUtil;
import com.lastartupsaas.workbench.util.MenuDataTest;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.CheckboxGroupEditor;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.RadioboxYesOrNoEditor;
import com.lastartupsaas.workbench.view.form.impl.SelectFieldGroupEditor;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.spring.annotation.SpringView;

/**
 * 资源编辑页
 * 
 * @author lifeilong
 * @date 2016-12-29
 */
@SpringView(name = ResourceEditView.VIEW_NAME)
public class ResourceEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = -796860637052680159L;
	private Logger logger = LoggerFactory.getLogger(CaptchaUtil.class);
	public static final String VIEW_NAME = "resource_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：系统管理 > 权限管理 > 资源";
	}

	@Override
	protected void declareFormAgent(final FormAgent formAgent) {
		logger.info("拼装资源页面");
		List<FormField> base_message = new ArrayList<FormField>();
		
		base_message.add(new FormField("资源名称", "name", InputFieldEditor.class, true, null, true));
		
		List<Resource> resources = MenuDataTest.getInstance().getMenuData();
		base_message.add(new FormField("所属模块", "resource", new SelectFieldGroupEditor(resources, "id", "name", "resourceList"), true, null, true));
		
		RadioboxYesOrNoEditor radioboxYesOrNoEditor = new RadioboxYesOrNoEditor("父节点", "子节点");
		Property.ValueChangeListener listener = new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if ("0".equals(event.getProperty().getValue())) {
					formAgent.setFormRowVisible(0, 3);
				} else {
					formAgent.setFormRowHide(0, 3);
				}
			}
		};
		radioboxYesOrNoEditor.addValueChangeListener(listener);
		base_message.add(new FormField("资源类型", "active", radioboxYesOrNoEditor, true, null, true));
		

		List<Resource> btns = new ArrayList<>();
		btns.add(new Resource(1L, "新增", false, false, null, null, null));
		btns.add(new Resource(2L, "编辑", false, false, null, null, null));
		btns.add(new Resource(3L, "删除", false, false, null, null, null));
		btns.add(new Resource(4L, "审核", false, false, null, null, null));
		base_message.add(new FormField("资源按钮", "btns", new CheckboxGroupEditor(btns, "id", "name"), true, null, true, false));

		base_message.add(new FormField("状态", "state", new RadioboxYesOrNoEditor("正常", "禁用"), true, null, true));
		
		formAgent.addFieldListToMap("资源信息", base_message);
	}

	@Override
	protected Object createVirginObject() {
		return new Resource();
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
			return new Resource(600101L, "资源", false, false, null, null, "user_list.view");
		}
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return "resource_list.view";
	}
}
