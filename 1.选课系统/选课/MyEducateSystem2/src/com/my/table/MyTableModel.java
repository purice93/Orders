package com.my.table;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
//能够增加Jtable的行数据 获取行数据
public class MyTableModel extends AbstractTableModel
{
	 private JTable table;
	 
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return table.getRowCount();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return table.getColumnCount();
	}

	

	public MyTableModel(JTable table) {
		super();
		this.table = table;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return table.getValueAt(rowIndex, columnIndex);
	}
	public void setValueAt(Object data,int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		table.setValueAt(data, rowIndex, columnIndex);
	}
	public Object [] getRow(int rowIndex)
	{
		int len=table.getColumnCount();
		Object data[]=new Object[len];
		for(int i=0;i<len;i++ )
		{
			data[i]=getValueAt(rowIndex,i);
		}
		return data;
	}
	public void addRow(Object data[],int rowIndex)
	{
		int len=table.getColumnCount();
		for(int i=0;i<len;i++)
		{
			table.setValueAt(data[i], rowIndex, i);
		}
	}
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	public void clearAllData()
	{
		int len=table.getColumnCount();
		int len2=table.getRowCount();
		
		for(int j=0;j<len2;j++){
			
			Object data[]=this.getRow(j);
			if(data[0]!=null){//
				for(int i=0;i<len;i++)
				{
					if(data[i] instanceof Boolean)
					{
					setValueAt(false,j, i);
					}
					else
					{
					setValueAt("",j, i);
					}
				}
			}
		}
	}
}
