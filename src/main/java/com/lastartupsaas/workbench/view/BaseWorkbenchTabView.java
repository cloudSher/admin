package com.lastartupsaas.workbench.view;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Tab标签页
 * 
 * @author lifeilong
 * @date 2017-01-12
 */
public abstract class BaseWorkbenchTabView extends BaseWorkbenchView {

	private static final long serialVersionUID = 5140034466988789370L;

	protected Map<String, BaseWorkbenchView> viewsMap;

	@Override
	public void initView() {
		viewsMap = new LinkedHashMap<>();
		this.setupViews(this.viewsMap);

		if (viewsMap != null && !viewsMap.isEmpty()) {
			TabSheet tabSheet = new TabSheet();
			Iterator<String> iterator = viewsMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				BaseWorkbenchView view = viewsMap.get(key);

				tabSheet.addTab(view, key);
			}
			tabSheet.setWidth("100%");
			tabSheet.setHeight("100%");
			tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
			tabSheet.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
			this.getViewRoot().addComponent(tabSheet);
			this.getViewRoot().setExpandRatio(tabSheet, 1);
		}
	}

	protected abstract void setupViews(Map<String, BaseWorkbenchView> viewsMap);

	protected Label addLabel(String caption, HorizontalLayout layout) {
		Label lb = new Label(caption);
		lb.setSizeUndefined();
		layout.addComponent(lb);
		layout.setComponentAlignment(lb, Alignment.MIDDLE_CENTER);
		return lb;
	}

	@Override
	protected void showView(ViewContext vc) {
	}
}
