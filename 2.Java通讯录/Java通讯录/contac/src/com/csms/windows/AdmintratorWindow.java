/**
 * @program 主窗口
 *
 */

package com.csms.windows;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.csms.dao.PersionDao;
import com.csms.entity.Persion;

public class AdmintratorWindow extends JFrame implements ActionListener {
	private static final int WINDOWWIDE = 750;
	private static final int WINDOWHIGH = 400;

	private JFrame frame = new JFrame();
	private DefaultTableModel tableModel;
	private JTable table = null;

	private Frame f;
	private FileDialog fd;
	private File file1 = null;
	private List<Persion> pList = new LinkedList<Persion>();

	// 定义七个按钮
	private JButton loadFileBtn = new JButton("读取文件");
	private JButton insertPersionBtn = new JButton("插入名单");
	private JButton deletePersionBtn = new JButton("删除名单");
	private JButton modifyPersionBtn = new JButton("修改名单");
	private JButton sortPersionBtn = new JButton("排序名单");
	private JButton findPersionBtn = new JButton("查找名单");
	private JButton outputFileBtn = new JButton("输出文件");

	// 通讯录窗口
	public AdmintratorWindow() {

		// 主界面
		this.rigthInformation();
		PublicWindowSet.windowAttribute(this, WINDOWWIDE, WINDOWHIGH, "通讯录");
	}

