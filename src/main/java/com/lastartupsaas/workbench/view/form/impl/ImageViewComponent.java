package com.lastartupsaas.workbench.view.form.impl;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lastartupsaas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.server.FileResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

/**
 * 图片上传控件
 * 
 * @author lifeilong
 * @date: 2016-12-14
 */
public class ImageViewComponent extends BaseFormFieldEditor {

	private HorizontalLayout imageLayout;

	private String width;
	private String height;

	public ImageViewComponent() {
		this("160px", "200px");
	}

	public ImageViewComponent(String width, String height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public void setValue(Object value) {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setSpacing(true);
		vLayout.addStyleName("draft-thumb");
		if (value != null) {
			String[] values = value.toString().split("|");
			if (values.length != 0) {
				for (String imageName : values) {
					Pattern pattern = Pattern.compile(".+(.jpeg|.jpg|.gif|.bmp|.png)$");
					Matcher matcher = pattern.matcher(imageName.toLowerCase());
					if (matcher.matches()) {
						File file = new File(imageName);
						if (file.exists()) {
							Image image = new Image(null, new FileResource(file));
							image.setWidth(width);
							image.setHeight(height);
							vLayout.addComponent(image);
						}
					}
				}
			}
		} else {
			Image image = new Image(null, new ThemeResource("img/common/img_add.png"));
			image.setWidth(width);
			image.setHeight(height);
			vLayout.addComponent(image);
		}
		imageLayout.addComponent(vLayout);
	}

	@Override
	protected Component createComponent() {
		this.imageLayout = new HorizontalLayout();
		this.imageLayout.setSpacing(true);
		setValue(null);
		return this.imageLayout;
	}

	@Override
	public String validate() {
		return null;
	}

	@Override
	public void resetValue() {
	}

	@Override
	public Object getValue() {
		return null;
	}
}