/**
 * 
 *
 */

package com.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PublicWindowSet {

	private static JTable table = null;
	private static DefaultTableModel tableModel;
	
	
	// 设置面板格式
	public static void addLabel(JFrame jfr, int size, int x, int y, int wide, int high) {
		Font font = null;
		font = new Font("黑体", Font.BOLD, size);
		JLabel adminLabel = new JLabel();
		adminLabel.setFont(font);
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setBounds(x, y, wide, high);
		jfr.add(adminLabel);
	}
	
	// 设置面板高宽和标题
	public static void windowAttribute(JFrame jfr, int wide, int high, String title) {

		windowIsCenter(wide, high, jfr);
		jfr.setTitle(title);
		jfr.setSize(wide, high);
		jfr.setVisible(true);
		jfr.setResizable(false);
	}

	// 设置面板位置
	public static void windowIsCenter(int windowWide, int windowHigh, JFrame frame) {
		int screenWide = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHigh = Toolkit.getDefaultToolkit().getScreenSize().height;
		int x = screenWide / 2 - windowWide / 2;
		int y = screenHigh / 2 - windowHigh / 2;
		frame.setLocation(x, y);
	}

	// 面板中添加文本方法
	public static void addTextField(JTextField tf, int x, int y, int wide, int high, JFrame jfr) {
		tf.setBounds(x, y, wide, high);
		tf.setOpaque(false);
		jfr.add(tf);
	}

	// 提示函数方法
	public static void promptPopUp(String content, String title, JFrame jfr) {
		JOptionPane.showMessageDialog(jfr.getContentPane(), content, title, JOptionPane.INFORMATION_MESSAGE);
	}

	// 添加图表模式
	public static DefaultTableModel addTableList(JFrame jfr, String[] str) {

        table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setPreferredScrollableViewportSize(new Dimension(600, 0));
		table.getTableHeader().setReorderingAllowed(false);
		table.enable(false);
		JScrollPane scr = new JScrollPane(table);
		jfr.add(scr, BorderLayout.CENTER);

		return tableModel;
	}

	//另一种面板格式
	public static void addLabel(JFrame jfr, int size, int x, int y, int wide, int high, String str) {
		Font font = null;
		font = new Font("黑体", Font.BOLD, size);
		JLabel adminLabel = new JLabel(str);
		adminLabel.setFont(font);
		adminLabel.setForeground(Color.BLACK);
		adminLabel.setBounds(x, y, wide, high);
		jfr.add(adminLabel);
	}
}
