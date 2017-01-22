package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.DateFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.ImageUploadEditor;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌 > 品牌 > 品牌编辑页面 > 基本信息
 * 
 * @author lifeilong
 * @date: 2016-12-13
 */
@SpringView(name = BaseMessageEditView.VIEW_NAME)
public class BaseMessageEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = 4806669141679911421L;
	public static final String VIEW_NAME = "brand_base_message_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("品牌编号", "businessLicenseNo", new LabelFieldEditor("系统自动生成", "100%"), false, null, true));
		base_message.add(new FormField("品牌商编号", "contractNo", InputFieldEditor.class, false, null, true));
		base_message.add(new FormField("品牌名称", "firstParty", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("品牌LOGO", "firstParty", ImageUploadEditor.class, true, null, true));
		base_message.add(new FormField("所属分类", "secondParty", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("所属销售", "signTime", DateFieldEditor.class, true, null, true));
		base_message.add(new FormField("加盟城市", "cooperateEffeDate", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("加盟店数", "businessLicenseNo1", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("品牌标签", "taxNo", InputFieldEditor.class, true, null, true).setInputDescr("标签间使用分号隔开，最多添加2个标签，每个最多4个字符"));
		base_message.add(new FormField("直营店数", "legalPerson", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("年服务能力", "settlementPeriod", InputFieldEditor.class, true, null, true));
		base_message.add(new FormField("品牌宣传片", "businessLicenceImg", ImageUploadEditor.class, true, null, true));
		base_message.add(new FormField("品牌图集", "organizationCodeImg", ImageUploadEditor.class, true, null, true));
		formAgent.addFieldListToMap("基本信息", base_message);
	}

	@Override
	protected Object createVirginObject() {
		return null;
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
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return "brand_list.view";
	}
}
