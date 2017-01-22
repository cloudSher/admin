package com.lastartupsaas.workbench.view.business.community.dynamic;

import java.util.ArrayList;
import java.util.List;

//import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.domain.KeyValueObject;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.RichTextAreaEditor;
import com.lastartupsaas.workbench.view.form.impl.SelectFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 动态管理编辑页面
 * 
 * @author lifeilong
 * @date: 2016-12-09
 */
@SpringView(name = DynamicEditView.VIEW_NAME)
public class DynamicEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = -2043356237409270589L;
	public static final String VIEW_NAME = "dynamic_edit.view";
    
    @Override
    protected String getObjectName(Object obj) {
        return "当前位置：社区运营 > 话题 > 话题列表";
    }

    @Override
    protected void declareFormAgent(FormAgent formAgent) {
    	
    	List<FormField> base_message = new ArrayList<FormField>();
    	base_message.add(new FormField("动态ID", "id", new LabelFieldEditor("系统自动生成", "100%"), false, null, true));
    	base_message.add(new FormField("动态标题", "title", InputFieldEditor.class, true, null, true).setInputDescr("话题限制20字符以内，非必填项"));
    	
    	List<KeyValueObject> list = new ArrayList<>();
		list.add(new KeyValueObject("1", "会员1"));
		list.add(new KeyValueObject("2", "会员2"));
		list.add(new KeyValueObject("3", "会员3"));
		base_message.add(new FormField("系统会员", "btns", new SelectFieldEditor(list, "key", "value", "30%"), true, null, true));
		
		List<KeyValueObject> list1 = new ArrayList<>();
		list1.add(new KeyValueObject("1", "话题1"));
		list1.add(new KeyValueObject("2", "话题2"));
		list1.add(new KeyValueObject("3", "话题3"));
		list1.add(new KeyValueObject("4", "话题4"));
		list1.add(new KeyValueObject("5", "话题5"));
		list1.add(new KeyValueObject("6", "话题6"));
		list1.add(new KeyValueObject("7", "话题7"));
		list1.add(new KeyValueObject("8", "话题8"));
		list1.add(new KeyValueObject("9", "话题9"));
		list1.add(new KeyValueObject("10", "话题10"));
		base_message.add(new FormField("选择话题", "btns", new SelectFieldEditor(list1, "key", "value", "30%"), true, null, true));
    	base_message.add(new FormField("动态内容", "tags", RichTextAreaEditor.class, true, null, true));
        formAgent.addFieldListToMap("动态信息", base_message);
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
			}
		}
    	return null;
    }

    @Override
    protected String getReturnViewUrl() {
        return "dynamic_list.view";
    }
}
