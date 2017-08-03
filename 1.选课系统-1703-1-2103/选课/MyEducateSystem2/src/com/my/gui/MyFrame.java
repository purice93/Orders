package com.my.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.my.beans.Record;
import com.my.beans.Student;
import com.my.beans.Teacher;
import com.my.beans.User;
import com.my.manage.UserManage;
import com.my.util.Tools;

public class MyFrame extends JFrame{
	private Container pn;
	
	private JPanel pn_name,pn_pwd,pn_code,pn_login;
	private JLabel label,label_name,label_pwd,label_code;
	private JTextField filed_name,filed_code,field_code_play;
	private JPasswordField pwd;
	private JButton login;
	
	private String uname;
	private String upwd;
	private String code;
	private String codePlay;
	
	private UserManage um=null;
	
	private Record record=null;
	private Date inTime;
	private Date outTime;
	
	private User user=null;
	
	
	private AdmisterGUI agui=null;
	private TeacherGUI tgui=null;
	private StudentGUI sgui=null;
	
	
	public UserManage getUm() {
		return um;
	}
	public MyFrame()
	{
		ini();
		register();
		this.setVisible(true);
	}
	public void ini()
	{
		this.setSize(400,300);
		this.setTitle("ѧ��ѡ�ν���ϵͳ");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pn=new Container();
		pn_name=new JPanel();
		pn_pwd=new JPanel();
		pn_code=new JPanel();
		pn_login=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		label=new JLabel("��¼");
		label_name=new JLabel("�˻�", JLabel.CENTER);
		label_pwd=new JLabel("����", JLabel.CENTER);
		label_code=new JLabel("��֤��", JLabel.CENTER);
		
		filed_name=new JTextField(20);
		field_code_play = new JTextField(9);
		filed_code=new JTextField(9);
		
		pwd=new JPasswordField(20);
		login=new JButton("��¼");
		//�������
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension dm=kit.getScreenSize();
		this.setLocation((dm.width-this.getWidth())/2, (dm.height-this.getHeight())/2);
		//����
		
		pn_name.add(label_name);
		pn_name.add(filed_name);
		pn_pwd.add(label_pwd);
		pn_pwd.add(pwd);
		pn_code.add(label_code);
		pn_code.add(filed_code);
		//����tools�������֤�룬����ʾ�ڽ�����
		codePlay=Tools.codeGen();
		field_code_play.setText(codePlay);
		field_code_play.setHorizontalAlignment(JTextField.CENTER);
		
		
		pn_code.add(field_code_play);
		
		pn_login.add(login);
		
		pn.setLayout(new GridLayout(4, 1));
		this.add(label,BorderLayout.NORTH);
		
		pn.add(pn_name);
		pn.add(pn_pwd);
		pn.add(pn_code);
		 pn.add(pn_login);
		this.add(pn,BorderLayout.CENTER);
		
		
	}
	public void register()
	{
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				um=new UserManage();
				uname=filed_name.getText();
				upwd=pwd.getText();
				code=filed_code.getText();
				if("".equals(uname)||"".equals(upwd))
				{
					//�Զ���Ի��� MyDialog ������ʾ
					new MyDialog("�˻������벻��Ϊ�գ�");
					
				}
				else
				{
					
					if("".equals(code)){
						new MyDialog("��֤�벻��Ϊ�գ�");
					}
					else if(code.equalsIgnoreCase(codePlay))
					{
						
						
						//����UserManage�� login����
						
						user=um.login( uname,upwd);
						if(user==null){
						new MyDialog("�˻������벻��ȷ��");}
						else
						{
							//�ظ���¼ ��֤������
							codePlay=Tools.codeGen();
							field_code_play.setText(codePlay);
							field_code_play.setHorizontalAlignment(JTextField.CENTER);
							//�������
							filed_code.setText("");
							
							record=new Record();
							record.setUser(user);
							
							inTime=  new Date(Calendar.getInstance().getTimeInMillis());
							record.setInTime(inTime);
							//�ж�uright,���ù���Ա����ʦ��ѧ������
							int uright=user.getUright();
							 if(uright==1)//����Ա
							 {
								 agui=new AdmisterGUI(record,MyFrame.this);////������ʵ�� �����˳� д��¼��־
								 
							 }
							 else
								 if(uright==2)//��ʦ
								 {
									 Teacher teacher=(Teacher)user;
									 tgui=new TeacherGUI(record,MyFrame.this,teacher);////������ʵ�� �����˳� д��¼��־
								 }
								 else
								 {
									 Student student=(Student)user;
									 sgui=new StudentGUI(record,MyFrame.this,student);////������ʵ�� �����˳� д��¼��־
								 }
							}
							
					}
					else
					{
						new MyDialog("��֤�벻��ȷ��");
						//����tools�������֤�룬����ʾ�ڽ�����
						codePlay=Tools.codeGen();
						field_code_play.setText(codePlay);
						field_code_play.setHorizontalAlignment(JTextField.CENTER);
						//�������
						filed_code.setText("");
					}
				}
			}
		});
		
			//���������ڹر� �ͷ���Դ	
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(um!=null){
				um.closeConnection();}
				((MyFrame)e.getSource()).dispose();
				System.exit(0);
				
			}
			});		
				
	}
	//�Զ���Ի���
	class MyDialog extends JDialog{
		private JLabel msg;
		private JButton ok;
		private JPanel pne;
		public MyDialog(String msg)
		{
			ini( msg);
			register();
			MyDialog.this.setVisible(true);
		}
		public void ini(String msg)
		{
			MyDialog.this.setSize(300,100);
			//�������
			Toolkit kit=Toolkit.getDefaultToolkit();
			Dimension dm=kit.getScreenSize();
			MyDialog.this.setLocation((dm.width-MyDialog.this.getWidth())/2, (dm.height-MyDialog.this.getHeight())/2);
			pne=new JPanel();
			pne.setLayout(new GridLayout(2, 1));
			MyDialog.this.msg=new JLabel(msg, JLabel.CENTER);
			MyDialog.this.ok=new JButton("ȷ��");
			pne.add(MyDialog.this.msg);
			pne.add(MyDialog.this.ok);
			
			MyDialog.this.add(pne,BorderLayout.CENTER);
		}
		public void register()
		{
			MyDialog.this.ok.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					MyDialog.this.dispose();
				}
			});
		}
	}
}
