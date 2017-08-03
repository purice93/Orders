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


public class Tchlookinfo extends JFrame implements ActionListener{

	JPanel jpl=new JPanel ();
	Container cont=new Container();//������
	JButton btn_sure=new JButton("�� ѯ");
	JButton btn_cancle=new JButton("�� ��");
	JTextField[] txt ={ new JTextField() , new JTextField() , new JTextField() , new JTextField() ,
			new JTextField() , new JTextField() ,new JTextField() , new JTextField()};
	
	JLabel namel=new JLabel("������");
	JLabel xuehaol=new JLabel("ѧ�ţ�");
	JLabel mayorl=new JLabel("רҵ��");
	JLabel phonenuml=new JLabel("�ֻ����룺");
	JLabel addressl=new JLabel("���᣺");
	JLabel sexl=new JLabel("�Ա�");
	JLabel cometimel=new JLabel("��ѧʱ�䣺");
	JLabel birthtimel=new JLabel("�������ڣ�");
	JLabel yuanxil=new JLabel("Ժϵ��");
	public Tchlookinfo()
	{
		super("��ѯ��Ϣ");
		this.setSize(420,400);
		this.setLocation(300,200);
		this.setVisible(true);
		this.setResizable(false);//�����С���ɱ�
		cont=getContentPane();//��ȡ����
		cont.add(jpl);
		btn_sure.setBounds(200,  50, 65, 20);
		btn_sure.addActionListener(this);
		btn_cancle.setBounds(270, 50, 65, 20);
		btn_cancle.addActionListener(this);
		xuehaol.setBounds(15, 45, 80, 30);
		namel.setBounds(15, 100, 80, 30);
		sexl.setBounds(15, 155, 80, 30);
		birthtimel.setBounds(200, 155, 80, 30);
		phonenuml.setBounds(15, 205, 80, 30);
		addressl.setBounds(200, 205, 80, 30);
		mayorl.setBounds(15, 255, 80,30);
		yuanxil.setBounds(200, 255, 80, 30);
		 txt[0].setBounds(85,50,100,20);
		 txt[1].enable(false);
		 txt[1].setBounds(85,105,100,20);
		 txt[2].setBounds(85,160,100,20);
		 txt[2].enable(false);
		 txt[3].setBounds(270,160,100,20);
		 txt[3].enable(false);
		 txt[4].setBounds(85,210,100,20);
		 txt[4].enable(false);
		 txt[5].setBounds(270,210,100,20);
		 txt[5].enable(false);
		 txt[6].setBounds(85,260,100,20);
		 txt[6].enable(false);
		 txt[7].setBounds(270,260,100,20);
		 txt[7].enable(false);
		 
		    jpl.add(txt[0]);
			jpl.add(txt[1]);
			jpl.add(txt[2]);
			jpl.add(txt[3]);
			jpl.add(txt[4]);
			jpl.add(txt[5]);
			jpl.add(txt[6]);
			jpl.add(txt[7]);
			
			
			//jpl.setBorder(BorderFactory.createTitledBorder("��������"));
			jpl.setLayout(null);//������jpl�Ĳ��ַ�ʽ
			jpl.setBounds(12, 30, 390,350);
			
			
			jpl.add(xuehaol);
			jpl.add(namel);
			jpl.add(sexl);
			jpl.add(birthtimel);
			jpl.add(phonenuml);
			jpl.add(addressl);
			jpl.add(mayorl);
			jpl.add(yuanxil);
			jpl.add(btn_sure);
			jpl.add(btn_cancle);
			jpl.setVisible(true);
		
	}
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tchlookinfo();
	}*/
	public  void start() {
		// TODO Auto-generated method stub
		new Tchlookinfo();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������

		String user = "root"; //SQL�������ݿ�
		String userpwd = "root";
		String url = "jdbc:mysql://localhost:3306;DatabaseName=myclass";
		//String url = "jdbc:sqlserver://localhost:1433;DatabaseName=StuLearn";
		if(e.getSource()==btn_sure)
		{
			String id = txt[0].getText();
			try
			{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"). newInstance();
			}
			catch (ClassNotFoundException ce)
			{
				JOptionPane.showMessageDialog(null,ce.getMessage());
			} catch (InstantiationException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			try
			{
				Connection con = DriverManager.getConnection(url, user, userpwd);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from stu_info where stu_id = '" + id + "'");
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
		txt[0].requestFocus();
		}
	}
}
