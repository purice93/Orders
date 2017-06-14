package com.csms.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class PublicWindowSet {

	private static JTable table = null;
	private static DefaultTableModel tableModel;

	public static void addLabel(JFrame jfr, int size, int x, int y, int wide, int high, String str) {
		Font font = null;
		if (str.equals("����Ա��Ϣ") || str.equals("���ѧ��") || str.equals("ɾ���γ���Ϣ")) {
			font = new Font("����", Font.BOLD, size);
		} else {
			font = new Font("����", Font.PLAIN, size);
		}
		JLabel adminLabel = new JLabel(str);
		adminLabel.setFont(font);
		if (str.equals("����Ա��Ϣ") || str.equals("���ѧ��") || str.equals("ɾ���γ���Ϣ")) {
			adminLabel.setForeground(Color.WHITE);
		} else {
			adminLabel.setForeground(Color.BLACK);
		}
		adminLabel.setBounds(x, y, wide, high);
		jfr.add(adminLabel);
	}

	public static void addPhotoLabel(JFrame jfr, int x, int y, int wide, int high, String url) {
		Icon icon = new ImageIcon(url);
		// ��ͼ���������ǩ��
		JLabel photoLabel = new JLabel(icon);
		photoLabel.setBounds(x, y, wide, high);
		jfr.add(photoLabel);
	}

	public static void windowAttribute(JFrame jfr, int wide, int high, String backUrl, String title) {
		windowsIcon(jfr);

		windowIsCenter(wide, high, jfr);

		windowsBackground(backUrl, jfr);
		jfr.setTitle(title);
		jfr.setSize(wide, high);
		jfr.setVisible(true);
		jfr.setResizable(false);
	}

	public static void windowsIcon(JFrame jfr) {
		String url = "src//images//icons//loginIcon1.png";
		Image icon = new ImageIcon(url).getImage();
		jfr.setIconImage(icon);
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

	public static DefaultTableModel addTableList(JFrame jfr, String[] str) {
		tableModel = new DefaultTableModel(new CourseList(str).userInfo, new CourseList(str).titles);
		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setPreferredScrollableViewportSize(new Dimension(600, 0));
		table.getTableHeader().setReorderingAllowed(false);
		table.enable(false);
		JScrollPane scr = new JScrollPane(table);
		jfr.add(scr, BorderLayout.CENTER);

		return tableModel;
	}
}

class CourseList extends AbstractTableModel {
	public String[] titles = null;
	public Object[][] userInfo = {};

	public CourseList(String[] str) {
		titles = str;
	}

	public int getRowCount() {
		return 0;
	}

	public int getColumnCount() {
		return 0;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
}