/**
 * 
 */
package com.startup.saas.workbench.view.form.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.startup.saas.workbench.view.form.BaseFormFieldEditor;
import com.startup.saas.workbench.view.form.IFormDataHelper;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.TreeTable;

/**
 * Checkbox Group
 * 
 * @author lifeilong
 */
public class TreeTableFieldEditor extends BaseFormFieldEditor {

	private TreeTable treeTable;
	private List<?> items;
	private String valuePropertyName;
	private String displayPropertyName;
	private String listPropertyName;
	private Map<Object, Object> itemsMap = new HashMap<>();

	public TreeTableFieldEditor() {
	}

	public TreeTableFieldEditor(List<?> items, String valuePropertyName, String displayPropertyName, String listPropertyName) {
		super();
		this.items = items;
		this.valuePropertyName = valuePropertyName;
		this.displayPropertyName = displayPropertyName;
		this.listPropertyName = listPropertyName;
	}

	@Override
	public void setValue(Object value) {
		this.resetValue();
		if (this.treeTable != null && value != null) {
			doSetValue((List<?>) value);
		}
	}

	private void doSetValue(List<?> values) {
		if (values != null && values.size() > 0) {
			IFormDataHelper dataHelper = this.getFormAgent().getDataHelper();
			for (Object obj : values) {
				Object itemId = dataHelper.getProperty(obj, valuePropertyName);
				if (itemId != null) {
					Item item = this.treeTable.getItem(itemId);
					if (item != null) {
						treeTable.setCollapsed(itemId, false);// 设置展开或收起
						Property property = item.getItemProperty("__SELECT__");
						if (property != null && property.getType() == CheckBox.class) {
							CheckBox cb = (CheckBox) property.getValue();
							changeCheckBoxValueWithoutListener(cb, true);
						}
					}
				}
				List<?> childrenItems = (List<?>) dataHelper.getProperty(obj, listPropertyName);
				doSetValue(childrenItems);
			}
		}
	}

	@Override
	public Object getValue() {
		if (this.treeTable == null)
			return null;

		removeUnSelectItems(items);
		
		if (items == null || items.size() == 0)
			return null;
		return items;
	}

	// 从items中迭代删除未选中的项，剩下的就是选中的值
	private void removeUnSelectItems(List<?> itemList) {
		if (itemList != null && itemList.size() > 0) {
			List<Object> dropList = new ArrayList<>();
			IFormDataHelper dataHelper = this.getFormAgent().getDataHelper();
			for (Object object : itemList) {
				if (object != null) {
					Object itemId = dataHelper.getProperty(object, valuePropertyName);
					Item item = this.treeTable.getItem(itemId);
					if (item != null) {
						Property property = item.getItemProperty("__SELECT__");
						if (property != null && property.getType() == CheckBox.class) {
							CheckBox cb = (CheckBox) property.getValue();
							if (cb.getValue() == false) {
								dropList.add(itemsMap.get(itemId));
							} else {
								List<?> childrenItemList = (List<?>) dataHelper.getProperty(object, listPropertyName);
								removeUnSelectItems(childrenItemList);
							}
						}
					}
				}
			}
			itemList.removeAll(dropList);
		}
	}

	@Override
	public String validate() {
		if (this.treeTable == null)
			return this.getRequiredErrorMessage();
		Object value = this.getValue();
		if (this.field.isRequired() && value == null)
			return this.getRequiredErrorMessage();
		return null;
	}

	@Override
	public void resetValue() {
		if (this.treeTable == null)
			return;
		Collection<?> itemIds = this.treeTable.getItemIds();
		if (itemIds == null || itemIds.isEmpty())
			return;
		Iterator<?> iterator = itemIds.iterator();
		while (iterator.hasNext()) {
			Object itemId = iterator.next();
			Item item = this.treeTable.getItem(itemId);
			Property property = item.getItemProperty("__SELECT__");
			if (property != null && property.getType() == CheckBox.class) {
				CheckBox cb = (CheckBox) property.getValue();
				cb.setValue(false);
			}
		}
	}

