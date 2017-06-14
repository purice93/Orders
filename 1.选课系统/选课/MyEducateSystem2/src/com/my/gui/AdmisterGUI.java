//*******************************
//write by zyy in 18,5,2016
//ѧ������ ��ʦ���� �γ̹��������Ϣά��
	//ѧ���������� ɾ�� ���� ��¼����ǿ�� ��ͬʱ��Ӧ���û���Ϣ���£�
	//��ʦ���� ������ ɾ�� ���� ��¼����ǿ�� ��ͬʱ��Ӧ���û���Ϣ���£�
	//�γ̹������� ɾ�� ����
	//������Ϣ���޸ĵ�¼�� ���� Ȩ��
//*******************************
package com.my.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.my.beans.Record;
import com.my.beans.User;
import com.my.gui.MyFrame.MyDialog;
import com.my.main.LoginFrame;
import com.my.manage.UserManage;
import com.my.util.Tools;

public class AdmisterGUI extends JFrame{
	private Container pn;
	private JTabbedPane jp,jp_stu,jp_tea,jp_cou;
	private JPanel pn_stuManage,pn_teaManage,pn_couManage,pn_perManage,pn_main,pn_main1;
	private JPanel pn_stu_addUpdate,pn_stu_delete,pn_stu_count;//ѧ��ѡ��Ϸ���jp_stu��Ȼ���������panel
	private JPanel pn_tea_addUpdate,pn_tea_delete,pn_tea_count;
	private JPanel pn_cou_addUpdate,pn_cou_delete,pn_cou_count;
	private JPanel pn_perManage0,pn_perManage1,pn_perManage2,pn_perManage3;
	private JPanel pn_stu_addUpdate1,pn_stu_addUpdate2,pn_stu_addUpdate3,pn_stu_delete1,pn_stu_count1,pn_stu_count2;
	private JPanel pn_tea_addUpdate1,pn_tea_addUpdate2,pn_tea_addUpdate3,pn_tea_delete1,pn_tea_count1,pn_tea_count2;
	private JPanel pn_cou_addUpdate1,pn_cou_addUpdate2,pn_cou_addUpdate3,pn_cou_delete1,pn_cou_count1,pn_cou_count2;
	private JTextField text_sid,text_sname,text_sex,text_saca,text_force,text_delete,text_count;
	private JTextField text_tid,text_tname,text_tea_sex,text_taca,text_tea_delete,text_tea_count;
	private JTextField text_cid,text_cname,text_caca,text_cou_delete,text_cou_count;
	private JTextField text_uname;
	private JPasswordField text_upwd,text_upwd_ensure,text_upwd_old;
	private JButton bt_user_pwdForce,bt_addUpdate,bt_delete,bt_count;
	private JButton bt_tea_addUpdate,bt_tea_delete,bt_tea_count;
	private JButton bt_cou_addUpdate,bt_cou_delete,bt_cou_count;
	private JButton bt_user_update;
	private JLabel label_sid,label_sname,label_sex,label_saca,label_delete,label_count;
	private JLabel label_tid,label_tname,label_tea_sex,label_taca,label_tea_delete,label_tea_count;
	private JLabel label_cid,label_cname,label_caca,label_cou_delete,label_cou_count;
	private JLabel label_uname,label_upwd,label_upwd_ensure,label_user_inf,label_upwd_old;
	private JLabel mainLabel;
	
	
	private MyFrame mf;
	private Connection conn;
	private PreparedStatement ps;
	private UserManage um;
	private Date outTime;
	private Record record=null;
	private ResultSet rs;
	
