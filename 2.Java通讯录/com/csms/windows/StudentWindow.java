/**
 * @program ����ѧ��ѡ��ϵͳ��ѧ������
 *
 */
package com.csms.windows;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;

import com.csms.dao.CourseDAO;
import com.csms.dao.SelectCourseDAO;
import com.csms.dao.StudentDAO;
import com.csms.entity.Student;
import com.csms.entity.Course;
import com.csms.entity.SelectCourse;
 
public class StudentWindow extends JFrame implements ActionListener {
    // ѡ��ϵͳ��ѧ�����ڽ���Ĵ�С
    private static final int WINDOWWIDE = 922;
    private static final int WINDOWHIGH = 570;
    // �ӵ�¼����ͨ��ȡ��������û������û����룬��ͨ����ѯȡ�ø��û������е�����
    public Student student = null;
    // �жϴ�ӡ�γ̵ı�־��flag��=1��ʱ��Ͳ��ܴ�ӡ�ˣ�ֻ�ܴ�ӡһ��
    private static int courseFlag = 1;
    // �ж�ˢ��ѡ�εı�־��flag��=1��ʱ��Ͳ��ܴ�ӡ�ˣ�ֻ�ܴ�ӡһ��
    private static int flushFlag = 1;
    // ���ý����table����ı���
    private JTable table = null;
    private DefaultTableModel tableModel;
    private JButton addFlushBtn = new JButton("ˢ�¿γ�"); // ���尴ť
    private JButton addSelectBtn = new JButton("ȷ��ѡ��"); // ���尴ť
    private JButton findBtn = new JButton("���ҿγ�"); // ���尴ť
    private JButton stopBtn = new JButton("�˳�"); // ���尴ť
    private JTextField courseIDText = null;
 
    // Student���������ܺ���
    public StudentWindow() {
        // ȡ���û���������Ϣ
        student = new StudentDAO().searchStudentAll(LoginWindow.getLoginUserName(), LoginWindow.getLoginPassword());
        // 1.����Ϣ��ʾ��
        this.rigthInformation(student);
 
        // 2.����ұߵ���Ϣ���߿�
        this.liftInformation(student);
 
        // 3.���ô��ڵ�����
        PublicWindowSet.windowAttribute(this, WINDOWWIDE, WINDOWHIGH, null, "ѧ�����洰��");
    }
 
    // ��ߴ�����Ϣ��
    public void liftInformation(Student student) {
        // 1).���һ���ı���ǩ
        Icon lan = new ImageIcon("src//images//icons//lan.png");
        JLabel label = new JLabel(lan);
        label.setBounds(-20, 2, 230, 50);
        this.add(label);
 
        // 2).���һ����Ƭ��ʾ�Ĵ���
        // addPhotoLabel,addLabelҲ��PublicWindowSet���з�װ�õı�ǩ��Ӻ���
        // 
        String photoUrl = "src//images//students//" + student.getLoginName() + ".jpg";// ��ʱ���ṩ
        PublicWindowSet.addPhotoLabel(this, 40, 70, 130, 170, photoUrl);
        // ���ѧ����Ϣ��ǩ
        PublicWindowSet.addLabel(this, 16, 40, 243, 200, 40, "����:   " + student.getStuName());
        PublicWindowSet.addLabel(this, 16, 40, 263, 200, 40, "ѧ��:   " + student.getLoginName());
        PublicWindowSet.addLabel(this, 16, 40, 283, 200, 40, "ѧԺ:   " + student.getStuSdept());
 
        // 4).���÷ָ���ǩ
        Icon away = new ImageIcon("src//images//icons//tree1.png");
        JLabel awayLabel = new JLabel(away);
        awayLabel.setBounds(200, 50, 10, 450);
        this.add(awayLabel);
    }
 
    // �Ҳ��б���Ϣ
    public void rigthInformation(Student student) {
    	// ����JTabel��Ĭ������
        this.tableModel = new DefaultTableModel(new CourseList().userInfo, new CourseList().titles);
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
 
        // ��ӵײ���Ϣ��ʾ��ǩ
        Icon bottom = new ImageIcon("src//images//icons//bottom1.png");
        JLabel bottomLab = new JLabel(bottom);
        toolBar.add(bottomLab);
 
        // ��ӿγ̺������ı���
        courseIDText = new JTextField(25);
        toolBar.add(courseIDText);
 
        // ���������ť��ѡ�ΰ�ť�ͣ�ˢ�¿γ̰�ť
        toolBar.add(this.addFlushBtn);
        toolBar.add(this.addSelectBtn);
        toolBar.add(this.findBtn);
        toolBar.add(this.stopBtn);
 
        // ���ײ������������������ϲ�����
        this.add(toolBar, BorderLayout.SOUTH);
        // ������������Tabel�����������Ķ�������
        this.add(scr, BorderLayout.EAST);
 
        // ���ѡ�ΰ�ť��ˢ�¿γ��б�ť����Ӧ�¼�
        this.addFlushBtn.addActionListener(this);
        this.addSelectBtn.addActionListener(this);
        this.findBtn.addActionListener(this);
        this.stopBtn.addActionListener(this);
        
        
        // ˢ�¿α�
        // �����ԭʼ����Ϣ
        while(this.tableModel.getRowCount() > 0) {
        	this.tableModel.removeRow(this.tableModel.getRowCount()-1);
        }
        List<Course> list = new CourseDAO().searchCourseInformation();
        Course arrange = null;
        for (int i = 0; i < list.size(); i++) {
            arrange = list.get(i);
            this.tableModel.addRow(new Object[] { arrange.getCourID(), arrange.getCourName(),
            		arrange.getCsum(), arrange.getCselected() });
        }
        flushFlag++;
    }
 
