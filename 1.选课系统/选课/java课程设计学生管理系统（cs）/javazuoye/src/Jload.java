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
    String []lx={"        ѧ            ��          ","        ��           ʦ           "};
    JButton btn_load = new JButton("��¼");
    JButton btn_cancle = new JButton("�˳�");
    JPanel titlep = new JPanel();//��������
    JPanel namep = new JPanel();//�û�������
    JPanel pwdp = new JPanel();//����������
    JPanel leixingp = new JPanel();//����������
    JPanel btnp = new JPanel();//��ť������
    public Jload()
    {
    	getContentPane().setLayout(null);
    	this.setTitle("��¼ҳ��");
    	
    	//this.textField=textField;
    	
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setBounds(100, 100, 300, 250);//���ô��ڴ�С
	    
	    contentPane = new JPanel();//�����еĿؼ��������������������
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//�ڲ��߿�Ϊ��
	    contentPane.setLayout(new GridLayout(5, 1, 5, 5));//�ֳ�5��
	    contentPane.add(titlep);
	    contentPane.add(namep);
	    contentPane.add(pwdp);
	    contentPane.add(leixingp);
	    contentPane.add(btnp);
	    
	    JLabel titlel=new JLabel("��ӭʹ��ѧ������ϵͳ��"); 
	    JLabel namel=new JLabel("ѧ  �ţ�"); 
	    JLabel pwdl=new JLabel("��  �룺"); 
	    JLabel leixingl=new JLabel("��  �ͣ�"); 
	    //leixingl.setLocation(200, 100);
	    
	    textField=new JTextField();//��ʵ����һ��textField
	    textField.setColumns(13);//���ó���
	    passwordField1=new JPasswordField();
	    passwordField1.setColumns(13);
	    jcbm=new JComboBox();
	    jcbm.addItem(lx[0]);//��jcombobox�����Ԫ��
	    jcbm.addItem(lx[1]);
	   
	    
	    titlep.add(titlel);//����������û���������
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
	    this.setResizable(false);//�����С���ɱ�
	  
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
			String xh , password;//�����ݿ��е�����ƥ��
			xh = textField.getText();
		    password = passwordField1.getText();
		    xh=xh.trim();//ȥ���ո�
	    	password=password.trim();
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
			
			if(jcbm.getSelectedIndex()==0)
			{
				try
			    {
					
					
					Connection con = DriverManager.getConnection(url, user, userpwd);
				
				    Statement stmt = con.createStatement();//��������
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
					    	
						    JOptionPane.showMessageDialog(null, "��¼ʧ��,	�����¼��Ϣ��");
						   
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
						JOptionPane.showMessageDialog(null,"��½�ɹ�");
						new Tchyemian();
						this.dispose();
					}
						else
						{
							JOptionPane.showMessageDialog(null,"��¼ʧ��,�����¼��Ϣ��");
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
			System.exit(0);//�˳�����ጷ��YԴ
			
		}
	}

}
