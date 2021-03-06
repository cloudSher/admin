package com.lastartupsaas.workbench.view.business.brand.supplier;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.domain.brand.BrandBusiness;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌商编辑页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = SupplierEditView.VIEW_NAME)
public class SupplierEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = 257577382163845423L;
	public static final String VIEW_NAME = "brand_business_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：品牌 > 品牌商 > 品牌商列表";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> fields = new ArrayList<FormField>();

		fields.add(new FormField("品牌商编号", "id", new LabelFieldEditor("系统自动生成", "100%"), false, null, true));
		fields.add(new FormField("品牌商名称", "enterpriseName", InputFieldEditor.class, true, null, true));
		fields.add(new FormField("品牌商企业规模(人)", "contactPhone", InputFieldEditor.class, true, null, true));
		fields.add(new FormField("品牌商地址", "enterpriseAddress", InputFieldEditor.class, true, null, false));
		fields.add(new FormField("工商执照注册号", "businessLicenseNo", InputFieldEditor.class, true, null, true));

		formAgent.addFieldListToMap("品牌商信息", fields);
	}

	@Override
	protected Object createVirginObject() {
		return new BrandBusiness();
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
			BrandBusiness brandBusiness = new BrandBusiness("100000001", "久久鸭", "100201612090011", "北京市朝阳区望京东路1号摩托罗拉大厦8层", "张三", "10000", "通过", "是",
					"2016-12-09 16:26:22");
			return brandBusiness;
		}
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return "brand_business_list.view";
	}
}
