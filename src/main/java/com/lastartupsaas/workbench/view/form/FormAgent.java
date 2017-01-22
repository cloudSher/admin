package com.lastartupsaas.workbench.view.form;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * @author shixin
 *
 */
public class FormAgent {

	private List<FormField> fields;
	private int fieldColumnCount;
	private List<FormFieldMapping> mappings;
	private Map<String, FormFieldMapping> mappingMap;
	private FormBuildLayout formLayout;
	private boolean searchMode;
	private IFormDataHelper dataHelper;
	private Alignment captionAlignment;

	private Map<String, List<FormField>> formFieldListMap;

	public FormAgent() {
		this.fields = new ArrayList<FormField>();
		this.mappings = new ArrayList<FormFieldMapping>();
		this.mappingMap = new HashMap<String, FormFieldMapping>();
		this.fieldColumnCount = 1;
		this.captionAlignment = Alignment.MIDDLE_LEFT;

		this.formFieldListMap = new LinkedHashMap<String, List<FormField>>();
		this.formLayout = new FormBuildLayout();
	}

	public boolean validateForm() {
		int c = 0;
		for (FormFieldMapping fm : this.mappings) {
			String vMsg = fm.getFieldEditor().validate();
			if (vMsg != null) {
				c++;
				fm.getFieldEditor().showValidateMessage(vMsg);
			} else {
				fm.getFieldEditor().showValidateMessage(null);
			}
		}
		return c == 0;
	}

	public void resetFormData() {
		for (FormFieldMapping fm : this.mappings) {
			fm.getFieldEditor().resetValue();
			fm.getFieldEditor().showValidateMessage(null);
		}
	}

