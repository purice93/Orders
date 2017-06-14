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
		this.setTitle("学生选课教务系统");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pn=new Container();
		pn_name=new JPanel();
		pn_pwd=new JPanel();
		pn_code=new JPanel();
		pn_login=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		label=new JLabel("登录");
		label_name=new JLabel("账户", JLabel.CENTER);
		label_pwd=new JLabel("密码", JLabel.CENTER);
		label_code=new JLabel("验证码", JLabel.CENTER);
		
		filed_name=new JTextField(20);
		field_code_play = new JTextField(9);
		filed_code=new JTextField(9);
		
		pwd=new JPasswordField(20);
		login=new JButton("登录");
		//窗体居中
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension dm=kit.getScreenSize();
		this.setLocation((dm.width-this.getWidth())/2, (dm.height-this.getHeight())/2);
		//放置
		
		pn_name.add(label_name);
		pn_name.add(filed_name);
		pn_pwd.add(label_pwd);
		pn_pwd.add(pwd);
		pn_code.add(label_code);
		pn_code.add(filed_code);
		//调用tools类产生验证码，并显示在界面上
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
					//自定义对话框 MyDialog 弹出提示
					new MyDialog("账户和密码不能为空！");
					
				}
				else
				{
					
					if("".equals(code)){
						new MyDialog("验证码不能为空！");
					}
					else if(code.equalsIgnoreCase(codePlay))
					{
						
						
						//调用UserManage类 login方法
						
						user=um.login( uname,upwd);
						if(user==null){
						new MyDialog("账户和密码不正确！");}
						else
						{
							//重复登录 验证码清零
							codePlay=Tools.codeGen();
							field_code_play.setText(codePlay);
							field_code_play.setHorizontalAlignment(JTextField.CENTER);
							//清空输入
							filed_code.setText("");
							
							record=new Record();
							record.setUser(user);
							
							inTime=  new Date(Calendar.getInstance().getTimeInMillis());
							record.setInTime(inTime);
							//判断uright,调用管理员、教师、学生窗口
							int uright=user.getUright();
							 if(uright==1)//管理员
							 {
								 agui=new AdmisterGUI(record,MyFrame.this);////在里面实现 窗口退出 写登录日志
								 
							 }
							 else
								 if(uright==2)//教师
								 {
									 Teacher teacher=(Teacher)user;
									 tgui=new TeacherGUI(record,MyFrame.this,teacher);////在里面实现 窗口退出 写登录日志
								 }
								 else
								 {
									 Student student=(Student)user;
									 sgui=new StudentGUI(record,MyFrame.this,student);////在里面实现 窗口退出 写登录日志
								 }
							}
							
					}
					else
					{
						new MyDialog("验证码不正确！");
						//调用tools类产生验证码，并显示在界面上
						codePlay=Tools.codeGen();
						field_code_play.setText(codePlay);
						field_code_play.setHorizontalAlignment(JTextField.CENTER);
						//清空输入
						filed_code.setText("");
					}
				}
			}
		});
		
			//主窗口正在关闭 释放资源	
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(um!=null){
				um.closeConnection();}
				((MyFrame)e.getSource()).dispose();
				System.exit(0);
				
			}
			});		
				
	}
	//自定义对话框
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
