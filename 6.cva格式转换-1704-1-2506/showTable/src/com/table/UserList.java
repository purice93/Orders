package com.table;

import javax.swing.table.AbstractTableModel;

/*
 * ԭʼ���ݱ������Դ
 * 
 */
public class UserList extends AbstractTableModel {
	public String[] titles = { "username", "udate", "utime", "tcode" };
	public Object[][] userInfo = {}; //

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
