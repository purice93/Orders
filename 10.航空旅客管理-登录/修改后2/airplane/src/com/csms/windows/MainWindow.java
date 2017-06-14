
package com.csms.windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.csms.dao.AdminitartorDAO;
import com.csms.dao.PassagerDAO;

public class MainWindow {
	JFrame frame = new JFrame();
	private static final int WINDOWSWIDE = 500;
	private static final int WINDOWSHIGH = 300;
	private static int flag = 0;

	JLabel psdLabel;
	JLabel textLabel;

	JRadioButton radioButton;
	private static String loginUserName;
	private static String loginPassword;
	ButtonGroup buttGroup = new ButtonGroup();
	JPanel southPanel = new JPanel();

	Icon icon = new ImageIcon("");

	public MainWindow() {

		JLabel label = new JLabel(icon);
		label.setBounds(70, 0, 260, 60);
		frame.add(label);
		southPanel.setBounds(60, 140, 200, 30);
		frame.add(southPanel);
		this.addButton1("登录", 1, frame);
		this.addButton2("乘客注册", 2, frame);

		PublicWindowSet.windowAttribute(frame, WINDOWSWIDE, WINDOWSHIGH, null, "登录注册系统");

	}

	private void addButton1(String buttonName, int flag, JFrame jfr) {
		JButton button = new JButton(buttonName);
		button.setBounds(140, 100, 100, 25);
		button.setFont(new Font("黑体", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginWindow();
			}
		});
		jfr.add(button);
	}
	
	private void addButton2(String buttonName, int flag, JFrame jfr) {
		JButton button = new JButton(buttonName);
		button.setBounds(260, 100, 100, 25);
		button.setFont(new Font("黑体", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterWindow();
			}
		});
		jfr.add(button);
	}


	private static int getFlag() {
		return flag;
	}

	private static void setFlag(int flag) {
		MainWindow.flag = flag;
	}

	public static String getLoginUserName() {
		return loginUserName;
	}

	public static void setLoginUserName(String loginUserName) {
		MainWindow.loginUserName = loginUserName;
	}

	public static String getLoginPassword() {
		return loginPassword;
	}

	public static void setLoginPassword(String loginPassword) {
		MainWindow.loginPassword = loginPassword;
	}
}