package com.lastartupsaas.workbench.view.datagrid;

import java.util.Date;

/**
 * @author shixin
 *
 */
public class TimestampDataItem {

    private Date time;
    private Number value;

    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public Number getValue() {
        return value;
    }
    public void setValue(Number value) {
        this.value = value;
    }


}