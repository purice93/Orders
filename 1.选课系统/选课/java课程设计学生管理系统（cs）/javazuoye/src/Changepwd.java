import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.sql.*;
public class Changepwd extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	String strI;//用于接收传值
	private static JPanel contentPane;
    private static JTextField textField;
    private static JPasswordField passwordField1,passwordField2;
    JButton btn_sure = new JButton("保存");
    JButton btn_back = new JButton("退出");
    
	public Changepwd(String strI) {
		//super("");
		this.strI=strI;
		this.setTitle("修改密码页面");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setBounds(100, 100, 300, 300);//设置窗口大小
	    this.setVisible(true);
	    this.setResizable(false);//窗体大小不可变
	   
	     contentPane = new JPanel();//把所有的控件都放在这个大容器里面
	     contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//内部边框为空
	     this.setContentPane(contentPane);//把contentPane放在j3中
	     contentPane.setLayout(new GridLayout(5, 1, 5, 5));//分成5组    
	     JPanel panel1 = new JPanel();
	     contentPane.add(panel1);   
	     JLabel label1 = new JLabel("请用户修改密码");
	     panel1.add(label1);  
	     JPanel panel2 = new JPanel();
	     FlowLayout flowLayout = (FlowLayout) panel2.getLayout();//流布局管理器
	     flowLayout.setAlignment(FlowLayout.LEFT);
	     contentPane.add(panel2);    
	     JLabel label2 = new JLabel("用  户  名：");
	     panel2.add(label2);   
	     textField = new JTextField();
	     textField.setText(strI);//接收传值
	     textField.enable(false);
	     panel2.add(textField);
	     textField.setColumns(14);  
	     
	     JPanel panel3 = new JPanel();
	     FlowLayout flowLayout_1 = (FlowLayout) panel3.getLayout();
	     flowLayout_1.setAlignment(FlowLayout.LEFT);
	     contentPane.add(panel3);    
	     JLabel label3 = new JLabel("密        码：");
	     panel3.add(label3); 
	     passwordField1 = new JPasswordField();
	     passwordField1.setColumns(14);//长度14
	     panel3.add(passwordField1);
	    
	     JPanel panel4=new JPanel();
	     FlowLayout flowLayout_2 = (FlowLayout) panel4.getLayout();
	     flowLayout_2.setAlignment(FlowLayout.LEFT);
	     contentPane.add(panel4);
	     JLabel label4=new JLabel("确认密码：");
	     panel4.add(label4);
	     passwordField2=new JPasswordField();
	     passwordField2.setColumns(14);
	     panel4.add(passwordField2);
	    
	     
	     JPanel panel5 = new JPanel();     
	     contentPane.add(panel5);       
         //给button1添加事件
	     btn_sure.addActionListener((ActionListener) this);  
	     //给button2添加事件
	     btn_back.addActionListener((ActionListener) this);   
	     panel5.add(btn_sure);
	     panel5.add(btn_back);
	  
	 	
	
	}

	/**
	 * @param args
	 */
	
/*	public static void main(String[] args) {
		Changepwd pwdcg=new Changepwd("F");
	 
	}*/
	public  void start() {
		Changepwd pwdcg=new Changepwd("F");
	 
	}
	
	public void actionPerformed(ActionEvent e) {
		//Students stu = new Students();
		if(e.getSource()==btn_sure)
		{
			this.dispose();
			String username , password1,password2;//与数据库中的数据匹配
			username = textField.getText();
			
		    password1 = passwordField2.getText();
		    password2=passwordField1.getText();
		    username=username.trim();//去除空格
	    	password1=password1.trim();
	    	password2=password2.trim();
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
			
				try
			    {
					Connection con = DriverManager.getConnection(url, user, userpwd);
				
				    Statement stmt = con.createStatement();//创建声明
				    if(password1.equals(password2))
				    { 
				    //	System.out.println(password1);
				    //	System.out.println(password2);
				    String str="update Stu_info set stu_pwd='"+password1+"' where stu_id='"+username+"'";
				  //  ResultSet rs = stmt.executeQuery(str);
				    int rs = stmt.executeUpdate(str);
				//   String s=  rs.getString("Pwd");
				//   System.out.println(rs);
				    
				    if(rs!=0)
				    {
				    	 new Stuyemian("fdew");
				    	 this.dispose();
				    }
					  
					    else
					    {
						    JOptionPane.showMessageDialog(null, "更新失败！");
						   
					    }
				  //   rs.close();
				     stmt.close();
				    }
				    else
				    {
				    	JOptionPane.showMessageDialog(null, "两次输入的密码不一样，请重新输入！");
				    }
				    
				   
			    }
			    catch (SQLException se)
			    {
				   // JOptionPane.showMessageDialog(stu,se.getMessage());
			    	se.setNextException(se);
			    }
			
			
		}
		else 
		{
			System.exit(0);//退出程序，釋放資源
			
		}
	}
}
	
	




