package com.startup.saas.workbench.view.form;



import java.util.UUID;

import com.startup.saas.workbench.view.form.impl.SectionLineFieldEditor;

/**
 * @author shixin
 *
 */
public class FormField {

    public static FormField newSection(String sectionTitle) {
        return new FormField("", UUID.randomUUID().toString(), SectionLineFieldEditor.class, false, null, true).setInputDescr(sectionTitle);
    }

    private String title;
    private String name;
    private Object editor;
    private String dictType;
    private boolean required;
    private String validators;
    private boolean fullMode;
    private String inputDescr;
    private String width;
    private String height;
    public FormField() {
        super();
        this.dictType = null;
        this.editor = null;
        this.validators = null;
        this.fullMode = false;
        this.inputDescr = null;
        this.required = false;
        this.width = null;
        this.height = null;
    }

    public FormField(String title, String name, Object editor, boolean required, String validators, boolean fullMode) {
        this();
        this.title = title;
        this.name = name;
        this.editor = editor;
        this.dictType = null;
        this.required = required;
        this.validators = validators;
        this.fullMode = fullMode;
    }



    public FormField(String title, String name, Object editor, boolean fullMode) {
        super();
        this.title = title;
        this.name = name;
        this.editor = editor;
        this.fullMode = fullMode;
        this.dictType = null;
        this.required = false;
        this.validators = null;
        this.width = null;
        this.height = null;
    }

    public FormField setSizeFull(){
        this.width = "100%";
        this.height = "100%";
        return this;
    }

    public String getName() {
        return name;
    }
    public FormField setName(String name) {
        this.name = name;
        return this;
    }

    public Object getEditor() {
        return editor;
    }

    public FormField setEditor(Object editor) {
        this.editor = editor;
        return this;
    }

    public String getDictType() {
        return dictType;
    }
    public FormField setDictType(String dictType) {
        this.dictType = dictType;
        return this;
    }
    public boolean isRequired() {
        return required;
    }
    public FormField setRequired(boolean required) {
        this.required = required;
        return this;
    }
    public String getValidators() {
        return validators;
    }
    public FormField setValidators(String validators) {
        this.validators = validators;
        return this;
    }
    public boolean isFullMode() {
        return fullMode;
    }
    public FormField setFullMode(boolean fullMode) {
        this.fullMode = fullMode;
        return this;
    }
    public String getTitle() {
        return title;
    }
    public FormField setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getInputDescr() {
        return inputDescr;
    }

    public FormField setInputDescr(String inputDescr) {
        this.inputDescr = inputDescr;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public FormField setWidth(String width) {
        this.width = width;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public FormField setHeight(String height) {
        this.height = height;
        return this;
    }


}