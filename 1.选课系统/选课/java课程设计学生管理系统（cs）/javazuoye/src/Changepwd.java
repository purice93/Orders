import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.sql.*;
public class Changepwd extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	String strI;//���ڽ��մ�ֵ
	private static JPanel contentPane;
    private static JTextField textField;
    private static JPasswordField passwordField1,passwordField2;
    JButton btn_sure = new JButton("����");
    JButton btn_back = new JButton("�˳�");
    
	public Changepwd(String strI) {
		//super("");
		this.strI=strI;
		this.setTitle("�޸�����ҳ��");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setBounds(100, 100, 300, 300);//���ô��ڴ�С
	    this.setVisible(true);
	    this.setResizable(false);//�����С���ɱ�
	   
	     contentPane = new JPanel();//�����еĿؼ��������������������
	     contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//�ڲ��߿�Ϊ��
	     this.setContentPane(contentPane);//��contentPane����j3��
	     contentPane.setLayout(new GridLayout(5, 1, 5, 5));//�ֳ�5��    
	     JPanel panel1 = new JPanel();
	     contentPane.add(panel1);   
	     JLabel label1 = new JLabel("���û��޸�����");
	     panel1.add(label1);  
	     JPanel panel2 = new JPanel();
	     FlowLayout flowLayout = (FlowLayout) panel2.getLayout();//�����ֹ�����
	     flowLayout.setAlignment(FlowLayout.LEFT);
	     contentPane.add(panel2);    
	     JLabel label2 = new JLabel("��  ��  ����");
	     panel2.add(label2);   
	     textField = new JTextField();
	     textField.setText(strI);//���մ�ֵ
	     textField.enable(false);
	     panel2.add(textField);
	     textField.setColumns(14);  
	     
	     JPanel panel3 = new JPanel();
	     FlowLayout flowLayout_1 = (FlowLayout) panel3.getLayout();
	     flowLayout_1.setAlignment(FlowLayout.LEFT);
	     contentPane.add(panel3);    
	     JLabel label3 = new JLabel("��        �룺");
	     panel3.add(label3); 
	     passwordField1 = new JPasswordField();
	     passwordField1.setColumns(14);//����14
	     panel3.add(passwordField1);
	    
	     JPanel panel4=new JPanel();
	     FlowLayout flowLayout_2 = (FlowLayout) panel4.getLayout();
	     flowLayout_2.setAlignment(FlowLayout.LEFT);
	     contentPane.add(panel4);
	     JLabel label4=new JLabel("ȷ�����룺");
	     panel4.add(label4);
	     passwordField2=new JPasswordField();
	     passwordField2.setColumns(14);
	     panel4.add(passwordField2);
	    
	     
	     JPanel panel5 = new JPanel();     
	     contentPane.add(panel5);       
         //��button1����¼�
	     btn_sure.addActionListener((ActionListener) this);  
	     //��button2����¼�
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
			String username , password1,password2;//�����ݿ��е�����ƥ��
			username = textField.getText();
			
		    password1 = passwordField2.getText();
		    password2=passwordField1.getText();
		    username=username.trim();//ȥ���ո�
	    	password1=password1.trim();
	    	password2=password2.trim();
			String user = "sa"; //SQL�������ݿ�
			String userpwd = "123456";
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Stulearn";
		    try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"). newInstance();
				
				//JOptionPane.showInputDialog("���ݿ������ɹ���");
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
				
				    Statement stmt = con.createStatement();//��������
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
						    JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
						   
					    }
				  //   rs.close();
				     stmt.close();
				    }
				    else
				    {
				    	JOptionPane.showMessageDialog(null, "������������벻һ�������������룡");
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
			System.exit(0);//�˳�����ጷ��YԴ
			
		}
	}
}
	
	




