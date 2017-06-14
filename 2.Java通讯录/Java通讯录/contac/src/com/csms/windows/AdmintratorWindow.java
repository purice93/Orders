/**
 * @program ������
 *
 */

package com.csms.windows;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.csms.dao.PersionDao;
import com.csms.entity.Persion;

public class AdmintratorWindow extends JFrame implements ActionListener {
	private static final int WINDOWWIDE = 750;
	private static final int WINDOWHIGH = 400;

	private JFrame frame = new JFrame();
	private DefaultTableModel tableModel;
	private JTable table = null;

	private Frame f;
	private FileDialog fd;
	private File file1 = null;
	private List<Persion> pList = new LinkedList<Persion>();

	// �����߸���ť
	private JButton loadFileBtn = new JButton("��ȡ�ļ�");
	private JButton insertPersionBtn = new JButton("��������");
	private JButton deletePersionBtn = new JButton("ɾ������");
	private JButton modifyPersionBtn = new JButton("�޸�����");
	private JButton sortPersionBtn = new JButton("��������");
	private JButton findPersionBtn = new JButton("��������");
	private JButton outputFileBtn = new JButton("����ļ�");

	// ͨѶ¼����
	public AdmintratorWindow() {

		// ������
		this.rigthInformation();
		PublicWindowSet.windowAttribute(this, WINDOWWIDE, WINDOWHIGH, "ͨѶ¼");
	}

