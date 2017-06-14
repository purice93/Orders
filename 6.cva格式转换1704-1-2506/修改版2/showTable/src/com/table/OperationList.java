package com.table;

import javax.swing.table.AbstractTableModel;

/*
 * 操作流程的数据源类
 * 
 */
public class OperationList extends AbstractTableModel {
	public String[] titles = null;
	public Object[][] OperationInfo = {}; //

	public int getRowCount() {
		return 0;
	}

	public int getColumnCount() {
		return 0;
	}

	//
	@Override
	public Object getValueAt(int arg0, int arg1) {
		return null;
	}
}