	public AdmisterGUI(Record record,MyFrame mf){
		this.record=record;
		this.mf=mf;
//		this.um=mf.getUserManage();
//		this.conn=um.getConn();
//		this.ps=um.getPs();
		this.mf.setVisible(false);
	ini();
	register();
	this.setVisible(true);
	}
	public void ini()
	{
		this.setSize(800,400);
		this.setTitle("����Աϵͳ");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//
		
		//�������
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension dm=kit.getScreenSize();
		this.setLocation((dm.width-this.getWidth())/2, (dm.height-this.getHeight())/2);
//ʵ����********************************************
		pn=new Container();
		pn.setLayout(new GridLayout(1, 1));
		jp=new JTabbedPane(JTabbedPane.LEFT);
		pn_main=new JPanel(new GridLayout(2, 1));
		pn_main1=new JPanel();
		bt_user_pwdForce=new JButton("�����ʼ��");
		text_force=new JTextField(20);
		//ѧ�����
		pn_stuManage=new JPanel();
		jp_stu=new JTabbedPane(JTabbedPane.TOP);
		pn_stu_addUpdate=new JPanel(new GridLayout(3, 1));
		pn_stu_addUpdate1=new JPanel();
		pn_stu_addUpdate2=new JPanel();
		pn_stu_addUpdate3=new JPanel();
		pn_stu_delete=new JPanel(new BorderLayout());
		
		pn_stu_count=new JPanel(new GridLayout(1, 1));
		text_sid=new JTextField(20);
		text_sname=new JTextField(20);
		text_sex=new JTextField(20);
		text_saca=new JTextField(20);
		label_sname=new JLabel("����");
		label_sid=new JLabel("ѧ��");
		label_sex=new JLabel("�Ա�");
		label_saca=new JLabel("ѧԺ");
		bt_addUpdate=new JButton("����");
		pn_stu_delete1=new JPanel();
		label_delete=new JLabel("ѧ��");
		text_delete=new JTextField(20);
		bt_delete=new JButton("ɾ��");
		pn_stu_count=new JPanel(new GridLayout(2, 1));
		pn_stu_count1=new JPanel();
		pn_stu_count2=new JPanel();
		label_count=new JLabel("ѧ������", JLabel.CENTER);
		text_count=new JTextField(20);
		bt_count=new JButton("ͳ��ѧ������");
		////ѧ�����
		//��ʦ���
		pn_teaManage=new JPanel();
		jp_tea=new JTabbedPane();
		pn_tea_addUpdate=new JPanel(new GridLayout(3, 1));
		pn_tea_addUpdate1=new JPanel();
		pn_tea_addUpdate2=new JPanel();
		pn_tea_addUpdate3=new JPanel();
		pn_tea_delete=new JPanel(new BorderLayout());
		pn_tea_count=new JPanel(new GridLayout(1, 1));
		text_tid=new JTextField(20);
		text_tname=new JTextField(20);
		text_tea_sex=new JTextField(20);
		text_taca=new JTextField(20);
		label_tname=new JLabel("����");
		label_tid=new JLabel("����");
		label_tea_sex=new JLabel("�Ա�");
		label_taca=new JLabel("ѧԺ");
		bt_tea_addUpdate=new JButton("����");
		
		pn_tea_delete1=new JPanel();
		label_tea_delete=new JLabel("����");
		text_tea_delete=new JTextField(20);
		bt_tea_delete=new JButton("ɾ��");
		pn_tea_count=new JPanel(new GridLayout(2, 1));
		pn_tea_count1=new JPanel();
		pn_tea_count2=new JPanel();
		label_tea_count=new JLabel("��ʦ����", JLabel.CENTER);
		text_tea_count=new JTextField(20);
		bt_tea_count=new JButton("ͳ�ƽ�ʦ����");
		//��ʦ���
		//�γ����
		pn_couManage=new JPanel();
		jp_cou=new JTabbedPane();
		pn_cou_addUpdate=new JPanel(new GridLayout(3, 1));
		pn_cou_addUpdate1=new JPanel();
		pn_cou_addUpdate2=new JPanel();
		pn_cou_addUpdate3=new JPanel();
		pn_cou_delete=new JPanel(new BorderLayout());
		pn_cou_count=new JPanel(new GridLayout(1, 1));
		text_cid=new JTextField(20);
		text_cname=new JTextField(20);
		text_caca=new JTextField(20);
		label_cname=new JLabel("�γ���");
		label_cid=new JLabel("�γ̺�");
		label_caca=new JLabel("ѧԺ");
		bt_cou_addUpdate=new JButton("����");
		
		pn_cou_delete1=new JPanel();
		label_cou_delete=new JLabel("�γ̺�");
		text_cou_delete=new JTextField(20);
		bt_cou_delete=new JButton("ɾ��");
		pn_cou_count=new JPanel(new GridLayout(2, 1));
		pn_cou_count1=new JPanel();
		pn_cou_count2=new JPanel();
		label_cou_count=new JLabel("�γ̸���", JLabel.CENTER);
		text_cou_count=new JTextField(20);
		bt_cou_count=new JButton("ͳ�ƿγ̸���");
		//�γ����
		//������Ϣ���
		pn_perManage1=new JPanel();
		pn_perManage2=new JPanel();
		pn_perManage3=new JPanel();
		label_uname=new JLabel("�û���");
		text_uname=new JTextField(20);
		label_upwd=new JLabel("����");
		text_upwd=new JPasswordField(20);
		text_upwd_ensure=new JPasswordField(20);
		label_upwd_ensure=new JLabel("ȷ������");
		bt_user_update=new JButton("�޸�");
		pn_perManage0=new JPanel(new GridLayout(3, 1));
		pn_perManage=new JPanel(new BorderLayout());
		label_user_inf=new JLabel("����Ա��Ϣ�޸�", JLabel.CENTER);
		text_upwd_old=new JPasswordField(20);
		label_upwd_old=new JLabel("������");
		mainLabel=new JLabel("����Աϵͳ", JLabel.CENTER);
//����*************************************************
		
		pn_main1.add(text_force);
		pn_main1.add(bt_user_pwdForce );
		pn_main.add(mainLabel);
		pn_main.add(pn_main1);
		jp.addTab("", pn_main);
		
		//ѧ�����
		pn_stu_addUpdate1.add(label_sid);
		pn_stu_addUpdate1.add(text_sid);
		pn_stu_addUpdate1.add(label_sname);
		pn_stu_addUpdate1.add(text_sname);
		pn_stu_addUpdate2.add(label_sex);
		pn_stu_addUpdate2.add(text_sex);
		pn_stu_addUpdate2.add(label_saca);
		pn_stu_addUpdate2.add(text_saca);
		pn_stu_addUpdate3.add(bt_addUpdate);
		pn_stu_addUpdate.add(pn_stu_addUpdate1);
		pn_stu_addUpdate.add(pn_stu_addUpdate2);
		pn_stu_addUpdate.add(pn_stu_addUpdate3);
		jp_stu.addTab("�޸�ѧ����Ϣ",pn_stu_addUpdate );
		
		pn_stu_delete1.add(label_delete);
		pn_stu_delete1.add(text_delete);
		pn_stu_delete1.add(bt_delete);
		pn_stu_delete.add(pn_stu_delete1,BorderLayout.CENTER);
		jp_stu.addTab("ɾ��ѧ��",pn_stu_delete );
		
		pn_stu_count1.add(label_count);
		pn_stu_count1.add(text_count);
		pn_stu_count2.add(bt_count);
		pn_stu_count.add(pn_stu_count1);
		pn_stu_count.add(pn_stu_count2);
		jp_stu.addTab("ѧ������ͳ��",pn_stu_count );
		pn_stuManage.add(jp_stu);
//		pn_stuManage.setSize(500,500);
		jp.addTab("ѧ������", pn_stuManage);
		//ѧ�����
		//��ʦ���
		pn_tea_addUpdate1.add(label_tid);
		pn_tea_addUpdate1.add(text_tid);
		pn_tea_addUpdate1.add(label_tname);
		pn_tea_addUpdate1.add(text_tname);
		pn_tea_addUpdate2.add(label_tea_sex);
		pn_tea_addUpdate2.add(text_tea_sex);
		pn_tea_addUpdate2.add(label_taca);
		pn_tea_addUpdate2.add(text_taca);
		pn_tea_addUpdate3.add(bt_tea_addUpdate);
		pn_tea_addUpdate.add(pn_tea_addUpdate1);
		pn_tea_addUpdate.add(pn_tea_addUpdate2);
		pn_tea_addUpdate.add(pn_tea_addUpdate3);
		jp_tea.addTab("�޸Ľ�ʦ��Ϣ",pn_tea_addUpdate );
		
		pn_tea_delete1.add(label_tea_delete);
		pn_tea_delete1.add(text_tea_delete);
		pn_tea_delete1.add(bt_tea_delete);
		pn_tea_delete.add(pn_tea_delete1,BorderLayout.CENTER);
		jp_tea.addTab("ɾ����ʦ",pn_tea_delete );
		
		pn_tea_count1.add(label_tea_count);
		pn_tea_count1.add(text_tea_count);
		pn_tea_count2.add(bt_tea_count);
		pn_tea_count.add(pn_tea_count1);
		pn_tea_count.add(pn_tea_count2);
		jp_tea.addTab("��ʦ����ͳ��",pn_tea_count );
		pn_teaManage.add(jp_tea);
		jp.addTab("��ʦ����", pn_teaManage);
		//��ʦ���
		//�γ����
		pn_cou_addUpdate1.add(label_cid);
		pn_cou_addUpdate1.add(text_cid);
		pn_cou_addUpdate1.add(label_cname);
		pn_cou_addUpdate1.add(text_cname);
		pn_cou_addUpdate2.add(label_caca);
		pn_cou_addUpdate2.add(text_caca);
		pn_cou_addUpdate3.add(bt_cou_addUpdate);
		pn_cou_addUpdate.add(pn_cou_addUpdate1);
		pn_cou_addUpdate.add(pn_cou_addUpdate2);
		pn_cou_addUpdate.add(pn_cou_addUpdate3);
		jp_cou.addTab("�޸Ŀγ���Ϣ",pn_cou_addUpdate );
		
		pn_cou_delete1.add(label_cou_delete);
		pn_cou_delete1.add(text_cou_delete);
		pn_cou_delete1.add(bt_cou_delete);
		pn_cou_delete.add(pn_cou_delete1,BorderLayout.CENTER);
		jp_cou.addTab("ɾ���γ�",pn_cou_delete );
		
		pn_cou_count1.add(label_cou_count);
		pn_cou_count1.add(text_cou_count);
		pn_cou_count2.add(bt_cou_count);
		pn_cou_count.add(pn_cou_count1);
		pn_cou_count.add(pn_cou_count2);
		jp_cou.addTab("�γ̸���ͳ��",pn_cou_count );
		pn_couManage.add(jp_cou);
		jp.addTab("�γ̹���", pn_couManage);
		//�γ����
		//������Ϣ���
		pn_perManage1.add(label_user_inf);
		pn_perManage2.add(label_uname);
		pn_perManage2.add(text_uname);
		pn_perManage2.add(label_upwd_old);
		pn_perManage2.add(text_upwd_old);
		pn_perManage3.add(label_upwd);
		pn_perManage3.add(text_upwd);
		pn_perManage3.add(label_upwd_ensure);
		pn_perManage3.add(text_upwd_ensure);
		pn_perManage3.add(bt_user_update);
		
		pn_perManage0.add(pn_perManage1);
		pn_perManage0.add(pn_perManage2);
		pn_perManage0.add(pn_perManage3);
		pn_perManage.add(pn_perManage0,BorderLayout.CENTER);
		jp.addTab("������Ϣ����", pn_perManage);
		//�������
		pn.add(jp);   //��ѡ�������ӵ� ��������ȥ
		this.add(pn,BorderLayout.CENTER);
	}
	public void register()
	{
		//���ڹصĹ�����  ////������ʵ�� �����˳� д��¼��־
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			outTime=  new Date(Calendar.getInstance().getTimeInMillis());
			record.setOutTime(outTime);
			Tools.writeRecord(record);
			System.out.println(Tools.readRecord());
			
			if(AdmisterGUI.this.mf.getUm()!=null){
				AdmisterGUI.this.mf.getUm().closeConnection();}
			((AdmisterGUI)e.getSource()).dispose();
			AdmisterGUI.this.mf.dispose();
			System.exit(0);
		}
		});
		
		//ǿ������ 
		bt_user_pwdForce.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String upwd=text_force.getText();
				Object params[]={upwd};
				synchronized(LoginFrame.class){
					AdmisterGUI.this.mf.getUm().sqlBuilde("update user1 set upwd=?",params);
					try {
						AdmisterGUI.this.mf.getUm().getPs().execute();					
						new MyDialog("�޸ĳɹ� ��ʼ����Ϊ111");
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						new MyDialog("�޸�ʧ��");
						e2.printStackTrace();
					}
				}
				
			}
		});
		//����ѧ�����߸���ѧ��
		bt_addUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sname=text_sname.getText();
				String sid=text_sid.getText();
				String sex=text_sex.getText();
				String saca=text_saca.getText();
				if("".equals(sname)||"".equals(sid)||"".equals(sex)||"".equals(saca))
				{
					new MyDialog("ѧ����Ϣ��������");
				}
				else
				{
					Object params[]={sid};
					AdmisterGUI.this.mf.getUm().sqlBuilde("select *from student where sid=?",params);
					try {
						ResultSet rs=AdmisterGUI.this.mf.getUm().getPs().executeQuery();
						if(rs.next())
						{
							if(sid.equals(rs.getString(1)))
							{
								synchronized(LoginFrame.class){
									Object params3[]={sname,sex,saca,sid};
									AdmisterGUI.this.mf.getUm().sqlBuilde("update student set sname=?,ssex=?,saca=? where sid=?",params3);
									try {
										AdmisterGUI.this.mf.getUm().getPs().execute();
										new MyDialog("ѧ�������Ҹ��£�");
									} catch (Exception e1) {
										new MyDialog("ѧ������,����ʧ�ܣ�");
										e1.printStackTrace();
									}
								}
								
							}
						}
						else
						{
							synchronized(LoginFrame.class){
								Object params2[]={sid,sname,sex,saca};
								AdmisterGUI.this.mf.getUm().sqlBuilde("insert into student (sid,sname,ssex,saca)values(?,?,?,?)",params2);
								try {
									AdmisterGUI.this.mf.getUm().getPs().execute();
									//��ѧ����ѧ����Ϊ�û��� Ȩ��Ϊ3 ����user��
									Object params5[]={sid,3};
									AdmisterGUI.this.mf.getUm().sqlBuilde("insert into user1(uname,uright,upwd)values(?,?,'111')",params5);
									AdmisterGUI.this.mf.getUm().getPs().execute();
									new MyDialog("���ӳɹ�");
								} catch (Exception e1) {
									new MyDialog("����ʧ��");
									e1.printStackTrace();
								}	
							}
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		//ɾ��ѧ��
		bt_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sid=text_delete.getText();
				if("".equals(sid))
				{
					new MyDialog("ѧ�Ų���Ϊ�գ�");
				}
				else
				{
					Object params[]={sid};
					AdmisterGUI.this.mf.getUm().sqlBuilde("select *from student where sid=?",params);
					try {
						ResultSet rs=AdmisterGUI.this.mf.getUm().getPs().executeQuery();
						if(rs.next())
						{
							if(sid.equals(rs.getString(1)))
							{
								synchronized(LoginFrame.class){
									Object params3[]={sid};
									AdmisterGUI.this.mf.getUm().sqlBuilde("delete from student where sid=?",params3);
									try {
										AdmisterGUI.this.mf.getUm().getPs().execute();
										Object params4[]={sid};
										AdmisterGUI.this.mf.getUm().sqlBuilde("delete from user1 where uname=?",params4);
										AdmisterGUI.this.mf.getUm().getPs().execute();
										new MyDialog("ɾ���ɹ�");
									} catch (Exception e1) {
										new MyDialog("ɾ��ʧ��");
										e1.printStackTrace();
									}
								}
								
							}
						}
						else
						{new MyDialog("������");}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		//ͳ��ѧ������
		bt_count.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdmisterGUI.this.mf.getUm().sqlBuilde("select count(sid) from student",null);
				ResultSet rs;
				try {
					rs = AdmisterGUI.this.mf.getUm().getPs().executeQuery();
					rs.next();
					Integer a=rs.getInt(1);
					text_count.setText(a.toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
//��ʦ********************************************************
		//���ӽ�ʦ���߸��½�ʦ
				bt_tea_addUpdate.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String tname=text_tname.getText();
						String tid=text_tid.getText();
						String sex=text_tea_sex.getText();
						String taca=text_taca.getText();
						if("".equals(tname)||"".equals(tid)||"".equals(sex)||"".equals(taca))
						{
							new MyDialog("��ʦ��Ϣ��������");
						}
						else
						{
							Object params[]={tid};
							AdmisterGUI.this.mf.getUm().sqlBuilde("select *from teacher where tid=?",params);
							try {
								ResultSet rs=AdmisterGUI.this.mf.getUm().getPs().executeQuery();
								if(rs.next())
								{
									if(tid.equals(rs.getString(1)))
									{
										synchronized(LoginFrame.class){
											Object params3[]={tname,sex,taca,tid};
											AdmisterGUI.this.mf.getUm().sqlBuilde("update teacher set tname=?,tsex=?,taca=? where tid=?",params3);
											try {
												AdmisterGUI.this.mf.getUm().getPs().execute();
												new MyDialog("��ʦ�����Ҹ��£�");
											} catch (Exception e1) {
												new MyDialog("��ʦ����,����ʧ�ܣ�");
												e1.printStackTrace();
											}
										}
										
									}
								}
								else
								{
									synchronized(LoginFrame.class){
										Object params2[]={tid,tname,sex,taca};
										AdmisterGUI.this.mf.getUm().sqlBuilde("insert into teacher (tid,tname,tsex,taca)values(?,?,?,?)",params2);
										try {
											AdmisterGUI.this.mf.getUm().getPs().execute();
											//��ѧ����ѧ����Ϊ�û��� Ȩ��Ϊ3 ����user��
											Object params5[]={tid,2};
											AdmisterGUI.this.mf.getUm().sqlBuilde("insert into user1 (uname,uright,upwd)values(?,?,'111')",params5);
											AdmisterGUI.this.mf.getUm().getPs().execute();
											new MyDialog("���ӳɹ�");
										} catch (Exception e1) {
											new MyDialog("����ʧ��");
											e1.printStackTrace();
										}	
									}
									
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				//ɾ����ʦ
				bt_tea_delete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String sid=text_tea_delete.getText();
						if("".equals(sid))
						{
							new MyDialog("���Ų���Ϊ�գ�");
						}
						else
						{
							Object params[]={sid};
							AdmisterGUI.this.mf.getUm().sqlBuilde("select *from teacher where tid=?",params);
							try {
								ResultSet rs=AdmisterGUI.this.mf.getUm().getPs().executeQuery();
								if(rs.next())
								{
									if(sid.equals(rs.getString(1)))
									{
										Object params3[]={sid};
										AdmisterGUI.this.mf.getUm().sqlBuilde("delete from teacher where tid=?",params3);
										try {
											AdmisterGUI.this.mf.getUm().getPs().execute();
											Object params4[]={sid};
											AdmisterGUI.this.mf.getUm().sqlBuilde("delete from user1 where uname=?",params4);
											AdmisterGUI.this.mf.getUm().getPs().execute();
											new MyDialog("ɾ���ɹ�");
										} catch (Exception e1) {
											new MyDialog("ɾ��ʧ��");
											e1.printStackTrace();
										}
									}
								}
								else
								{new MyDialog("������");}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				//ͳ�ƽ�ʦ����
				bt_tea_count.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						AdmisterGUI.this.mf.getUm().sqlBuilde("select count(tid) from teacher",null);
						ResultSet rs;
						try {
							rs = AdmisterGUI.this.mf.getUm().getPs().executeQuery();
							rs.next();
							Integer a=rs.getInt(1);
							text_tea_count.setText(a.toString());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}
				});
//�γ���Ϣ*******************************************************************************
				//���ӿγ� ����
				bt_cou_addUpdate.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String tname=text_cname.getText();
						String tid=text_cid.getText();
						String taca=text_caca.getText();
						if("".equals(tname)||"".equals(tid)||"".equals(taca))
						{
							new MyDialog("�γ���Ϣ��������");
						}
						else
						{
							Object params[]={tid};
							AdmisterGUI.this.mf.getUm().sqlBuilde("select *from course where cid=?",params);
							try {
								ResultSet rs=AdmisterGUI.this.mf.getUm().getPs().executeQuery();
								if(rs.next())
								{
									if(tid.equals(rs.getString(1)))
									{
										synchronized(LoginFrame.class){
											Object params3[]={tname,taca,tid};
											AdmisterGUI.this.mf.getUm().sqlBuilde("update course set cname=?,caca=? where cid=?",params3);
											try {
												AdmisterGUI.this.mf.getUm().getPs().execute();
												new MyDialog("�γ̴����Ҹ��£�");
											} catch (Exception e1) {
												new MyDialog("�γ̴���,����ʧ�ܣ�");
												e1.printStackTrace();
											}
										}
										
									}
								}
								else
								{
									 synchronized(LoginFrame.class){
										 Object params2[]={tid,tname,taca};
											AdmisterGUI.this.mf.getUm().sqlBuilde("insert into course (cid,cname,caca)values(?,?,?)",params2);
											try {
												AdmisterGUI.this.mf.getUm().getPs().execute();
												//��ѧ����ѧ����Ϊ�û��� Ȩ��Ϊ3 ����user��
												new MyDialog("���ӳɹ�");
											} catch (Exception e1) {
												new MyDialog("����ʧ��");
												e1.printStackTrace();
											}	
									 }
									
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				//ɾ���γ�
				bt_cou_delete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String sid=text_cou_delete.getText();
						if("".equals(sid))
						{
							new MyDialog("�γ̺Ų���Ϊ�գ�");
						}
						else
						{
							Object params[]={sid};
							AdmisterGUI.this.mf.getUm().sqlBuilde("select *from course where cid=?",params);
							try {
								ResultSet rs=AdmisterGUI.this.mf.getUm().getPs().executeQuery();
								if(rs.next())
								{
									if(sid.equals(rs.getString(1)))
									{
										
										Object params3[]={sid};
										AdmisterGUI.this.mf.getUm().sqlBuilde("delete from course where cid=?",params3);
										try {
											AdmisterGUI.this.mf.getUm().getPs().execute();
											new MyDialog("ɾ���ɹ�");
										} catch (Exception e1) {
											new MyDialog("ɾ��ʧ��");
											e1.printStackTrace();
										}
									}
								}
								else
								{new MyDialog("������");}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				//ͳ�ƿγ���
				bt_cou_count.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						AdmisterGUI.this.mf.getUm().sqlBuilde("select count(cid) from course ",null);
						ResultSet rs;
						try {
							rs = AdmisterGUI.this.mf.getUm().getPs().executeQuery();
							rs.next();
							Integer a=rs.getInt(1);
							text_cou_count.setText(a.toString());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}
				});	
//����Ա��Ϣ****************************
				 bt_user_update.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String newName=text_uname.getText();
						String newPwd=text_upwd.getText();
						String pwd=text_upwd_ensure.getText();
						String oldPwd=text_upwd_old.getText();
						String oldName=AdmisterGUI.this.mf.getUm().getUser().getUname();
						if(AdmisterGUI.this.mf.getUm().getUser().getPwd().equals(oldPwd)==false){
							new MyDialog("���������");
							text_upwd_old.setText("");
						}
						else
						{
							if(pwd.equals(newPwd)==false)
							{
								new MyDialog("�������벻һ��");
								text_upwd.setText("");
								text_upwd_ensure.setText("");
							}
							else
							{
								 synchronized(LoginFrame.class){
									 Object params3[]={newName,pwd,oldName};
										AdmisterGUI.this.mf.getUm().sqlBuilde("update user1 set uname=?,upwd=? where uname=? ",params3);
										try {
											AdmisterGUI.this.mf.getUm().getPs().execute();
											new MyDialog("�޸ĳɹ�");
										} catch (SQLException e1) {
											new MyDialog("�޸�ʧ��");
											e1.printStackTrace();
										}
								 }
								
							}
						}
						
					}
				});
		}
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
