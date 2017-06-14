/**
 * @program ����Ա����
 *
 */
 
package com.csms.windows;
 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
 
import com.csms.dao.AdminitartorDAO;
import com.csms.dao.CourseDAO;
import com.csms.dao.SelectCourseDAO;
import com.csms.dao.StudentDAO;
import com.csms.entity.Adminitartor;
import com.csms.entity.Course;
import com.csms.entity.SelectCourse;
import com.csms.entity.Student;
 
public class AdmintratorWindow {
    private static final int WINDOWWIDE = 200;
    private static final int WINDOWHIGH = 400;
    private static Adminitartor admin = null;
    String userName = LoginWindow.getLoginUserName();
    String userPsd = LoginWindow.getLoginPassword();
    String url = "src//images//icons//admin1.png";
    private JFrame frame = new JFrame();

    // ����Ա����
    public AdmintratorWindow() {
        admin = this.getAdminitratorInformation();
        this.informationArea(frame);
 
        PublicWindowSet.windowAttribute(frame, WINDOWWIDE, WINDOWHIGH, url, "����Ա����");
    }
 
    // �����Ϣ����
    public void informationArea(JFrame jfr) {
        // �����
        PublicWindowSet.addLabel(jfr, 26, 30, 10, 150, 30, "����Ա��Ϣ");
        // ����Ա�˺���
        PublicWindowSet.addLabel(jfr, 16, 20, 80, 100, 15, "�˺�:");
        // ����Ա���湦�ܰ�ť
        addButton(1, "���ѧ��", 140, jfr);
        addButton(2, "��ӿγ�", 180, jfr);
        addButton(3, "�γ��޸�", 220, jfr);
        addButton(4, "�˳�", 260, jfr);
        // ��ӹ���Ա����ϸ��Ϣ
        PublicWindowSet.addLabel(jfr, 16, 65, 80, 100, 15, admin.getAdminName());
    }
 
    // ���button
    private static void addButton(int flag, String str, int high, JFrame frm) {
        JButton button = new JButton(str);
        button.setFont(new Font("����", Font.PLAIN, 16));
        button.setBounds(30, high, 130, 30);
        button.setContentAreaFilled(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (flag) {
                case 1:
                    // ���ѧ��
                    new releaseStudentInformationWindow();
                    break;
                case 2:
                    // ��ӿγ�
                    new releaseCourseInformationWindow();
                    break;
                case 3:
                    // �γ��޸�
                    new deleteInformationWindow();
                    break;
                case 4:
                    // �˳�
                    frm.dispose();
                    break;
                }
            }
        });
        frm.add(button);
    }
 
    // ��ȡ����Ա��������Ϣ
    public Adminitartor getAdminitratorInformation() {
        return new AdminitartorDAO().getInformation(userName, userPsd);
    }
}
 
// ���ѧ����Ϣ
class releaseStudentInformationWindow {
 
    private JFrame frame = new JFrame();
    String url = "src//images//icons//admin.png";
    public releaseStudentInformationWindow() {
        this.displayInformation(this.frame);
 
    }
 
    private void displayInformation(JFrame jfr) {
        PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "ѧ����Ϣ¼��");
        PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "ѧ��ID:");
        PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "��¼����:");
        PublicWindowSet.addLabel(jfr, 16, 20, 130, 90, 30, "ѧ������:");
        PublicWindowSet.addLabel(jfr, 16, 20, 160, 90, 30, "ѧ��Ժϵ:");
 
        JTextField userName = new JTextField();
        PublicWindowSet.addTextField(userName, 100, 70, 120, 25, jfr);
        JTextField userPsd = new JTextField();
        PublicWindowSet.addTextField(userPsd, 100, 100, 120, 25, jfr);
        JTextField id = new JTextField();
        PublicWindowSet.addTextField(id, 100, 130, 120, 25, jfr);
        JTextField sdept = new JTextField();
        PublicWindowSet.addTextField(sdept, 100, 160, 120, 25, jfr);
 
        JButton button = new JButton("ȷ�����");
        button.setBounds(75, 330, 100, 30);
        button.setFont(new Font("����", Font.PLAIN, 16));
        button.setContentAreaFilled(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student student = new Student();
                if((userPsd.getText() == null) || (userPsd.getText() == null)|| (userName.getText() == null)|| 
                		(sdept.getText() == null)) {
                	PublicWindowSet.promptPopUp("���벻��Ϊ��!!!", "��ʾ", jfr);
                }
                // ID
                student.setLoginName(userPsd.getText());
                // ��¼����
                student.setLoginPSD(userPsd.getText());
                // ѧ������
                student.setStuName(userName.getText());
                // ѧ��ϵԺ
                student.setStuSdept(sdept.getText());
                new StudentDAO().insertStudentInformation(student);
                PublicWindowSet.promptPopUp("¼��ѧ���ɹ�!!!", "��ʾ", jfr);
                userName.setText("");
                userPsd.setText("");
                id.setText("");
                sdept.setText("");
            }
        });
        jfr.add(button);
        PublicWindowSet.windowAttribute(this.frame, 250, 400, url, "���ѧ��");
    }
}
 