	// 列表信息
	public void rigthInformation() {
		// 设置JTabel的默认类型
		this.tableModel = new DefaultTableModel(new PersionList().userInfo, new PersionList().titles);
		// 添加JLabel组件
		this.table = new JTable(this.tableModel);
		// 禁止JLabel组件随着窗口的大小而改变
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// JTabel组件表格的显示尺寸
		table.setPreferredScrollableViewportSize(new Dimension(690, 0));
		// 使列表不可以整列的进行移动
		table.getTableHeader().setReorderingAllowed(false);
		// 使列表框不可被编辑
		table.enable(false);
		// 给JTabel组件添加滑动条
		JScrollPane scr = new JScrollPane(this.table);

		// 设置面板
		JPanel toolBar = new JPanel();

		// 添加七个按钮
		toolBar.add(this.loadFileBtn);
		toolBar.add(this.insertPersionBtn);
		toolBar.add(this.deletePersionBtn);
		toolBar.add(this.modifyPersionBtn);
		toolBar.add(this.findPersionBtn);
		toolBar.add(this.sortPersionBtn);
		toolBar.add(this.outputFileBtn);

		// 将底部操作组件添加入面板的下部
		this.add(toolBar, BorderLayout.SOUTH);
		// 将带滚动条的Tabel组件添加入面板的左部区域
		this.add(scr, BorderLayout.EAST);

		// 添加按钮响应事件
		this.loadFileBtn.addActionListener(this);
		this.insertPersionBtn.addActionListener(this);
		this.deletePersionBtn.addActionListener(this);
		this.modifyPersionBtn.addActionListener(this);
		this.findPersionBtn.addActionListener(this);
		this.sortPersionBtn.addActionListener(this);
		this.outputFileBtn.addActionListener(this);

		// 清空表格
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
		}
	}

	// JTabel列表属性设置类
	class PersionList extends AbstractTableModel {
		public String[] titles = { "姓名", "号码", "地址", "邮箱" };
		public Object[][] userInfo = {}; // 定义数据

		public int getRowCount() {
			return 0;
		}

		public int getColumnCount() {
			return 0;
		}
		
		//默认重写方法
		@Override
		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	// 事件响应
	public void actionPerformed(ActionEvent e) {

		// 1.打开文件
		if (e.getSource() == this.loadFileBtn) {
			Persion per = null;
			List<String> array = new ArrayList<>();
			fd = new FileDialog(f, "Open", FileDialog.LOAD);// 创建并显示打开文件对话框
			fd.setVisible(true);

			// 以缓冲区方式读取文件内容
			try {

				file1 = new File(fd.getDirectory(), fd.getFile());
				FileReader fr = new FileReader(file1);
				BufferedReader br = new BufferedReader(fr);
				String aline;

				// 按行读取文本
				while ((aline = br.readLine()) != null) {
					array = Arrays.asList(aline.split(" "));
					per = new Persion();
					per.setName(array.get(0));
					per.setPhoneNumber(array.get(1));
					per.setAddress(array.get(2));
					per.setEmail(array.get(3));
					pList.add(per);
				}
				fr.close();
				br.close();
				flushWindow(pList);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		// 2.插入信息
		if (e.getSource() == this.insertPersionBtn) {
			setEnabled(false);
			persionInformationWindow piw = new persionInformationWindow(this, pList);
			setEnabled(true);
			flushWindow(pList);
		}

		// 3.删除信息
		if (e.getSource() == this.deletePersionBtn) {
			new deleteWindow(this, pList);
		}

		// 4.修改信息
		if (e.getSource() == this.modifyPersionBtn) {
			new modifyWindow(this, pList);
		}

		// 5.查找信息
		if (e.getSource() == this.findPersionBtn) {
			new findWindow(pList);
		}

		// 6.排序信息
		if (e.getSource() == this.sortPersionBtn) {
			new sortWindow(this, pList);
		}

		// 7.输出文件（直接输出到数据库）
		if (e.getSource() == this.outputFileBtn) {
			Persion pe = new Persion();
			PersionDao pd = new PersionDao();
			for (int i = 0; i < pList.size(); i++) {
				pe.setName(pList.get(i).getName());
				pe.setPhoneNumber(pList.get(i).getPhoneNumber());
				pe.setAddress(pList.get(i).getAddress());
				pe.setEmail(pList.get(i).getEmail());
				pd.insertPersion(pe);
			}
			PublicWindowSet.promptPopUp("输出文件成功!!!", "提示", frame);
		}
	}

	// 打开文件后，呈现通讯录信息（刷新）
	public void flushWindow(List<Persion> pList2) {
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
		}
		Persion pe2 = new Persion();
		for (int i = 0; i < pList2.size(); i++) {
			pe2 = pList2.get(i);
			this.tableModel
					.addRow(new Object[] { pe2.getName(), pe2.getPhoneNumber(), pe2.getAddress(), pe2.getEmail() });
		}
	}

}

// 添加信息界面
class persionInformationWindow {

	private JFrame frame = new JFrame();
	private AdmintratorWindow admintratorWindow = null;

	public persionInformationWindow(AdmintratorWindow admintratorWindow, List<Persion> pList) {
		this.admintratorWindow = admintratorWindow;
		this.displayInformation(this.frame, pList);
	}

	// 界面内容
	private void displayInformation(JFrame jfr, List<Persion> pList) {
		//输入提示
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "添加通讯录");
		PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "姓名:");
		PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "号码:");
		PublicWindowSet.addLabel(jfr, 16, 20, 130, 90, 30, "地址:");
		PublicWindowSet.addLabel(jfr, 16, 20, 160, 90, 30, "邮箱:");

		// 文本输入框
		JTextField name = new JTextField();
		PublicWindowSet.addTextField(name, 100, 70, 120, 25, jfr);
		JTextField phoneNumber = new JTextField();
		PublicWindowSet.addTextField(phoneNumber, 100, 100, 120, 25, jfr);
		JTextField address = new JTextField();
		PublicWindowSet.addTextField(address, 100, 130, 120, 25, jfr);
		JTextField email = new JTextField();
		PublicWindowSet.addTextField(email, 100, 160, 120, 25, jfr);

		PublicWindowSet.addTableList(jfr, null);// 占位，避免冲突

		JButton button = new JButton("确定添加");
		button.setBounds(75, 400, 100, 30);
		button.setFont(new Font("楷体", Font.PLAIN, 16));
		button.setContentAreaFilled(false);

		// 设置在面板底部（南）
		jfr.add(button, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 250, 300, "添加通讯录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 添加信息函数
				insertAction(pList, jfr, name, phoneNumber, address, email);
				// 刷新列表信息
				admintratorWindow.flushWindow(pList);
			}
		});
	}

	// 插入联系人
	private void insertAction(List<Persion> pList, JFrame jfr, JTextField name, JTextField phoneNumber,
			JTextField address, JTextField email) {
		Persion persion = new Persion();
		
		if ((name.getText() == null) || (phoneNumber.getText() == null) || (address.getText() == null)
				|| (email.getText() == null)) {
			PublicWindowSet.promptPopUp("输入不能为空!!!", "提示", jfr);
		}
		
		//获取面板文本信息
		persion.setName(name.getText());
		persion.setPhoneNumber(phoneNumber.getText());
		persion.setAddress(address.getText());
		persion.setEmail(email.getText());
		pList.add(persion);
		PublicWindowSet.promptPopUp("添加成功!!!", "提示", jfr);
		name.setText("");
		phoneNumber.setText("");
		address.setText("");
		email.setText("");
		jfr.setDefaultCloseOperation(jfr.DISPOSE_ON_CLOSE);
		jfr.dispose();
	}
}

