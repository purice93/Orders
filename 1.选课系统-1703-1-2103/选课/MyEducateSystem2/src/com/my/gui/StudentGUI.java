//还没有处理 每门课限制人数
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.my.beans.Course;
import com.my.beans.Record;
import com.my.beans.Student;
import com.my.beans.Teacher;
import com.my.gui.AdmisterGUI.MyDialog;
import com.my.main.LoginFrame;
import com.my.table.CreatTable;
import com.my.table.MyTableModel;
import com.my.util.Tools;

public class StudentGUI extends JFrame{
	private static int COURSE_NUM=20;
	private static int COURSE_NUM2=200;
	private static int STUDENT_NUM=200;
	private Container pn;
	private JTabbedPane jp;
	private JPanel pn_choose,pn_per,pn_main,pn_course;
	private JPanel pn_choose1,pn_choose3,pn_course2;
	private JPanel pn_per1,pn_per2,pn_per3,pn_per4;
	private JScrollPane jp_choose2,jp_course1;
	private JTable pn_choose2_table,pn_course_table;
	private JLabel label_oldPwd,label_newPwd,label_pwdEnsure;
	private JPasswordField text_oldPwd,text_newPwd,text_pwdEnsure;
	private JButton bt_pwdVerify;
	private JLabel labelMain;
	private JTextField text_choose_aca;
	private JLabel label_choose_aca;
	private JButton bt_course,bt_choose,bt_chosen,bt_delete;
	private JCheckBox checkBox[]=null;
	
	
	private List<Course> list_play,list_choose;
	private List<Course> list_chosen,list_delete;
	private MyTableModel tableModel,tableModel_course;
	private int play_row=0;
	private MyFrame mf;
	private Date outTime;
	private Record record=null;
	private Student student;
	private String caca;
	
