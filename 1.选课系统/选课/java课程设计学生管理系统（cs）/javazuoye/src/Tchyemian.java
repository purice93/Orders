import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Tchyemian extends JFrame implements ActionListener {

	JLabel jlb=new JLabel();
	ImageIcon img=new ImageIcon("./image/tch.png");
	
	Container cont=new Container();//������
	JMenuBar tchcomtentpane = new JMenuBar();//��Ų˵���
	JMenu tchlookbox = new JMenu("����");
	JMenu tchaddbox = new JMenu("���");
	JMenu looksc = new JMenu("�鿴ѧ���ɼ�");
	
	//JMenuItem looksc = new JMenuItem("�鿴�ɼ�");
	JMenuItem lookmsg = new JMenuItem("�鿴��Ϣ");
	JMenuItem back = new JMenuItem("����");
	JMenuItem addsc = new JMenuItem("¼��ɼ�");
	JMenuItem addmsg = new JMenuItem("¼�������Ϣ");
	JMenuItem tchexit = new JMenuItem("�˳�");
	JMenuItem look1 = new JMenuItem("�鿴2011~2012ѧ���ɼ�");
	JMenuItem look2 = new JMenuItem("�鿴2010~2011ѧ���ɼ�");
	
	public Tchyemian()
	{
		super("ѧ������");
		setLayout(null);//ȡ�����ֹ����������Ⲽ��
		this.setSize(400,300);
		this.setLocation(200,200);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(tchcomtentpane);
		cont=getContentPane();//��ȡ����
		jlb.setIcon(img);
		jlb.setSize(img.getIconWidth(),img.getIconHeight());
		cont.add(jlb);
		
		
		tchcomtentpane.add(tchlookbox);
		tchcomtentpane.add(tchaddbox);
		tchcomtentpane.add(tchexit);
		tchcomtentpane.add(looksc);
	//	comtentpane.add(jpl);
	//	tchlookbox.add(looksc);
		
	//	tchlookbox.add(lookmsg);
		tchlookbox.add(back);
		tchlookbox.add(tchexit);
		tchaddbox.add(addsc);
		tchaddbox.add(addmsg);
		looksc.add(look1);
		looksc.add(look2);
		looksc.add(lookmsg);
		look1.addActionListener(this);//��Ӽ���
		look2.addActionListener(this);//��Ӽ���
		lookmsg.addActionListener(this);
		tchexit.addActionListener(this);
	    back.addActionListener(this);
	    addsc.addActionListener(this);
	    addmsg.addActionListener(this);
	  //  tchexit.addActionListener(this);
		
	}
	
	public void tchye()
	{
		new Tchyemian();
	}

/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tchyemian();
	}*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==look1)
		{
			new Scserchone();
		}
		if(e.getSource()==look2)
		{
			new Scserchtwo();
		}
		if(e.getSource()==lookmsg)
		{
			new Tchlookinfo();
		}
		if(e.getSource()==tchexit)
		{
			System.exit(0);
		}
		if(e.getSource()==back)
		{
			new Jload();
			this.dispose();
					
	     }
		if(e.getSource()==addsc)
		{
			new Tchaddsc();
		}
		if(e.getSource()==addmsg)
		{
			new Tchaddinfo();
		}
		
	}
}