// 删除信息
class deleteWindow {

	private JFrame frame = new JFrame();
	private AdmintratorWindow admintratorWindow;

	public deleteWindow(AdmintratorWindow awin, List<Persion> pList) {
		this.admintratorWindow = awin;
		this.displayInformation(this.frame, pList);
	}
	
	//删除联系人函数
	private void displayInformation(JFrame jfr, List<Persion> pList) {
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "删除通讯录");
		PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "姓名:");

		JTextField name = new JTextField();
		PublicWindowSet.addTextField(name, 100, 70, 120, 25, jfr);

		PublicWindowSet.addTableList(jfr, null);// 占位，避免冲突

		JButton button = new JButton("确定删除");
		button.setBounds(75, 400, 100, 30);
		button.setFont(new Font("楷体", Font.PLAIN, 16));
		button.setContentAreaFilled(false);

		// 设置在面板底部（南）
		jfr.add(button, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 250, 300, "删除通讯录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(pList, jfr, name);
				admintratorWindow.flushWindow(pList);
			}
		});
	}

	protected void delete(List<Persion> pList, JFrame jfr, JTextField name) {
		Persion persion = new Persion();
		if ((name.getText() == null)) {
			PublicWindowSet.promptPopUp("输入不能为空!!!", "提示", jfr);
		}

		String pname = name.getText();
		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).getName().equals(pname)) {
				pList.remove(i);
			}
		}
		PublicWindowSet.promptPopUp("删除成功!!!", "提示", jfr);
		name.setText("");
		jfr.dispose();
	}

}

// 修改信息
class modifyWindow {

	private JFrame frame = new JFrame();
	private AdmintratorWindow admintratorWindow = null;

	public modifyWindow(AdmintratorWindow awin, List<Persion> pList) {
		this.admintratorWindow = awin;
		this.displayInformation(this.frame, pList);
	}