    // JTabel�б�����������
    class CourseList extends AbstractTableModel {
        public String[] titles = { "�γ�ID", "�γ���","ѡ������", "��ѡ����" };
        public Object[][] userInfo = {}; // ��������
 
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
 
    // ȡ�ô��ڵĿ��
    public static int getWindowwide() {
        return WINDOWWIDE;
    }
 
    // ȡ�ô��ڵĸ߶�
    public static int getWindowhigh() {
        return WINDOWHIGH;
    }
 
    // ѡ�ΰ�ť��ˢ�°�ť��ʱ����Ӧ���ƴ�����
    public void actionPerformed(ActionEvent e) {
        // ˢ�¿γ���Ϣ�������ݿ��ж������еĿγ���Ϣ
        if (e.getSource() == this.addFlushBtn) {
        	while(this.tableModel.getRowCount() > 0) {
            	this.tableModel.removeRow(this.tableModel.getRowCount()-1);
            }
            List<Course> list = new CourseDAO().searchCourseInformation();
            Course arrange = null;
            for (int i = 0; i < list.size(); i++) {
                arrange = list.get(i);
                this.tableModel.addRow(new Object[] { arrange.getCourID(), arrange.getCourName(),
                		arrange.getCsum(), arrange.getCselected() });
            }
        }
        // ȷ����ӿγ���Ϣ
        if (e.getSource() == this.addSelectBtn) {
 
            // 1. ȡ��ѧ����ID
            String studentID = student.getLoginName();
            // 2. ȡ����д�Ŀγ�ID
            String courseID = courseIDText.getText();
            // 3. ������ˢ�µ����ݿ����ѡ�γ̱�
            if (courseID.length() != 0) {
            	
            	int sc_count= new SelectCourseDAO().getById(studentID, courseID);

        		List<Course> c_list= new SelectCourseDAO().getNumById(courseID);
        		Course sc_course = c_list.get(0);
                
            	if(sc_count > 0) {
            		PublicWindowSet.promptPopUp("���Ѿ�ѡ���˿Σ�����Ҫ��ѡ", "ѡ����ʾ", this);
            	} else if (sc_course.getCsum() == sc_course.getCselected()) {
            			PublicWindowSet.promptPopUp("���������޷���ѡ", "ѡ����ʾ", this);
            	} else {
            		int count = new SelectCourseDAO().addStudentSelectCourseInformation(studentID, courseID);
                	if(count > 0) {
                		PublicWindowSet.promptPopUp("ѡ�γɹ�", "ѡ����ʾ", this);
                	}
                }
            } else {
                PublicWindowSet.promptPopUp("����д�γ�ID,��ѡ��!", "ѡ����ʾ", this);
            }
        }
        // ���ҿγ�
        if (e.getSource() == this.findBtn) {
 
            // 1. ȡ����д�Ŀγ�ID
            String courseID = courseIDText.getText();
            // 3. ������ˢ�µ����ݿ����ѡ�γ̱�
            if (courseID.length() != 0) {
            	// �г��γ��б�
                List<Course> list = new CourseDAO().searchCourseInformationById(courseID);
                Course arrange = null;
                while(this.tableModel.getRowCount() > 0) {
                	this.tableModel.removeRow(this.tableModel.getRowCount()-1);
                }
                
                for (int i = 0; i < list.size(); i++) {
                    arrange = list.get(i);
                    this.tableModel.addRow(new Object[] { arrange.getCourID(), arrange.getCourName(),
                    		arrange.getCsum(), arrange.getCselected() });
                }
                flushFlag++;
            	
            } else {
                PublicWindowSet.promptPopUp("����д�γ�ID,��ѡ��!", "ѡ����ʾ", this);
            }
        }
        // �˳�
        if (e.getSource() == this.stopBtn) {
        	this.dispose();
        }
    }
 
}