	public void fillFormDataFromBean(Object bean) {
		for (FormFieldMapping fm : this.mappings) {
			if (hasFieldInBean(bean, fm.getField().getName())) {
				try {
					Object v = this.dataHelper.getProperty(bean, fm.getField().getName());
					if (v != null) {
						this.setFieldValue(fm.getField().getName(), v);
					} else {
						fm.getFieldEditor().resetValue();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public void loadFormDataToBean(Object bean) {
		for (FormFieldMapping fm : this.mappings) {
			if (hasFieldInBean(bean, fm.getField().getName())) {
				try {
					Object v = this.getFieldValue(fm.getField().getName());
					if (v != null) {
						this.dataHelper.setProperty(bean, fm.getField().getName(), v);
					}
				} catch (Exception ex) {
				}
			}
		}
	}

	private boolean hasFieldInBean(Object bean, String fname) {
		if (bean == null)
			return false;
		if (bean instanceof Map) {
			Map map = (Map) bean;
			return map.containsKey(fname);
		} else {
			for (Field f : bean.getClass().getDeclaredFields()) {
				if (f.getName().equals(fname))
					return true;
			}
		}
		return false;
	}

	public Map<String, Object> getFormData() {
		Map<String, Object> data = new HashMap<String, Object>();
		for (FormFieldMapping fm : this.mappings) {
			Object value = this.getFieldValue(fm.getField().getName());
			if (value != null) {
				data.put(fm.getField().getName(), value);
			}
		}
		return data;
	}

	public FormBuildLayout buildForm() {
		if (formFieldListMap != null && !formFieldListMap.isEmpty()) {
			Iterator<String> iterator = formFieldListMap.keySet().iterator();
			int panelIndex = 0;
			while (iterator.hasNext()) {
				String key = iterator.next();
				List<FormField> fieldList = formFieldListMap.get(key);
				Panel panel = new Panel(key);
				if (fieldList != null && fieldList.size() > 0) {
					GridLayout gridLayout = new GridLayout(2, fieldList.size());
					gridLayout.setStyleName("h-form-build");
					gridLayout.setWidth("100%");
					gridLayout.setSpacing(true);
					gridLayout.setMargin(true);

					for (int i = 0; i < fieldList.size(); i++) {
						FormField field = fieldList.get(i);

						Label label = this.createFieldLabel(field);
						label.setWidth("150px");
						IFormFieldEditor editor = this.getFieldEditor(field);
						if (editor == null)
							continue;
						editor.setField(field);
						editor.setFormAgent(this);

						FormFieldMapping fm = new FormFieldMapping();
						fm.setField(field);
						fm.setFieldEditor(editor);
						fm.setFieldLabel(label);

						gridLayout.addComponent(fm.getFieldLabel(), 0, i);
						gridLayout.addComponent(fm.getFieldEditor().getEditorComponent(), 1, i);
						gridLayout.setComponentAlignment(fm.getFieldLabel(), this.captionAlignment);
						gridLayout.setComponentAlignment(fm.getFieldEditor().getEditorComponent(), Alignment.MIDDLE_LEFT);
						gridLayout.setColumnExpandRatio(1, 1);

						gridLayout.getComponent(0, i).setVisible(field.isVisible());
						gridLayout.getComponent(1, i).setVisible(field.isVisible());

						this.mappings.add(fm);
						this.mappingMap.put(field.getName(), fm);
					}
					panel.setContent(gridLayout);
					formLayout.addFormFieldComponent(panel, panelIndex++);
				}
			}
		}
		return formLayout;
	}

	public FormBuildLayout buildSearchForm() {
		if (fields != null && fields.size() > 0) {
			int columnCount = this.fieldColumnCount * 2;

			int rowCount = fields.size() / this.fieldColumnCount;
			if (fields.size() % this.fieldColumnCount != 0) {
				rowCount += 1;
			}
			GridLayout gridLayout = new GridLayout(columnCount, rowCount);
			gridLayout.setStyleName("h-form-build");
			gridLayout.setWidth("100%");
			gridLayout.setSpacing(true);

			int row = 0;
			int col = 0;
			for (FormField field : fields) {
				Label label = this.createFieldLabel(field);
				IFormFieldEditor editor = this.getFieldEditor(field);
				if (editor == null)
					continue;
				editor.setField(field);
				editor.setFormAgent(this);

				FormFieldMapping fm = new FormFieldMapping();
				fm.setField(field);
				fm.setFieldEditor(editor);
				fm.setFieldLabel(label);

				gridLayout.addComponent(fm.getFieldLabel(), col, row);
				gridLayout.addComponent(fm.getFieldEditor().getEditorComponent(), col + 1, row);
				gridLayout.setComponentAlignment(fm.getFieldLabel(), this.captionAlignment);
				gridLayout.setComponentAlignment(fm.getFieldEditor().getEditorComponent(), Alignment.MIDDLE_LEFT);
				gridLayout.setColumnExpandRatio(col + 1, 1);

				col += 2;
				if (col >= columnCount) {
					col = 0;
					row++;
				}

				this.mappings.add(fm);
				this.mappingMap.put(field.getName(), fm);
			}
			formLayout.addFormFieldComponent(gridLayout, 0);
		}
		return formLayout;
	}
	
	public FormBuildLayout buildViewForm() {
		if (formFieldListMap != null && !formFieldListMap.isEmpty()) {
			Iterator<String> iterator = formFieldListMap.keySet().iterator();
			int panelIndex = 0;
			while (iterator.hasNext()) {
				String key = iterator.next();
				List<FormField> fieldList = formFieldListMap.get(key);
				Panel panel = new Panel(key);
				if (fieldList != null && fieldList.size() > 0) {
					int columnCount = this.fieldColumnCount * 2;

					int rowCount = fieldList.size() / this.fieldColumnCount;
					if (fieldList.size() % this.fieldColumnCount != 0) {
						rowCount += 1;
					}
					GridLayout gridLayout = new GridLayout(columnCount, rowCount);
					gridLayout.setStyleName("h-form-build");
					gridLayout.setWidth("100%");
					gridLayout.setSpacing(true);
					gridLayout.setMargin(true);

					int row = 0;
					int col = 0;
					for (FormField field : fieldList) {
						Label label = this.createFieldLabel(field);
						IFormFieldEditor editor = this.getFieldEditor(field);
						if (editor == null)
							continue;
						editor.setField(field);
						editor.setFormAgent(this);

						FormFieldMapping fm = new FormFieldMapping();
						fm.setField(field);
						fm.setFieldEditor(editor);
						fm.setFieldLabel(label);

						gridLayout.addComponent(fm.getFieldLabel(), col, row);
						gridLayout.addComponent(fm.getFieldEditor().getEditorComponent(), col + 1, row);
						gridLayout.setComponentAlignment(fm.getFieldLabel(), this.captionAlignment);
						gridLayout.setComponentAlignment(fm.getFieldEditor().getEditorComponent(), Alignment.MIDDLE_LEFT);
						gridLayout.setColumnExpandRatio(col + 1, 1);

						col += 2;
						if (col >= columnCount) {
							col = 0;
							row++;
						}

						this.mappings.add(fm);
						this.mappingMap.put(field.getName(), fm);
					}
					panel.setContent(gridLayout);
					formLayout.addFormFieldComponent(panel, panelIndex++);
				}
			}
		}
		return formLayout;
	}

	/**
	 * 设置form表单的某一行隐藏
	 * 
	 * @param formIndex
	 *            第几个form表单(从0开始)
	 * @param rowIndex
	 *            第几行(从0开始)
	 */
	public void setFormRowHide(int formIndex, int rowIndex) {
		setFormRowVisibleOrHide(formIndex, rowIndex, false);
	}

	/**
	 * 设置form表单的某一行隐藏
	 * 
	 * @param formIndex
	 *            第几个form表单(从0开始)
	 * @param rowIndex
	 *            第几行(从0开始)
	 */
	public void setFormRowVisible(int formIndex, int rowIndex) {
		setFormRowVisibleOrHide(formIndex, rowIndex, true);
	}

	/**
	 * 设置form表单的某一行显示或隐藏
	 * 
	 * @param formIndex
	 *            第几个form表单(从0开始)
	 * @param rowIndex
	 *            第几行(从0开始)
	 * @param visibleFlag
	 *            显示或隐藏标识
	 */
	public void setFormRowVisibleOrHide(int formIndex, int rowIndex, boolean visibleFlag) {
		GridLayout gridLayout = getFormByIndex(formIndex);
		if (gridLayout != null && gridLayout.getRows() >= rowIndex) {
			gridLayout.getComponent(0, rowIndex).setVisible(visibleFlag);
			gridLayout.getComponent(1, rowIndex).setVisible(visibleFlag);
		}
	}

	/**
	 * 获取页面的某个form表单
	 * 
	 * @param index
	 *            第几个form表单(从0开始)
	 * @return
	 */
	public GridLayout getFormByIndex(int index) {
		VerticalLayout formVerLayout = (VerticalLayout) formLayout.getComponent(0);
		if (formVerLayout.getComponentCount() >= index) {
			Panel panel = (Panel) formVerLayout.getComponent(index);
			if (panel != null) {
				return (GridLayout) panel.getContent();
			}
		}
		return null;
	}

	public Object getFieldValue(String fieldName) {
		FormFieldMapping fm = this.mappingMap.get(fieldName);
		if (fm == null)
			return null;
		return fm.getFieldEditor().getValue();
	}

	public void setFieldValue(String fieldName, Object value) {
		FormFieldMapping fm = this.mappingMap.get(fieldName);
		if (fm == null)
			return;
		fm.getFieldEditor().setValue(value);
	}

	public void addField(FormField field) {
		this.fields.add(field);
	}

	public void addFieldListToMap(String key, List<FormField> fields) {
		this.formFieldListMap.put(key, fields);
	}

	public FormBuildLayout getFormLayout() {
		return formLayout;
	}

	private IFormFieldEditor getFieldEditor(FormField field) {
		if (field.getEditor() instanceof IFormFieldEditor) {
			return (IFormFieldEditor) field.getEditor();
		} else if (field.getEditor() instanceof Class) {
			Class clz = (Class) field.getEditor();
			if (IFormFieldEditor.class.isAssignableFrom(clz)) {
				try {
					return (IFormFieldEditor) clz.newInstance();
				} catch (Exception e) {
				}
			}
		}
		return null;
	}

	private Label createFieldLabel(FormField field) {
		Label l = new Label(field.getTitle() + (field.isRequired() ? "*" : ""));
		l.setSizeUndefined();
		l.setStyleName("h-form-build-caption");
		return l;
	}

	public List<FormField> getFields() {
		return fields;
	}

	public void setFields(List<FormField> fields) {
		this.fields = fields;
	}

	public int getFieldColumnCount() {
		return fieldColumnCount;
	}

	public void setFieldColumnCount(int fieldColumnCount) {
		this.fieldColumnCount = fieldColumnCount;
	}

	public boolean isSearchMode() {
		return searchMode;
	}

	public void setSearchMode(boolean searchMode) {
		this.searchMode = searchMode;
	}

	public IFormDataHelper getDataHelper() {
		return dataHelper;
	}

	public void setDataHelper(IFormDataHelper dataHelper) {
		this.dataHelper = dataHelper;
	}

	public Alignment getCaptionAlignment() {
		return captionAlignment;
	}

	public void setCaptionAlignment(Alignment captionAlignment) {
		this.captionAlignment = captionAlignment;
	}

}