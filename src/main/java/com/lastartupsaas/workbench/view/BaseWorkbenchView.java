package com.lastartupsaas.workbench.view;


import java.util.HashMap;
import java.util.Map;

import com.lastartupsaas.workbench.util.DataHelper;
import com.lastartupsaas.workbench.widgets.ConfirmYesNoDialog;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author shixin
 *
 */
public abstract class BaseWorkbenchView extends VerticalLayout implements View{


    private static final long serialVersionUID = -6341119974768325524L;
    private Label viewCaptionLabel;
    private VerticalLayout viewRoot;
    private Panel viewRootHost;
    private boolean viewInitialized;//TODO:该属性目的
    private Navigator viewNavigator;
    //TODO:缺少Session数据

    public BaseWorkbenchView(){
        this.viewInitialized = false;
        this.setSizeFull();
//        this.setMargin(new MarginInfo(false,true,true,true));

        HorizontalLayout viewHeader = new HorizontalLayout();
        //TODO:此处可以改成css
        viewHeader.setSizeFull();
        viewHeader.setWidth("100%");
        viewHeader.setHeight("28px");

        viewCaptionLabel = new Label();
        viewCaptionLabel.setWidth("100%");
        viewCaptionLabel.addStyleName(ValoTheme.LABEL_LIGHT);
        viewCaptionLabel.addStyleName(ValoTheme.LABEL_SMALL);
        viewHeader.addComponent(viewCaptionLabel);
        viewHeader.setExpandRatio(viewCaptionLabel, 1);

        this.addComponent(viewHeader);

        viewRootHost = new Panel();
        //TODO:此处可以改成css
        viewRootHost.setSizeFull();

        viewRoot = new VerticalLayout();
        //TODO:此处可以改成css
        viewRoot.setSizeFull();
        viewRoot.setMargin(true);
        viewRoot.setSpacing(true);

        viewRootHost.setContent(viewRoot);

        this.addComponent(viewRootHost);
        this.setComponentAlignment(viewRootHost, Alignment.TOP_LEFT);
        this.setExpandRatio(viewRootHost, 1);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        if(!this.viewInitialized) {
            this.initView();
            this.viewInitialized = true;
        }

        viewNavigator = event.getNavigator();

        Map<String, String> parameterMap;
        String parameters = event.getParameters();
        if(parameters!=null && parameters.length()>0){
            parameterMap = DataHelper.parametersToMap(parameters);
        }else{
            parameterMap = new HashMap<String, String>();
        }


        ViewContext vc = new ViewContext();
        vc.setParameterMap(parameterMap);
        vc.setParameters(parameters);
        vc.setViewName(event.getViewName());
        vc.setNavigator(event.getNavigator());

        this.showView(vc);
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 展示视图
     * @param vc
     */
    protected abstract void showView(ViewContext vc);

    /**
     * 获取Root视图
     * @return
     */
    protected VerticalLayout getViewRoot(){
        return this.viewRoot;
    }

    /**
     * 设置视图是否可以滚动
     * @param scrollable
     */
    protected void setViewCanScrollable(boolean scrollable) {
        if(scrollable) {
            this.viewRoot.setSizeUndefined();
            this.viewRoot.setWidth("100%");
        }else{
            this.viewRoot.setSizeFull();
        }
    }

    /**
     * 设置视图标题
     * @param caption
     */
    protected void setViewCaption(String caption){
        this.viewCaptionLabel.setValue(caption);
    }

    protected String getViewCaption(){
        return  this.viewCaptionLabel.getValue();
    }

    /**
     * 跳转视图
     * @param viewIdAndParameters
     */
    protected void navigateToView(String viewIdAndParameters) {
//		String base64String = DataHelper.encodeBase64String(viewIdAndParameters);
//		base64String = "xu-" + base64String;
        UI.getCurrent().getNavigator().navigateTo(viewIdAndParameters);
    }

    /**
     * 展示Confirm会话
     * @param caption
     * @param text
     * @param confirmListener
     */
    protected void showConfirmDialog(String caption, String text, ConfirmYesNoDialog.ConfirmListener confirmListener) {
        ConfirmYesNoDialog dlg = new ConfirmYesNoDialog(caption, text);
        dlg.addConfirmListener(confirmListener);
        UI.getCurrent().addWindow(dlg);
    }

    /**
     * 展示通知
     * @param caption
     * @param text
     */
    protected void showNotification(String caption, String text) {
        Notification.show(caption, text, Notification.Type.WARNING_MESSAGE);
    }

    /**
     *
     * @param caption
     * @param text
     */
    protected void showTrayNotification(String caption, String text) {
        Notification.show(caption, text, Notification.Type.TRAY_NOTIFICATION);
    }
}
