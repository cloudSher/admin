package com.lastartupsaas.workbench.view.business.brand.supplier;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormBuildLayout;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.ImageUploadEditor;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog;
import com.lastartupsaas.workbench.widgets.FormActionButton;
import com.lastartupsaas.workbench.widgets.FormWindow;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

public class ServiceContractViewWindow extends FormWindow {

	private static final long serialVersionUID = 2686898529905522381L;

	public ServiceContractViewWindow(String caption) {
		super(caption, "1","70%");
	}

	@Override
	protected boolean doDoneActione(FormAgent formAgent) {
		return true;
	}

	@Override
	protected void setupFormAgent(FormAgent formAgent) {
		formAgent.setFieldColumnCount(2);

		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("系统服务合同编号:", "oldrd", new LabelFieldEditor("10000001", "100%"), false, null, true));
		base_message.add(new FormField("线下服务合同编号:", "nerd", new LabelFieldEditor("132232000035431", "100%"), false, null, true));
		base_message.add(new FormField("品牌编号:", "newPrm", new LabelFieldEditor("1233444", "100%"), false, null, true));
		base_message.add(new FormField("甲方:", "newfirm1", new LabelFieldEditor("三胞集团", "100%"), false, null, true));
		base_message.add(new FormField("乙方:", "nm2", new LabelFieldEditor("张三", "100%"), false, null, true));
		base_message.add(new FormField("签约时间:", "nerm3", new LabelFieldEditor("2016-10-11 12:41:25", "100%"), false, null, true));
		base_message.add(new FormField("合作有效期:", "oldrd3", new LabelFieldEditor("3年", "100%"), false, null, true));
		formAgent.addFieldListToMap("基本信息", base_message);
		
		List<FormField> firstParty_message = new ArrayList<FormField>();
		firstParty_message.add(new FormField("工商执照注册号:", "oldrd311", new LabelFieldEditor("132232000035431", "100%"), false, null, true));
		firstParty_message.add(new FormField("税务登记证号:", "oldrd312", new LabelFieldEditor("63223200003566661", "100%"), false, null, true));
		firstParty_message.add(new FormField("法定代表人:", "oldrd322", new LabelFieldEditor("", "100%"), false, null, true));
		formAgent.addFieldListToMap("甲方基本信息", firstParty_message);
		
		List<FormField> account_message = new ArrayList<FormField>();
		account_message.add(new FormField("户名:", "oldrd3113", new LabelFieldEditor("李四", "100%"), false, null, true));
		account_message.add(new FormField("银行账号:", "oldrd3124", new LabelFieldEditor("63223200003566661", "100%"), false, null, true));
		account_message.add(new FormField("开户行:", "oldrd3225", new LabelFieldEditor("招商银行", "100%"), false, null, true));
		formAgent.addFieldListToMap("甲方账户信息", account_message);
		
		List<FormField> settlement_message = new ArrayList<FormField>();
		settlement_message.add(new FormField("平台抽佣比率:", "oldrd311a", new LabelFieldEditor("10%", "100%"), false, null, true));
		settlement_message.add(new FormField("保证金:", "oldrd312a", new LabelFieldEditor("30000", "100%"), false, null, true));
		formAgent.addFieldListToMap("甲方基本信息", settlement_message);
		
		List<FormField> person_message = new ArrayList<FormField>();
		person_message.add(new FormField("姓名:", "oldrd311b", new LabelFieldEditor("李四", "100%"), false, null, true));
		person_message.add(new FormField("手机号:", "oldrd312b", new LabelFieldEditor("18612345678", "100%"), false, null, true));
		formAgent.addFieldListToMap("联系人信息", person_message);
		
		List<FormField> attachment_message = new ArrayList<FormField>();
		attachment_message.add(new FormField("营业执照照片:", "oldrd311b", ImageUploadEditor.class, false, null, true));
		attachment_message.add(new FormField("组织机构代码照片:", "oldrd312b", ImageUploadEditor.class, false, null, true));
		attachment_message.add(new FormField("合同照片:", "oldrd312bd", ImageUploadEditor.class, false, null, true));
		formAgent.addFieldListToMap("附件", attachment_message);
	}

	@Override
	protected void addActionComponent(FormBuildLayout layout) {
		super.addActionComponent(layout);
		layout.addActionComponent(new FormActionButton("审核通过", new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				showConfirmDialog("提示", "确定审核通过吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
					@Override
					public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
						if (event.isConfirm()) {
							Notification.show("提示", "审核通过成功", Notification.Type.HUMANIZED_MESSAGE);
							ServiceContractViewWindow.this.close();
						} else {
						}
					}
				});
			}
		}));
		layout.addActionComponent(new FormActionButton("审核拒绝", new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				showConfirmDialog("提示", "确定审核拒绝吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
					@Override
					public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
						if (event.isConfirm()) {
							Notification.show("提示", "审核拒绝成功", Notification.Type.HUMANIZED_MESSAGE);
							ServiceContractViewWindow.this.close();
						} else {
						}
					}
				});
			}
		}));
	}

}