	// �б���Ϣ
	public void rigthInformation() {
		// ����JTabel��Ĭ������
		this.tableModel = new DefaultTableModel(new PersionList().userInfo, new PersionList().titles);
		// ���JLabel���
		this.table = new JTable(this.tableModel);
		// ��ֹJLabel������Ŵ��ڵĴ�С���ı�
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// JTabel���������ʾ�ߴ�
		table.setPreferredScrollableViewportSize(new Dimension(690, 0));
		// ʹ�б��������еĽ����ƶ�
		table.getTableHeader().setReorderingAllowed(false);
		// ʹ�б�򲻿ɱ��༭
		table.enable(false);
		// ��JTabel�����ӻ�����
		JScrollPane scr = new JScrollPane(this.table);

		// �������
		JPanel toolBar = new JPanel();

		// ����߸���ť
		toolBar.add(this.loadFileBtn);
		toolBar.add(this.insertPersionBtn);
		toolBar.add(this.deletePersionBtn);
		toolBar.add(this.modifyPersionBtn);
		toolBar.add(this.findPersionBtn);
		toolBar.add(this.sortPersionBtn);
		toolBar.add(this.outputFileBtn);

		// ���ײ������������������²�
		this.add(toolBar, BorderLayout.SOUTH);
		// ������������Tabel������������������
		this.add(scr, BorderLayout.EAST);

		// ��Ӱ�ť��Ӧ�¼�
		this.loadFileBtn.addActionListener(this);
		this.insertPersionBtn.addActionListener(this);
		this.deletePersionBtn.addActionListener(this);
		this.modifyPersionBtn.addActionListener(this);
		this.findPersionBtn.addActionListener(this);
		this.sortPersionBtn.addActionListener(this);
		this.outputFileBtn.addActionListener(this);

		// ��ձ��
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
		}
	}

	// JTabel�б�����������
	class PersionList extends AbstractTableModel {
		public String[] titles = { "����", "����", "��ַ", "����" };
		public Object[][] userInfo = {}; // ��������

		public int getRowCount() {
			return 0;
		}

		public int getColumnCount() {
			return 0;
		}
		
		//Ĭ����д����
		@Override
		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	// �¼���Ӧ
	public void actionPerformed(ActionEvent e) {

		// 1.���ļ�
		if (e.getSource() == this.loadFileBtn) {
			Persion per = null;
			List<String> array = new ArrayList<>();
			fd = new FileDialog(f, "Open", FileDialog.LOAD);// ��������ʾ���ļ��Ի���
			fd.setVisible(true);

			// �Ի�������ʽ��ȡ�ļ�����
			try {

				file1 = new File(fd.getDirectory(), fd.getFile());
				FileReader fr = new FileReader(file1);
				BufferedReader br = new BufferedReader(fr);
				String aline;

				// ���ж�ȡ�ı�
				while ((aline = br.readLine()) != null) {
					array = Arrays.asList(aline.split(" "));
					per = new Persion();
					per.setName(array.get(0));
					per.setPhoneNumber(array.get(1));
					per.setAddress(array.get(2));
					per.setEmail(array.get(3));
					pList.add(per);
				}
				fr.close();
				br.close();
				flushWindow(pList);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		// 2.������Ϣ
		if (e.getSource() == this.insertPersionBtn) {
			setEnabled(false);
			persionInformationWindow piw = new persionInformationWindow(this, pList);
			setEnabled(true);
			flushWindow(pList);
		}

		// 3.ɾ����Ϣ
		if (e.getSource() == this.deletePersionBtn) {
			new deleteWindow(this, pList);
		}

		// 4.�޸���Ϣ
		if (e.getSource() == this.modifyPersionBtn) {
			new modifyWindow(this, pList);
		}

		// 5.������Ϣ
		if (e.getSource() == this.findPersionBtn) {
			new findWindow(pList);
		}

		// 6.������Ϣ
		if (e.getSource() == this.sortPersionBtn) {
			new sortWindow(this, pList);
		}

		// 7.����ļ���ֱ����������ݿ⣩
		if (e.getSource() == this.outputFileBtn) {
			Persion pe = new Persion();
			PersionDao pd = new PersionDao();
			for (int i = 0; i < pList.size(); i++) {
				pe.setName(pList.get(i).getName());
				pe.setPhoneNumber(pList.get(i).getPhoneNumber());
				pe.setAddress(pList.get(i).getAddress());
				pe.setEmail(pList.get(i).getEmail());
				pd.insertPersion(pe);
			}
			PublicWindowSet.promptPopUp("����ļ��ɹ�!!!", "��ʾ", frame);
		}
	}

	// ���ļ��󣬳���ͨѶ¼��Ϣ��ˢ�£�
	public void flushWindow(List<Persion> pList2) {
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
		}
		Persion pe2 = new Persion();
		for (int i = 0; i < pList2.size(); i++) {
			pe2 = pList2.get(i);
			this.tableModel
					.addRow(new Object[] { pe2.getName(), pe2.getPhoneNumber(), pe2.getAddress(), pe2.getEmail() });
		}
	}

}

// �����Ϣ����
class persionInformationWindow {

	private JFrame frame = new JFrame();
	private AdmintratorWindow admintratorWindow = null;

	public persionInformationWindow(AdmintratorWindow admintratorWindow, List<Persion> pList) {
		this.admintratorWindow = admintratorWindow;
		this.displayInformation(this.frame, pList);
	}

	// ��������
	private void displayInformation(JFrame jfr, List<Persion> pList) {
		//������ʾ
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "���ͨѶ¼");
		PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "����:");
		PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "����:");
		PublicWindowSet.addLabel(jfr, 16, 20, 130, 90, 30, "��ַ:");
		PublicWindowSet.addLabel(jfr, 16, 20, 160, 90, 30, "����:");

		// �ı������
		JTextField name = new JTextField();
		PublicWindowSet.addTextField(name, 100, 70, 120, 25, jfr);
		JTextField phoneNumber = new JTextField();
		PublicWindowSet.addTextField(phoneNumber, 100, 100, 120, 25, jfr);
		JTextField address = new JTextField();
		PublicWindowSet.addTextField(address, 100, 130, 120, 25, jfr);
		JTextField email = new JTextField();
		PublicWindowSet.addTextField(email, 100, 160, 120, 25, jfr);

		PublicWindowSet.addTableList(jfr, null);// ռλ�������ͻ

		JButton button = new JButton("ȷ�����");
		button.setBounds(75, 400, 100, 30);
		button.setFont(new Font("����", Font.PLAIN, 16));
		button.setContentAreaFilled(false);

		// ���������ײ����ϣ�
		jfr.add(button, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 250, 300, "���ͨѶ¼");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����Ϣ����
				insertAction(pList, jfr, name, phoneNumber, address, email);
				// ˢ���б���Ϣ
				admintratorWindow.flushWindow(pList);
			}
		});
	}

	// ������ϵ��
	private void insertAction(List<Persion> pList, JFrame jfr, JTextField name, JTextField phoneNumber,
			JTextField address, JTextField email) {
		Persion persion = new Persion();
		
		if ((name.getText() == null) || (phoneNumber.getText() == null) || (address.getText() == null)
				|| (email.getText() == null)) {
			PublicWindowSet.promptPopUp("���벻��Ϊ��!!!", "��ʾ", jfr);
		}
		
		//��ȡ����ı���Ϣ
		persion.setName(name.getText());
		persion.setPhoneNumber(phoneNumber.getText());
		persion.setAddress(address.getText());
		persion.setEmail(email.getText());
		pList.add(persion);
		PublicWindowSet.promptPopUp("��ӳɹ�!!!", "��ʾ", jfr);
		name.setText("");
		phoneNumber.setText("");
		address.setText("");
		email.setText("");
		jfr.setDefaultCloseOperation(jfr.DISPOSE_ON_CLOSE);
		jfr.dispose();
	}
}

