package com.startup.saas.workbench.widgets;

/**
 * Author: alvin
 * Date: 2016-08-30
 */
public class PageNavigatorModel {

    private Integer pageIndex;

    private Integer pageCount;

    private Integer pageSize;

    private Integer count;

    public PageNavigatorModel(Integer pageIndex, Integer pageCount, Integer pageSize, Integer count) {
		super();
		this.pageIndex = pageIndex;
		this.pageCount = pageCount;
		this.pageSize = pageSize;
		this.count = count;
	}

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
    
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
