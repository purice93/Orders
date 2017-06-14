package com.windows.fourInput;
/*
 * 添加窗口
 */
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.dao.EmployeeDAO;
import com.entity.Employee;
import com.windows.ManagementWindow;
import com.windows.PublicWindowSet;

public class SelectWindows extends JFrame implements ActionListener {
	private static final int WINDOWWIDE = 400;
	private static final int WINDOWHIGH = 300;
	private ManagementWindow managementWindow = null;
	private JLabel jLabel2 = null;

	private JTextField jText2 = null;

	private JButton getItemsBtn = null;

	public SelectWindows(ManagementWindow managementWindow) {
		this.managementWindow = managementWindow;
		this.liftInformation();
		PublicWindowSet.windowAttribute(this, WINDOWWIDE, WINDOWHIGH, "添加员工");
	}

	public void liftInformation() {
		// 1).
		jLabel2 = new JLabel("姓名：");
		jLabel2.setFont(new Font("黑体", Font.BOLD, 16));
		jLabel2.setBounds(30, 70, 120, 25);
		this.add(jLabel2);

		// 2).
		jText2 = new JTextField();

		jText2.setFont(new Font("黑体", Font.BOLD, 16));

		jText2.setBounds(160, 70, 120, 25);

		this.add(jText2);
		// 3).
		getItemsBtn = new JButton("查找员工");

		getItemsBtn.setBounds(30, 190, 120, 25);

		this.add(getItemsBtn);

		//
		this.getItemsBtn.addActionListener(this);

		// 4).
		JLabel awayLabel = new JLabel();
		awayLabel.setBounds(200, 50, 10, 450);
		this.add(awayLabel);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.getItemsBtn) {
			EmployeeDAO employeeDao = new EmployeeDAO();
			String name = jText2.getText();
			Employee employee = new Employee();
			
			employee.setName(name);
			List<Employee> employeeList = employeeDao.select(employee);
			/*if(flag==true) {
				PublicWindowSet.promptPopUp("添加员工成功!!!", "提示", this);
			}*/
			managementWindow.setdList(employeeList);
			managementWindow.flushUserWindow(managementWindow.getdList());
			PublicWindowSet.promptPopUp("查找用户成功!!!", "promptʾ", this);
			this.dispose();
		}
	}

}