// ɾ����Ϣ
class deleteWindow {

	private JFrame frame = new JFrame();
	private AdmintratorWindow admintratorWindow;

	public deleteWindow(AdmintratorWindow awin, List<Persion> pList) {
		this.admintratorWindow = awin;
		this.displayInformation(this.frame, pList);
	}
	
	//ɾ����ϵ�˺���
	private void displayInformation(JFrame jfr, List<Persion> pList) {
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "ɾ��ͨѶ¼");
		PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "����:");

		JTextField name = new JTextField();
		PublicWindowSet.addTextField(name, 100, 70, 120, 25, jfr);

		PublicWindowSet.addTableList(jfr, null);// ռλ�������ͻ

		JButton button = new JButton("ȷ��ɾ��");
		button.setBounds(75, 400, 100, 30);
		button.setFont(new Font("����", Font.PLAIN, 16));
		button.setContentAreaFilled(false);

		// ���������ײ����ϣ�
		jfr.add(button, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 250, 300, "ɾ��ͨѶ¼");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(pList, jfr, name);
				admintratorWindow.flushWindow(pList);
			}
		});
	}

	protected void delete(List<Persion> pList, JFrame jfr, JTextField name) {
		Persion persion = new Persion();
		if ((name.getText() == null)) {
			PublicWindowSet.promptPopUp("���벻��Ϊ��!!!", "��ʾ", jfr);
		}

		String pname = name.getText();
		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).getName().equals(pname)) {
				pList.remove(i);
			}
		}
		PublicWindowSet.promptPopUp("ɾ���ɹ�!!!", "��ʾ", jfr);
		name.setText("");
		jfr.dispose();
	}

}

// �޸���Ϣ
class modifyWindow {

	private JFrame frame = new JFrame();
	private AdmintratorWindow admintratorWindow = null;

	public modifyWindow(AdmintratorWindow awin, List<Persion> pList) {
		this.admintratorWindow = awin;
		this.displayInformation(this.frame, pList);
	}

