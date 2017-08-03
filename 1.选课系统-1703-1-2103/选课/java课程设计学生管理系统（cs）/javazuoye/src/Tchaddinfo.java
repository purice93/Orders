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
	Container cont=new Container();//������
	JButton serch = new JButton("���");
	JButton reset = new JButton("����");
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
	public Tchaddinfo()
	{
		super("���ѧ��������Ϣ");
		setLayout(null);//ȡ�����ֹ����������Ⲽ��
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
		cont=getContentPane();//��ȡ����
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
		
		
		jpl.setBorder(BorderFactory.createTitledBorder("��������"));
		jpl.setLayout(null);//������jpl�Ĳ��ַ�ʽ
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
		// TODO �Զ����ɵķ������
		String user = "sa"; //SQL�������ݿ�
		String userpwd = "123456";
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=StuLearn";
		if(arg0.getSource()==serch)
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
				String b="select * from Stu_info where stu_id='"+txt[0].getText()+"'";
				 ResultSet rs = stmt.executeQuery(b);
			
			   if(rs.next())
				 {
					System.out.println(txt[0].getText());
					 JOptionPane.showMessageDialog(null,"���û��Ѿ�����ӹ�����������ӣ�");
				 }
					
			   else
			   {
				   int a = stmt.executeUpdate("insert into stu_info(stu_id ,  stu_name ,stu_pwd , stu_sex , stu_birth , stu_phone , stu_address ,stu_mayor,stu_yx)values('"+txt[0].getText()+"','"+txt[1].getText()+"','"+txt[0].getText()+"','"+txt[2].getText()+"','"+txt[3].getText()+"','"+txt[4].getText()+"','"+txt[5].getText()+"','"+txt[6].getText()+"','"+txt[7].getText()+"')");
					if(a==1)
					{
						System.out.println(a);
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
