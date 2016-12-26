package com.lastartupsaas.workbench.widgets;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shixin
 *
 */
public class PagingNavigator extends HorizontalLayout implements Button.ClickListener {

    private static final long serialVersionUID = -9046555742156355535L;

    private List<Button> buttons;

    private PageNavigatorModel model;

    private Label infoLabel;
    public PagingNavigator() {
        this.buttons = new ArrayList<Button>();
        infoLabel = new Label("共有 0 条记录 ");
        this.addComponent(infoLabel);
        this.setComponentAlignment(infoLabel, Alignment.MIDDLE_LEFT);
        this.infoLabel.addStyleName(ValoTheme.LABEL_SMALL);
    }

    public void updateNavigator(PageNavigatorModel model) {
        this.model = model;
        for(Button b : this.buttons){
            this.removeComponent(b);
        }
        this.infoLabel.setValue("共有 "+model.getCount()+" 条记录 ");
        this.createButton("上一页", -1, false);
        int start = Math.max(1, model.getPageIndex()-3);
        int end = Math.min(model.getPageCount(), model.getPageIndex()+3);
        if(start>1){
            this.createButton("1", 1, false);
        }
        for(int i=start;i<=end;i++){
            String cap = String.valueOf(i);
            if(i==(model.getPageIndex()-3) || i==(model.getPageIndex()+3)){
                cap = "..";
            }
            this.createButton(cap, i, i==model.getPageIndex());
        }
        if(end<model.getPageCount()){
            this.createButton(String.valueOf(model.getPageCount()), model.getPageCount(), false);
        }
        this.createButton("下一页", -2, false);

    }

    private Button createButton(String caption, int pIndex, boolean current) {
        Button b = new Button(caption);
        if(current) {
            b.addStyleName(ValoTheme.BUTTON_SMALL);
            b.addStyleName(ValoTheme.BUTTON_LINK);
        }else{
            b.addStyleName(ValoTheme.BUTTON_SMALL);
            b.addStyleName(ValoTheme.BUTTON_LINK);
        }
        b.addClickListener(this);
        b.setData(pIndex);
        this.addComponent(b);
        this.buttons.add(b);
        return b;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        int pidx = (Integer)event.getButton().getData();
        int targetIndex = 0;
        if(pidx==-1){
            targetIndex = Math.max(1, this.model.getPageIndex()-1);
        }else if(pidx==-2){
            targetIndex = Math.min(this.model.getPageCount(), this.model.getPageIndex()+1);
        }else{
            targetIndex = pidx;
        }
        if(targetIndex==this.model.getPageIndex()) return;
        PagingItemClickEvent evt = new PagingItemClickEvent(event.getButton(), targetIndex);
        this.fireEvent(evt);
    }

    public void addPagingItemClickListener(PageItemClickListener itemClickListener){
        this.addListener(PagingItemClickEvent.class, itemClickListener, PageItemClickListener.PAGINGITEM_CLICK_METHOD);
    }

    public class PagingItemClickEvent extends Event {
        private static final long serialVersionUID = -6171676700130768248L;
        private int pageIndex;
        public PagingItemClickEvent(Component comp, int pageIndex) {
            super(comp);
            this.pageIndex = pageIndex;
        }
        public int getPageIndex() {
            return pageIndex;
        }

    }

    public interface PageItemClickListener extends Serializable {

        public static final Method PAGINGITEM_CLICK_METHOD = ReflectTools
                .findMethod(PageItemClickListener.class, "pagingItemClick",
                        PagingItemClickEvent.class);

        public void pagingItemClick(PagingItemClickEvent event);

    }

}