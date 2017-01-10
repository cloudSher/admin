package com.lastartupsaas.workbench.view.business.community;

import java.util.ArrayList;
import java.util.List;

//import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.ImageUploadEditor;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.TextFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌商编辑页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = TopicEditView.VIEW_NAME)
public class TopicEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = -8666918143468897045L;
	Logger logger = LoggerFactory.getLogger(TopicListView.class);
    public static final String VIEW_NAME = "topic_edit.view";
    
//    @Autowired
//	private TopicsResource topicsResource;
    private String xLaAuthorization = "oauth2.0";
//	private XLaFormat xLaFormat = XLaFormat.json;
	private String xLaAppKey = "123456";
//	private XLaSignMethod xLaSignMethod = XLaSignMethod.MD5;
	private String sign = "C71F538BC1243D2903D3AB935949379B";

    @Override
    protected String getObjectName(Object obj) {
        return "当前位置：社区运营 > 话题 > 话题列表";
    }

    @Override
    protected void declareFormAgent(FormAgent formAgent) {
    	
    	List<FormField> base_message = new ArrayList<FormField>();
    	base_message.add(new FormField("话题ID", "id", new LabelFieldEditor("系统自动生成", "100%"), false, null, true));
    	base_message.add(new FormField("话题名称", "title", InputFieldEditor.class, true, null, true));
    	base_message.add(new FormField("话题描述", "desc", TextFieldEditor.class, true, null, true));
    	base_message.add(new FormField("话题图片", "image", ImageUploadEditor.class, true, null, true));
    	base_message.add(new FormField("是否有子标签", "tags", InputFieldEditor.class, true, null, true));
    	base_message.add(new FormField("品牌话题", "tags", InputFieldEditor.class, true, null, true));
    	base_message.add(new FormField("推荐首页", "status", InputFieldEditor.class, true, null, true));
    	base_message.add(new FormField("推荐热门", "status", InputFieldEditor.class, true, null, true));
        formAgent.addFieldListToMap("话题信息", base_message);
    }

    @Override
    protected Object createVirginObject() {
//        return new Topic();
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
    	String id = vc.getParameter("id");
    	if (StringUtils.isNotBlank(id)) {
    		try {
//    			Response response = topicsResource.getTopicsByTopicId(id, xLaAuthorization, xLaFormat, xLaAppKey, xLaSignMethod, sign);
//    			if (response.getStatus() == 200) {
//    				return response.readEntity(Topic.class);
//    			}
			} catch (Exception e) {
				logger.error("查询话题列表发生错误", e);
			}
		}
    	return null;
    }

    @Override
    protected String getReturnViewUrl() {
        return "topic_list.view";
    }
}
