package com.lastartupsaas.workbench.view.business.community.dynamic;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.view.business.community.comment.CommentListView;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormBuildLayout;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog;
import com.lastartupsaas.workbench.widgets.FormActionButton;
import com.lastartupsaas.workbench.widgets.FormWindow;
import com.lastartupsaas.workbench.widgets.ModalWindow;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class DynamicViewWindow extends FormWindow {

	private static final long serialVersionUID = -1494418901055989104L;

	public DynamicViewWindow(String caption) {
		super(caption, "1");
	}

	@Override
	protected boolean doDoneActione(FormAgent formAgent) {
		return true;
	}

	@Override
	protected void setupFormAgent(FormAgent formAgent) {

		List<FormField> base_message = new ArrayList<FormField>();
		base_message.add(new FormField("动态ID:", "oldrd", new LabelFieldEditor("10000001", "100%"), false, null, true));
		base_message.add(new FormField("动态标题:", "nerd", new LabelFieldEditor("成功故事案例之加盟肯德基月入千万", "100%"), false, null, true));
		base_message.add(new FormField("所属话题:", "newPrm", new LabelFieldEditor("成功故事", "100%"), false, null, true));
		base_message.add(new FormField("发布者:", "newfirm1", new LabelFieldEditor("张三", "100%"), false, null, true));
		base_message.add(new FormField("发布时间:", "nm2", new LabelFieldEditor("2016-10-11 12:41:25", "100%"), false, null, true));
		base_message.add(new FormField("点赞数:", "nerm3", new LabelFieldEditor("23", "100%"), false, null, true));
		base_message.add(new FormField("分享数:", "oldrd3", new LabelFieldEditor("233", "100%"), false, null, true));
		base_message.add(new FormField("评论数:", "nerd2", new LabelFieldEditor("4234", "100%"), false, null, true));
		base_message.add(new FormField("动态内容:", "nerd2q1",
				new LabelFieldEditor(
						"内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容",
						"100%"),
				false, null, true));
		formAgent.addFieldListToMap("动态信息", base_message);
	}

	@Override
	protected void addActionComponent(FormBuildLayout layout) {
		super.addActionComponent(layout);
		layout.addActionComponent(new FormActionButton("查看评论", new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				CommentListView firstView = new CommentListView("");
				firstView.initView();
				ModalWindow formWindow = new ModalWindow("", firstView, "75%");
				UI.getCurrent().addWindow(formWindow);
			}
		}));
		layout.addActionComponent(new FormActionButton("删除动态", new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				showConfirmDialog("提示", "确定要删除此动态吗 ?", new ConfirmYesNoDialog.ConfirmListener() {
					@Override
					public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
						if (event.isConfirm()) {
							Notification.show("提示", "动态删除成功", Notification.Type.HUMANIZED_MESSAGE);
							DynamicViewWindow.this.close();
						} else {
						}
					}
				});
			}
		}));
	}

}
