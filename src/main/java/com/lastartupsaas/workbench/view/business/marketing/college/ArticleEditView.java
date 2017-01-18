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
import com.lastartupsaas.workbench.view.form.impl.RadioboxYesOrNoEditor;
import com.lastartupsaas.workbench.view.form.impl.TextFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 文章管理编辑页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = ArticleEditView.VIEW_NAME)
public class ArticleEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = 1532887540438627109L;
	public static final String VIEW_NAME = "article_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：社区运营 > 创业学院 > 文章管理";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("文章标题", "name", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("所属分类", "image", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("列表图片", "image", ImageUploadEditor.class, true, null, true).setInputDescr("尺寸要求宽度为***像素，高度为***像素；支持格式gif,jpg,png"));
		base_message.add(new FormField("是否显示", "order", RadioboxYesOrNoEditor.class, true, null, true));
		base_message.add(new FormField("视频内容", "order", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("内容", "order", TextFieldEditor.class, true, null, true));

		formAgent.addFieldListToMap("文章信息", base_message);
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
		return "article_list.view";
	}
}
