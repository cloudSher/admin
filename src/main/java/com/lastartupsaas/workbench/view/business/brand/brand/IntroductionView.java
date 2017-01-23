package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.view.BaseWorkBenchViewView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌 > 品牌 > 品牌查看页面 > 基本信息
 * 
 * @author lifeilong
 * @date: 2016-12-13
 */
@SpringView(name = IntroductionView.VIEW_NAME)
public class IntroductionView extends BaseWorkBenchViewView {

	private static final long serialVersionUID = 6566775857356604856L;
	public static final String VIEW_NAME = "introduction.view";

	@Override
	protected String getObjectName(Object obj) {
		return "";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		String str = "&nbsp;品牌介 绍<font face='Arial'>品牌介绍 &nbsp;&nbsp;</font>品牌介<b>绍品牌介绍</b><i>品牌介绍品</i>牌介<div>绍品</div><div><br></div><div>&nbsp; &nbsp;牌介绍<u>品牌介绍</u>品<span style='background-color: red;'>牌介绍品牌</span>介绍<font color='#008000'>品牌介绍品牌</font>介绍品牌介绍</div>"	;
		String str1 = "&nbsp;产品介绍<font face='Arial'>产品介绍 &nbsp;&nbsp;</font>产品介<b>绍产品介绍</b><i>产品介绍品</i>牌介<div>绍品</div><div><br></div><div>&nbsp; &nbsp;牌介绍<u>产品介绍</u>品<span style='background-color: red;'>牌介绍产品</span>介绍<font color='#008000'>产品介绍产品</font>介绍产品介绍</div>"	;
		String str2 = "&nbsp;商机亮点介 绍<font face='Arial'>商机亮点介绍 &nbsp;&nbsp;</font>商机亮点介<b>绍商机亮点介绍</b><i>商机亮点介绍品</i>牌介<div>绍品</div><div><br></div><div>&nbsp; &nbsp;牌介绍<u>商机亮点介绍</u>品<span style='background-color: red;'>牌介绍商机亮点</span>介绍<font color='#008000'>商机亮点介绍商机亮点</font>介绍商机亮点介绍</div>"	;
		base_message.add(new FormField("品牌介绍", "firstParty", new LabelFieldEditor(str, "100%"), false, null, true));
		base_message.add(new FormField("产品介绍", "contractNo", new LabelFieldEditor(str1, "100%"), false, null, true));
		base_message.add(new FormField("商机亮点", "businessLicenseNo", new LabelFieldEditor(str2, "100%"), false, null, true));
		formAgent.addFieldListToMap("品牌介绍", base_message);
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
