package com.startup.saas.workbench.view.form;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author lifeilong
 * @date 2016-12-19 16:42
 */
public class ViewAgent {

    private List<FormField> fields;
    private int fieldColumnCount;
    private List<FormFieldMapping> mappings;
    private Map<String, FormFieldMapping> mappingMap;
    private FormBuildLayout gridLayout;
    private boolean searchMode;
    private IFormDataHelper dataHelper;
    private Alignment captionAlignment;
    
    public ViewAgent() {
        this.fields = new ArrayList<FormField>();
        this.mappings = new ArrayList<FormFieldMapping>();
        this.mappingMap = new HashMap<String, FormFieldMapping>();
        this.fieldColumnCount = 1;
        this.captionAlignment = Alignment.TOP_LEFT;
    }

    public void fillFormDataFromBean(Object bean){
        for(FormFieldMapping fm : this.mappings){
            if(hasFieldInBean(bean, fm.getField().getName())){
                try{
                    //Object v = MVEL.getProperty(fm.getField().getName(), bean);
//					bean.getClass().getDeclaredField(fm.getField().getName()).setAccessible(true);
//					Object v = bean.getClass().getDeclaredField(fm.getField().getName()).get(bean);
                    Object v = this.dataHelper.getProperty(bean, fm.getField().getName());
                    if(v!=null){
                        this.setFieldValue(fm.getField().getName(), v);
                    }else{
                        fm.getFieldEditor().resetValue();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    public void loadFormDataToBean(Object bean){
        for(FormFieldMapping fm : this.mappings){
            if(hasFieldInBean(bean, fm.getField().getName())){
                try{
                    Object v = this.getFieldValue(fm.getField().getName());
                    if(v!=null){
                        //MVEL.setProperty(bean, fm.getField().getName(), v);
//						bean.getClass().getDeclaredField(fm.getField().getName()).setAccessible(true);
//						bean.getClass().getDeclaredField(fm.getField().getName()).set(bean, v);
                        this.dataHelper.setProperty(bean, fm.getField().getName(), v);
                    }
                }catch(Exception ex){}
            }
        }
    }

    private boolean hasFieldInBean(Object bean, String fname){
        if(bean==null) return false;
        if(bean instanceof Map){
            Map map = (Map) bean;
            return map.containsKey(fname);
        }else{
            for(Field f : bean.getClass().getDeclaredFields()){
                if(f.getName().equals(fname)) return true;
            }
        }
        return false;
    }

    public Map<String, Object> getFormData(){
        Map<String, Object> data = new HashMap<String, Object>();
        for(FormFieldMapping fm : this.mappings){
            Object value = this.getFieldValue(fm.getField().getName());
            if(value!=null){
                data.put(fm.getField().getName(), value);
            }
        }
        return data;
    }

    public FormBuildLayout buildForm(){
        if(this.fields==null || this.fields.size()==0) {
//            gridLayout = new FormBuildLayout(2, 1, this);
            gridLayout.setStyleName("h-form-build");
            gridLayout.setSizeUndefined();
            gridLayout.setSpacing(true);
            return gridLayout;
        }

        int _col = -1;
        int _row = -1;

        for(FormField field : this.fields) {
            IFormFieldEditor editor = this.getFieldEditor(field);
            if(editor==null) continue;
            editor.setField(field);
//            editor.setFormAgent(this);
//			Component component = this.createFieldComponent(field);
//			if(component==null) continue;
            Label label = this.createFieldLabel(field);
            FormFieldMapping fm = new FormFieldMapping();
            fm.setField(field);
            fm.setFieldEditor(editor);
            fm.setFieldLabel(label);

            if(field.isFullMode()){
                _row += 1;
                _col = 0;
                fm.setFieldRC(new FormGridRC(_row, _col, _row, this.fieldColumnCount-1));
                _col = this.fieldColumnCount-1;
            }else{
                _col += 1;
                if(_row < 0) _row = 0;
                if((_col) >= this.fieldColumnCount){
                    _row += 1;
                    _col = 0;
                }
                fm.setFieldRC(new FormGridRC(_row, _col, _row, _col));
            }
            this.mappings.add(fm);
            this.mappingMap.put(field.getName(), fm);
        }

        int gridRows = _row+1;
        int gridCols = this.fieldColumnCount * 2;

//        gridLayout = new FormBuildLayout(gridCols, gridRows, this);
        gridLayout.setStyleName("h-form-build");
        gridLayout.setSizeUndefined();
        gridLayout.setSpacing(true);
        for(FormFieldMapping fm : this.mappings){
            FormGridRC rc = fm.getFieldRC();
            //label
            gridLayout.addComponent(fm.getFieldLabel(), rc.getCol()*2, rc.getRow());
            gridLayout.addComponent(fm.getFieldEditor().getEditorComponent(), rc.getCol()*2+1, rc.getRow(), rc.getCol2()*2+1, rc.getRow());
            gridLayout.setComponentAlignment(fm.getFieldLabel(), this.captionAlignment);
            gridLayout.setComponentAlignment(fm.getFieldEditor().getEditorComponent(), Alignment.MIDDLE_LEFT);
        }

        for(int i=0;i<this.fieldColumnCount;i++){
            gridLayout.setColumnExpandRatio(i*2+1, 1);
        }

        return gridLayout;
    }

    public Object getFieldValue(String fieldName){
        FormFieldMapping fm = this.mappingMap.get(fieldName);
        if(fm==null) return null;
        return fm.getFieldEditor().getValue();
    }

    public void setFieldValue(String fieldName, Object value){
        FormFieldMapping fm = this.mappingMap.get(fieldName);
        if(fm==null) return;
        fm.getFieldEditor().setValue(value);
    }

    public void addField(FormField field){
        this.fields.add(field);
    }


    public GridLayout getGridLayout() {
        return gridLayout;
    }

    private IFormFieldEditor getFieldEditor(FormField field) {
        if(field.getEditor() instanceof IFormFieldEditor){
            return (IFormFieldEditor) field.getEditor();
        }else if(field.getEditor() instanceof Class){
            Class clz = (Class) field.getEditor();
            if(IFormFieldEditor.class.isAssignableFrom(clz)){
                try {
                    return (IFormFieldEditor) clz.newInstance();
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    private Label createFieldLabel(FormField field) {
        Label l = new Label(field.getTitle() + (field.isRequired()?"*":""));
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