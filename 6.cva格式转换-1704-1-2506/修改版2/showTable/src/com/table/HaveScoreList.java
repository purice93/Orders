package com.table;

import javax.swing.table.AbstractTableModel;
/*
 * 有分数表的数据源类
 * 
 */
public class HaveScoreList extends AbstractTableModel {
	public String[] titles = { "username", "stepnum", "date", "time", "grade" };
	public Object[][] scoreInfo = {}; //

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
