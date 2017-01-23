package com.lastartupsaas.workbench.view;


import com.lastartupsaas.workbench.util.DataHelper;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormBuildLayout;
import com.lastartupsaas.workbench.view.form.FormDataHelper;
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog;
import com.lastartupsaas.workbench.widgets.FormActionButton;
import com.vaadin.ui.Button;

/**
 * @author shixin
 *
 */
public abstract class BaseWorkBenchEditorView extends BaseWorkbenchView {

    private static final long serialVersionUID = 124283188287494386L;
    private FormAgent formAgent;

    private Object edittingData;


    @Override
    public void initView() {
//        formPanel = new SubPanel(this.getObjectName(null));
//        formPanel = new SubPanel("");
//        this.getViewRoot().addComponent(formPanel);
        formAgent = new FormAgent();
        formAgent.setFieldColumnCount(2);
        formAgent.setDataHelper(new FormDataHelper());
        this.declareFormAgent(formAgent);
        
        FormBuildLayout buildForm = formAgent.buildForm();
        buildForm.setWidth("100%");
        buildForm.setMargin(true);
        buildForm.setSpacing(true);
        this.getViewRoot().addComponent(buildForm);
//        formPanel.setContent(buildForm);

        FormActionButton saveButton = new FormActionButton("保 存", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                saveForm();
            }
        });
        buildForm.addActionComponent(saveButton);

        FormActionButton clearButton = new FormActionButton("清 空", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                showConfirmDialog("操作确认", "清空后数据不可恢复, 是否放弃目前的编辑数据 ?", new ConfirmYesNoDialog.ConfirmListener() {
                    @Override
                    public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
                        if(event.isConfirm()){
                            clearForm();
                        }
                    }

                });
            }
        });
        buildForm.addActionComponent(clearButton);

        FormActionButton returnButton = new FormActionButton("返 回", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                navigateToView(getReturnViewUrl());
            }
        });
        buildForm.addActionComponent(returnButton);

    }

    protected abstract String getObjectName(Object obj);

    protected abstract void declareFormAgent(FormAgent formAgent);

    protected abstract  Object createVirginObject();

    protected abstract boolean saveObject(Object data);

    protected abstract boolean updateObject(Object data);

    protected abstract Object loadEdittingDataFromContext(ViewContext vc);

    protected abstract String getReturnViewUrl();

    protected void clearForm() {
        this.formAgent.resetFormData();
    }

    protected void saveForm() {
        if(!this.formAgent.validateForm()){
            this.showNotification("数据填写不完整", "请检查填写项目右侧的提示信息!");
            return;
        }

        String msg = this.validateForm();
        if(msg!=null){
            this.showNotification("数据填写不正确", msg);
            return;
        }

        Object data = this.createVirginObject();
        this.formAgent.loadFormDataToBean(data);
        if(this.edittingData==null){
            boolean ret = this.saveObject(data);
            if(ret){
//            	this.showConfirmDialog("操作成功", "已成功添加["+this.getObjectName(data)+"], 是否继续添加 ?",  new ConfirmYesNoDialog.ConfirmListener() {
                this.showConfirmDialog("操作成功", "已成功添加, 是否继续添加 ?",  new ConfirmYesNoDialog.ConfirmListener() {
                    private static final long serialVersionUID = 4753664139107429400L;
                    @Override
                    public void confirmClick(ConfirmYesNoDialog.ConfirmEvent event) {
                        if(event.isConfirm()){
                            clearForm();
                        }else{
                            clearForm();
                            navigateToView(getReturnViewUrl());
                        }
                    }
                });
            }else{
//                this.showNotification("操作失败", "保存"+this.getObjectName(null)+"失败, 请检查数据是否符合要求, 然后再试");
                this.showNotification("操作失败", "保存失败, 请检查数据是否符合要求, 然后再试");
            }
        }else{
            this.fillFormDataToEdittingData(this.edittingData, data);

            if(this.updateObject(this.edittingData)){
                this.clearForm();
//                this.showTrayNotification("操作成功", this.getObjectName(null) + "编辑成功!");
                this.showNotification("操作成功", "编辑成功!");
                navigateToView(getReturnViewUrl());
            }else{
//                this.showNotification("操作失败", "保存"+this.getObjectName(null)+"失败, 请检查数据是否符合要求, 然后再试");
                this.showNotification("操作失败", "保存失败, 请检查数据是否符合要求, 然后再试");
            }
        }
    }

    protected String validateForm() {
        return null;
    }

    protected void fillFormDataToEdittingData(Object oldData, Object formData) {
        DataHelper.copyProperties(formData, oldData, true);
    }
    @Override
    protected void showView(ViewContext vc) {
        this.edittingData = this.loadEdittingDataFromContext(vc);
        this.formAgent.resetFormData();
        if(edittingData!=null){
        	this.setViewCaption(this.getObjectName(null) + " > 编辑");
        	this.formAgent.fillFormDataFromBean(edittingData);
        } else {
        	this.setViewCaption(this.getObjectName(null) + " > 新增");
		}
    }

    protected FormAgent getFormAgent() {
        return formAgent;
    }

    protected Object getEdittingData() {
        return edittingData;
    }
}
