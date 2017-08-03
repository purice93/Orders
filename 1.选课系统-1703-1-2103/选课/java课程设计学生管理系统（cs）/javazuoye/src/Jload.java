import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class Jload extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	private static JPanel contentPane;
    private static JTextField textField;
    private static JPasswordField passwordField1;
    private static JComboBox jcbm;
    String []lx={"        学            生          ","        老           师           "};
    JButton btn_load = new JButton("登录");
    JButton btn_cancle = new JButton("退出");
    JPanel titlep = new JPanel();//标题容器
    JPanel namep = new JPanel();//用户行容器
    JPanel pwdp = new JPanel();//密码行容器
    JPanel leixingp = new JPanel();//类型行容器
    JPanel btnp = new JPanel();//按钮行容器
    public Jload()
    {
    	getContentPane().setLayout(null);
    	this.setTitle("登录页面");
    	
    	//this.textField=textField;
    	
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setBounds(100, 100, 300, 250);//设置窗口大小
	    
	    contentPane = new JPanel();//把所有的控件都放在这个大容器里面
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//内部边框为空
	    contentPane.setLayout(new GridLayout(5, 1, 5, 5));//分成5组
	    contentPane.add(titlep);
	    contentPane.add(namep);
	    contentPane.add(pwdp);
	    contentPane.add(leixingp);
	    contentPane.add(btnp);
	    
	    JLabel titlel=new JLabel("欢迎使用学生管理系统！"); 
	    JLabel namel=new JLabel("学  号："); 
	    JLabel pwdl=new JLabel("密  码："); 
	    JLabel leixingl=new JLabel("类  型："); 
	    //leixingl.setLocation(200, 100);
	    
	    textField=new JTextField();//先实例化一个textField
	    textField.setColumns(13);//设置长度
	    passwordField1=new JPasswordField();
	    passwordField1.setColumns(13);
	    jcbm=new JComboBox();
	    jcbm.addItem(lx[0]);//向jcombobox中添加元素
	    jcbm.addItem(lx[1]);
	   
	    
	    titlep.add(titlel);//将标题放在用户栏容器中
	    namep.add(namel);
	    namep.add(textField);
	    pwdp.add(pwdl);
	    pwdp.add(passwordField1);
	   
	    leixingp.add(leixingl);
	    leixingp.add(jcbm); 
	   
	    btn_load.addActionListener((ActionListener) this);
	    btn_cancle.addActionListener((ActionListener) this);
	   
	    btnp.add(btn_load);
	    btnp.add(btn_cancle);
	    
	    this.setContentPane(contentPane);
	    this.setVisible(true);
	    this.setResizable(false);//窗体大小不可变
	  
    }
    
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      new Jload();
     
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn_load)
		{
			String xh , password;//与数据库中的数据匹配
			xh = textField.getText();
		    password = passwordField1.getText();
		    xh=xh.trim();//去除空格
	    	password=password.trim();
			String user = "sa"; //SQL连接数据库
			String userpwd = "123456";
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Stulearn";
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
			
			if(jcbm.getSelectedIndex()==0)
			{
				try
			    {
					
					
					Connection con = DriverManager.getConnection(url, user, userpwd);
				
				    Statement stmt = con.createStatement();//创建声明
				    String str="select * from Stu_info where stu_id='"+xh+"'and stu_pwd='"+password+"'";
				    ResultSet rs = stmt.executeQuery(str);
				    if(rs.next())
				    {
				    	
				    	 new Stuyemian(xh);
				    	// Jload.this.setVisible(false);
				    	 this.dispose();
				    }
					  
					    else
					    {
					    	
						    JOptionPane.showMessageDialog(null, "登录失败,	请检查登录信息！");
						   
					    }
				    
				    rs.close();
				    stmt.close();
			    }
			    catch (SQLException se)
			    {
				    JOptionPane.showInputDialog(se.getMessage());
			    }
			}
			else if(jcbm.getSelectedIndex()==1)
			{
				try
				{
					Connection con = DriverManager.getConnection(url, user, userpwd);
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from tch_user where tch_id='"+xh+"'and tch_pwd='"+password+"' ");
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null,"登陆成功");
						new Tchyemian();
						this.dispose();
					}
						else
						{
							JOptionPane.showMessageDialog(null,"登录失败,请检查登录信息！");
						}
					}
				
				catch (SQLException se)
				{
					JOptionPane.showInputDialog(se.getMessage());
				}
			}
		}
		else 
		{
			System.exit(0);//退出程序，釋放資源
			
		}
	}

}
