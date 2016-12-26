package com.lastartupsaas.workbench.view;

import com.vaadin.navigator.Navigator;

import java.util.Map;

/**
 * @author shixin
 *
 */
public class ViewContext {

    private String parameters;
    private Map<String, String> parameterMap;
    private String viewName;
    private Navigator navigator;

    public String getParameter(String pName) {
        return this.parameterMap.get(pName);
    }

    public String getParameter(String pName, String dftValue) {
        String v = this.parameterMap.get(pName);
        return v==null?dftValue:v;
    }

    public String getParameters() {
        return parameters;
    }
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
    public Map<String, String> getParameterMap() {
        return parameterMap;
    }
    public void setParameterMap(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }
    public String getViewName() {
        return viewName;
    }
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    public Navigator getNavigator() {
        return navigator;
    }
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }



}