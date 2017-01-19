package com.lastartupsaas.workbench.view.business.marketing.college;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.domain.admin.User;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.ImageUploadEditor;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 分类管理编辑页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = ClassifyEditView.VIEW_NAME)
public class ClassifyEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = -3699172478466125818L;
	public static final String VIEW_NAME = "classify_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：社区运营 > 创业学院 > 分类管理";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("分类名称", "name", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("分类图片", "image", ImageUploadEditor.class, true, null, true).setInputDescr("尺寸要求宽度为**像素，高度为**像素；支持格式gif,jpg,png"));
		base_message.add(new FormField("排序", "order", InputFieldEditor.class, true, null, true));

		formAgent.addFieldListToMap("分类信息", base_message);
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
		return "classify_list.view";
	}
}