	// ���������ʾ
	private void displayInformation(JFrame jfr, List<Persion> pList) {
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "�޸�ͨ��¼");
		PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "ԭʼ����:");
		PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "��������:");
		PublicWindowSet.addLabel(jfr, 16, 20, 130, 90, 30, "���ĺ���:");
		PublicWindowSet.addLabel(jfr, 16, 20, 160, 90, 30, "���ĵ�ַ:");
		PublicWindowSet.addLabel(jfr, 16, 20, 190, 90, 30, "��������:");

		JTextField bName = new JTextField();
		PublicWindowSet.addTextField(bName, 100, 70, 120, 25, jfr);
		JTextField name = new JTextField();
		PublicWindowSet.addTextField(name, 100, 100, 120, 25, jfr);
		JTextField phoneNumber = new JTextField();
		PublicWindowSet.addTextField(phoneNumber, 100, 130, 120, 25, jfr);
		JTextField address = new JTextField();
		PublicWindowSet.addTextField(address, 100, 160, 120, 25, jfr);
		JTextField email = new JTextField();
		PublicWindowSet.addTextField(email, 100, 190, 120, 25, jfr);

		PublicWindowSet.addTableList(jfr, null);// ռλ�������ͻ

		JButton button = new JButton("ȷ���޸�");
		button.setBounds(75, 400, 100, 30);
		button.setFont(new Font("����", Font.PLAIN, 16));
		button.setContentAreaFilled(false);

		// ���������ײ����ϣ�
		jfr.add(button, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 250, 300, "�޸�ͨѶ¼");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyPersion(pList, jfr, bName, name, phoneNumber, address, email);
				admintratorWindow.flushWindow(pList);
			}
		});
	}

	// �޸���ϵ��
	protected void modifyPersion(List<Persion> pList, JFrame jfr, JTextField bName, JTextField name,
			JTextField phoneNumber, JTextField address, JTextField email) {
		Persion persion = new Persion();
		if ((bName.getText() == null) || (name.getText() == null) || (phoneNumber.getText() == null)
				|| (address.getText() == null) || (email.getText() == null)) {
			PublicWindowSet.promptPopUp("���벻��Ϊ��!!!", "��ʾ", jfr);
		}

		String pName = bName.getText();

		persion.setName(name.getText());
		persion.setPhoneNumber(phoneNumber.getText());
		persion.setAddress(address.getText());
		persion.setEmail(email.getText());

		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).getName().equals(pName)) {
				pList.set(i, persion);
				System.out.println(pList.get(i).getEmail());
			}
		}

		PublicWindowSet.promptPopUp("�޸ĳɹ�!!!", "��ʾ", jfr);
		name.setText("");
		phoneNumber.setText("");
		address.setText("");
		email.setText("");
		jfr.dispose();
	}

}

// ������Ϣ
class findWindow {

	private JFrame frame = new JFrame();

	public findWindow(List<Persion> pList) {
		this.displayInformation(this.frame, pList);
	}

	private void displayInformation(JFrame jfr, List<Persion> pList) {
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "����ͨѶ¼");
		PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "��������:");
		PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "���ߺ���:");

		PublicWindowSet.addLabel(jfr, 16, 20, 130, 90, 30, "����:");
		PublicWindowSet.addLabel(jfr, 16, 20, 160, 90, 30, "����:");
		PublicWindowSet.addLabel(jfr, 16, 20, 190, 90, 30, "��ַ:");
		PublicWindowSet.addLabel(jfr, 16, 20, 220, 90, 30, "����:");

		JTextField fName = new JTextField();
		PublicWindowSet.addTextField(fName, 100, 70, 120, 25, jfr);
		JTextField fPhoneNumber = new JTextField();
		PublicWindowSet.addTextField(fPhoneNumber, 100, 100, 120, 25, jfr);
		JTextField name = new JTextField();
		PublicWindowSet.addTextField(name, 100, 130, 120, 25, jfr);
		JTextField phoneNumber = new JTextField();
		PublicWindowSet.addTextField(phoneNumber, 100, 160, 120, 25, jfr);
		JTextField address = new JTextField();
		PublicWindowSet.addTextField(address, 100, 190, 120, 25, jfr);
		JTextField email = new JTextField();
		PublicWindowSet.addTextField(email, 100, 220, 120, 25, jfr);

		PublicWindowSet.addTableList(jfr, null);// ռλ�������ͻ

		JButton button = new JButton("����");
		button.setBounds(75, 400, 100, 30);
		button.setFont(new Font("����", Font.PLAIN, 16));
		button.setContentAreaFilled(false);

		// ���������ײ����ϣ�
		jfr.add(button, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 250, 300, "����ͨѶ¼");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findPersion(pList, jfr, fName, fPhoneNumber, name, phoneNumber, address, email);
			}
		});
	}

	protected void findPersion(List<Persion> pList, JFrame jfr, JTextField fname, JTextField fphoneNumber,
			JTextField name, JTextField phoneNumber, JTextField address, JTextField email) {
		Persion persion = new Persion();
		if ((fname.getText() == null) && (fphoneNumber.getText() == null)) {
			PublicWindowSet.promptPopUp("���벻��Ϊ��!!!", "��ʾ", jfr);
		}

		if (fname.getText() != null) {
			for (int i = 0; i < pList.size(); i++) {
				if (pList.get(i).getName().equals(fname.getText())) {
					persion.setName(pList.get(i).getName());
					persion.setPhoneNumber(pList.get(i).getPhoneNumber());
					persion.setAddress(pList.get(i).getAddress());
					persion.setEmail(pList.get(i).getEmail());
				}
			}
		}

		if (fphoneNumber.getText() != null) {
			for (int i = 0; i < pList.size(); i++) {
				if (pList.get(i).getPhoneNumber().equals(fphoneNumber.getText())) {
					persion.setName(pList.get(i).getName());
					persion.setPhoneNumber(pList.get(i).getPhoneNumber());
					persion.setAddress(pList.get(i).getAddress());
					persion.setEmail(pList.get(i).getEmail());
				}
			}
		}

		name.setText(persion.getName());
		phoneNumber.setText(persion.getPhoneNumber());
		address.setText(persion.getAddress());
		email.setText(persion.getEmail());
	}
}

