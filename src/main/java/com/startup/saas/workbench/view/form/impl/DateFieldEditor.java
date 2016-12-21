package com.startup.saas.workbench.view.form.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.startup.saas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Component;
import com.vaadin.ui.PopupDateField;

/**
 * 日期控件
 * 
 * @author lifeilong
 * @date: 2016-12-14
 */
public class DateFieldEditor extends BaseFormFieldEditor {

	private PopupDateField dateField;

	private String width = "100%";
	private String dateFormat = "yyyy-MM-dd HH:mm:ss";// 日期格式
	private Date startDate;// 起始日期
	private Date endDate;// 终止日期
	private String resolution = "SECOND";// 日期分辨率，默认秒
	private boolean readOnly = false;// 只读标识

	public DateFieldEditor() {
	}

	public DateFieldEditor(String width) {
		super();
		this.width = width;
	}

	public DateFieldEditor(Date startDate, Date endDate, String resolution, String dateFormat, String width, boolean readOnly) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.resolution = resolution;
		this.dateFormat = dateFormat;
		this.width = width;
		this.readOnly = readOnly;
	}

	@Override
	public void setValue(Object value) {
		if (this.dateField != null && value != null) {
			Date valueDate = null;
			try {
				SimpleDateFormat format = new SimpleDateFormat(dateFormat);
				valueDate = format.parse((String) value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.dateField.setValue(valueDate);
		}
	}

	@Override
	public String validate() {
		if (super.validate() != null)
			return super.validate();
		Date v = this.dateField.getValue();
		if (this.field.isRequired() && v == null)
			return this.getRequiredErrorMessage();
		return null;
	}

	@Override
	public void resetValue() {
		if (this.dateField != null)
			this.dateField.setValue(null);
	}

	@Override
	public Object getValue() {
		String value = null;
		Date v = this.dateField.getValue();
		if (v != null) {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			value = format.format(v);
		}
		return value;
	}

	@Override
	protected Component createComponent() {
		this.dateField = new PopupDateField();
		this.dateField.setDateFormat(this.dateFormat);// 设置日期格式
		this.dateField.setTextFieldEnabled(false);// 设置文本框不可手动输入
		this.dateField.setReadOnly(this.readOnly);// 设置是否只读
		this.dateField.setWidth(this.width);// 设置宽度
		// 设置日期分辨率
		if ("YEAR".equals(this.resolution)) {
			this.dateField.setResolution(Resolution.YEAR);
		} else if ("MONTH".equals(this.resolution)) {
			this.dateField.setResolution(Resolution.MONTH);
		} else if ("DAY".equals(this.resolution)) {
			this.dateField.setResolution(Resolution.DAY);
		} else if ("HOUR".equals(this.resolution)) {
			this.dateField.setResolution(Resolution.HOUR);
		} else if ("MINUTE".equals(this.resolution)) {
			this.dateField.setResolution(Resolution.MINUTE);
		} else {
			this.dateField.setResolution(Resolution.SECOND);
		}
		if (this.startDate != null) {
			// 设置可点选的起始日期
			this.dateField.setRangeStart(this.startDate);
		}
		if (endDate != null) {
			// 设置可点选的终止日期
			this.dateField.setRangeEnd(this.endDate);
		}
		if (this.field.getInputDescr() != null) {
			this.dateField.setInputPrompt(this.field.getInputDescr());
		}
		return this.dateField;
	}

}