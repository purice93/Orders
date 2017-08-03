/**
 * @program 实现登录界面
 *
 */
package com.csms.windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.csms.dao.*;
import javax.swing.*;

// 学生选课系统登录界面
public class LoginWindow {
	JFrame frame = new JFrame();
	// 窗口的大小
	private static final int WINDOWSWIDE = 400;
	private static final int WINDOWSHIGH = 200;
	// 定义用户名输入框和密码框的组件变量
	final JTextField userText;
	final JPasswordField pswText;

	// 设置密码标签和用户名标签
	JLabel psdLabel;
	JLabel textLabel;

	// 单选按钮组件变量
	JRadioButton radioButton;
	// 用户登录名变量，通过这两个变量，在学生界面，老师界面来取得登陆的用户名和密码
	private static String loginUserName;
	private static String loginPassword;
	// 创建单选按钮组
	ButtonGroup buttGroup = new ButtonGroup();
	JPanel southPanel = new JPanel();

	public LoginWindow() {
		// 1.设置label标签
		JLabel label = new JLabel();
		label.setBounds(70, 0, 260, 60);
		frame.add(label);

		// 2.设置文本标签和文本框的位置
		// 用户名标签及用户名登录框，并将它们设置好位置，添加进面板
		PublicWindowSet.addLabel(frame, 16, 65, 70, 90, 30, "用户名:");
		userText = new JTextField();
		PublicWindowSet.addTextField(userText, 130, 70, 200, 30, frame);
		// 密码标签及密码登录框，并将它们设置好位置，添加进面板
		PublicWindowSet.addLabel(frame, 16, 65, 105, 90, 30, "密  码:");
		pswText = new JPasswordField();
		PublicWindowSet.addTextField(pswText, 130, 105, 200, 30, frame);

		// 3.设置单选按钮
		// 创建单选按钮
		southPanel.setBounds(60, 140, 200, 30);
		frame.add(southPanel);

		// 4.创建登录按钮
		this.addButton("登录", frame);

		// 5.设置窗口相关的属性,windowAttribute函数是封装在PublicWindowSet类中的窗口属性设置函数
		// 设置窗口的标题
		PublicWindowSet.windowAttribute(frame, WINDOWSWIDE, WINDOWSHIGH, null,
				"学生选课系统");
	}

	// 添加登录确认和取消按钮并同时设置按钮监听器
	private void addButton(String buttonName, final JFrame jfr) {
		JButton button = new JButton(buttonName);
		button.setBounds(260, 140, 60, 25);
		button.setFont(new Font("楷体", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					// 执行登录
					// 设置用户名和密码，保存起来
					loginUserName = userText.getText();
					loginPassword = pswText.getText();
					LoginWindow.setLoginUserName(loginUserName);
					LoginWindow.setLoginPassword(loginPassword);
					// 判断学生
					// 判断输入框的内容是否为空
					if (loginUserName.length() == 0
							|| loginPassword.length() == 0) {
						PublicWindowSet.promptPopUp("请输入用户名和密码!", "登录提示", jfr);
					}
					// 验证身份，判断是否正确的用户名和密码
					else if (new StudentDAO().loginConfirm(loginUserName,
							loginPassword)) {
						PublicWindowSet.promptPopUp("登录成功!!!", "登录提示", jfr);
						StudentWindow sw = new StudentWindow();
						sw.setSize(sw.getWindowwide(), sw.getWindowhigh());
						// 释放登录窗口
						jfr.dispose();
					} else {
						PublicWindowSet.promptPopUp("用户名或密码错误，请重新输入!!!",
								"登录提示", jfr);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		jfr.add(button);
	}

	// 获取登录用户名
	public static String getLoginUserName() {
		return loginUserName;
	}

	// 设置登录用户名
	public static void setLoginUserName(String loginUserName) {
		LoginWindow.loginUserName = loginUserName;
	}

	// 获取登录密码
	public static String getLoginPassword() {
		return loginPassword;
	}

	// 设置登录密码
	public static void setLoginPassword(String loginPassword) {
		LoginWindow.loginPassword = loginPassword;
	}
}