// ¼���ſ���Ϣ
class releaseCourseInformationWindow {
    private JFrame frame = new JFrame();
    String url = "src//images//icons//admin.png";
 
    private void displayInformation(JFrame jfr) {
        PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "�ſ���Ϣ¼��");
        PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "�γ�ID:");
        PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "�γ�����:");
        PublicWindowSet.addLabel(jfr, 16, 20, 130, 90, 30, "�γ�����:");
 
        JTextField courID = new JTextField();
        PublicWindowSet.addTextField(courID, 100, 70, 120, 25, jfr);
        JTextField name = new JTextField();
        PublicWindowSet.addTextField(name, 100, 100, 120, 25, jfr);
        JTextField number = new JTextField();
        PublicWindowSet.addTextField(number, 100, 130, 120, 25, jfr);
 
        JButton button = new JButton("ȷ�����");
        button.setBounds(75, 350, 100, 30);
        button.setFont(new Font("����", Font.PLAIN, 16));
        button.setContentAreaFilled(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Course ca = new Course();
            	if((courID.getText() == null) || (courID.getText() == null)|| (courID.getText() == null) 
            			|| (courID.getText() == null)) {
                	PublicWindowSet.promptPopUp("���벻��Ϊ��!!!", "��ʾ", jfr);
                }
                ca.setCourID(courID.getText());
                ca.setCourName(name.getText());
                ca.setCsum(Integer.valueOf(number.getText()));
                

                new CourseDAO().insertCourse(ca);
                PublicWindowSet.promptPopUp("¼��γ̳ɹ�!!!", "��ʾ", jfr);
                courID.setText("");
                name.setText("");
                number.setText("");
            }
        });
        jfr.add(button);
 
        PublicWindowSet.windowAttribute(this.frame, 250, 420, url, "��ӿγ�");
    }
 
    public releaseCourseInformationWindow() {
        this.displayInformation(frame);
    }
}
 
// ɾ����Ϣ
class deleteInformationWindow {
    private JFrame frame = new JFrame();
    String url = "src//images//icons//admin.png";
 
    public deleteInformationWindow() {
        this.displayInformation(frame);
    }
 
    private void displayInformation(JFrame jfr) {
        // ��ӷ�����ǩ
        PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "�γ��޸�");
 
        // ��ӿγ���Ϣ
        PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "�γ�ID:");
        JTextField couIDField = new JTextField();
        PublicWindowSet.addTextField(couIDField, 85, 70, 125, 25, jfr);
        this.addButton(1, "�鿴���пγ�", 235, this.frame, couIDField);
        this.addButton(2, "ɾ���γ���Ϣ", 270, this.frame, couIDField);
 
        // ���ô�������
        PublicWindowSet.windowAttribute(jfr, 250, 420, url, "�γ��޸�");
    }
 
    public void addButton(int flag, String str, int high, JFrame jfr, JTextField tf) {
        JButton button = new JButton(str);
        button.setFont(new Font("����", Font.PLAIN, 16));
        button.setContentAreaFilled(false);
        button.setBounds(50, high, 150, 30);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (flag) {
                case 1:
                    // �鿴�ſ���Ϣ
                    DefaultTableModel courTableModel;
                    String[] courStr = { "�γ�ID", "�γ�����","�γ�������", "��ѡ����" };
                    JFrame courFrame = new JFrame();
                    PublicWindowSet.windowAttribute(courFrame, 300, 400, null, "�ſ���Ϣ");
                    courTableModel = PublicWindowSet.addTableList(courFrame, courStr);
                    List<Course> courseList = new CourseDAO().searchAllCourse();
                    Course courseArrange = null;
                    for (int i = 0; i < courseList.size(); i++) {
                        courseArrange = courseList.get(i);
                        courTableModel.addRow(new Object[] { 
                        		courseArrange.getCourID(),
                        		courseArrange.getCourName(),
                        		courseArrange.getCsum(),
                        		courseArrange.getCselected()
                        });
                    }
                    break;
                case 2:
                    // ɾ���ſ���Ϣ
                    String arrangeId = tf.getText();
                    if (arrangeId.length() == 0) {
                        PublicWindowSet.promptPopUp("������γ�ID������", "������ʾ", jfr);
                    } else {
                        new CourseDAO().deleteArrageCourse(arrangeId);
                        PublicWindowSet.promptPopUp("ɾ���γ̳ɹ�!!!", "��ʾ", jfr);
                        tf.setText("");
                    }
                    break;
                }
            }
        });
        jfr.add(button);
    }
}