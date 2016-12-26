package com.lastartupsaas.workbench.view.datagrid;

/**
 * @author shixin
 *
 */
public class DataListRequest {

    private int pageIndex;
    private int pageSize;
    private String orderClause;

    public int getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public String getOrderClause() {
        return orderClause;
    }
    public void setOrderClause(String orderClause) {
        this.orderClause = orderClause;
    }



}