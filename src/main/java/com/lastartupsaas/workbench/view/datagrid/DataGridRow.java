package com.lastartupsaas.workbench.view.datagrid;

import java.util.List;

/**
 * @author shixin
 *
 */
public class DataGridRow {

	private Object rowId;
	private Object[] datas;
	private List<?> childList;

	public DataGridRow() {
	}

	public DataGridRow(Object rowId, Object[] datas) {
		super();
		this.rowId = rowId;
		this.datas = datas;
	}

	public DataGridRow(Object rowId, Object[] datas, List<?> childList) {
		super();
		this.rowId = rowId;
		this.datas = datas;
		this.childList = childList;
	}

	public Object getRowId() {
		return rowId;
	}

	public void setRowId(Object rowId) {
		this.rowId = rowId;
	}

	public Object[] getDatas() {
		return datas;
	}

	public void setDatas(Object[] datas) {
		this.datas = datas;
	}

	public List<?> getChildList() {
		return childList;
	}

	public void setChildList(List<?> childList) {
		this.childList = childList;
	}
}
