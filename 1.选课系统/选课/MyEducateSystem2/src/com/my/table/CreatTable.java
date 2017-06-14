package com.my.table;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class CreatTable {
	public CreatTable() {
		super();
		
	}

	private JTable table;
	public static JTable creatTable(String[] columnNames,Object cellData[][],Class[] classType)
	{
		//将第一列置为复选框列 其余为String类型
		JTable table=new JTable(cellData, columnNames);
//		table.setModel(new DefaultTableModel());
		TableColumnModel cmodel=table.getColumnModel();
		TableColumn   aColumn  ;  
		for(int i=0;i<columnNames.length;i++){
		aColumn=cmodel.getColumn(i); 
		aColumn.setCellEditor(table.getDefaultEditor(classType[i]));   
		aColumn.setCellRenderer(table.getDefaultRenderer(classType[i]));}
		return table;
	}
}
