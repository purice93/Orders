import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Tchaddinfo  extends JFrame implements ActionListener{

	JPanel jpl=new JPanel ();
	Container cont=new Container();//总容器
	JButton serch = new JButton("添加");
	JButton reset = new JButton("重置");
	JTextField[] txt ={ new JTextField() , new JTextField() , new JTextField() , new JTextField() ,
			new JTextField() , new JTextField() ,new JTextField() , new JTextField()};
	JLabel namel=new JLabel("姓名：");
	JLabel xuehaol=new JLabel("学号：");
	JLabel mayorl=new JLabel("专业：");
	JLabel phonenuml=new JLabel("手机号码：");
	JLabel addressl=new JLabel("籍贯：");
	JLabel sexl=new JLabel("性别：");
	JLabel cometimel=new JLabel("入学时间：");
	JLabel birthtimel=new JLabel("出生日期：");
	JLabel yuanxil=new JLabel("院系：");
	public Tchaddinfo()
	{
		super("添加学生基本信息");
		setLayout(null);//取消布局管理器，任意布局
		this.setSize(420,400);
		this.setLocation(200,200);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serch.setBounds(100,250,90,20);
		serch.addActionListener(this);
		reset.setBounds(230,250,90,20);
		reset.addActionListener(this);
		jpl.add(serch);
		jpl.add(reset);
		cont=getContentPane();//获取容器
		cont.add(jpl);
		xuehaol.setBounds(15, 45, 80, 30);
		namel.setBounds(200, 45, 80, 30);
		sexl.setBounds(15, 100, 80, 30);
		birthtimel.setBounds(200, 100, 80, 30);
		phonenuml.setBounds(15, 155, 80, 30);
		addressl.setBounds(200, 155, 80, 30);
		mayorl.setBounds(15, 205, 80,30);
		yuanxil.setBounds(200, 205, 80, 30);
		
	    txt[0].setBounds(85,50,100,20);
	    
	    txt[1].setBounds(270,50,100,20);
	    
	    txt[2].setBounds(85,105,100,20);
	   
	    txt[3].setBounds(270,105,100,20);
	    
	    txt[4].setBounds(85,160,100,20);
	    
	    txt[5].setBounds(270,160,100,20);
	    
	    txt[6].setBounds(85,210,100,20);
	    
	    txt[7].setBounds(270,210,100,20);
	    
	    
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
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tchaddinfo();
	}*/
	public  void start() {
		// TODO Auto-generated method stub
		new Tchaddinfo();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		String user = "sa"; //SQL连接数据库
		String userpwd = "123456";
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=StuLearn";
		if(arg0.getSource()==serch)
		{
			
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"). newInstance();
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
				Statement stmt = con.createStatement();
				String b="select * from Stu_info where stu_id='"+txt[0].getText()+"'";
				 ResultSet rs = stmt.executeQuery(b);
			
			   if(rs.next())
				 {
					System.out.println(txt[0].getText());
					 JOptionPane.showMessageDialog(null,"此用户已经被添加过，请重新添加！");
				 }
					
			   else
			   {
				   int a = stmt.executeUpdate("insert into stu_info(stu_id ,  stu_name ,stu_pwd , stu_sex , stu_birth , stu_phone , stu_address ,stu_mayor,stu_yx)values('"+txt[0].getText()+"','"+txt[1].getText()+"','"+txt[0].getText()+"','"+txt[2].getText()+"','"+txt[3].getText()+"','"+txt[4].getText()+"','"+txt[5].getText()+"','"+txt[6].getText()+"','"+txt[7].getText()+"')");
					if(a==1)
					{
						System.out.println(a);
						JOptionPane.showMessageDialog(null,"添加成功");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"添加失败");
					}
			   }
			   }
			
			catch (SQLException se)
			{
				JOptionPane.showMessageDialog(null,se.getMessage());
			}
		}
		else
		{
			txt[0].setText("");
			txt[1].setText("");
			txt[2].setText("");
			txt[3].setText("");
			txt[4].setText("");
			txt[5].setText("");
			txt[6].setText("");
			txt[7].setText("");
			txt[0].requestFocus();
		}
	}
}
