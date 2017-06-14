package com.windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PublicWindowSet {
	public static void addLabel(JFrame jfr, int size, int x, int y, int wide, int high, String str) {
		Font font = null;
		font = new Font("Courier", Font.BOLD, size);
		JLabel adminLabel = new JLabel(str);
		adminLabel.setFont(font);
		adminLabel.setForeground(Color.BLACK);
		adminLabel.setBounds(x, y, wide, high);
		jfr.add(adminLabel);
	}

	public static void windowAttribute(JFrame jfr, int wide, int high, String backUrl, String title) {

		windowIsCenter(wide, high, jfr);

		windowsBackground(backUrl, jfr);
		jfr.setTitle(title);
		jfr.setSize(wide, high);
		jfr.setVisible(true);
		jfr.setResizable(false);
	}


	public static void windowIsCenter(int windowWide, int windowHigh, JFrame frame) {
		int screenWide = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHigh = Toolkit.getDefaultToolkit().getScreenSize().height;
		int x = screenWide / 2 - windowWide / 2;
		int y = screenHigh / 2 - windowHigh / 2;
		frame.setLocation(x, y);
	}

	public static void windowsBackground(String url, JFrame jfr) {
		Icon icon = new ImageIcon(url);
		JLabel backLabel = new JLabel(icon);
		jfr.add(backLabel);
	}

	public static void addTextField(JTextField tf, int x, int y, int wide, int high, JFrame jfr) {
		tf.setBounds(x, y, wide, high);
		tf.setOpaque(false);
		jfr.add(tf);
	}

	public static void promptPopUp(String content, String title, JFrame jfr) {
		JOptionPane.showMessageDialog(jfr.getContentPane(), content, title, JOptionPane.INFORMATION_MESSAGE);
	}
}