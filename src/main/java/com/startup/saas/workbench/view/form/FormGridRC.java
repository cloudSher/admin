package com.startup.saas.workbench.view.form;

/**
 * @author shixin
 *
 */
public class FormGridRC {
    private int row;
    private int col;
    private int row2;
    private int col2;

    public FormGridRC() {
    }

    public FormGridRC(int row, int col, int row2, int col2) {
        super();
        this.row = row;
        this.col = col;
        this.row2 = row2;
        this.col2 = col2;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public int getRow2() {
        return row2;
    }
    public void setRow2(int row2) {
        this.row2 = row2;
    }
    public int getCol2() {
        return col2;
    }
    public void setCol2(int col2) {
        this.col2 = col2;
    }

}