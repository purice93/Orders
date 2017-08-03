package com.stj.views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


/*
 * ��Ϸ����
 */
public class MyFrame extends JFrame implements Observer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyPanel panel;      //��Ϸ����
	private MainPanel mainpanel; //��ʼ����
	private JMenuBar bar;        //�˵���     
	private JMenu    menu1;      //�˵�1,2         
	private JMenu    menu2;
	protected boolean  isStart;
	protected boolean  isExit;
	
	
	private JMenuItem item1;    //�˵���1,2,3,4,5
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
		this.menu1 = new JMenu("�˵�");
		this.menu2 = new JMenu("����");
		
		this.item1 = new JMenuItem("��ʼ");
		this.item2 = new JMenuItem("��ͣ");
		this.item3 = new JMenuItem("�˳�");
		this.item4 = new JMenuItem("��������");
		this.item5 = new JMenuItem("��Ϸ����");
		
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

//��Ϸ��ʼ���л�����Ϸ������
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
