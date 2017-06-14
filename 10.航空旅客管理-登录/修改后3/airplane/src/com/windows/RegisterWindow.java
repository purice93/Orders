
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

import com.dao.PassagerDAO;
import com.entity.Admin;
import com.entity.Passager;

public class RegisterWindow {
	JFrame frame = new JFrame();
	private static final int WINDOWSWIDE = 400;
	private static final int WINDOWSHIGH = 300;
	final JTextField passagerID;
	final JTextField realName;
	final JTextField identityID;
	final JPasswordField password;

	private List<Admin> adminList = new ArrayList<>();
	private List<Passager> passagerList = new ArrayList<>();

	JLabel psdLabel;
	JLabel textLabel;

	JRadioButton radioButton;
	private static String loginUserName;
	private static String loginPassword;
	private static String reName;
	private static String identityId;
	ButtonGroup buttGroup = new ButtonGroup();
	JPanel southPanel = new JPanel();

	Icon icon = new ImageIcon("");

	public RegisterWindow() {
		JLabel label = new JLabel(icon);
		label.setBounds(70, 0, 260, 60);
		frame.add(label);

		PublicWindowSet.addLabel(frame, 16, 65, 70, 90, 30, "passagerID:");
		passagerID = new JTextField();
		PublicWindowSet.addTextField(passagerID, 160, 70, 200, 30, frame);

		PublicWindowSet.addLabel(frame, 16, 65, 100, 90, 30, "realName:");
		realName = new JTextField();
		PublicWindowSet.addTextField(realName, 160, 100, 200, 30, frame);

		PublicWindowSet.addLabel(frame, 16, 65, 130, 90, 30, "identityID:");
		identityID = new JTextField();
		PublicWindowSet.addTextField(identityID, 160, 130, 200, 30, frame);

		PublicWindowSet.addLabel(frame, 16, 65, 160, 90, 30, "password:");
		password = new JPasswordField();
		PublicWindowSet.addTextField(password, 160, 160, 200, 30, frame);

		southPanel.setBounds(60, 200, 200, 30);
		frame.add(southPanel);

		this.addButton1("Register", 1, frame);

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

	private void addButton1(String buttonName, int flag, JFrame jfr) {
		JButton button = new JButton(buttonName);
		button.setBounds(160, 230, 100, 25);
		button.setFont(new Font("Courier", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					loginUserName = passagerID.getText();
					reName = realName.getText();
					identityId = identityID.getText();
					loginPassword = password.getText();

					if (loginUserName.length() == 0 || loginPassword.length() == 0) {
						PublicWindowSet.promptPopUp("username or password can not be empty", "prompt", jfr);
					} else {
						if (new PassagerDAO().register(loginUserName, reName, identityId, loginPassword,
								passagerList)) {
							PublicWindowSet.promptPopUp("registered successfully!!!", "prompt", jfr);
							jfr.dispose();
						} else {
							PublicWindowSet.promptPopUp("admin already exists!!!", "promptʾ", jfr);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		jfr.add(button);

	}

	
}