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
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
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

public class AdmintratorWindow extends JFrame implements ActionListener {
	//设置主窗口长和宽
	private static final int WINDOWWIDE = 600;
	private static final int WINDOWHIGH = 420;

	//主窗口对象、右侧图表格式、图表对象
	private JFrame frame = new JFrame();
	private DefaultTableModel tableModel;
	private JTable table = null;

	//标题控件
	private JLabel jText = null;
	
	//两个按钮控件（获取IP、端口、mac地址信息；输出txt文件）
	private JButton noMacBtn = null;
	private JButton macBtn = null;

	//主窗口构造方法
	public AdmintratorWindow() {
		this.twoButton();//左侧交互面板
		//设置主窗口名
		PublicWindowSet.windowAttribute(this, WINDOWWIDE, WINDOWHIGH, "信息查询");
	}

	// 左边窗口交互栏
	public void twoButton() {
		// 1).添加一个文本提示标签
		jText = new JLabel("请选择功能：1.获取名称-IP-MAC地址——2.只获取特定IP的开放端口");
		jText.setFont(new Font("宋体", Font.BOLD, 16));
		jText.setBounds(30, 40, 600, 50);
		this.add(jText);

		// 3).两个操作按钮
		noMacBtn = new JButton("获取名称-IP-MAC地址");
		macBtn = new JButton("只获取特定IP的开放端口");
		
		noMacBtn.setBounds(30, 200, 400, 25);
		macBtn.setBounds(30, 230, 400, 25);
		
		this.add(noMacBtn);
		this.add(macBtn);

		//添加监听响应
		this.noMacBtn.addActionListener(this);
		this.macBtn.addActionListener(this);

		
		// 4).设置左右分隔标签
		JLabel awayLabel = new JLabel();
		awayLabel.setBounds(200, 50, 10, 450);
		this.add(awayLabel);
	}

		//监听按钮响应方法
		public void actionPerformed(ActionEvent e) {
			// 1).“获取信息”按钮响应处理
			if (e.getSource() == this.noMacBtn) {
				new NoPortWindow();
			}

			// 2)."输出"响应按钮
			if (e.getSource() == this.macBtn) {
				new PortWindow();
			}
		}
}
