package com.startup.saas.workbench.view.business.community;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.startup.saas.api.model.Topic;
import com.startup.saas.api.model.Topics;
import com.startup.saas.api.resource.TopicsResource;
import com.startup.saas.api.resource.TopicsResource.XLaFormat;
import com.startup.saas.api.resource.TopicsResource.XLaSignMethod;
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
@SpringView(name = TopicEditView.VIEW_NAME)
public class TopicEditView extends BaseWorkBenchEditorView {

    public static final String VIEW_NAME = "topic_edit.view";
    
    @Autowired
	private TopicsResource topicsResource;
    private String xLaAuthorization = "oauth2.0";
	private XLaFormat xLaFormat = XLaFormat.json;
	private String xLaAppKey = "123456";
	private XLaSignMethod xLaSignMethod = XLaSignMethod.MD5;
	private String sign = "C71F538BC1243D2903D3AB935949379B";

    @Override
    protected String getObjectName(Object obj) {
        return "当前位置：社区运营 > 话题 > 话题列表";
    }

    @Override
    protected void declareFormAgent(FormAgent formAgent) {
		formAgent.addField(new FormField("话题ID", "id", LabelFieldEditor.class, false, null, true));
        formAgent.addField(new FormField("话题名称", "enterpriseName", InputFieldEditor.class, true, null, true));
        formAgent.addField(new FormField("话题描述", "enterpriseAddress", InputFieldEditor.class, true, null, true));
        formAgent.addField(new FormField("话题图片", "businessLicenseNo", InputFieldEditor.class, true, null, true));
        formAgent.addField(new FormField("是否有子标签", "businessLicenseNo", InputFieldEditor.class, true, null, true));
        formAgent.addField(new FormField("品牌话题", "businessLicenseNo", InputFieldEditor.class, true, null, true));
        formAgent.addField(new FormField("推荐首页", "businessLicenseNo", InputFieldEditor.class, true, null, true));
        formAgent.addField(new FormField("推荐热门", "businessLicenseNo", InputFieldEditor.class, true, null, true));
    }

    @Override
    protected Object createVirginObject() {
        return new Topic();
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
    	Response response = topicsResource.getTopicsByTopicId(id, xLaAuthorization, xLaFormat, xLaAppKey, xLaSignMethod, sign);
		if (response.getStatus() == 200) {
			return response.readEntity(Topic.class);
		}
    	return null;
    }

    @Override
    protected String getReturnViewUrl() {
        return "brand_business_list.view";
    }
}
