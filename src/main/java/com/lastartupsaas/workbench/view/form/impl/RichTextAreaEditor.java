package com.lastartupsaas.workbench.view.form.impl;

import com.lastartupsaas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.server.ClientConnector.AttachEvent;
import com.vaadin.server.ClientConnector.AttachListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.RichTextArea;

/**
 * 可调字体的富文本框
 * 
 * @author lifeilong
 * @date 2017-01-20
 */
public class RichTextAreaEditor extends BaseFormFieldEditor {

	private RichTextArea richTextArea;
	private String width;
	
	public RichTextAreaEditor() {
		this("100%");
	}
	
	public RichTextAreaEditor(String width) {
		super();
		this.width = width;
	}

	@Override
	public void setValue(Object value) {
		if (this.richTextArea != null) {
			this.richTextArea.setValue(value != null ? value.toString() : "");
		}
	}

	@Override
	public void resetValue() {
		if (this.richTextArea != null)
			this.richTextArea.setValue("");
	}

	@Override
	public Object getValue() {
		return this.richTextArea != null ? this.richTextArea.getValue() : null;
	}

	@Override
	public String validate() {
		if (super.validate() != null)
			return super.validate();
		String v = this.richTextArea.getValue();
		if (this.field.isRequired() && (v == null || v.trim().length() == 0))
			return this.getRequiredErrorMessage();
		return null;
	}

	@Override
	protected Component createComponent() {
		this.richTextArea = new RichTextArea();
		this.richTextArea.setWidth(width);
		this.richTextArea.addAttachListener(new AttachListener() {
			@Override
			public void attach(AttachEvent event) {
				richTextArea.focus();
				richTextArea.selectAll();

			}
		});
		return this.richTextArea;
	}

}
