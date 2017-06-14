package com.windows.fourInput;
/*
 * 删除窗口
 */
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.dao.EmployeeDAO;
import com.entity.Employee;
import com.windows.ManagementWindow;
import com.windows.PublicWindowSet;

public class DeleteWindows extends JFrame implements ActionListener {
	private static final int WINDOWWIDE = 400;
	private static final int WINDOWHIGH = 300;
	private ManagementWindow managementWindow = null;
	private JLabel jLabel1 = null;

	private JTextField jText1 = null;

	private JButton getItemsBtn = null;

	public DeleteWindows(ManagementWindow managementWindow) {
		this.managementWindow = managementWindow;
		this.liftInformation();
		PublicWindowSet.windowAttribute(this, WINDOWWIDE, WINDOWHIGH, "删除员工");
	}

	public void liftInformation() {
		// 1).
		jLabel1 = new JLabel("id：");
		jLabel1.setFont(new Font("黑体", Font.BOLD, 16));
		jLabel1.setBounds(30, 40, 120, 25);
		this.add(jLabel1);

		// 2).
		jText1 = new JTextField();

		jText1.setFont(new Font("黑体", Font.BOLD, 16));
		jText1.setBounds(160, 40, 120, 25);

		this.add(jText1);

		// 3).
		getItemsBtn = new JButton("删除员工");

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
			String id = jText1.getText();
			Employee employee = new Employee();
			
			employee.setEmployeeID(id);
			boolean flag = employeeDao.delete(employee);
			/*if(flag==true) {
				PublicWindowSet.promptPopUp("添加员工成功!!!", "提示", this);
			}*/
			managementWindow.setdList(new EmployeeDAO().selectAll());
			managementWindow.flushUserWindow(managementWindow.getdList());
			PublicWindowSet.promptPopUp("删除用户成功!!!", "promptʾ", this);
			this.dispose();
		}
	}

}
