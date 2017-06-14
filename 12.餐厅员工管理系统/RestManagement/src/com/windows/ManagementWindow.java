package com.windows;
/**
 * 餐厅员工管理窗口
 *
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.dao.EmployeeDAO;
import com.entity.Employee;
import com.table.UserList;
import com.windows.fourInput.AddWindows;
import com.windows.fourInput.DeleteWindows;
import com.windows.fourInput.ModifyWindows;
import com.windows.fourInput.SelectWindows;

public class ManagementWindow extends JFrame implements ActionListener {
	private static final int WINDOWWIDE = 700;
	private static final int WINDOWHIGH = 640;

	// 定义员工信息图表和图标的样式（model）
	private DefaultTableModel tableModel;
	private JTable table = null;

	// 定义一个滑动窗口（存放图表table）
	private JScrollPane scr = null;
	
	// 定义一个员工信息list，用于存放数据库中的员工，并将信息显示在table中
	private List<Employee> dList = new LinkedList<>();
	
	// 四个按钮
	private JButton addBtn = new JButton("添加员工");
	private JButton deleteBtn = new JButton("删除员工");
	private JButton modifyBtn = new JButton("修改员工");
	private JButton selectBtn = new JButton("查找员工");

	// 构造函数
	public ManagementWindow() {
		this.tableInformation(); // 加载图表显示
		PublicWindowSet.windowAttribute(this, WINDOWWIDE, WINDOWHIGH, "ͨ餐厅员工管理系统");
	}

	// 图表窗口
	public void tableInformation() {

		// 添加4个按钮和面板（4个按钮存放在面板中）
		JPanel toolBar = new JPanel();
		toolBar.add(this.addBtn);
		toolBar.add(this.deleteBtn);
		toolBar.add(this.modifyBtn);
		toolBar.add(this.selectBtn);

		// 向主窗口中添加按钮面板，位置为下面（北部-north）
		this.add(toolBar, BorderLayout.NORTH);

		// 设置按钮监控
		this.addBtn.addActionListener(this);
		this.deleteBtn.addActionListener(this);
		this.modifyBtn.addActionListener(this);
		this.selectBtn.addActionListener(this);

		// 设置图表窗口信息源，信息格式来自UserList类中
		this.tableModel = new DefaultTableModel(new UserList().userInfo, new UserList().titles);
		this.table = new JTable(this.tableModel);
		
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 自动调整格式
		table.getTableHeader().setReorderingAllowed(false);// 设置图表是否可以拖动

		// 向主窗口中添加图表面板
		scr = new JScrollPane();
		scr.setViewportView(this.table);
		this.add(scr, BorderLayout.CENTER);// 位置
		
		// 初始启动时，设置数据来源dList为数据库中所有的数据
		setdList(new EmployeeDAO().selectAll());// 获取数据库中所有的员工数据
		flushUserWindow(getdList());// 刷新，加载数据库数据到图表中显示
	}

	// 监控按钮响应
	public void actionPerformed(ActionEvent e) {
		
		// 分别监听增删改查按钮，生成对应的操作界面
		if (e.getSource() == this.addBtn) {
			new AddWindows(this); // 生成添加界面（下面类似）
		}
		
		else if(e.getSource() == this.deleteBtn) {
			new DeleteWindows(this);
		}
		
		else if(e.getSource() == this.modifyBtn) {
			new ModifyWindows(this); 
		}
		
		else {
			new SelectWindows(this);
		}
	}

	// 刷新或者加载数据库数据到图表信息窗口
	public void flushUserWindow(List<Employee> dList) {
		// 首先清空图表信息
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
		}
		
		// 遍历加载数据库数据到图表中
		Employee employee = new Employee();
		for (int i = 0; i < dList.size(); i++) {
			employee = dList.get(i);

			this.tableModel.addRow(new Object[] { employee.getEmployeeID(), employee.getName(),employee.getGender(),employee.getAge(),employee.getBirthday(),employee.getPosition(),employee.getSalary()});
		}
	}

	// dList为从数据库读取的数据，以下两个函数分别设置和获取dList数据
	public List<Employee> getdList() {
		return dList;
	}

	public void setdList(List<Employee> dList) {
		this.dList = dList;
	}

	
}
