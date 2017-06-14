
package com.windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.dao.AdminDAO;
import com.dao.PassagerDAO;
import com.entity.Admin;
import com.entity.Passager;

public class LoginWindow {
	JFrame frame = new JFrame();
	private static final int WINDOWSWIDE = 400;
	private static final int WINDOWSHIGH = 300;
	private static int flag = 0;
	final JTextField userText;
	final JPasswordField pswText;

	private List<Admin> adminList = new ArrayList<>();
	private List<Passager> passagerList = new ArrayList<>();

	JLabel psdLabel;
	JLabel textLabel;

	JRadioButton radioButton;
	private static String loginUserName;
	private static String loginPassword;
	ButtonGroup buttGroup = new ButtonGroup();
	JPanel southPanel = new JPanel();

	Icon icon = new ImageIcon("");

	public LoginWindow() {
		JLabel label = new JLabel(icon);
		label.setBounds(70, 0, 260, 60);
		frame.add(label);

		PublicWindowSet.addLabel(frame, 16, 65, 70, 90, 30, "username:");
		userText = new JTextField();
		PublicWindowSet.addTextField(userText, 160, 70, 200, 30, frame);
		PublicWindowSet.addLabel(frame, 16, 65, 105, 90, 30, "password:");
		pswText = new JPasswordField();
		PublicWindowSet.addTextField(pswText, 160, 105, 200, 30, frame);

		this.addRadioButton("Admin", 1, false);
		this.addRadioButton("Passager", 2, false);
		southPanel.setBounds(60, 140, 200, 30);
		frame.add(southPanel);

		this.addButton("Login", 1, frame);

		loadAdminFile(adminList);
		loadPassagerFile(passagerList);
		PublicWindowSet.windowAttribute(frame, WINDOWSWIDE, WINDOWSHIGH, null, "Login register system");

	}

	private void loadAdminFile(List<Admin> adminList2) {
		try {
			File fileTxt = new File("admin.txt");
			BufferedReader br = new BufferedReader(new FileReader(fileTxt));
			String str = null;
			Admin admin = new Admin();
			while ((str = br.readLine()) != null) {
				if (str.trim().equals("")) {
					continue;
				} else {
					String[] temp = str.trim().split(" ");
					admin = new Admin();
					admin.setUserName(temp[0]);
					admin.setPassword(temp[1]);
					adminList2.add(admin);
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void loadPassagerFile(List<Passager> passagerList2) {
		try {
			File fileTxt = new File("passager.txt");
			BufferedReader br = new BufferedReader(new FileReader(fileTxt));
			String str = null;
			Passager passager = new Passager();
			while ((str = br.readLine()) != null) {
				if (str.trim().equals("")) {
					continue;
				} else {
					String[] temp = str.trim().split(" ");
					passager = new Passager();
					passager.setPassagerID(temp[0]);
					passager.setRealName(temp[1]);
					passager.setIdentityID(temp[2]);
					passager.setPassword(temp[3]);
					passager.setOrderID("");
					passagerList2.add(passager);
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void addRadioButton(String name, int flag, Boolean bool) {
		radioButton = new JRadioButton(name, bool);
		radioButton.setContentAreaFilled(false);
		radioButton.setFont(new Font("Courier", Font.PLAIN, 12));
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
		button.setBounds(160, 200, 100, 25);
		button.setFont(new Font("Courier", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					loginUserName = userText.getText();
					loginPassword = pswText.getText();
					LoginWindow.setLoginUserName(loginUserName);
					LoginWindow.setLoginPassword(loginPassword);
					if (LoginWindow.getFlag() == 1) {
						if (loginUserName.length() == 0 || loginPassword.length() == 0) {
							PublicWindowSet.promptPopUp("username or password can not be empty", "prompt", jfr);
						} else {
							if (new AdminDAO().loginConfirm(loginUserName, loginPassword, adminList)) {
								PublicWindowSet.promptPopUp("Logined successfully!!!", "prompt", jfr);
								jfr.dispose();
							} else {
								PublicWindowSet.promptPopUp("username or password is incorrect!!!", "promptʾ", jfr);
							}
						}
					} else if (LoginWindow.getFlag() == 2) {
						if (loginUserName.length() == 0 || loginPassword.length() == 0) {
							PublicWindowSet.promptPopUp("username or password can not be empty", "prompt", jfr);
						} else {
							if (new PassagerDAO().loginConfirm(loginUserName, loginPassword, passagerList)) {
								PublicWindowSet.promptPopUp("Logined successfully", "prompt", jfr);
								jfr.dispose();
							} else {
								PublicWindowSet.promptPopUp("username or password is incorrect!!!", "promptʾ", jfr);
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