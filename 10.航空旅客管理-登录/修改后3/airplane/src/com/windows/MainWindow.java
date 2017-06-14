
package com.windows;

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
import javax.swing.JRadioButton;

public class MainWindow {
	JFrame frame = new JFrame();
	private static final int WINDOWSWIDE = 400;
	private static final int WINDOWSHIGH = 300;

	JLabel psdLabel;
	JLabel textLabel;

	JRadioButton radioButton;
	ButtonGroup buttGroup = new ButtonGroup();
	JPanel southPanel = new JPanel();

	Icon icon = new ImageIcon("");

	public MainWindow() {

		JLabel label = new JLabel(icon);
		label.setBounds(70, 0, 260, 60);
		frame.add(label);
		southPanel.setBounds(60, 140, 200, 30);
		frame.add(southPanel);
		this.addButton1("Login", 1, frame);
		this.addButton2("Register", 2, frame);

		PublicWindowSet.windowAttribute(frame, WINDOWSWIDE, WINDOWSHIGH, null, "Login register system");

	}

	private void addButton1(String buttonName, int flag, JFrame jfr) {
		JButton button = new JButton(buttonName);
		button.setBounds(80, 100, 100, 25);
		button.setFont(new Font("Courier", Font.PLAIN, 12));
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
		button.setBounds(200, 100, 100, 25);
		button.setFont(new Font("Courier", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterWindow();
			}
		});
		jfr.add(button);
	}
}