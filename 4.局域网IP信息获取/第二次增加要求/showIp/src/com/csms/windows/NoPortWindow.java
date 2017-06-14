package com.csms.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.csms.IP;

public class NoPortWindow extends JFrame implements ActionListener {
	// 存储表格信息
	private Information information = new Information();

	// 设置主窗口长和宽
	private static final int WINDOWWIDE = 600;
	private static final int WINDOWHIGH = 420;

	// 主窗口对象、右侧图表格式、图表对象
	private JFrame frame = new JFrame();
	private DefaultTableModel tableModel;
	private JTable table = null;

	// txt文件对象
	private File file1 = null;

	// 标题控件
	private JLabel jText = null;

	// 两个文本输入框（输入IP范围）
	private JTextField jTextStart = null;
	private JTextField jTextEnd = null;

	// 两个按钮控件（获取IP、端口、mac地址信息；输出txt文件）
	private JButton getItemsBtn = null;
	private JButton outputBtn = null;

	// 保存所有的信息条，保存单个信息条
	private List<LinkedList<String>> strList = new LinkedList<LinkedList<String>>();
	private LinkedList<String> infoList = null;

	// 主窗口构造方法
	public NoPortWindow() {
		this.liftInformation();// 左侧交互面板
		this.rigthInformation();// 右侧图表显示面板

		// 设置主窗口名
		PublicWindowSet.windowAttribute(this, WINDOWWIDE, WINDOWHIGH, "信息查询");
	}

	// 左边窗口交互栏
	public void liftInformation() {
		// 1).添加一个文本标签
		jText = new JLabel("请输入IP范围");
		jText.setFont(new Font("宋体", Font.BOLD, 16));
		jText.setBounds(30, 40, 230, 50);
		this.add(jText);

		// 2).两个文本输入框
		jTextStart = new JTextField();
		jTextEnd = new JTextField();

		jTextStart.setFont(new Font("宋体", Font.BOLD, 16));
		jTextEnd.setFont(new Font("宋体", Font.BOLD, 16));

		jTextStart.setBounds(30, 80, 120, 25);
		jTextEnd.setBounds(30, 110, 120, 25);

		this.add(jTextStart);
		this.add(jTextEnd);

		// 3).两个操作按钮
		getItemsBtn = new JButton("获取信息");
		outputBtn = new JButton("导出txt");

		getItemsBtn.setBounds(30, 200, 120, 25);
		outputBtn.setBounds(30, 230, 120, 25);

		this.add(getItemsBtn);
		this.add(outputBtn);

		// 添加监听响应
		this.getItemsBtn.addActionListener(this);
		this.outputBtn.addActionListener(this);

		// 4).设置左右分隔标签
		JLabel awayLabel = new JLabel();
		awayLabel.setBounds(200, 50, 10, 450);
		this.add(awayLabel);
	}

	// 右部列表信息
	public void rigthInformation() {
		// 设置JTabel的默认类型（数据来源、图表标题）
		this.tableModel = new DefaultTableModel(new infoList().Info, new infoList().titles);
		// 添加JLabel组件
		this.table = new JTable(this.tableModel);

		// 设置列宽度
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(190);

		// 禁止JLabel组件随着窗口的大小而改变
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// JTabel组件表格的显示尺寸
		table.setPreferredScrollableViewportSize(new Dimension(420, 0));

		// 使列表不可以整列的进行移动
		table.getTableHeader().setReorderingAllowed(false);

		// 使列表框可被编辑
		table.enable(true);

		// 给JTabel组件添加滑动条
		JScrollPane scr = new JScrollPane(this.table);

		// 将带滚动条的Tabel组件添加入面板的东部区域
		this.add(scr, BorderLayout.EAST);
	}

	// 图表数据信息
	class infoList extends AbstractTableModel {
		// 标题
		public String[] titles = { "主机名", "IP号", "MAC地址" };
		// 数据
		public Object[][] Info = {}; //

		public int getRowCount() {
			return 0;
		}

		public int getColumnCount() {
			return 0;
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {
			return null;
		}
	}

	// 监听按钮响应方法
	public void actionPerformed(ActionEvent e) {
		// 1).“获取信息”按钮响应处理
		if (e.getSource() == this.getItemsBtn) {
			// 获取文本起始IP地址
			String start = jTextStart.getText();
			String end = jTextEnd.getText();
			getinfo(start, end, this);
		}

		// 2)."输出"响应按钮
		if (e.getSource() == this.outputBtn) {
			try {
				file1 = new File("信息.txt");
				OutputStream outstream = new FileOutputStream(file1);
				outstream.write("主机名 IP号 MAC地址\n".getBytes());

				// 按行输出信息（信息间隔为空格）
				for (int rowNum = 0; rowNum < strList.size(); rowNum++) {
					String StrLine = strList.get(rowNum).get(0) + " " + strList.get(rowNum).get(1) + " "
							+ strList.get(rowNum).get(2) + "\n";
					outstream.write(StrLine.getBytes());
				}
				// 刷新、关闭输出流
				outstream.flush();
				outstream.close();

				// 输出成功提示框
				PublicWindowSet.promptPopUp("存储文件成功!!!", "提示", frame);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	// 刷新图表信息
	private void flushInfo() {
		// 清空图表历史信息
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
		}

		// 暂时存储单条信息
		List<String> list = new LinkedList<String>();

		// 将strList信息显示到表格中
		// strList = new LinkedList<LinkedList<String>>();
		if (strList != null) {
			for (int i = 0; i < strList.size(); i++) {
				list = strList.get(i);
				this.tableModel.addRow(new Object[] { list.get(0), list.get(1), list.get(2) });
			}
		}
	}

	// “获取信息”方法
	private void getinfo(String start, String end, NoPortWindow noPortWindow) {

		List<String> ipList = new LinkedList<String>();

		int k = 0;
		k = start.lastIndexOf(".");
		String index = start.substring(0, k + 1);
		String lift = start.substring(k + 1, start.length());
		String right = end.substring(k + 1, end.length());
		int first = Integer.valueOf(lift);// 端口起始
		int last = Integer.valueOf(right);// 端口结尾

		// 将ip范围添加到ipList中
		IP ip = new IP();
		ipList = ip.getPing(index, first, last);

		// 获取信息（ip、端口、mac地址）
		// 依次遍历IP号-->遍历端口号（以一个端口为单位）
		for (int j = 0; j < ipList.size(); j++) {
			infoList = new LinkedList<String>();
			// 1).添加主机名
			infoList.add(information.getHostnames(ipList.get(j)));
			// 2).添加IP号
			infoList.add(String.valueOf(ipList.get(j)));
			// 3).添加MAC地址
			try {
				String mac = information.getMac(ipList.get(j));
				infoList.add(mac);
			} catch (Exception e) {
				e.printStackTrace();
				infoList.add("null");
			}
			// 将整条信息添加到strList中
			strList.add(infoList);
		}
		// 刷新加载显示到右侧图表中
		flushInfo();
	}
}
