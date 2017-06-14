package com.stj.views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


/*
 * 游戏窗体
 */
public class MyFrame extends JFrame implements Observer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyPanel panel;      //游戏界面
	private MainPanel mainpanel; //开始界面
	private JMenuBar bar;        //菜单栏     
	private JMenu    menu1;      //菜单1,2         
	private JMenu    menu2;
	protected boolean  isStart;
	protected boolean  isExit;
	
	
	private JMenuItem item1;    //菜单项1,2,3,4,5
	private JMenuItem item2;
	private JMenuItem item3;
	private JMenuItem item4;
	private JMenuItem item5;
	
	public MyFrame(Observable ob)
	{
		ob.addObserver(this);
		Observable obs = ob;
		
		this.panel = new MyPanel();
		this.mainpanel = new MainPanel(obs);
		this.isStart = mainpanel.getIsStart();
		this.isExit  = mainpanel.isExit();

	}
	

	public void showMyFrame()
	{
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		this.bar = new JMenuBar();
		this.menu1 = new JMenu("菜单");
		this.menu2 = new JMenu("帮助");
		
		this.item1 = new JMenuItem("开始");
		this.item2 = new JMenuItem("暂停");
		this.item3 = new JMenuItem("退出");
		this.item4 = new JMenuItem("关于我们");
		this.item5 = new JMenuItem("游戏规则");
		
		this.menu1.add(item1);
		this.menu1.add(item2);
		this.menu1.add(item3);
		this.menu2.add(item4);
		this.menu2.add(item5);
		
		bar.add(menu1);
		bar.add(menu2);
		this.bar.setVisible(false);
		this.setJMenuBar(this.bar);
		
		item1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				MyFrame.this.panel.requestFocus();
				
		
				MyFrame.this.panel.startGame();
			
				
				
			}
		}
		);
		
		item2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MyFrame.this.panel.stopGame();
				
			}
		}
		);
		
		item3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
				
			}
		}
		);
		
		item4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				//MyFrame.this.panel.setFocusable(false);
				MyFrame.this.panel.setSuspendFlag(true);
				new InfoDialog(MyFrame.this,true,MyFrame.this.panel);
				
			}
		}
		);
		
		item5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//MyFrame.this.panel.setFocusable(false);
				MyFrame.this.panel.setSuspendFlag(true);
				new HelpDialog(MyFrame.this,true,MyFrame.this.panel);

			}
		}
		);
		c.add(this.mainpanel,BorderLayout.CENTER);
		
		this.setSize(645,511);
		this.setVisible(true);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
	}
	
	
	public void changedPanel()
	{
		
	}

//游戏开始，切换到游戏主界面
	public void update(Observable arg0, Object arg1) 
	{
		MainPanel panel = (MainPanel) arg1;
		//System.out.println("update" + panel.getIsStart());
		if(panel.getIsStart())
		{
			//System.out.println("update" + panel.getIsStart());
			this.getContentPane().remove(this.mainpanel);
			//this.panel.setPreferredSize(new Dimension(645,511));

			this.bar.setVisible(true);
			this.getContentPane().add(this.panel, BorderLayout.CENTER);
			this.repaint();
		}
	}

	
	
}
