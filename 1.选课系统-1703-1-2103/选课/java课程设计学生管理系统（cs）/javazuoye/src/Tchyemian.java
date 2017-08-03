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
	
	Container cont=new Container();//总容器
	JMenuBar tchcomtentpane = new JMenuBar();//存放菜单栏
	JMenu tchlookbox = new JMenu("操作");
	JMenu tchaddbox = new JMenu("添加");
	JMenu looksc = new JMenu("查看学生成绩");
	
	//JMenuItem looksc = new JMenuItem("查看成绩");
	JMenuItem lookmsg = new JMenuItem("查看信息");
	JMenuItem back = new JMenuItem("返回");
	JMenuItem addsc = new JMenuItem("录入成绩");
	JMenuItem addmsg = new JMenuItem("录入基本信息");
	JMenuItem tchexit = new JMenuItem("退出");
	JMenuItem look1 = new JMenuItem("查看2011~2012学生成绩");
	JMenuItem look2 = new JMenuItem("查看2010~2011学生成绩");
	
	public Tchyemian()
	{
		super("学生界面");
		setLayout(null);//取消布局管理器，任意布局
		this.setSize(400,300);
		this.setLocation(200,200);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(tchcomtentpane);
		cont=getContentPane();//获取容器
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
		look1.addActionListener(this);//添加监视
		look2.addActionListener(this);//添加监视
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
		// TODO 自动生成的方法存根
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
