package com.startup.saas.workbench.view.datagrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shixin
 *
 */
public class DataGridModel {

    private IDataGridRowConverter gridRowConverter;
    private IDataProvider dataProvider;
    private IActionCommandHandler commandHandler;

    private boolean withInfoSection;

    private List<DataOrderField> orderFields;

    private List<ActionCommand> listActionCommands;
    private List<ActionCommand> itemActionCommands;
    private List<ActionCommand> commonActionCommands;

    private List<DataGridColumn> gridColumns;

    private Map<String, String> categoryIconMappings;

    private int pageSize;

    public DataGridModel() {

        this.withInfoSection = false;
        this.orderFields = new ArrayList<DataOrderField>();
        this.listActionCommands = new ArrayList<ActionCommand>();
        this.itemActionCommands = new ArrayList<ActionCommand>();
        this.commonActionCommands = new ArrayList<ActionCommand>();
        this.gridColumns = new ArrayList<DataGridColumn>();
        this.categoryIconMappings = new HashMap<String, String>();
        this.pageSize = 50;
    }

    public DataGridModel addColumn(DataGridColumn column){
        this.gridColumns.add(column);
        return this;
    }

    public DataGridModel addOrderField(DataOrderField orderField){
        this.orderFields.add(orderField);
        return this;
    }

    public DataGridModel addListAction(ActionCommand actionCommand){
        if(!this.listActionCommands.contains(actionCommand)){
            this.listActionCommands.add(actionCommand);
        }
        return this;
    }

    public DataGridModel addItemAction(ActionCommand actionCommand){
        if(!this.itemActionCommands.contains(actionCommand)){
            this.itemActionCommands.add(actionCommand);
        }
        return this;
    }

    public DataGridModel addCommonAction(ActionCommand actionCommand){
        if(!this.commonActionCommands.contains(actionCommand)){
            this.commonActionCommands.add(actionCommand);
        }
        return this;
    }

    public List<String> getListActionCategories(){
        List<String> cs = new ArrayList<String>();
        for(ActionCommand c : this.listActionCommands) {
            String ct = c.getCategory();
            if(ct!=null && !cs.contains(ct)){
                cs.add(ct);
            }
        }
        return cs;
    }

    public List<ActionCommand> getActionsByCategory(String category) {
        List<ActionCommand> ls = new ArrayList<ActionCommand>();
        for(ActionCommand c : this.listActionCommands) {
            if((category==null && c.getCategory()==null) || (category!=null && category.equals(c.getCategory()))){
                ls.add(c);
            }
        }
        return ls;
    }

    public DataGridModel setActionCategoryIcon(String category, String iconUrl) {
        if(category!=null){
            this.categoryIconMappings.put(category, iconUrl);
        }
        return this;
    }

    public String getActionCategoryIcon(String category) {
        if(category==null) return null;
        return this.categoryIconMappings.get(category);
    }

    public IDataGridRowConverter getGridRowConverter() {
        return gridRowConverter;
    }
    public DataGridModel setGridRowConverter(IDataGridRowConverter gridRowConverter) {
        this.gridRowConverter = gridRowConverter;
        return this;
    }
    public IDataProvider getDataProvider() {
        return dataProvider;
    }
    public DataGridModel setDataProvider(IDataProvider dataProvider) {
        this.dataProvider = dataProvider;
        return this;
    }
    public boolean isWithInfoSection() {
        return withInfoSection;
    }
    public DataGridModel setWithInfoSection(boolean withInfoSection) {
        this.withInfoSection = withInfoSection;
        return this;
    }
    public List<DataOrderField> getOrderFields() {
        return orderFields;
    }
    public DataGridModel setOrderFields(List<DataOrderField> orderFields) {
        this.orderFields = orderFields;
        return this;
    }
    public List<ActionCommand> getListActionCommands() {
        return listActionCommands;
    }
    public DataGridModel setListActionCommands(List<ActionCommand> listActionCommands) {
        this.listActionCommands = listActionCommands;
        return this;
    }
    public List<ActionCommand> getItemActionCommands() {
        return itemActionCommands;
    }
    public DataGridModel setItemActionCommands(List<ActionCommand> itemActionCommands) {
        this.itemActionCommands = itemActionCommands;
        return this;
    }
    public IActionCommandHandler getCommandHandler() {
        return commandHandler;
    }
    public DataGridModel setCommandHandler(IActionCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        return this;
    }
    public List<DataGridColumn> getGridColumns() {
        return gridColumns;
    }
    public DataGridModel setGridColumns(List<DataGridColumn> gridColumns) {
        this.gridColumns = gridColumns;
        return this;
    }


    public int getPageSize() {
        return pageSize;
    }


    public DataGridModel setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public List<ActionCommand> getCommonActionCommands() {
        return commonActionCommands;
    }

    public DataGridModel setCommonActionCommands(List<ActionCommand> commonActionCommands) {
        this.commonActionCommands = commonActionCommands;
        return this;
    }

}
