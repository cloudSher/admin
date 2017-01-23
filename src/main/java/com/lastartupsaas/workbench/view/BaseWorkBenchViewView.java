package com.lastartupsaas.workbench.view;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormBuildLayout;
import com.lastartupsaas.workbench.view.form.FormDataHelper;
import com.lastartupsaas.workbench.widgets.FormActionButton;
import com.vaadin.ui.Button;

/**
 * 查看视图
 * 
 * @author lifeilong
 * @date 2017-01-12
 */
public abstract class BaseWorkBenchViewView extends BaseWorkbenchView {

	private static final long serialVersionUID = 4961017201764904749L;
	private FormAgent formAgent;

	private Object formData;

	@Override
	public void initView() {
		formAgent = new FormAgent();
		formAgent.setDataHelper(new FormDataHelper());
		this.declareFormAgent(formAgent);

		FormBuildLayout buildForm = formAgent.buildForm();
		buildForm.setWidth("100%");
		buildForm.setMargin(true);
		buildForm.setSpacing(true);
		this.getViewRoot().addComponent(buildForm);

		if (StringUtils.isNotBlank(getReturnViewUrl())) {
			FormActionButton returnButton = new FormActionButton("返 回", new Button.ClickListener() {
				@Override
				public void buttonClick(Button.ClickEvent event) {
					navigateToView(getReturnViewUrl());
				}
			});
			buildForm.addActionComponent(returnButton);
		}
	}

	protected abstract String getObjectName(Object obj);

	protected abstract void declareFormAgent(FormAgent formAgent);

	protected abstract Object loadFormDataByContext(ViewContext vc);

	protected abstract String getReturnViewUrl();

	@Override
	protected void showView(ViewContext vc) {
		this.formData = this.loadFormDataByContext(vc);
		this.formAgent.resetFormData();
		this.setViewCaption(this.getObjectName(null));
		if (formData != null) {
			this.formAgent.fillFormDataFromBean(formData);
		}
	}

	protected FormAgent getFormAgent() {
		return formAgent;
	}
}