// ������Ϣ
class sortWindow extends JFrame {

	private JFrame frame = new JFrame();
	private AdmintratorWindow admintratorWindow = null;
	private JPanel toolB = new JPanel();

	public sortWindow(AdmintratorWindow admintratorWindow, List<Persion> pList) {

		this.setLayout(new FlowLayout());
		this.admintratorWindow = admintratorWindow;
		this.displayInformation(this.frame, pList, toolB);
	}

	private void displayInformation(JFrame jfr, List<Persion> pList, JPanel toolB2) {
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "����ͨѶ¼");

		PublicWindowSet.addTableList(jfr, null);// ռλ�������ͻ

		JButton buttonByName = new JButton("����������");
		JButton buttonByPhone = new JButton("����������");

		buttonByName.setBounds(75, 400, 100, 30);
		buttonByPhone.setBounds(75, 200, 100, 30);
		buttonByName.setFont(new Font("����", Font.PLAIN, 16));
		buttonByPhone.setFont(new Font("����", Font.PLAIN, 16));
		buttonByName.setContentAreaFilled(false);
		buttonByPhone.setContentAreaFilled(false);

		toolB.add(buttonByPhone);
		toolB.add(buttonByName);

		// ���ײ������������������ϲ�����
		jfr.add(toolB, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 250, 300, "����ͨѶ¼");
		buttonByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortPersionByName(pList);
				admintratorWindow.flushWindow(pList);
			}
		});

		buttonByPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortPersionByPhone(pList);
				admintratorWindow.flushWindow(pList);
			}
		});
	}

	
	//����ϵ�˽�������������
	//��С�ȽϺ���ʹ��Java�Դ�compareTo����
	protected void sortPersionByName(List<Persion> pList) {
		Persion tPersion = null;
		for (int j = pList.size() - 1; j > 0; j--) {
			for (int i = 1; i < j+1; i++) {
				if ((pList.get(i).getName().compareTo(pList.get(i - 1).getName())) < 0) {
					tPersion = pList.get(i);
					pList.set(i, pList.get(i - 1));
					pList.set(i - 1, tPersion);
				}
			}
		}
	}
	
	//����ϵ�˽������򣨵绰���룩
	protected void sortPersionByPhone(List<Persion> pList) {
		Persion tPersion = null;
		for (int j = pList.size() - 1; j > 0; j--) {
			for (int i = 1; i < j+1; i++) {
				if ((pList.get(i).getPhoneNumber().compareTo(pList.get(i - 1).getPhoneNumber())) < 0) {
					tPersion = pList.get(i);
					pList.set(i, pList.get(i - 1));
					pList.set(i - 1, tPersion);
				}
			}
		}
	}

}
