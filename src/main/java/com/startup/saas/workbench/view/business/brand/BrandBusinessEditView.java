package com.startup.saas.workbench.view.business.brand;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.startup.saas.workbench.domain.admin.Role;
import com.startup.saas.workbench.domain.brand.BrandBusiness;
import com.startup.saas.workbench.view.BaseWorkBenchEditorView;
import com.startup.saas.workbench.view.ViewContext;
import com.startup.saas.workbench.view.datagrid.DataGridColumn;
import com.startup.saas.workbench.view.form.FormAgent;
import com.startup.saas.workbench.view.form.FormField;
import com.startup.saas.workbench.view.form.impl.InputFieldEditor;
import com.startup.saas.workbench.view.form.impl.LabelFieldEditor;
import com.startup.saas.workbench.view.form.impl.SelectFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌商编辑页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = BrandBusinessEditView.VIEW_NAME)
public class BrandBusinessEditView extends BaseWorkBenchEditorView {

    public static final String VIEW_NAME = "brand_business_edit.view";

    @Override
    protected String getObjectName(Object obj) {
        return "当前位置：品牌 > 品牌商 > 品牌商列表";
    }

    @Override
    protected void declareFormAgent(FormAgent formAgent) {
		formAgent.addField(new FormField("品牌商编号", "id", LabelFieldEditor.class, false, null, true));
        formAgent.addField(new FormField("企业名称", "enterpriseName", InputFieldEditor.class, true, null, true));
        formAgent.addField(new FormField("工商执照注册号", "businessLicenseNo", InputFieldEditor.class, true, null, true));
        formAgent.addField(new FormField("企业地址", "enterpriseAddress", InputFieldEditor.class, true, null, true));
        formAgent.addField(new FormField("姓名", "contact", InputFieldEditor.class, true, null, true));
        formAgent.addField(new FormField("手机号", "contactPhone", InputFieldEditor.class, true, null, true));
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
    		BrandBusiness brandBusiness = new BrandBusiness(1L, "久久鸭", "100201612090011", "北京市朝阳区望京东路1号摩托罗拉大厦8层", "张三", "18612345678", "通过", "是", "2016-12-09 16:26:22");
    		return brandBusiness;
		}
    	return null;
    }

    @Override
    protected String getReturnViewUrl() {
        return "brand_business_list.view";
    }
}
