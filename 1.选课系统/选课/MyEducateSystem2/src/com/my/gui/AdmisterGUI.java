//*******************************
//write by zyy in 18,5,2016
//学生管理 教师管理 课程管理个人信息维护
	//学生管理：增加 删除 更新 登录密码强制 （同时对应的用户信息更新）
	//教师管理 ：增加 删除 更新 登录密码强制 （同时对应的用户信息更新）
	//课程管理：增加 删除 更新
	//个人信息：修改登录名 密码 权限
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
	private JPanel pn_stu_addUpdate,pn_stu_delete,pn_stu_count;//学生选项卡上放置jp_stu，然后继续放置panel
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
		this.setTitle("管理员系统");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//
		
		//窗体居中
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension dm=kit.getScreenSize();
		this.setLocation((dm.width-this.getWidth())/2, (dm.height-this.getHeight())/2);
//实例化********************************************
		pn=new Container();
		pn.setLayout(new GridLayout(1, 1));
		jp=new JTabbedPane(JTabbedPane.LEFT);
		pn_main=new JPanel(new GridLayout(2, 1));
		pn_main1=new JPanel();
		bt_user_pwdForce=new JButton("密码初始化");
		text_force=new JTextField(20);
		//学生面板
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
		label_sname=new JLabel("姓名");
		label_sid=new JLabel("学号");
		label_sex=new JLabel("性别");
		label_saca=new JLabel("学院");
		bt_addUpdate=new JButton("增加");
		pn_stu_delete1=new JPanel();
		label_delete=new JLabel("学号");
		text_delete=new JTextField(20);
		bt_delete=new JButton("删除");
		pn_stu_count=new JPanel(new GridLayout(2, 1));
		pn_stu_count1=new JPanel();
		pn_stu_count2=new JPanel();
		label_count=new JLabel("学生人数", JLabel.CENTER);
		text_count=new JTextField(20);
		bt_count=new JButton("统计学生人数");
		////学生完毕
		//教师面板
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
		label_tname=new JLabel("姓名");
		label_tid=new JLabel("工号");
		label_tea_sex=new JLabel("性别");
		label_taca=new JLabel("学院");
		bt_tea_addUpdate=new JButton("增加");
		
		pn_tea_delete1=new JPanel();
		label_tea_delete=new JLabel("工号");
		text_tea_delete=new JTextField(20);
		bt_tea_delete=new JButton("删除");
		pn_tea_count=new JPanel(new GridLayout(2, 1));
		pn_tea_count1=new JPanel();
		pn_tea_count2=new JPanel();
		label_tea_count=new JLabel("教师人数", JLabel.CENTER);
		text_tea_count=new JTextField(20);
		bt_tea_count=new JButton("统计教师人数");
		//教师完毕
		//课程面板
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
		label_cname=new JLabel("课程名");
		label_cid=new JLabel("课程号");
		label_caca=new JLabel("学院");
		bt_cou_addUpdate=new JButton("增加");
		
		pn_cou_delete1=new JPanel();
		label_cou_delete=new JLabel("课程号");
		text_cou_delete=new JTextField(20);
		bt_cou_delete=new JButton("删除");
		pn_cou_count=new JPanel(new GridLayout(2, 1));
		pn_cou_count1=new JPanel();
		pn_cou_count2=new JPanel();
		label_cou_count=new JLabel("课程个数", JLabel.CENTER);
		text_cou_count=new JTextField(20);
		bt_cou_count=new JButton("统计课程个数");
		//课程完毕
		//个人信息面板
		pn_perManage1=new JPanel();
		pn_perManage2=new JPanel();
		pn_perManage3=new JPanel();
		label_uname=new JLabel("用户名");
		text_uname=new JTextField(20);
		label_upwd=new JLabel("密码");
		text_upwd=new JPasswordField(20);
		text_upwd_ensure=new JPasswordField(20);
		label_upwd_ensure=new JLabel("确认密码");
		bt_user_update=new JButton("修改");
		pn_perManage0=new JPanel(new GridLayout(3, 1));
		pn_perManage=new JPanel(new BorderLayout());
		label_user_inf=new JLabel("管理员信息修改", JLabel.CENTER);
		text_upwd_old=new JPasswordField(20);
		label_upwd_old=new JLabel("旧密码");
		mainLabel=new JLabel("管理员系统", JLabel.CENTER);
