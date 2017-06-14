
package com.csms.windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.csms.dao.*;
import javax.swing.*;

public class RegisterWindow {
	JFrame frame = new JFrame();
	private static final int WINDOWSWIDE = 800;
	private static final int WINDOWSHIGH = 500;
	private static int flag = 0;
	final JTextField passagerID;
	final JTextField realName;
	final JTextField identityID;
	final JPasswordField password;

	JLabel psdLabel;
	JLabel textLabel;

	JRadioButton radioButton;
	private static String loginUserName;
	private static String loginPassword;
	private static String reName;
	private static String identityId;
	ButtonGroup buttGroup = new ButtonGroup();
	JPanel southPanel = new JPanel();

	Icon icon = new ImageIcon("src//images//icons//loginIcon.png");

	public RegisterWindow() {
		JLabel label = new JLabel(icon);
		label.setBounds(70, 0, 260, 60);
		frame.add(label);

		PublicWindowSet.addLabel(frame, 16, 65, 70, 90, 30, "用户ID:");
		passagerID = new JTextField();
		PublicWindowSet.addTextField(passagerID, 130, 70, 200, 30, frame);

		PublicWindowSet.addLabel(frame, 16, 65, 100, 90, 30, "真实姓名:");
		realName = new JTextField();
		PublicWindowSet.addTextField(realName, 130, 100, 200, 30, frame);

		PublicWindowSet.addLabel(frame, 16, 65, 130, 90, 30, "身份证号:");
		identityID = new JTextField();
		PublicWindowSet.addTextField(identityID, 130, 130, 200, 30, frame);

		PublicWindowSet.addLabel(frame, 16, 65, 160, 90, 30, "密码:");
		password = new JPasswordField();
		PublicWindowSet.addTextField(password, 130, 160, 200, 30, frame);

		southPanel.setBounds(60, 200, 200, 30);
		frame.add(southPanel);

		this.addButton1("注册", 1, frame);

		PublicWindowSet.windowAttribute(frame, WINDOWSWIDE, WINDOWSHIGH, null, "登录注册系统");

	}

	private void addButton1(String buttonName, int flag, JFrame jfr) {
		JButton button = new JButton(buttonName);
		button.setBounds(160, 260, 60, 25);
		button.setFont(new Font("黑体", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					loginUserName = passagerID.getText();
					reName = realName.getText();
					identityId = identityID.getText();
					loginPassword = password.getText();
					RegisterWindow.setLoginUserName(loginUserName);
					RegisterWindow.setLoginPassword(loginPassword);

					if (loginUserName.length() == 0 || loginPassword.length() == 0) {
						PublicWindowSet.promptPopUp("不能为空", "提示", jfr);
					} else {
						try {
							new PassagerDAO().register(loginUserName, reName,identityId, loginPassword);
							PublicWindowSet.promptPopUp("注册成功", "提示", jfr);
							jfr.dispose();
						} catch (Exception e) {
							PublicWindowSet.promptPopUp("注册失败!!!", "提示ʾ", jfr);
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
		RegisterWindow.flag = flag;
	}

	public static String getLoginUserName() {
		return loginUserName;
	}

	public static void setLoginUserName(String loginUserName) {
		RegisterWindow.loginUserName = loginUserName;
	}

	public static String getLoginPassword() {
		return loginPassword;
	}

	public static void setLoginPassword(String loginPassword) {
		RegisterWindow.loginPassword = loginPassword;
	}
}