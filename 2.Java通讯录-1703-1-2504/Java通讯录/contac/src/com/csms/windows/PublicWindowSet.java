/**
 * 主界面界面属性设置
 *
 */

package com.csms.windows;

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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class PublicWindowSet {

	private static JTable table = null;
	private static DefaultTableModel tableModel;

	// 添加标签方法封装
	public static void addLabel(JFrame jfr, int size, int x, int y, int wide, int high) {
		Font font = null;
		// 进行字体的设置，在某些特殊的情况下使用粗体，某些情况下使用正常字体
		font = new Font("楷体", Font.BOLD, size);
		JLabel adminLabel = new JLabel();
		// 将标签设置字体
		adminLabel.setFont(font);
		adminLabel.setForeground(Color.WHITE);
		// 设置标签的位置
		adminLabel.setBounds(x, y, wide, high);
		jfr.add(adminLabel);
	}

	// 窗口属性
	public static void windowAttribute(JFrame jfr, int wide, int high, String title) {

		// 窗口居中显示
		windowIsCenter(wide, high, jfr);

		// 设置窗口标题
		jfr.setTitle(title);
		// 设置窗口大小
		jfr.setSize(wide, high);
		// 设置窗口可见
		jfr.setVisible(true);
		// 设置窗口的大小不能改变
		jfr.setResizable(false);
	}

	// 窗口居中显示
	public static void windowIsCenter(int windowWide, int windowHigh, JFrame frame) {
		// 取得屏幕的宽度
		int screenWide = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHigh = Toolkit.getDefaultToolkit().getScreenSize().height;
		// 计算居中比例
		int x = screenWide / 2 - windowWide / 2;
		int y = screenHigh / 2 - windowHigh / 2;
		// 设置窗口居中
		frame.setLocation(x, y);
	}

	// 设置标签
	public static void addTextField(JTextField tf, int x, int y, int wide, int high, JFrame jfr) {
		tf.setBounds(x, y, wide, high);
		// 设置标签透明
		tf.setOpaque(false);
		jfr.add(tf);
	}

	// 提示弹出框
	public static void promptPopUp(String content, String title, JFrame jfr) {
		JOptionPane.showMessageDialog(jfr.getContentPane(), content, title, JOptionPane.INFORMATION_MESSAGE);
	}

	// 添加列表
	public static DefaultTableModel addTableList(JFrame jfr, String[] str) {

        table = new JTable(tableModel);
		// 禁止JLabel组件随着窗口的大小而改变
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// JTabel组件表格的显示尺寸
		table.setPreferredScrollableViewportSize(new Dimension(600, 0));
		// 使列表不可以整列的进行移动
		table.getTableHeader().setReorderingAllowed(false);
		// 使列表框不可被编辑
		table.enable(false);
		// 给JTabel组件添加滑动条
		JScrollPane scr = new JScrollPane(table);
		// 将带滚动条的Tabel组件添加入面板的东部区域
		jfr.add(scr, BorderLayout.CENTER);

		return tableModel;
	}

	public static void addLabel(JFrame jfr, int size, int x, int y, int wide, int high, String str) {
		Font font = null;
		// 进行字体的设置，在某些特殊的情况下使用粗体，某些情况下使用正常字体
		font = new Font("楷体", Font.BOLD, size);
		JLabel adminLabel = new JLabel(str);
		// 将标签设置字体
		adminLabel.setFont(font);
		adminLabel.setForeground(Color.BLACK);
		// 设置标签的位置
		adminLabel.setBounds(x, y, wide, high);
		jfr.add(adminLabel);
	}
}
