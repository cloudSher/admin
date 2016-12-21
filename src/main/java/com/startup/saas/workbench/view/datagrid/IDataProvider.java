package com.startup.saas.workbench.view.datagrid;

import java.util.List;

/**
 * @author shixin
 *
 */
public interface IDataProvider {

    public int getDataCount();

    public List<?> getDataList(DataListRequest request);

}