package com.startup.saas.workbench.widgets;

import java.util.ArrayList;
import java.util.List;

import com.startup.saas.workbench.view.BaseWorkbenchView;
import com.vaadin.ui.TabSheet;

/**
 * @author shixin
 *
 */
public class MultiPages extends TabSheet {

    private static final long serialVersionUID = -863664899939049025L;

    private List<BaseWorkbenchView> pages;

    private Object currentEdittingData;

    public MultiPages() {
        this.pages = new ArrayList<BaseWorkbenchView>();
    }

    public Object getCurrentEdittingData() {
        return currentEdittingData;
    }

    public void addPage(BaseWorkbenchView page){
        if(page==null || this.pages.contains(page)) return;
        int index = this.pages.size();
        this.addPage(page, index);
    }
    public void addPage(BaseWorkbenchView page, String caption){
        if(page==null || this.pages.contains(page)) return;
        int index = this.pages.size();
        this.addTab(page, caption, null, index);
    }

    public void addPage(BaseWorkbenchView page, int index) {
        if(page==null || this.pages.contains(page)) return;
        this.pages.add(index, page);
        this.addTab(page, index);
    }

    public void addPage(BaseWorkbenchView page, int index, String caption) {
        if(page==null || this.pages.contains(page)) return;
        this.pages.add(index, page);
        this.addTab(page, caption, null, index);
    }

//    public void loadData(Object data) {
//        this.currentEdittingData = data;
//        for(BaseWorkBenchPageView editor : this.pages) {
//            editor.loadDataObject(currentEdittingData);
//        }
//    }
//
//    public String validateData(boolean autoSwitch){
//        for(int i=0;i<this.pages.size();i++){
//            String msg = this.pages.get(i).validateData();
//            if(msg!=null){
//                if(autoSwitch){
//                    this.setSelectedTab(i);
//                }
//                return msg;
//            }
//        }
//        return null;
//    }
//
//    public void saveData() {
//        if(this.currentEdittingData==null) return;
//        for(BaseWorkBenchPageView editor : this.pages) {
//            editor.saveDataObject(this.currentEdittingData);
//        }
//    }

}
