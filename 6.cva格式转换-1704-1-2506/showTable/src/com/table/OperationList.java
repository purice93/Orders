package com.table;

import javax.swing.table.AbstractTableModel;

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
