package mysql;

/*
 * 界面设置
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class MainFrame  implements ActionListener{

	
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	int x = 400;
	int y = 300;
	int a = 350;
	int b = 200;
	int m = 250;
	int n = 125;
	
	//������ؼ�
	JFrame main;
	Container con;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JLabel label1;
	
	//�����水ť�ؼ�
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JButton b5;
	JButton b6;
	//��������ؼ�
	
	JScrollPane scrollPane;
	JList list;
	DefaultListModel model;
	
	//������Ϣ�Ի������
	JDialog insert;
	Container con1;
	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JLabel l4;
	JLabel l5;
	JLabel l6;
	JTextField t1;
	JTextField t2;
	JTextField t3;
	JTextField t4;
	JTextField t5;
	JTextField t6;
	JButton confirm1;
	JButton cancel1;
	
	//�޸���Ϣ�Ի���
	JDialog update;
	Container con2;
	JPanel pa1;
	JPanel pa2;
	JPanel pa3;
	JPanel pa4;
	JLabel la1;
	JLabel la2;
	JLabel la3;
	JLabel la4;
	JLabel la5;
	JLabel la6;
	JTextField te1;
	JTextField te2;
	JTextField te3;
	JTextField te4;
	JTextField te5;
	JTextField te6;
	JButton confirm2;
	JButton cancel2;
	
	//�޸���Ϣǰѡ��ѧ�ŶԻ���
	JDialog updateBySno;
	Container con3;
	JPanel pan1;
	JPanel pan2;
	JTextField tex1;
	JButton confirm3;
	JButton cancel3;
	
	//ɾ����Ϣǰѡ��ѧ�ŶԻ���
	JDialog deleteBySno;
	Container con4;
	JPanel pane1;
	JPanel pane2;
	JTextField text1;
	JButton confirm4;
	JButton cancel4;
	
	//��ѯ��Ϣǰ��������ؼ��ֶԻ���
	JDialog keyword;
	Container con5;
	JPanel p01;
	JPanel p02;
	JTextField textf1;
	JButton confirm5;
	JButton cancel5;
	
	
	public MainFrame(){
		main = new JFrame("通讯录");
		con = new Container();
		con.setLayout(new FlowLayout());
		panel1 = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		panel3 = new JPanel(new FlowLayout());
		
		label1 = new JLabel("通讯录");
		b1 = new JButton("插入");
		b1.addActionListener(this);
		b2 = new JButton("123");
		b2.addActionListener(this);
		b3 = new JButton("124");
		b3.addActionListener(this);
		b4 = new JButton("132");
		b4.addActionListener(this);
		b5 = new JButton("423");
		b5.addActionListener(this);
		b6 = new JButton("4235");	
		b6.addActionListener(this);
		model = new DefaultListModel();
		list = new JList(model);
		scrollPane = new JScrollPane(list);
		list.setVisible(true);
		//���container��panel
		main.add(con);
		con.add(panel1);
		con.add(panel2);
		con.add(panel3);
		con.add(scrollPane);
		//panel��ӿؼ�
		panel1.add(label1);
		panel2.add(b1);
		panel2.add(b2);
		panel2.add(b3);
		panel3.add(b4);
		panel3.add(b5);
		panel3.add(b6);
		//�������趨
		main.setSize(x,y);
		main.setLocation((width-x)/2,(height-y)/2);
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		
		//������Ϣ����ʵ����
		insert = new JDialog(main,"对话");
		con1 = new Container();
		con1 = insert.getContentPane();
		con1.setLayout(new FlowLayout());
		p1 = new JPanel(new FlowLayout());
		p2 = new JPanel(new FlowLayout());
		p3 = new JPanel(new FlowLayout());
		p4 = new JPanel(new FlowLayout());
		l1 = new JLabel("123");
		l2 = new JLabel("24");
		l3 = new JLabel("53");
		l4 = new JLabel("5123");
		l5 = new JLabel("535");
		l6 = new JLabel("523");
		t1 = new JTextField("",10);
		t2 = new JTextField("",10);
		t3 = new JTextField("",10);
		t4 = new JTextField("",10);
		t5 = new JTextField("",10);
		t6 = new JTextField("",10);
		confirm1 = new JButton("432");
		confirm1.addActionListener(this);
		cancel1 = new JButton("6754");
		cancel1.addActionListener(this);
		p1.add(l1);
		p1.add(t1);
		p1.add(l2);
		p1.add(t2);
		p2.add(l3);
		p2.add(t3);
		p2.add(l4);
		p2.add(t4);
		p3.add(l5);
		p3.add(t5);
		p3.add(l6);
		p3.add(t6);
		p4.add(confirm1);
		p4.add(cancel1);
		con1.add(p1);
		con1.add(p2);
		con1.add(p3);
		con1.add(p4);
		insert.setSize(a,b);
		insert.setLocation((width-a)/2,(height-b)/2);
		insert.setVisible(false);
		
		//�޸���Ϣ����ʵ����
		update = new JDialog(main,"7597");
		con2 = new Container();
		con2 = update.getContentPane();
		con2.setLayout(new FlowLayout());
		pa1 = new JPanel(new FlowLayout());
		pa2 = new JPanel(new FlowLayout());
		pa3 = new JPanel(new FlowLayout());
		pa4 = new JPanel(new FlowLayout());
		la1 = new JLabel("7345");
		la2 = new JLabel("7345");
		la3 = new JLabel("854");
		la4 = new JLabel("85");
		la5 = new JLabel("854");
		la6 = new JLabel("85");
		te1 = new JTextField("",10);
		te1.setEditable(false);
		te2 = new JTextField("",10);
		te3 = new JTextField("",10);
		te4 = new JTextField("",10);
		te5 = new JTextField("",10);
		te6 = new JTextField("",10);
		confirm2 = new JButton("856");
		confirm2.addActionListener(this);
		cancel2 = new JButton("489");
		cancel2.addActionListener(this);
		pa1.add(la1);
		pa1.add(te1);
		pa1.add(la2);
		pa1.add(te2);
		pa2.add(la3);
		pa2.add(te3);
		pa2.add(la4);
		pa2.add(te4);
		pa3.add(la5);
		pa3.add(te5);
		pa3.add(la6);
		pa3.add(te6);
		pa4.add(confirm2);
		pa4.add(cancel2);
		con2.add(pa1);
		con2.add(pa2);
		con2.add(pa3);
		con2.add(pa4);
		update.setSize(a,b);
		update.setLocation((width-a)/2,(height-b)/2);
		update.setVisible(false);
		
		//�޸���Ϣǰ,ѡ��ѧ�Ž���ʵ����
		updateBySno = new JDialog(main,"48777777776945");
		con3 = new Container();
		con3 = updateBySno.getContentPane();
		con3.setLayout(new FlowLayout());
		pan1 = new JPanel(new FlowLayout());
		pan2 = new JPanel(new FlowLayout());
		tex1 = new JTextField("",20);
		confirm3 = new JButton("8567");
		confirm3.addActionListener(this);
		cancel3 = new JButton("95");
		cancel3.addActionListener(this);
		con3.add(pan1);
		con3.add(pan2);
		pan1.add(tex1);
		pan2.add(confirm3);
		pan2.add(cancel3);
		updateBySno.setSize(m,n);
		updateBySno.setLocation((width-m)/2,(height-n)/2);
		updateBySno.setVisible(false);
		
		//ɾ����Ϣǰ��ѡ��ѧ�Ž���ʵ����
		deleteBySno = new JDialog(main,"8456478");
		con4 = new Container();
		con4 = deleteBySno.getContentPane();
		con4.setLayout(new FlowLayout());
		pane1 = new JPanel(new FlowLayout());
		pane2 = new JPanel(new FlowLayout());
		text1 = new JTextField("",20);
		confirm4 = new JButton("ȷ��");
		confirm4.addActionListener(this);
		cancel4 = new JButton("ȡ��");
		cancel4.addActionListener(this);
		con4.add(pane1);
		con4.add(pane2);
		pane1.add(text1);
		pane2.add(confirm4);
		pane2.add(cancel4);
		deleteBySno.setSize(m,n);
		deleteBySno.setLocation((width-m)/2,(height-n)/2);
		deleteBySno.setVisible(false);
		
		
		//��ѯ��Ϣǰ����������ؼ��ֶԻ��� ʵ����
		keyword = new JDialog(main,"78596790");
		con5 = new Container();
		con5 = keyword.getContentPane();
		con5.setLayout(new FlowLayout());
		p01 = new JPanel(new FlowLayout());
		p02 = new JPanel(new FlowLayout());
		textf1 = new JTextField("",20);
		confirm5 = new JButton("967");
		confirm5.addActionListener(this);
		cancel5 = new JButton("056");
		cancel5.addActionListener(this);
		con5.add(p01);
		con5.add(p02);
		p01.add(textf1);
		p02.add(confirm5);
		p02.add(cancel5);
		keyword.setSize(m,n);
		keyword.setLocation((width-m)/2,(height-n)/2);
		keyword.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Student insertStu = new Student();
//		Student updateBySnoStu = new Student();
		Student updateStu = new Student();
		int snoForUpdate;
		int snoForDelete;
		
		//������Ϣ,����������Ϣ�Ի���
		if(e.getSource() == b1)
		{
			insert.setVisible(true);
		}
		//ȷ��������Ϣ
		if(e.getSource() == confirm1)
		{
			insert.setVisible(false);
			insertStu.setSno(Integer.parseInt(t1.getText()));
			insertStu.setName(t2.getText());
			insertStu.setSex(t3.getText());
			insertStu.setAge(Integer.parseInt(t4.getText()));
			try
			{
				insertStu.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(t5.getText()));
			}
			catch(ParseException ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "85684");
			}
			insertStu.setPhoneNum(t6.getText());
			try
			{
				DAOfactory.getStudentDAOInstance().doCreate(insertStu);
				JOptionPane.showMessageDialog(null, "9687-078");
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "0689-87");
			}
		}
		//ȡ��������Ϣ������������Ϣ�Ի���
		if(e.getSource() == cancel1)
		{
			insert.setVisible(false);
		}
		
		//�޸���Ϣ������ѡ��ѧ�ŶԻ���
		if(e.getSource() == b2)
		{
			updateBySno.setVisible(true);
		}
		
		//ȷ��ѡ��ѧ�ţ������޸���Ϣ�Ի���
		if(e.getSource() == confirm3)
		{
			snoForUpdate = Integer.parseInt(tex1.getText());
			try
			{
				updateStu = DAOfactory.getStudentDAOInstance().findById(snoForUpdate);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			if(updateStu != null)
			{
				updateBySno.setVisible(false);
				te1.setText(updateStu.getSno()+"");
				update.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "95896790");
			}
		}
		//ȡ��ѡ��ѧ�ţ�����ѡ��ѧ�ŶԻ���
		if(e.getSource() == cancel3)
		{
			updateBySno.setVisible(false);
		}
		
		//ȷ���޸���Ϣ
		if(e.getSource() == confirm2)
		{
			update.setVisible(false);
			
			updateStu.setSno(Integer.parseInt(te1.getText()));			
			updateStu.setName(te2.getText());
			updateStu.setSex(te3.getText());
			updateStu.setAge(Integer.parseInt(te4.getText()));
			try
			{
				updateStu.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(te5.getText()));
			}
			catch(ParseException ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "05789657");
			}
			updateStu.setPhoneNum(te6.getText());
			try
			{
				DAOfactory.getStudentDAOInstance().doUpdate(updateStu);
				JOptionPane.showMessageDialog(null, "0678690");
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "9567859");
			}
		}
		//ȡ���޸���Ϣ
		if(e.getSource() == cancel2)
		{
			update.setVisible(false);
		}
		
		//ɾ����Ϣ������ѡ��ѧ�ŶԻ���
		if(e.getSource() == b3)
		{
			deleteBySno.setVisible(true);
		}
		if(e.getSource() == confirm4)
		{
			deleteBySno.setVisible(false);
			snoForDelete = Integer.parseInt(text1.getText());
			try
			{
				DAOfactory.getStudentDAOInstance().doDelete(snoForDelete);
				JOptionPane.showMessageDialog(null, "95789");
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "59789");
			}
		}
		if(e.getSource() == cancel4)
		{
			deleteBySno.setVisible(false);
		}
		
		
		if(e.getSource() == b4)
		{
			List<Student> all = null;
			try
			{
				all = DAOfactory.getStudentDAOInstance().findAll("");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			Iterator<Student> iter = all.iterator();
			while (iter.hasNext())
			{
				Student stu = iter.next();
				model.addElement("4968:"+stu.getSno()+"9567:"+stu.getName()+"84565656565:"+stu.getSex()+"6234645:"+stu.getAge());
				model.addElement("967:"+stu.getBirthday()+"8468:"+stu.getPhoneNum());
				model.addElement(" ");
			}
		}
		
		if(e.getSource() == b5)
		{
			keyword.setVisible(true);
		}
		if(e.getSource() == confirm5)
		{
			keyword.setVisible(false);
			List<Student> all = null;
			String kw = textf1.getText();
			try
			{
				all = DAOfactory.getStudentDAOInstance().findAll(kw);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			Iterator<Student> iter = all.iterator();
			while (iter.hasNext())
			{
				Student stu = iter.next();
				model.addElement("432:"+stu.getSno()+"5123:"+stu.getName()+"123:"+stu.getSex()+"523:"+stu.getAge());
				model.addElement("52512:"+stu.getBirthday()+"5132:"+stu.getPhoneNum());
				model.addElement(" ");
			}
		}
		if(e.getSource() == cancel5)
		{
			keyword.setVisible(false);
		}
		
		if(e.getSource() == b6)
		{
			JOptionPane.showMessageDialog(null, "ллʹ�ã�");
			System.exit(0);
		}
		
	}
	
	
}
