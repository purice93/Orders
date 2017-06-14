package com.csms.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class PortWindow extends JFrame implements ActionListener {

	private Information information = new Information();

	// ���������ڳ��Ϳ�
	private static final int WINDOWWIDE = 600;
	private static final int WINDOWHIGH = 420;

	// �����ڶ����Ҳ�ͼ���ʽ��ͼ�����
	private JFrame frame = new JFrame();
	private DefaultTableModel tableModel;
	private JTable table = null;

	// txt�ļ�����
	private File file1 = null;

	// ����ؼ�
	private JLabel jText = null;

	// �����ı����������IP��Χ��
	private JTextField jTextStart = null;
	private JTextField jTextEnd = null;

	// ������ť�ؼ�����ȡIP���˿ڡ�mac��ַ��Ϣ�����txt�ļ���
	private JButton getItemsBtn = null;
	private JButton outputBtn = null;

	// �������е���Ϣ�������浥����Ϣ��
	private List<LinkedList<String>> strList = new LinkedList<LinkedList<String>>();
	private LinkedList<String> infoList = null;

	// �����ڹ��췽��
	public PortWindow() {
		this.liftInformation();// ��ཻ�����
		this.rigthInformation();// �Ҳ�ͼ����ʾ���

		// ������������
		PublicWindowSet.windowAttribute(this, WINDOWWIDE, WINDOWHIGH, "��Ϣ��ѯ");
	}

	// ��ߴ��ڽ�����
	public void liftInformation() {
		// 1).���һ���ı���ǩ
		jText = new JLabel("������IP");
		jText.setFont(new Font("����", Font.BOLD, 16));
		jText.setBounds(30, 40, 230, 50);
		this.add(jText);

		// 2).�����ı������
		jTextStart = new JTextField();

		jTextStart.setFont(new Font("����", Font.BOLD, 16));

		jTextStart.setBounds(30, 80, 120, 25);

		this.add(jTextStart);

		// 3).����������ť
		getItemsBtn = new JButton("��ȡ��Ϣ");
		outputBtn = new JButton("����txt");

		getItemsBtn.setBounds(30, 200, 120, 25);
		outputBtn.setBounds(30, 230, 120, 25);

		this.add(getItemsBtn);
		this.add(outputBtn);

		// ��Ӽ�����Ӧ
		this.getItemsBtn.addActionListener(this);
		this.outputBtn.addActionListener(this);

		// 4).�������ҷָ���ǩ
		JLabel awayLabel = new JLabel();
		awayLabel.setBounds(200, 50, 10, 450);
		this.add(awayLabel);
	}

	// �Ҳ��б���Ϣ
	public void rigthInformation() {
		// ����JTabel��Ĭ�����ͣ�������Դ��ͼ����⣩
		this.tableModel = new DefaultTableModel(new infoList().Info, new infoList().titles);
		// ���JLabel���
		this.table = new JTable(this.tableModel);
		// �����п��
		table.getColumnModel().getColumn(0).setPreferredWidth(150);

		// ��ֹJLabel������Ŵ��ڵĴ�С���ı�
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// JTabel���������ʾ�ߴ�
		table.setPreferredScrollableViewportSize(new Dimension(420, 0));

		// ʹ�б��������еĽ����ƶ�
		table.getTableHeader().setReorderingAllowed(false);

		// ʹ�б��ɱ��༭
		table.enable(true);

		// ��JTabel�����ӻ�����
		JScrollPane scr = new JScrollPane(this.table);

		// ������������Tabel�����������Ķ�������
		this.add(scr, BorderLayout.EAST);
	}

	// ͼ��������Ϣ
	class infoList extends AbstractTableModel {
		// ����
		public String[] titles = { "���ж˿ں�" };
		// ����
		public Object[][] Info = {}; //

		public int getRowCount() {
			return 0;
		}

		public int getColumnCount() {
			return 0;
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {
			return null;
		}
	}

	// ������ť��Ӧ����
	public void actionPerformed(ActionEvent e) {
		// 1).����ȡ��Ϣ����ť��Ӧ����
		if (e.getSource() == this.getItemsBtn) {
			// ��ȡ�ı���ʼIP��ַ
			String ip = jTextStart.getText();
			getinfo(ip, this);
		}

		// 2)."���"��Ӧ��ť
		if (e.getSource() == this.outputBtn) {
			try {
				file1 = new File("�˿���Ϣ.txt");
				OutputStream outstream = new FileOutputStream(file1);
				outstream.write("�˿�\n".getBytes());

				// ���������Ϣ����Ϣ���Ϊ�ո�
				for (int rowNum = 0; rowNum < strList.size(); rowNum++) {
					String StrLine = strList.get(rowNum).get(0)+"\n";
					outstream.write(StrLine.getBytes());
				}
				// ˢ�¡��ر������
				outstream.flush();
				outstream.close();

				// ����ɹ���ʾ��
				PublicWindowSet.promptPopUp("�洢�ļ��ɹ�!!!", "��ʾ", frame);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	// ˢ��ͼ����Ϣ
	private void flushInfo() {
		// ���ͼ����ʷ��Ϣ
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
		}

		// ��ʱ�洢������Ϣ
		List<String> list = new LinkedList<String>();

		// ��strList��Ϣ��ʾ�������
		// strList = new LinkedList<LinkedList<String>>();
		if (strList != null) {
			for (int i = 0; i < strList.size(); i++) {
				list = strList.get(i);
				this.tableModel.addRow(new Object[] { list.get(0) });
			}
		}
	}

	// ����ȡ��Ϣ������
	private void getinfo(String ip, PortWindow portWindow) {
		List<String> portList = information.getPorts(ip);
		for (int j = 0; j < portList.size(); j++) {
			infoList = new LinkedList<String>();
			// 1).��Ӷ˿ں�
			infoList.add(portList.get(j));
			// ��������Ϣ��ӵ�strList��
			strList.add(infoList);
		}
		// ˢ�¼�����ʾ���Ҳ�ͼ����
		flushInfo();
	}
}