	public StudentGUI(Record record,MyFrame mf,Student student){
		this.record=record;
		this.mf=mf;
		this.student=student;
		this.mf.setVisible(false);
	ini();
	register();
	this.setVisible(true);
	}
	public void ini()
	{
		this.setSize(800,600);
		this.setTitle("学生系统");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//
		
		//窗体居中
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension dm=kit.getScreenSize();
		this.setLocation((dm.width-this.getWidth())/2, (dm.height-this.getHeight())/2);
		
		//实例化
		pn=new Container();
		pn.setLayout(new GridLayout(1, 1));
		jp=new JTabbedPane();
		pn_choose=new JPanel(new GridLayout(3, 1));
		label_choose_aca=new JLabel("学院");
		text_choose_aca=new JTextField(20);
		pn_per=new JPanel(new GridLayout(4, 1));
		pn_main=new JPanel();
		labelMain=new JLabel("学生系统", JLabel.CENTER);
		pn_choose1=new JPanel();
		//最多选择几门课程
		String[] columnNames = {"选择","课程号", "课程名","老师","老师工号"};
		Object cellData[][]=new Object[COURSE_NUM2][5];
		Class classType[]={Boolean.class,String.class,String.class,String.class,String.class};
		pn_choose2_table = CreatTable.creatTable(columnNames, cellData,classType);
		jp_choose2=new JScrollPane(pn_choose2_table);//不用滑动面板  表头不显示
		jp_choose2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jp_choose2.setVisible(false);//没有点击显示课程时，不显示表  后面别忘了可见
		//所选课程面板
		pn_course2=new JPanel();
		pn_course=new JPanel(new GridLayout(3, 1));
		Object cellData2[][]=new Object[COURSE_NUM][5];
		String[] columnNames2 = {"选择","课程号", "课程名","老师","老师工号"};
		pn_course_table=CreatTable.creatTable(columnNames2, cellData2, classType);
		jp_course1=new JScrollPane(pn_course_table);
		jp_course1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jp_course1.setVisible(false);
		bt_chosen=new JButton("显示所选课程");
		bt_delete=new JButton("删除课程");
		//-----------------------------
		
		pn_choose3=new JPanel();
		bt_course=new JButton("显示课程");
		bt_choose=new JButton("选课");
		
		//个人信息
		pn_per1=new JPanel();
		pn_per2=new JPanel();
		pn_per3=new JPanel();
		pn_per4=new JPanel();
		label_oldPwd=new JLabel("旧密码", JLabel.CENTER);
		text_oldPwd=new JPasswordField(20);
		label_newPwd=new JLabel("新密码", JLabel.CENTER);
		text_newPwd=new JPasswordField(20);
		label_pwdEnsure=new JLabel("密码确认", JLabel.CENTER);
		text_pwdEnsure=new JPasswordField(20);
		bt_pwdVerify=new JButton("修改密码");
		pn_per1.add(label_oldPwd);
		pn_per1.add(text_oldPwd);
		pn_per2.add(label_newPwd);
		pn_per2.add(text_newPwd);
		pn_per3.add(label_pwdEnsure);
		pn_per3.add(text_pwdEnsure);
		pn_per4.add(bt_pwdVerify);
		pn_per.add(pn_per1);
		pn_per.add(pn_per2);
		pn_per.add(pn_per3);
		pn_per.add(pn_per4);
		//
		//放置
		pn_main.add(labelMain);
		
		pn_choose1.add(label_choose_aca);
		pn_choose1.add(text_choose_aca);
		pn_choose1.add(bt_course);
		pn_choose3.add(bt_choose);
		pn_choose.add(pn_choose1);
		pn_choose.add(jp_choose2);
		pn_choose.add(pn_choose3);
		//所选课程面板
		pn_course2.add(bt_chosen);
		pn_course2.add(bt_delete);
		pn_course.add(jp_course1);
		pn_course.add(new JLabel());
		pn_course.add(pn_course2);
		//
		
		
		jp.addTab("", pn_main);
		jp.addTab("选课", pn_choose);
		jp.addTab("所选课程", pn_course);
		jp.addTab("个人信息", pn_per);//修改个人密码
		pn.add(jp);
		this.add(pn);
	}
	public void register()
	{
		
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			outTime=  new Date(Calendar.getInstance().getTimeInMillis());
			record.setOutTime(outTime);
			Tools.writeRecord(record);
			System.out.println(Tools.readRecord());
			
			if(StudentGUI.this.mf.getUm()!=null){
				StudentGUI.this.mf.getUm().closeConnection();}
			((StudentGUI)e.getSource()).dispose();
			StudentGUI.this.mf.dispose();
			System.exit(0);
		}
		});
		//查询数据库课程表中 学院符合的 老师非空的课程   界面显示课程号 课程名 老师  复选框 只能选择一个学院的synchronized(LoginFrame.class)
		//cid  tid  sid 三个一个不同就是不同的记录
		bt_course.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				{
					play_row=0;
					  caca=text_choose_aca.getText();
					  if("".equals(caca)==true){new MyDialog("学院不能为空！");}
					  else{
						  Object params[]={caca};
							StudentGUI.this.mf.getUm().sqlBuilde("select distinct cid,cname,tid,teacher from course where caca=? and tid!='' ",params);
							try {
								ResultSet rs=StudentGUI.this.mf.getUm().getPs().executeQuery();
							
											list_play=new ArrayList<Course>();
											//将读取的课程信息读到List<Course>中
											while(rs.next())
											{
												Course course=new Course();
												course.setCid(rs.getString(1));
												course.setCname(rs.getString(2));
												course.setCaca(caca);
												Teacher tea=new Teacher();
												tea.setTname(rs.getString(4));
												tea.setTid(rs.getString(3));
												course.setTea(tea);
												list_play.add(course);
											}
											 tableModel = new MyTableModel(pn_choose2_table);
//											 MyTableModel dtm=(MyTableModel)pn_choose2_table.getModel();
											 tableModel.clearAllData();
											if(list_play.size()==0)
											{new MyDialog("现在不选课！");}
											else if(list_play.size()>COURSE_NUM){new MyDialog("选课数量超过限制！");}
											else{
												//在table里显示
												
												for(Course course:list_play)
												{
													tableModel.addRow(new Object[]{false, course.getCid(),course.getCname(), course.getTea().getTname(),course.getTea().getTid()},play_row++);
												}
												jp_choose2.setVisible(true);
											}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					  }
				}
				
				
			}
		});
		//选择课程 并在课程表里更新相应课程的学生信息    将选取的课程存到List<Course>中 然后更新数据库 限制选课人数synchronized(LoginFrame.class)
		bt_choose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				{
					list_choose=new ArrayList<Course>();
					tableModel = new MyTableModel(pn_choose2_table);
					if(list_play!=null){
						int len_list_play=list_play.size();
						
						for(int i=0;i<len_list_play;i++)
						{
							if((boolean)tableModel.getRow(i)[0]==true)
							{
								Course course=new Course();
								course.setCid((String)tableModel.getRow(i)[1]);
								course.setCname((String)tableModel.getRow(i)[2]);
								course.setCaca(caca);
								Teacher tea=new Teacher();
								tea.setTname((String)tableModel.getRow(i)[3]);
								tea.setTid((String)tableModel.getRow(i)[4]);
								course.setTea(tea);
								course.setStu(student);
								list_choose.add(course);
							}
						}
						if(list_choose.size()==0){new MyDialog("未选择课程！");}
						else{
							if(list_choose.size()>COURSE_NUM){new MyDialog("课程数超出！");}
							else{
								//更新数据库
								for(Course course:list_choose){
									String sname=course.getStu().getSname();
									String sid=course.getStu().getSid();
									String cid=course.getCid();
									//先查询 如果已经有学生选择了 就要插入   
									Object params3[]={cid,course.getTea().getTid()};
									StudentGUI.this.mf.getUm().sqlBuilde("select sid from course where cid=? and tid=? ",params3);
									try {
										ResultSet rs=StudentGUI.this.mf.getUm().getPs().executeQuery();
										int flag=2;//没有存在
										String tt=new String();
										while(rs.next())
										{
											 tt=rs.getString(1);
											if(tt!=null)
											{flag=1;
												if(tt.equals(sid)){flag=3;break;}
												
											}
											else
											{flag=2;}//
										}
										{
											if(flag==1){
												Object params5[]={course.getCname(),cid,course.getCaca(),course.getTea().getTname(),course.getTea().getTid(),sname,sid};
												StudentGUI.this.mf.getUm().sqlBuilde("insert into course (cname,cid,caca,teacher,tid,student,sid)values(?,?,?,?,?,?,?)",params5);
												StudentGUI.this.mf.getUm().getPs().execute();
//												new MyDialog("选课成功");
												}
												else if(flag==2)
												{
													//没有学生选择 就更新
													Object params2[]={sname,sid,cid,course.getTea().getTid()};
													StudentGUI.this.mf.getUm().sqlBuilde("update course set student=?,sid=? where cid=? and tid=?",params2);
													try {
														StudentGUI.this.mf.getUm().getPs().execute();
//														new MyDialog("选课成功");
													} catch (SQLException e1) {
														new MyDialog("选课失败");
														e1.printStackTrace();
													}
												}
										}
										
									} catch (SQLException e2) {
										new MyDialog("选课失败");
										e2.printStackTrace();
									}
									
								}
								new MyDialog("选课成功");
							}
						}
						
						
					
					}
					
				}
				
			}
		});
	//所选课程面板  显示已经存在数据库的所选课程synchronized(LoginFrame.class)
		bt_chosen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				{
					Object params[]={student.getSid()};
					list_chosen=new ArrayList<Course>();
					StudentGUI.this.mf.getUm().sqlBuilde("select * from course where sid=? ",params);
					try {
						ResultSet rs=StudentGUI.this.mf.getUm().getPs().executeQuery();
						while(rs.next())
						{
							Course course=new Course();
							course.setCid(rs.getString(2));
							course.setCname(rs.getString(3));
							course.setStu(student);
							Teacher tea=new Teacher();
							tea.setTname(rs.getString(5));
							tea.setTid(rs.getString(6));
							course.setTea(tea);
							list_chosen.add(course);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tableModel_course = new MyTableModel(pn_course_table);
					tableModel_course.clearAllData();
					if(list_chosen.size()==0)
					{new MyDialog("未选课！");}
					else{
						//在table里显示
						
						
						int i=0;
						for(Course course:list_chosen)
						{
							tableModel_course.addRow(new Object[]{false, course.getCid(),course.getCname(), course.getTea().getTname(),course.getTea().getTid()},i++);
						}
						jp_course1.setVisible(true);
					}
				}
				
			}
		});
		//所选课程面板 删除已存在数据库里的所选课程   更新数据库synchronized(LoginFrame.class)
		bt_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				{
					list_delete=new ArrayList<Course>();
					tableModel_course = new MyTableModel(pn_course_table);
					if(list_chosen!=null){
						if(list_chosen.size()==0){new MyDialog("现在还没有选定的课程！");}
						else
						{
							int len_chosen=list_chosen.size();
//							int j=0;
							for(int i=0;i<len_chosen;i++)
							{
								
								if((boolean)tableModel_course.getRow(i)[0]==true)
								{
//									j++;
									Course course=new Course();
									course.setCid((String)tableModel_course.getRow(i)[1]);
									course.setCname((String)tableModel_course.getRow(i)[2]);
									course.setCaca(caca);
									Teacher tea=new Teacher();
									tea.setTname((String)tableModel_course.getRow(i)[3]);
									tea.setTid((String)tableModel_course.getRow(i)[4]);
									course.setTea(tea);
									course.setStu(student);
									list_delete.add(course);
//									list_chosen.remove(i-j);//链表删除后 索引变化
								}
							}
							if(list_delete.size()==0){new MyDialog("未选择课程！");}
							//更新数据库
							else
							{
								for(Course course:list_delete){
									{
										Object params2[]={student.getSid(),course.getCid(),course.getTea().getTid()};
										StudentGUI.this.mf.getUm().sqlBuilde("update course set student='' ,sid='' where  sid=? and cid=? and tid=?",params2);
										try {
											StudentGUI.this.mf.getUm().getPs().execute();
										} catch (SQLException e1) {
											new MyDialog("删除失败");
											e1.printStackTrace();
										}
									}
									
								}
								new MyDialog("删除成功");
							}
						}
					}
				}
				
				
			}
		});
		//个人信息面板 修改密码synchronized(LoginFrame.class)
		bt_pwdVerify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				{
					String pwd=StudentGUI.this.mf.getUm().getUser().getPwd();
					String inputpwd=text_oldPwd.getText();
					String newPwd=text_newPwd.getText();
					String newPwdEnsure=text_pwdEnsure.getText();
					if(pwd.equals(inputpwd)==false)
					{
						new MyDialog("旧密码错误");
						text_oldPwd.setText("");
					}
					else
					{
						if(newPwd.equals(newPwdEnsure)==false)
						{
							new MyDialog("两次密码不一样");
							text_newPwd.setText("");
							text_pwdEnsure.setText("");
						}
						else
						{
							{
								Object params3[]={newPwd,StudentGUI.this.mf.getUm().getUser().getUname()};
								StudentGUI.this.mf.getUm().sqlBuilde("update user1 set upwd=? where uname=? ",params3);
								try {
									StudentGUI.this.mf.getUm().getPs().execute();
									new MyDialog("修改成功");
								} catch (SQLException e1) {
									new MyDialog("修改失败");
									e1.printStackTrace();
								}
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
