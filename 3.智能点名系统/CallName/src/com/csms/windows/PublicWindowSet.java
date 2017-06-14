/**
 * 
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
	
	public static void addLabel(JFrame jfr, int size, int x, int y, int wide, int high) {
		Font font = null;
		
		font = new Font("����", Font.BOLD, size);
		JLabel adminLabel = new JLabel();
		// ����ǩ��������
		adminLabel.setFont(font);
		adminLabel.setForeground(Color.WHITE);
		// ���ñ�ǩ��λ��
		adminLabel.setBounds(x, y, wide, high);
		jfr.add(adminLabel);
	}

	// ��������
	public static void windowAttribute(JFrame jfr, int wide, int high, String title) {

		// ���ھ�����ʾ
		windowIsCenter(wide, high, jfr);

		// ���ô��ڱ���
		jfr.setTitle(title);
		// ���ô��ڴ�С
		jfr.setSize(wide, high);
		// ���ô��ڿɼ�
		jfr.setVisible(true);
		// ���ô��ڵĴ�С���ܸı�
		jfr.setResizable(false);
	}

	// ���ھ�����ʾ
	public static void windowIsCenter(int windowWide, int windowHigh, JFrame frame) {
		// ȡ����Ļ�Ŀ��
		int screenWide = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHigh = Toolkit.getDefaultToolkit().getScreenSize().height;
		// ������б���
		int x = screenWide / 2 - windowWide / 2;
		int y = screenHigh / 2 - windowHigh / 2;
		// ���ô��ھ���
		frame.setLocation(x, y);
	}

	// ���ñ�ǩ
	public static void addTextField(JTextField tf, int x, int y, int wide, int high, JFrame jfr) {
		tf.setBounds(x, y, wide, high);
		// ���ñ�ǩ͸��
		tf.setOpaque(false);
		jfr.add(tf);
	}

	// ��ʾ������
	public static void promptPopUp(String content, String title, JFrame jfr) {
		JOptionPane.showMessageDialog(jfr.getContentPane(), content, title, JOptionPane.INFORMATION_MESSAGE);
	}

	// ����б�
	public static DefaultTableModel addTableList(JFrame jfr, String[] str) {

        table = new JTable(tableModel);
		// ��ֹJLabel������Ŵ��ڵĴ�С���ı�
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// JTabel���������ʾ�ߴ�
		table.setPreferredScrollableViewportSize(new Dimension(600, 0));
		// ʹ�б��������еĽ����ƶ�
		table.getTableHeader().setReorderingAllowed(false);
		// ʹ�б�򲻿ɱ��༭
		table.enable(false);
		// ��JTabel�����ӻ�����
		JScrollPane scr = new JScrollPane(table);
		// ������������Tabel�����������Ķ�������
		jfr.add(scr, BorderLayout.CENTER);

		return tableModel;
	}

	public static void addLabel(JFrame jfr, int size, int x, int y, int wide, int high, String str) {
		Font font = null;
		// ������������ã���ĳЩ����������ʹ�ô��壬ĳЩ�����ʹ����������
		font = new Font("����", Font.BOLD, size);
		JLabel adminLabel = new JLabel(str);
		// ����ǩ��������
		adminLabel.setFont(font);
		adminLabel.setForeground(Color.BLACK);
		// ���ñ�ǩ��λ��
		adminLabel.setBounds(x, y, wide, high);
		jfr.add(adminLabel);
	}
}
