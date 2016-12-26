package com.lastartupsaas.workbench.view.datagrid;

/**
 * @author shixin
 *
 */
public interface IActionCommandHandler {

    public void performAction(ActionCommand command, Object... parameters);

}