	@Override
	protected Component createComponent() {
		this.treeTable = new TreeTable();
		treeTable.setImmediate(true);
		treeTable.addContainerProperty("__SELECT__", CheckBox.class, false, "选择", null, Table.Align.LEFT);
		treeTable.addContainerProperty("名称", String.class, null);
		if (this.items != null && this.items.size() > 0) {
			buildTreeTable("all", this.items);
		}
		return treeTable;
	}

	// 组建TreeTable
	public void buildTreeTable(Object parentItemId, List<?> itemList) {
		IFormDataHelper dataHelper = this.getFormAgent().getDataHelper();
		for (Object item : itemList) {
			if (item != null) {
				Object itemId = dataHelper.getProperty(item, valuePropertyName);
				itemsMap.put(itemId, item);
				CheckBox checkBox = new CheckBox();
				checkBox.addValueChangeListener(new Property.ValueChangeListener() {
					@Override
					public void valueChange(Property.ValueChangeEvent event) {
						doSelectItems(itemId, (Boolean) event.getProperty().getValue());
					}
				});
				treeTable.addItem(new Object[] { checkBox, dataHelper.getProperty(item, displayPropertyName) }, itemId);
				treeTable.setCollapsed(itemId, true);// 设置展开或收起
				treeTable.setParent(itemId, parentItemId);
				List<?> items_1Level = (List<?>) dataHelper.getProperty(item, listPropertyName);
				if (items_1Level != null && items_1Level.size() > 0) {
					buildTreeTable(itemId, items_1Level);
				} else {
					treeTable.setChildrenAllowed(itemId, false);
				}
			}
		}
	}

	// 选择框改变事件
	private void doSelectItems(Object itemId, Boolean value) {
		if (value == true) {
			// 选择子checkbox时，同时选择父checkbox
			doSelectParentItems(itemId);
		}

		if (treeTable.hasChildren(itemId)) {
			Collection<?> childrenItemIds = treeTable.getChildren(itemId);
			for (Object childrenItemId : childrenItemIds) {
				Property property1 = treeTable.getItem(childrenItemId).getItemProperty("__SELECT__");
				if (property1 != null && property1.getType() == CheckBox.class) {
					CheckBox cb = (CheckBox) property1.getValue();
					cb.setValue(value);
				}
			}
		}
	}

	// 选择子checkbox时，同时选择父checkbox
	private void doSelectParentItems(Object itemId) {
		Object parentItemID = treeTable.getParent(itemId);
		if (parentItemID != null) {
			Item parentItem = treeTable.getItem(parentItemID);
			if (parentItem != null) {
				Property property = parentItem.getItemProperty("__SELECT__");
				if (property != null && property.getType() == CheckBox.class) {
					CheckBox cb = (CheckBox) property.getValue();
					changeCheckBoxValueWithoutListener(cb, true);
				}
			}
			doSelectParentItems(parentItemID);
		}
	}

	// 改变选择框的值并阻止listener
	private void changeCheckBoxValueWithoutListener(CheckBox checkBox, Boolean value) {
		List<?> listeners = (List<?>) checkBox.getListeners(Property.ValueChangeEvent.class);
		if (listeners != null && listeners.size() > 0) {
			Property.ValueChangeListener listener = (ValueChangeListener) listeners.get(0);
			checkBox.removeValueChangeListener(listener);
			checkBox.setValue(value);
			checkBox.addValueChangeListener(listener);
		} else {
			checkBox.setValue(value);
		}
	}

	public List<?> getItems() {
		return items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}

	public String getValuePropertyName() {
		return valuePropertyName;
	}

	public void setValuePropertyName(String valuePropertyName) {
		this.valuePropertyName = valuePropertyName;
	}

	public String getDisplayPropertyName() {
		return displayPropertyName;
	}

	public void setDisplayPropertyName(String displayPropertyName) {
		this.displayPropertyName = displayPropertyName;
	}

	public String getListPropertyName() {
		return listPropertyName;
	}

	public void setListPropertyName(String listPropertyName) {
		this.listPropertyName = listPropertyName;
	}
}