	// 更改面板显示
	private void displayInformation(JFrame jfr, List<Persion> pList) {
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "修改通信录");
		PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "原始姓名:");
		PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "更改姓名:");
		PublicWindowSet.addLabel(jfr, 16, 20, 130, 90, 30, "更改号码:");
		PublicWindowSet.addLabel(jfr, 16, 20, 160, 90, 30, "更改地址:");
		PublicWindowSet.addLabel(jfr, 16, 20, 190, 90, 30, "更改邮箱:");

		JTextField bName = new JTextField();
		PublicWindowSet.addTextField(bName, 100, 70, 120, 25, jfr);
		JTextField name = new JTextField();
		PublicWindowSet.addTextField(name, 100, 100, 120, 25, jfr);
		JTextField phoneNumber = new JTextField();
		PublicWindowSet.addTextField(phoneNumber, 100, 130, 120, 25, jfr);
		JTextField address = new JTextField();
		PublicWindowSet.addTextField(address, 100, 160, 120, 25, jfr);
		JTextField email = new JTextField();
		PublicWindowSet.addTextField(email, 100, 190, 120, 25, jfr);

		PublicWindowSet.addTableList(jfr, null);// 占位，避免冲突

		JButton button = new JButton("确定修改");
		button.setBounds(75, 400, 100, 30);
		button.setFont(new Font("楷体", Font.PLAIN, 16));
		button.setContentAreaFilled(false);

		// 设置在面板底部（南）
		jfr.add(button, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 250, 300, "修改通讯录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyPersion(pList, jfr, bName, name, phoneNumber, address, email);
				admintratorWindow.flushWindow(pList);
			}
		});
	}

	// 修改联系人
	protected void modifyPersion(List<Persion> pList, JFrame jfr, JTextField bName, JTextField name,
			JTextField phoneNumber, JTextField address, JTextField email) {
		Persion persion = new Persion();
		if ((bName.getText() == null) || (name.getText() == null) || (phoneNumber.getText() == null)
				|| (address.getText() == null) || (email.getText() == null)) {
			PublicWindowSet.promptPopUp("输入不能为空!!!", "提示", jfr);
		}

		String pName = bName.getText();

		persion.setName(name.getText());
		persion.setPhoneNumber(phoneNumber.getText());
		persion.setAddress(address.getText());
		persion.setEmail(email.getText());

		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).getName().equals(pName)) {
				pList.set(i, persion);
				System.out.println(pList.get(i).getEmail());
			}
		}

		PublicWindowSet.promptPopUp("修改成功!!!", "提示", jfr);
		name.setText("");
		phoneNumber.setText("");
		address.setText("");
		email.setText("");
		jfr.dispose();
	}

}

// 查找信息
class findWindow {

	private JFrame frame = new JFrame();

	public findWindow(List<Persion> pList) {
		this.displayInformation(this.frame, pList);
	}

	private void displayInformation(JFrame jfr, List<Persion> pList) {
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "查找通讯录");
		PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "输入姓名:");
		PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "或者号码:");

		PublicWindowSet.addLabel(jfr, 16, 20, 130, 90, 30, "姓名:");
		PublicWindowSet.addLabel(jfr, 16, 20, 160, 90, 30, "号码:");
		PublicWindowSet.addLabel(jfr, 16, 20, 190, 90, 30, "地址:");
		PublicWindowSet.addLabel(jfr, 16, 20, 220, 90, 30, "邮箱:");

		JTextField fName = new JTextField();
		PublicWindowSet.addTextField(fName, 100, 70, 120, 25, jfr);
		JTextField fPhoneNumber = new JTextField();
		PublicWindowSet.addTextField(fPhoneNumber, 100, 100, 120, 25, jfr);
		JTextField name = new JTextField();
		PublicWindowSet.addTextField(name, 100, 130, 120, 25, jfr);
		JTextField phoneNumber = new JTextField();
		PublicWindowSet.addTextField(phoneNumber, 100, 160, 120, 25, jfr);
		JTextField address = new JTextField();
		PublicWindowSet.addTextField(address, 100, 190, 120, 25, jfr);
		JTextField email = new JTextField();
		PublicWindowSet.addTextField(email, 100, 220, 120, 25, jfr);

		PublicWindowSet.addTableList(jfr, null);// 占位，避免冲突

		JButton button = new JButton("查找");
		button.setBounds(75, 400, 100, 30);
		button.setFont(new Font("楷体", Font.PLAIN, 16));
		button.setContentAreaFilled(false);

		// 设置在面板底部（南）
		jfr.add(button, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 250, 300, "查找通讯录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findPersion(pList, jfr, fName, fPhoneNumber, name, phoneNumber, address, email);
			}
		});
	}

	protected void findPersion(List<Persion> pList, JFrame jfr, JTextField fname, JTextField fphoneNumber,
			JTextField name, JTextField phoneNumber, JTextField address, JTextField email) {
		Persion persion = new Persion();
		if ((fname.getText() == null) && (fphoneNumber.getText() == null)) {
			PublicWindowSet.promptPopUp("输入不能为空!!!", "提示", jfr);
		}

		if (fname.getText() != null) {
			for (int i = 0; i < pList.size(); i++) {
				if (pList.get(i).getName().equals(fname.getText())) {
					persion.setName(pList.get(i).getName());
					persion.setPhoneNumber(pList.get(i).getPhoneNumber());
					persion.setAddress(pList.get(i).getAddress());
					persion.setEmail(pList.get(i).getEmail());
				}
			}
		}

		if (fphoneNumber.getText() != null) {
			for (int i = 0; i < pList.size(); i++) {
				if (pList.get(i).getPhoneNumber().equals(fphoneNumber.getText())) {
					persion.setName(pList.get(i).getName());
					persion.setPhoneNumber(pList.get(i).getPhoneNumber());
					persion.setAddress(pList.get(i).getAddress());
					persion.setEmail(pList.get(i).getEmail());
				}
			}
		}

		name.setText(persion.getName());
		phoneNumber.setText(persion.getPhoneNumber());
		address.setText(persion.getAddress());
		email.setText(persion.getEmail());
	}
}

