/**
 * @program �������Ҫ�Ƕ�һЩ���ھ���д���ظ�����ķ�װ
 *
 */
 
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
 
    // ��ӱ�ǩ������װ
    public static void addLabel(JFrame jfr, int size, int x, int y, int wide, int high, String str) {
        Font font = null;
        // ������������ã���ĳЩ����������ʹ�ô��壬ĳЩ�����ʹ����������
        if (str.equals("����Ա��Ϣ") || str.equals("���ѧ��") || str.equals("ɾ���γ���Ϣ")) {
            font = new Font("����", Font.BOLD, size);
        } else {
            font = new Font("����", Font.PLAIN, size);
        }
        JLabel adminLabel = new JLabel(str);
        // ����ǩ��������
        adminLabel.setFont(font);
        if (str.equals("����Ա��Ϣ") || str.equals("���ѧ��") || str.equals("ɾ���γ���Ϣ")) {
            adminLabel.setForeground(Color.WHITE);
        } else {
            adminLabel.setForeground(Color.BLACK);
        }
        // ���ñ�ǩ��λ��
        adminLabel.setBounds(x, y, wide, high);
        jfr.add(adminLabel);
    }
 
    // ���ͷ��
    public static void addPhotoLabel(JFrame jfr, int x, int y, int wide, int high, String url) {
        Icon icon = new ImageIcon(url);
        // ��ͼ���������ǩ��
        JLabel photoLabel = new JLabel(icon);
        photoLabel.setBounds(x, y, wide, high);
        jfr.add(photoLabel);
    }
 
    // ��������
    public static void windowAttribute(JFrame jfr, int wide, int high, String backUrl, String title) {
        // ���ô���ͼ��
        windowsIcon(jfr);
 
        // ���ھ�����ʾ
        windowIsCenter(wide, high, jfr);
 
        // ���ô��ڵı���
        windowsBackground(backUrl, jfr);
        // ���ô��ڱ���
        jfr.setTitle(title);
        // ���ô��ڴ�С
        jfr.setSize(wide, high);
        // ���ô��ڿɼ�
        jfr.setVisible(true);
        // ���ô��ڵĴ�С���ܸı�
        jfr.setResizable(false);
    }
 
    // ����ͼ������
    public static void windowsIcon(JFrame jfr) {
        String url = "src//images//icons//loginIcon1.png";
        Image icon = new ImageIcon(url).getImage();
        jfr.setIconImage(icon);
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
 
    // ���ô��ڱ�����ʾ
    public static void windowsBackground(String url, JFrame jfr) {
        Icon icon = new ImageIcon(url);
        JLabel backLabel = new JLabel(icon);
        jfr.add(backLabel);
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
        // ����JTabel��Ĭ������
        tableModel = new DefaultTableModel(new CourseList(str).userInfo, new CourseList(str).titles);
        // ���JLabel���
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
}
 
    // tableʵ����
class CourseList extends AbstractTableModel {
    public String[] titles = null;
    public Object[][] userInfo = {}; // ��������
     
    public CourseList(String[] str){
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