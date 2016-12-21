package com.startup.saas.workbench.view.form.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mozilla.javascript.tools.debugger.Main;

import com.startup.saas.workbench.view.form.BaseFormFieldEditor;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

/**
 * 图片上传控件
 * 
 * @author lifeilong
 * @date: 2016-12-14
 */
public class ImageUploadEditor extends BaseFormFieldEditor {

	private HorizontalLayout rootLayout;
	private HorizontalLayout imageLayout;
	private Upload upload;
	private ImageUploader receiver = new ImageUploader();

	private String width = "100%";
	private String fileUploadPath = ImageUploadEditor.class.getClassLoader().getResource("").getPath() + "/tmp/uploads/image/";
	private boolean multipleFlag = false;
    private String image_name_prefix = UUID.randomUUID().toString();
    private Set<String> image_names = new LinkedHashSet<>();

	public ImageUploadEditor() {
	}

	public ImageUploadEditor(String width) {
		super();
		this.width = width;
	}
	
	public ImageUploadEditor(boolean multipleFlag) {
		super();
		this.multipleFlag = multipleFlag;
	}
	
	public ImageUploadEditor(String width, boolean multipleFlag) {
		super();
		this.width = width;
		this.multipleFlag = multipleFlag;
	}

	@Override
	public void setValue(Object value) {
		if (value != null) {
			String[] values = value.toString().split("|");
			if (values.length != 0) {
				for (String imageName : values) {
					Pattern pattern = Pattern.compile(".+(.jpeg|.jpg|.gif|.bmp|.png)$");
					Matcher matcher = pattern.matcher(imageName.toLowerCase());
					if (matcher.matches()) {
						File file = new File(fileUploadPath + imageName);
						if (file.exists()) {
							imageLayout.addComponent(buildImageView(file));
						}
					}
				}
			}
		}
	}

	@Override
	public String validate() {
		if (super.validate() != null)
			return super.validate();
		return null;
	}

	@Override
	public void resetValue() {
		if (imageLayout != null) {
			imageLayout.removeAllComponents();
		}
		if (image_names != null && image_names.size() > 0) {
			for (String imageName : image_names) {
				image_names.remove(imageName);
				File file = new File(fileUploadPath + imageName);
				if (file.exists()) {
					file.delete();
				}
			}
		}
	}

	@Override
	public Object getValue() {
		if (image_names != null && image_names.size() > 0) {
			StringBuffer valueBuf = new StringBuffer();
			for (String imageName : image_names) {
				valueBuf.append(imageName);
				valueBuf.append("|");
			}
			return valueBuf.toString();
		}
		return null;
	}

	@Override
	protected Component createComponent() {
		this.rootLayout = new HorizontalLayout();
		rootLayout.setSpacing(true);

		imageLayout = new HorizontalLayout();
		imageLayout.setSpacing(true);

		this.upload = new Upload(null, receiver);
		this.upload.setButtonCaption("点击上传");
		this.upload.setImmediate(true);
		this.upload.addSucceededListener(receiver);
		upload.addStartedListener(new Upload.StartedListener() {
			@Override
			public void uploadStarted(StartedEvent event) {
				Pattern pattern = Pattern.compile(".+(.jpeg|.jpg|.gif|.bmp|.png)$");
				Matcher matcher = pattern.matcher(event.getFilename().toLowerCase());
				if (!matcher.matches()) {
					upload.interruptUpload();
					new Notification("文件格式错误", Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
				}
			}
		});
		rootLayout.addComponents(imageLayout, upload);
		rootLayout.setExpandRatio(upload, 1);
		return this.rootLayout;
	}

	class ImageUploader implements Receiver, SucceededListener {
		public File file;

		public OutputStream receiveUpload(String filename, String mimeType) {
			// Create upload stream
			FileOutputStream fos = null; // Stream to write to
			try {
				File dir = new File(fileUploadPath);
				if (!dir.exists() && !dir.isDirectory())
					dir.mkdirs();
				if (multipleFlag) {
					image_name_prefix = UUID.randomUUID().toString();
				}
				// Open the file for writing.
				filename = image_name_prefix + filename.substring(filename.lastIndexOf("."));
				image_names.add(filename);
				file = new File(fileUploadPath + filename);
				fos = new FileOutputStream(file);
			} catch (final java.io.FileNotFoundException e) {
				new Notification("Could not open file<br/>", e.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
				return null;
			}
			return fos; // Return the output stream to write to
		}

		public void uploadSucceeded(SucceededEvent event) {
			// Show the uploaded file in the image viewer
			if (!multipleFlag) {
				imageLayout.removeAllComponents();
			}
			imageLayout.addComponent(buildImageView(file));
		}
	};

	private Component buildImageView(File file) {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setSpacing(true);
		vLayout.addStyleName("draft-thumb");

		Image image = new Image(null, new FileResource(file));
		image.setWidth(160.0f, Unit.PIXELS);
		image.setHeight(200.0f, Unit.PIXELS);
		image.setDescription("点击预览");
		vLayout.addComponent(image);

		final Button delete_btn = new Button("×");
		delete_btn.setDescription("删除");
		delete_btn.setPrimaryStyleName("delete-button");
		delete_btn.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				imageLayout.removeComponent(vLayout);
				if (file.exists()) {
					file.delete();
				}
			}
		});
		vLayout.addComponent(delete_btn);
		return vLayout;
	}
}