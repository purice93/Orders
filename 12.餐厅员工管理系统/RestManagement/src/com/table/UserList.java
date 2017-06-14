package com.table;

import javax.swing.table.AbstractTableModel;

/*
 * 图表数据源
 * 
 */
public class UserList extends AbstractTableModel {
	public Object[][] userInfo = {}; //
	public String[] titles = { "id", "姓名", "性别", "年龄", "出生日期", "职务", "薪水" };

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
