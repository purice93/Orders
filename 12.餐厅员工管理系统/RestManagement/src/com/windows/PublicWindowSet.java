package com.windows;
/*
 * 窗口属性设置工具类
 * 主要用来设置窗口及控件的位置和大小等属性
 */
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
	
	// 设置输入框的提示（如“请输入id：”）
	public static void addLabel(JFrame jfr, int size, int x, int y, int wide, int high, String str) {
		Font font = null;
		font = new Font("Courier", Font.BOLD, size);
		JLabel adminLabel = new JLabel(str);
		adminLabel.setFont(font);
		adminLabel.setForeground(Color.BLACK);
		adminLabel.setBounds(x, y, wide, high);
		jfr.add(adminLabel);
	}

	// 设置窗口大小和标题
	public static void windowAttribute(JFrame jfr, int wide, int high, String backUrl, String title) {

		windowIsCenter(wide, high, jfr);

		windowsBackground(backUrl, jfr);
		jfr.setTitle(title);
		jfr.setSize(wide, high);
		jfr.setVisible(true);
		jfr.setResizable(false);
	}

	// 设置窗口居中（电脑界面中间）
	public static void windowIsCenter(int windowWide, int windowHigh, JFrame frame) {
		int screenWide = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHigh = Toolkit.getDefaultToolkit().getScreenSize().height;
		int x = screenWide / 2 - windowWide / 2;
		int y = screenHigh / 2 - windowHigh / 2;
		frame.setLocation(x, y);
	}

	// 设置背景格式
	public static void windowsBackground(String url, JFrame jfr) {
		Icon icon = new ImageIcon(url);
		JLabel backLabel = new JLabel(icon);
		jfr.add(backLabel);
	}

	// 添加设置输入框
	public static void addTextField(JTextField tf, int x, int y, int wide, int high, JFrame jfr) {
		tf.setBounds(x, y, wide, high);
		tf.setOpaque(false);
		jfr.add(tf);
	}

	// 设置提示框（如“提示：登录成功！！”）
	public static void promptPopUp(String content, String title, JFrame jfr) {
		JOptionPane.showMessageDialog(jfr.getContentPane(), content, title, JOptionPane.INFORMATION_MESSAGE);
	}

	// 设置窗口大小和标题（与上面不同，这里无背景）
	public static void windowAttribute(JFrame jfr, int wide, int high, String title) {

		windowIsCenter(wide, high, jfr);
		jfr.setTitle(title);
		jfr.setSize(wide, high);
		jfr.setVisible(true);
		jfr.setResizable(false);
	}
}