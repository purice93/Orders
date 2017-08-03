import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Scserchone extends JFrame  implements ActionListener {

	JLabel title = new JLabel("查询2011~2012学年成绩",JLabel.CENTER);
	
	Font f = new Font( "黑" ,Font.ITALIC ,16 );
	
	JPanel jpl = new JPanel();
	JLabel label1 = new JLabel("请输入学号：",JLabel.CENTER);
	JTextField num = new JTextField();
	JButton serch = new JButton("查询");
	JButton reset = new JButton("重置");
	JLabel label2 = new JLabel("算法分析：",JLabel.LEFT);
	JLabel label3 = new JLabel("汇编语言：",JLabel.LEFT);
	JLabel label4 = new JLabel("软件工程：",JLabel.LEFT);
	JLabel label5 = new JLabel("计算机操作系统：",JLabel.LEFT);
	JLabel label6 = new JLabel("数据库原理：",JLabel.LEFT);
	JLabel label7 = new JLabel("计算机网络：",JLabel.LEFT);
	JLabel label8 = new JLabel("学分：",JLabel.LEFT);
	
	JTextField[] txt ={  new JTextField() , new JTextField() ,new JTextField() , new JTextField() , new JTextField() ,new JTextField() , new JTextField()};
	static int p = 140;
	
	public Scserchone()
	{
		super("查看成绩");
		this.setSize(500,540);
		this.setLocation(260,150);
		this.setVisible(true);
	    this.setResizable(false);//窗体大小不可变
		this.add(jpl);
		jpl.setLayout(null);
		title.setBounds(100,20,300,20);
		jpl.add(title);
		label1.setBounds(100,60,90,20);
		jpl.add(label1);
		num.setBounds(210,60,140,20);
		jpl.add(num);
		serch.setBounds(130,100,90,20);
		reset.setBounds(240,100,90,20);
		jpl.add(serch);
		jpl.add(reset);
		serch.addActionListener(this);
		reset.addActionListener(this);
		label2.setBounds(100,140,140,20);
		label3.setBounds(100,180,140,20);
		label4.setBounds(100,220,140,20);
		label5.setBounds(100,260,140,20);
		label6.setBounds(100,300,140,20);
		label7.setBounds(100,340,140,20);
		label8.setBounds(100,380,140,20);
		jpl.add(label2);
		jpl.add(label3);
		jpl.add(label4);
		jpl.add(label5);
		jpl.add(label6);
		jpl.add(label7);
		jpl.add(label8);
		
		for(int i = 0 ;i<txt.length ; i++)
		{
			txt[i].setBounds(260,p,140,20);
			txt[i].enable(false);
			txt[i].setFont(f);
			jpl.add(txt[i]);
			
			p=p+40;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		String user = "sa"; //SQL连接数据库
		String userpwd = "123456";
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=StuLearn";
		if(arg0.getSource()==serch)
		{
			String id = num.getText();
			
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
				ResultSet rs = stmt.executeQuery("select * from stuTch_sc  where stu_id ='" + id + "'");
				while(rs.next())
				{
					for(int i = 0 ; i<txt.length ; i++)
					{
						txt[i].setText(rs.getString(i+4));
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
			for(int i = 0;i<txt.length ; i++)
			{
				txt[i].setText("");
			}
			num.setText("");
		}
	}

	/*public static void main(String[] args)
	{
		Score s = new Score();
	}*/
	public void start()
	{
		Scserchone cserchone=new Scserchone();
	}
	
	
	/*public static void main(String[] args) {
		Scserchone cserchone=new Scserchone();
	 
	}*/

	
}
