import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;


public class Stuyemian extends JFrame implements ActionListener {
	String stuId;//接收传值
	 static String str1;
	 
	String user = "root"; //SQL连接数据库
	String userpwd = "root";
	String url = "jdbc:mysql://127.0.0.1:3306/myclass";
	 
	JPanel jpl=new JPanel ();
	Container cont=new Container();//总容器
	static JTextField[] txt ={ new JTextField() , new JTextField() , new JTextField() , new JTextField() ,
			new JTextField() , new JTextField() ,new JTextField() , new JTextField()};
	JMenuBar comtentpane = new JMenuBar();//存放菜单栏
	JMenu msgbox = new JMenu("个人操作");
	JMenu Scorebox = new JMenu("成绩查询");
	JMenu wendang = new JMenu("文档");
	JMenuItem change = new JMenuItem("修改密码");
	JMenuItem back = new JMenuItem("返回");
	JMenuItem exit = new JMenuItem("退出");
	JMenuItem scserch_1 = new JMenuItem("11~12学年");
	JMenuItem scserch_2 = new JMenuItem("10~11学年");
	JMenuItem scserch_3 = new JMenuItem("总成绩");
	JMenuItem wenitem= new JMenuItem("开发者");
	
	JLabel namel=new JLabel("姓名：");
	JLabel xuehaol=new JLabel("学号：");
	JLabel mayorl=new JLabel("专业：");
	JLabel phonenuml=new JLabel("手机号码：");
	JLabel addressl=new JLabel("籍贯：");
	JLabel sexl=new JLabel("性别：");
	JLabel cometimel=new JLabel("入学时间：");
	JLabel birthtimel=new JLabel("出生日期：");
	JLabel yuanxil=new JLabel("院系：");
	
	public Stuyemian(String stuId)
	{
		super("学生界面");
		this.stuId=stuId;
		
		setLayout(null);//取消布局管理器，任意布局
		this.setSize(420,400);
		this.setLocation(200,200);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(comtentpane);
		cont=getContentPane();//获取容器
		cont.add(jpl);
		comtentpane.add(msgbox);
		comtentpane.add(Scorebox);
		comtentpane.add(wendang);
		msgbox.add(change);
		msgbox.add(back);
		msgbox.add(exit);
		Scorebox.add(scserch_1);
		Scorebox.add(scserch_2);
		Scorebox.add(scserch_3);
		wendang.add(wenitem);
	    change.addActionListener(this);//添加监视
	    back.addActionListener(this);
	    scserch_1.addActionListener(this);
	    scserch_2.addActionListener(this);
	    scserch_3.addActionListener(this);
	    exit.addActionListener(this);
	    wenitem.addActionListener(this);
		
	    xuehaol.setBounds(15, 45, 80, 30);
		namel.setBounds(200, 45, 80, 30);
		sexl.setBounds(15, 100, 80, 30);
		birthtimel.setBounds(200, 100, 80, 30);
		phonenuml.setBounds(15, 155, 80, 30);
		addressl.setBounds(200, 155, 80, 30);
		mayorl.setBounds(15, 205, 80,30);
		yuanxil.setBounds(200, 205, 80, 30);
		
	    txt[0].setBounds(85,50,100,20);
	    txt[0].enable(false);
	    txt[1].setBounds(270,50,100,20);
	    txt[1].enable(false);
	    txt[2].setBounds(85,105,100,20);
	    txt[2].enable(false);
	    txt[3].setBounds(270,105,100,20);
	    txt[3].enable(false);
	    txt[4].setBounds(85,160,100,20);
	    txt[4].enable(false);
	    txt[5].setBounds(270,160,100,20);
	    txt[5].enable(false);
	    txt[6].setBounds(85,210,100,20);
	    txt[6].enable(false);
	    txt[7].setBounds(270,210,100,20);
	    txt[7].enable(false);
	    txt[0].setText(stuId);
	    str1=txt[0].getText();
	    System.out.println(str1);
	   
	    
		jpl.add(txt[0]);
		jpl.add(txt[1]);
		jpl.add(txt[2]);
		jpl.add(txt[3]);
		jpl.add(txt[4]);
		jpl.add(txt[5]);
		jpl.add(txt[6]);
		jpl.add(txt[7]);
		
		
		jpl.setBorder(BorderFactory.createTitledBorder("基本资料"));
		jpl.setLayout(null);//不设置jpl的布局方式
		jpl.setBounds(12, 30, 390,300);
		
		
		jpl.add(xuehaol);
		jpl.add(namel);
		jpl.add(sexl);
		jpl.add(birthtimel);
		jpl.add(phonenuml);
		jpl.add(addressl);
		jpl.add(mayorl);
		jpl.add(yuanxil);
		jpl.setVisible(true);
		
		
		
		 try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"). newInstance();
				
				//JOptionPane.showInputDialog("数据库驱动成功！");
			} catch (InstantiationException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}					
			
				try
			    {
					
					 
					Connection con = DriverManager.getConnection(url, user, userpwd);
				
				    Statement stmt = con.createStatement();//创建声明
				    String str="select * from Stu_info where stu_id='"+str1+"'";
				    ResultSet rs = stmt.executeQuery(str);
				    while(rs.next())
				    {
				    	 
				    	txt[1].setText(rs.getString("stu_name"));
						txt[2].setText(rs.getString("stu_sex"));
						txt[3].setText(rs.getString("stu_birth"));
						txt[4].setText(rs.getString("stu_phone"));
						txt[5].setText(rs.getString("stu_address"));
						txt[6].setText(rs.getString("stu_mayor"));
						txt[7].setText(rs.getString("stu_yx"));
				    }
					  
					   /* else
					    {
					    	
						    JOptionPane.showMessageDialog(null, "登录失败,	请检查登录信息！");
						   
					    }*/
				    
				    rs.close();
				    stmt.close();
			    }
			    catch (SQLException se)
			    {
				    JOptionPane.showInputDialog(se.getMessage());
			    }
			}
			
	
	
	public void stuye()
	{
		Stuyemian stu=new Stuyemian("dfewf");
		
	}
	

	

	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Stuyemian("00");
		
	}*/
		
		
	

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO 自动生成的方法存根
	if(arg0.getSource()==change)
	{
		new Changepwd(str1);
	}
	if(arg0.getSource()==back)
	{
		new Jload();
		this.dispose();
	}
	if(arg0.getSource()==scserch_1)
	{
		new Scserchone();
	}
	if(arg0.getSource()==scserch_2)
	{
		new Scserchtwo();
		
	}
	if(arg0.getSource()==wenitem)
	{
		
	     
		JOptionPane.showMessageDialog(null,"软件开发者：李贝贝"+"\n"+"学号：102056141");
	}
	if(arg0.getSource()==scserch_3)
	{
	
	     double	num=2.24;
		JOptionPane.showMessageDialog(null,"平均学分:"+ num);
	}
	if(arg0.getSource()==exit)
	{
		System.exit(0);
	}

}






}
