/**
 * @program ʵ�ֵ�¼����
 *
 */
 
package com.csms.windows;
 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.csms.dao.*;
import javax.swing.*;
 
// ѧ��ѡ��ϵͳ��¼����
public class LoginWindow {
    JFrame frame = new JFrame();
    // ���ڵĴ�С
    private static final int WINDOWSWIDE = 400;
    private static final int WINDOWSHIGH = 200;
    // ȷ��radioButton��ѡ���1��ʾ����Ա��2��ʾѧ��
    private static int flag = 0;
    // �����û����������������������
    final JTextField userText;
    final JPasswordField pswText;
    
    // ���������ǩ���û�����ǩ
    JLabel psdLabel;
    JLabel textLabel;
    
    // ��ѡ��ť�������
    JRadioButton radioButton;
    // �û���¼��������ͨ����������������ѧ�����棬��ʦ������ȡ�õ�½���û���������
    private static String loginUserName;
    private static String loginPassword;
    // ������ѡ��ť��
    ButtonGroup buttGroup = new ButtonGroup();
    JPanel southPanel = new JPanel();
    
    // ���õ�¼�����logo��ǩ
    Icon icon = new ImageIcon("src//images//icons//loginIcon.png");
 
    public LoginWindow() {
        // 1.����label��ǩ
        JLabel label = new JLabel(icon);
        label.setBounds(70, 0, 260, 60);
        frame.add(label);
 
        // 2.�����ı���ǩ���ı����λ��
        // �û�����ǩ���û�����¼�򣬲����������ú�λ�ã���ӽ����
        PublicWindowSet.addLabel(frame, 16, 65, 70, 90, 30, "�û���:");
        userText = new JTextField();
        PublicWindowSet.addTextField(userText, 130, 70, 200, 30, frame);
        // �����ǩ�������¼�򣬲����������ú�λ�ã���ӽ����
        PublicWindowSet.addLabel(frame, 16, 65, 105, 90, 30, "��  ��:");
        pswText = new JPasswordField();
        PublicWindowSet.addTextField(pswText, 130, 105, 200, 30, frame);
 
        // 3.���õ�ѡ��ť
        // ������ѡ��ť
        this.addRadioButton("����Ա", 1, false);
        this.addRadioButton("ѧ��", 2, false);
        southPanel.setBounds(60, 140, 200, 30);
        frame.add(southPanel);
 
        // 4.������¼��ť
        this.addButton("��¼", frame);
 
        // 5.���ô�����ص�����,windowAttribute�����Ƿ�װ��PublicWindowSet���еĴ����������ú���
        // ���ô��ڵı���
        PublicWindowSet.windowAttribute(frame, WINDOWSWIDE, WINDOWSHIGH, null, "ѧ��ѡ��ϵͳ");
 
    }
 
    // ���õ�ѡ��ť�ļ���������ӵ�ѡ��ť
    private void addRadioButton(String name, int flag, Boolean bool) {
        radioButton = new JRadioButton(name, bool);
        // ���õ�ѡ��ť͸��
        radioButton.setContentAreaFilled(false);
        // ���õ�ѡ��ť�����������������
        radioButton.setFont(new Font("����", Font.PLAIN, 12));
        radioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginWindow.setFlag(flag);
            }
        });
        buttGroup.add(radioButton);
        southPanel.add(radioButton);
    }
 
    // ��ӵ�¼ȷ�Ϻ�ȡ����ť��ͬʱ���ð�ť������
    private void addButton(String buttonName, JFrame jfr) {
        JButton button = new JButton(buttonName);
        button.setBounds(260, 140, 60, 25);
        button.setFont(new Font("����", Font.PLAIN, 12));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    // ִ�е�¼
                    // �����û��������룬��������
                    loginUserName = userText.getText();
                    loginPassword = pswText.getText();
                    LoginWindow.setLoginUserName(loginUserName);
                    LoginWindow.setLoginPassword(loginPassword);
                    if (LoginWindow.getFlag() == 1) {
                        // 1.����Ա
                        // �ж������������Ƿ�Ϊ��
                        if (loginUserName.length() == 0 || loginPassword.length() == 0) {
                            // promptPopUp�����Ƿ�װ��PublicWindowSet�е���ʾ����ʾ����
                            PublicWindowSet.promptPopUp("�������û���������!", "��¼��ʾ", jfr);
                        }
                        // �ж������������û����Ƿ���ȷ
                        else if (new AdminitartorDAO().loginConfirm(loginUserName, loginPassword)) {
                            PublicWindowSet.promptPopUp("��¼�ɹ�!!!", "��¼��ʾ", jfr);
                            new AdmintratorWindow();
                            jfr.dispose();
                        } else {
                            PublicWindowSet.promptPopUp("�û����������������������!!!", "��¼��ʾ", jfr);
                        }
                    } else if (LoginWindow.getFlag() == 2) {
                        // 2.ѧ��
                        // �ж������������Ƿ�Ϊ��
                        if (loginUserName.length() == 0 || loginPassword.length() == 0) {
                            PublicWindowSet.promptPopUp("�������û���������!", "��¼��ʾ", jfr);
                        }
                        // ��֤��ݣ��ж��Ƿ���ȷ���û���������
                        else if (new StudentDAO().loginConfirm(loginUserName, loginPassword)) {
                            PublicWindowSet.promptPopUp("��¼�ɹ�!!!", "��¼��ʾ", jfr);
                            StudentWindow sw = new StudentWindow();
                            sw.setSize(sw.getWindowwide(), sw.getWindowhigh());
                            // �ͷŵ�¼����
                            jfr.dispose();
                        } else {
                            PublicWindowSet.promptPopUp("�û����������������������!!!", "��¼��ʾ", jfr);
                        }
                    } else {
                        PublicWindowSet.promptPopUp("����ѡ�������ݣ�ѧ��������Ա!", "��¼��ʾ", jfr);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        jfr.add(button);
    }
 
    // ȡ��flag��ʶ
    private static int getFlag() {
        return flag;
    }
 
    // ����flag��ʶ
    private static void setFlag(int flag) {
        LoginWindow.flag = flag;
    }
 
    // ��ȡ��¼�û���
    public static String getLoginUserName() {
        return loginUserName;
    }
 
    // ���õ�¼�û���
    public static void setLoginUserName(String loginUserName) {
        LoginWindow.loginUserName = loginUserName;
    }
 
    // ��ȡ��¼����
    public static String getLoginPassword() {
        return loginPassword;
    }
 
    // ���õ�¼����
    public static void setLoginPassword(String loginPassword) {
        LoginWindow.loginPassword = loginPassword;
    }
}