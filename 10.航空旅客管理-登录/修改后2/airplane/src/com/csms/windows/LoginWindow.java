
package com.csms.windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.csms.dao.*;
import javax.swing.*;

public class LoginWindow {
	JFrame frame = new JFrame();
	private static final int WINDOWSWIDE = 800;
	private static final int WINDOWSHIGH = 500;
	private static int flag = 0;
	final JTextField userText;
	final JPasswordField pswText;

	JLabel psdLabel;
	JLabel textLabel;

	JRadioButton radioButton;
	private static String loginUserName;
	private static String loginPassword;
	ButtonGroup buttGroup = new ButtonGroup();
	JPanel southPanel = new JPanel();

	Icon icon = new ImageIcon("src//images//icons//loginIcon.png");

	public LoginWindow() {
		JLabel label = new JLabel(icon);
		label.setBounds(70, 0, 260, 60);
		frame.add(label);

		PublicWindowSet.addLabel(frame, 16, 65, 70, 90, 30, "用户名:");
		userText = new JTextField();
		PublicWindowSet.addTextField(userText, 130, 70, 200, 30, frame);
		PublicWindowSet.addLabel(frame, 16, 65, 105, 90, 30, "密码:");
		pswText = new JPasswordField();
		PublicWindowSet.addTextField(pswText, 130, 105, 200, 30, frame);

		this.addRadioButton("管理员", 1, false);
		this.addRadioButton("乘客", 2, false);
		southPanel.setBounds(60, 140, 200, 30);
		frame.add(southPanel);

		this.addButton("登录", 1, frame);

		PublicWindowSet.windowAttribute(frame, WINDOWSWIDE, WINDOWSHIGH, null, "登录注册系统");

	}

	private void addRadioButton(String name, int flag, Boolean bool) {
		radioButton = new JRadioButton(name, bool);
		radioButton.setContentAreaFilled(false);
		radioButton.setFont(new Font("黑体", Font.PLAIN, 12));
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow.setFlag(flag);
			}
		});
		buttGroup.add(radioButton);
		southPanel.add(radioButton);
	}

	private void addButton(String buttonName, int flag, JFrame jfr) {
		JButton button = new JButton(buttonName);
		button.setBounds(160, 200, 60, 25);
		button.setFont(new Font("黑体", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					loginUserName = userText.getText();
					loginPassword = pswText.getText();
					LoginWindow.setLoginUserName(loginUserName);
					LoginWindow.setLoginPassword(loginPassword);
					if (LoginWindow.getFlag() == 1) {
						if (loginUserName.length() == 0 || loginPassword.length() == 0) {
							PublicWindowSet.promptPopUp("不能为空", "提示", jfr);
						} else {
							try {
								new AdminitartorDAO().loginConfirm(loginUserName, loginPassword);
								PublicWindowSet.promptPopUp("登陆成功", "提示", jfr);
								jfr.dispose();
							} catch (Exception e) {
								PublicWindowSet.promptPopUp("注册失败!!!", "提示ʾ", jfr);
							}
						}
					} else if (LoginWindow.getFlag() == 2) {
						if (loginUserName.length() == 0 || loginPassword.length() == 0) {
							PublicWindowSet.promptPopUp("不能为空", "提示", jfr);
						} else {
							try {
								new PassagerDAO().loginConfirm(loginUserName, loginPassword);
								PublicWindowSet.promptPopUp("登陆成功", "提示", jfr);
								jfr.dispose();
							} catch (Exception e) {
								PublicWindowSet.promptPopUp("注册失败!!!", "提示ʾ", jfr);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		jfr.add(button);

	}

	private static int getFlag() {
		return flag;
	}

	private static void setFlag(int flag) {
		LoginWindow.flag = flag;
	}

	public static String getLoginUserName() {
		return loginUserName;
	}

	public static void setLoginUserName(String loginUserName) {
		LoginWindow.loginUserName = loginUserName;
	}

	public static String getLoginPassword() {
		return loginPassword;
	}

	public static void setLoginPassword(String loginPassword) {
		LoginWindow.loginPassword = loginPassword;
	}
}