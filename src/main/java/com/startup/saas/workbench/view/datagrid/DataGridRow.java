package com.startup.saas.workbench.view.datagrid;

/**
 * @author shixin
 *
 */
public class DataGridRow {

    private Object rowId;
    private Object[] datas;

    public DataGridRow() {
    }


    public DataGridRow(Object rowId, Object[] datas) {
        super();
        this.rowId = rowId;
        this.datas = datas;
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



}