// 排序信息
class sortWindow extends JFrame {

	private JFrame frame = new JFrame();
	private AdmintratorWindow admintratorWindow = null;
	private JPanel toolB = new JPanel();

	public sortWindow(AdmintratorWindow admintratorWindow, List<Persion> pList) {

		this.setLayout(new FlowLayout());
		this.admintratorWindow = admintratorWindow;
		this.displayInformation(this.frame, pList, toolB);
	}

	private void displayInformation(JFrame jfr, List<Persion> pList, JPanel toolB2) {
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "排序通讯录");

		PublicWindowSet.addTableList(jfr, null);// 占位，避免冲突

		JButton buttonByName = new JButton("按姓名排序");
		JButton buttonByPhone = new JButton("按号码排序");

		buttonByName.setBounds(75, 400, 100, 30);
		buttonByPhone.setBounds(75, 200, 100, 30);
		buttonByName.setFont(new Font("楷体", Font.PLAIN, 16));
		buttonByPhone.setFont(new Font("楷体", Font.PLAIN, 16));
		buttonByName.setContentAreaFilled(false);
		buttonByPhone.setContentAreaFilled(false);

		toolB.add(buttonByPhone);
		toolB.add(buttonByName);

		// 将底部操作组件添加入面板的南部区域
		jfr.add(toolB, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 250, 300, "排序通讯录");
		buttonByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortPersionByName(pList);
				admintratorWindow.flushWindow(pList);
			}
		});

		buttonByPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortPersionByPhone(pList);
				admintratorWindow.flushWindow(pList);
			}
		});
	}

	
	//对联系人进行排序（姓名）
	//大小比较函数使用Java自带compareTo函数
	protected void sortPersionByName(List<Persion> pList) {
		Persion tPersion = null;
		for (int j = pList.size() - 1; j > 0; j--) {
			for (int i = 1; i < j+1; i++) {
				if ((pList.get(i).getName().compareTo(pList.get(i - 1).getName())) < 0) {
					tPersion = pList.get(i);
					pList.set(i, pList.get(i - 1));
					pList.set(i - 1, tPersion);
				}
			}
		}
	}
	
	//对联系人进行排序（电话号码）
	protected void sortPersionByPhone(List<Persion> pList) {
		Persion tPersion = null;
		for (int j = pList.size() - 1; j > 0; j--) {
			for (int i = 1; i < j+1; i++) {
				if ((pList.get(i).getPhoneNumber().compareTo(pList.get(i - 1).getPhoneNumber())) < 0) {
					tPersion = pList.get(i);
					pList.set(i, pList.get(i - 1));
					pList.set(i - 1, tPersion);
				}
			}
		}
	}

}
