import java.awt.Color;
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


public class Tchaddsc extends JFrame implements ActionListener{

	JLabel title = new JLabel("¼��ѧ���ɼ�",JLabel.CENTER);
	JLabel[] label = {new JLabel("ѧ�ţ�"), new JLabel("������"), new JLabel("�㷨������") , new JLabel("������ԣ�") , new JLabel("������̣�") , new JLabel("���������ϵͳ��") , new JLabel("���ݿ�ԭ��") , new JLabel("��������磺") , new JLabel("ѧ�֣�") }; 
	JTextField[] txt = {new JTextField(), new JTextField() , new JTextField() , new JTextField() , new JTextField() , new JTextField() ,new JTextField() , new JTextField() ,new JTextField() ,new JTextField() };
	JButton add = new JButton("���");
	JButton reset = new JButton("����");
	JPanel jpl = new JPanel();
	Font f = new Font("����" , Font.BOLD , 18 );
	int s = 100;
	

	
	
	public Tchaddsc()
	{
		super("��ʦ¼��ɼ�");
		this.setResizable(false);
		this.setSize(500,600);
		this.setLocation(240, 50);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.add(jpl);
		add.addActionListener(this);
		reset.addActionListener(this);
		jpl.setLayout(null);
		title.setBounds(150,40,200,20);
		title.setFont(f);
		title.setForeground(Color.red);
		jpl.setBackground(Color.lightGray);
		jpl.add(title);
		for(int i = 0 ; i <label.length ; i++)
		{
			label[i].setBounds(100,s,140,20);
			jpl.add(label[i]);
			txt[i].setBounds(260,s,140,20);
			jpl.add(txt[i]);
			s=s+40;
		}
		add.setBounds(150,s,80,20);
		reset.setBounds(250,s,80,20);
		jpl.add(add);
		jpl.add(reset);	
		
	}
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tchaddsc();
	}*/
	public void start()
	{
		new Tchaddsc();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		String user = "sa"; //SQL�������ݿ�
		String userpwd = "123456";
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=StuLearn";
		if(e.getSource()==add)
		{
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"). newInstance();
				} catch (InstantiationException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			
		
			try
			{
				Connection con = DriverManager.getConnection(url, user, userpwd);
				Statement stmt = con.createStatement();
				String b="select * from stuTch_sc where stu_id='"+txt[0].getText()+"'";
				 ResultSet rs = stmt.executeQuery(b);
				
			   if(rs.next())
				 {
					 JOptionPane.showMessageDialog(null,"���û��Ѿ�����ӹ�����������ӣ�");
					// return;
				 }
					
			   else
			   {
				   int a = stmt.executeUpdate("insert into stuTch_sc(stu_id , stu_term , stu_name , stu_sf , stu_hb , stu_rj , stu_cz , stu_sjk ,stu_wl,stu_xf)values('"+txt[0].getText()+"','"+"2011~2012"+"','"+txt[1].getText()+"','"+txt[2].getText()+"','"+txt[3].getText()+"','"+txt[4].getText()+"','"+txt[5].getText()+"','"+txt[6].getText()+"','"+txt[7].getText()+"','"+txt[8].getText()+"')");
					if(a==1)
					{
						JOptionPane.showMessageDialog(null,"��ӳɹ�");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"���ʧ��");
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
			for(int i = 0 ; i<txt.length ; i++)
			{
				txt[i].setText("");
				txt[0].requestFocus();
			}
		}
	}
}
