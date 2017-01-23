package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.view.BaseWorkBenchViewView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.ImageViewComponent;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌 > 品牌 > 品牌查看页面 > 基本信息
 * 
 * @author lifeilong
 * @date: 2016-12-13
 */
@SpringView(name = BaseMessageView.VIEW_NAME)
public class BaseMessageView extends BaseWorkBenchViewView {

	private static final long serialVersionUID = 811952702186803664L;
	public static final String VIEW_NAME = "brand_base_message.view";

	@Override
	protected String getObjectName(Object obj) {
		return "";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("品牌编号", "businessLicenseNo", new LabelFieldEditor("100000", "100%"), false, null, true));
		base_message.add(new FormField("品牌商编号", "contractNo", new LabelFieldEditor("100001", "100%"), false, null, true));
		base_message.add(new FormField("品牌名称", "firstParty", new LabelFieldEditor("黄焖鸡", "100%"), false, null, true));
		base_message.add(new FormField("品牌LOGO", "firstParty", ImageViewComponent.class, false, null, true));
		base_message.add(new FormField("所属分类", "secondParty", new LabelFieldEditor("餐饮", "100%"), false, null, true));
		base_message.add(new FormField("所属销售", "signTime", new LabelFieldEditor("北京分部", "100%"), false, null, true));
		base_message.add(new FormField("加盟城市", "cooperateEffeDate", new LabelFieldEditor("北京", "100%"), false, null, true));
		base_message.add(new FormField("加盟店数", "businessLicenseNo1", new LabelFieldEditor("200", "100%"), false, null, true));
		base_message.add(new FormField("品牌标签", "taxNo", new LabelFieldEditor("业界标杆", "100%"), false, null, true));
		base_message.add(new FormField("直营店数", "legalPerson", new LabelFieldEditor("2000", "100%"), false, null, true));
		base_message.add(new FormField("年服务能力", "settlementPeriod", new LabelFieldEditor("10,0000", "100%"), false, null, true));
		base_message.add(new FormField("品牌宣传片", "businessLicenceImg", ImageViewComponent.class, false, null, true));
		base_message.add(new FormField("品牌图集", "organizationCodeImg", ImageViewComponent.class, false, null, true));
		formAgent.addFieldListToMap("基本信息", base_message);
	}

	@Override
	protected Object loadFormDataByContext(ViewContext vc) {
		String id = vc.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
		}
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return null;
	}
}