//放置*************************************************
		
		pn_main1.add(text_force);
		pn_main1.add(bt_user_pwdForce );
		pn_main.add(mainLabel);
		pn_main.add(pn_main1);
		jp.addTab("", pn_main);
		
		//学生面板
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
		jp_stu.addTab("修改学生信息",pn_stu_addUpdate );
		
		pn_stu_delete1.add(label_delete);
		pn_stu_delete1.add(text_delete);
		pn_stu_delete1.add(bt_delete);
		pn_stu_delete.add(pn_stu_delete1,BorderLayout.CENTER);
		jp_stu.addTab("删除学生",pn_stu_delete );
		
		pn_stu_count1.add(label_count);
		pn_stu_count1.add(text_count);
		pn_stu_count2.add(bt_count);
		pn_stu_count.add(pn_stu_count1);
		pn_stu_count.add(pn_stu_count2);
		jp_stu.addTab("学生人数统计",pn_stu_count );
		pn_stuManage.add(jp_stu);
//		pn_stuManage.setSize(500,500);
		jp.addTab("学生管理", pn_stuManage);
		//学生完毕
		//教师面板
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
		jp_tea.addTab("修改教师信息",pn_tea_addUpdate );
		
		pn_tea_delete1.add(label_tea_delete);
		pn_tea_delete1.add(text_tea_delete);
		pn_tea_delete1.add(bt_tea_delete);
		pn_tea_delete.add(pn_tea_delete1,BorderLayout.CENTER);
		jp_tea.addTab("删除教师",pn_tea_delete );
		
		pn_tea_count1.add(label_tea_count);
		pn_tea_count1.add(text_tea_count);
		pn_tea_count2.add(bt_tea_count);
		pn_tea_count.add(pn_tea_count1);
		pn_tea_count.add(pn_tea_count2);
		jp_tea.addTab("教师人数统计",pn_tea_count );
		pn_teaManage.add(jp_tea);
		jp.addTab("教师管理", pn_teaManage);
		//教师完毕
		//课程面板
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
		jp_cou.addTab("修改课程信息",pn_cou_addUpdate );
		
		pn_cou_delete1.add(label_cou_delete);
		pn_cou_delete1.add(text_cou_delete);
		pn_cou_delete1.add(bt_cou_delete);
		pn_cou_delete.add(pn_cou_delete1,BorderLayout.CENTER);
		jp_cou.addTab("删除课程",pn_cou_delete );
		
		pn_cou_count1.add(label_cou_count);
		pn_cou_count1.add(text_cou_count);
		pn_cou_count2.add(bt_cou_count);
		pn_cou_count.add(pn_cou_count1);
		pn_cou_count.add(pn_cou_count2);
		jp_cou.addTab("课程个数统计",pn_cou_count );
		pn_couManage.add(jp_cou);
		jp.addTab("课程管理", pn_couManage);
		//课程完毕
		//个人信息面板
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
		jp.addTab("个人信息管理", pn_perManage);
		//个人完毕
		pn.add(jp);   //将选项卡窗体添加到 主窗体上去
		this.add(pn,BorderLayout.CENTER);
	}
	public void register()
	{
		//正在关的过程中  ////在里面实现 窗口退出 写登录日志
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
		
		//强制密码 
		bt_user_pwdForce.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String upwd=text_force.getText();
				Object params[]={upwd};
				synchronized(LoginFrame.class){
					AdmisterGUI.this.mf.getUm().sqlBuilde("update user1 set upwd=?",params);
					try {
						AdmisterGUI.this.mf.getUm().getPs().execute();					
						new MyDialog("修改成功 初始密码为111");
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						new MyDialog("修改失败");
						e2.printStackTrace();
					}
				}
				
			}
		});
		//增加学生或者更新学生
		bt_addUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sname=text_sname.getText();
				String sid=text_sid.getText();
				String sex=text_sex.getText();
				String saca=text_saca.getText();
				if("".equals(sname)||"".equals(sid)||"".equals(sex)||"".equals(saca))
				{
					new MyDialog("学生信息不完整！");
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
										new MyDialog("学生存在且更新！");
									} catch (Exception e1) {
										new MyDialog("学生存在,更新失败！");
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
									//以学生的学号作为用户名 权限为3 更新user表
									Object params5[]={sid,3};
									AdmisterGUI.this.mf.getUm().sqlBuilde("insert into user1(uname,uright,upwd)values(?,?,'111')",params5);
									AdmisterGUI.this.mf.getUm().getPs().execute();
									new MyDialog("增加成功");
								} catch (Exception e1) {
									new MyDialog("增加失败");
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
		//删除学生
		bt_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sid=text_delete.getText();
				if("".equals(sid))
				{
					new MyDialog("学号不能为空！");
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
										new MyDialog("删除成功");
									} catch (Exception e1) {
										new MyDialog("删除失败");
										e1.printStackTrace();
									}
								}
								
							}
						}
						else
						{new MyDialog("不存在");}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		//统计学生人数
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
//教师********************************************************
		//增加教师或者更新教师
				bt_tea_addUpdate.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String tname=text_tname.getText();
						String tid=text_tid.getText();
						String sex=text_tea_sex.getText();
						String taca=text_taca.getText();
						if("".equals(tname)||"".equals(tid)||"".equals(sex)||"".equals(taca))
						{
							new MyDialog("教师信息不完整！");
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
												new MyDialog("教师存在且更新！");
											} catch (Exception e1) {
												new MyDialog("教师存在,更新失败！");
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
											//以学生的学号作为用户名 权限为3 更新user表
											Object params5[]={tid,2};
											AdmisterGUI.this.mf.getUm().sqlBuilde("insert into user1 (uname,uright,upwd)values(?,?,'111')",params5);
											AdmisterGUI.this.mf.getUm().getPs().execute();
											new MyDialog("增加成功");
										} catch (Exception e1) {
											new MyDialog("增加失败");
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
				//删除教师
				bt_tea_delete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String sid=text_tea_delete.getText();
						if("".equals(sid))
						{
							new MyDialog("工号不能为空！");
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
											new MyDialog("删除成功");
										} catch (Exception e1) {
											new MyDialog("删除失败");
											e1.printStackTrace();
										}
									}
								}
								else
								{new MyDialog("不存在");}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				//统计教师人数
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
//课程信息*******************************************************************************
				//增加课程 更新
				bt_cou_addUpdate.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String tname=text_cname.getText();
						String tid=text_cid.getText();
						String taca=text_caca.getText();
						if("".equals(tname)||"".equals(tid)||"".equals(taca))
						{
							new MyDialog("课程信息不完整！");
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
												new MyDialog("课程存在且更新！");
											} catch (Exception e1) {
												new MyDialog("课程存在,更新失败！");
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
												//以学生的学号作为用户名 权限为3 更新user表
												new MyDialog("增加成功");
											} catch (Exception e1) {
												new MyDialog("增加失败");
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
				//删除课程
				bt_cou_delete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String sid=text_cou_delete.getText();
						if("".equals(sid))
						{
							new MyDialog("课程号不能为空！");
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
											new MyDialog("删除成功");
										} catch (Exception e1) {
											new MyDialog("删除失败");
											e1.printStackTrace();
										}
									}
								}
								else
								{new MyDialog("不存在");}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				//统计课程数
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
//管理员信息****************************
				 bt_user_update.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String newName=text_uname.getText();
						String newPwd=text_upwd.getText();
						String pwd=text_upwd_ensure.getText();
						String oldPwd=text_upwd_old.getText();
						String oldName=AdmisterGUI.this.mf.getUm().getUser().getUname();
						if(AdmisterGUI.this.mf.getUm().getUser().getPwd().equals(oldPwd)==false){
							new MyDialog("旧密码错误");
							text_upwd_old.setText("");
						}
						else
						{
							if(pwd.equals(newPwd)==false)
							{
								new MyDialog("两次密码不一样");
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
											new MyDialog("修改成功");
										} catch (SQLException e1) {
											new MyDialog("修改失败");
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
			//窗体居中
			Toolkit kit=Toolkit.getDefaultToolkit();
			Dimension dm=kit.getScreenSize();
			MyDialog.this.setLocation((dm.width-MyDialog.this.getWidth())/2, (dm.height-MyDialog.this.getHeight())/2);
			pne=new JPanel();
			pne.setLayout(new GridLayout(2, 1));
			MyDialog.this.msg=new JLabel(msg, JLabel.CENTER);
			MyDialog.this.ok=new JButton("确定");
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
