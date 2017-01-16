package com.lastartupsaas.workbench.view.business.member;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lastartupsaas.api.model.Member;
import com.lastartupsaas.workbench.domain.admin.Post;
import com.lastartupsaas.workbench.domain.admin.User;
import com.lastartupsaas.workbench.view.BaseWorkBenchEditorView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.ImageUploadEditor;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.RadioboxYesOrNoEditor;
import com.lastartupsaas.workbench.view.form.impl.SelectFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 管理员编辑页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = MemberEditView.VIEW_NAME)
public class MemberEditView extends BaseWorkBenchEditorView {

	private static final long serialVersionUID = -4358062864597621790L;
	public static final String VIEW_NAME = "member_edit.view";

	@Override
	protected String getObjectName(Object obj) {
		return "当前位置：会员 > 会员管理 > 会员列表";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("所属员工ID", "jobNumber", InputFieldEditor.class, true, null, true).setInputDescr("6-10位数字"));
		base_message.add(new FormField("昵称", "nickName", InputFieldEditor.class, true, null, true).setInputDescr("3-20位字符，可由中文、英文及数字”组成"));
		base_message.add(new FormField("会员头像", "header_img", ImageUploadEditor.class, true, null, true));
		base_message.add(new FormField("会员性别", "sex", new RadioboxYesOrNoEditor("男", "女"), true, null, true));
		base_message.add(new FormField("是否认证", "status", new RadioboxYesOrNoEditor("是", "否"), true, null, true));

		formAgent.addFieldListToMap("会员信息", base_message);
	}

	@Override
	protected Object createVirginObject() {
		return new Member();
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
			Member member = new Member();
			member.setId("2017011000001");
			member.setNickName("张三");
			member.setType("1");
			member.setEmail("test001@lashou-inc.com");
			member.setHeadImg("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
			member.setStatus("1");
			member.setAdditionalProperty("测试", "111111111");
			return member;
		}
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return "member_list.view";
	}
}
