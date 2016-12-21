package com.startup.saas.workbench.view.datagrid;

import java.util.List;

/**
 * @author shixin
 *
 */
public class ListDataProvider implements IDataProvider {

    private List<?> items;

    public ListDataProvider(List<?> items) {
        super();
        this.items = items;
    }

    @Override
    public int getDataCount() {
        return this.items==null?0:this.items.size();
    }

    @Override
    public List<?> getDataList(DataListRequest request) {
        return this.items;
    }

}
