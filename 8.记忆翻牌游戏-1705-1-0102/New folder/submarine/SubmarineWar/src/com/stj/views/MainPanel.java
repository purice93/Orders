package com.stj.views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.JPanel;


/*
 * 进图游戏的主界面面板
 */
public class MainPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	private MyButton startButton;
	private MyButton exitButton;
	private JLabel helpLabel;
	private JLabel helpLabel1;
	private JLabel helpLabel2;
	private JLabel helpLabel3;
	private Image    image;
	private JLabel  centerlabel;
	private boolean isStart;
	private boolean isExit;
	private Observable obs;


	public MainPanel(Observable ob)
	{
		 obs = ob;
		
		startButton = new MyButton("进入游戏");
		exitButton  = new MyButton("退出游戏");
		helpLabel  = new JLabel();
		helpLabel1  = new JLabel();
		helpLabel2  = new JLabel();
		helpLabel3  = new JLabel();
		centerlabel =  new JLabel();
		
	    this.setLayout(new BorderLayout());
	   
	    this.helpLabel.setPreferredSize(new Dimension(645,291));
	    this.helpLabel1.setPreferredSize(new Dimension(180,80));
	    this.helpLabel2.setPreferredSize(new Dimension(215,80));
	    this.helpLabel3.setPreferredSize(new Dimension(645,80));
	    this.centerlabel.setPreferredSize(new Dimension(460,80));
	    
	    centerlabel.setLayout(new GridLayout(2,1));
	    centerlabel.add(this.startButton);
	    centerlabel.add(this.exitButton);
	    
	    this.centerlabel.setBackground(new Color(255,255,0));
	    
	    this.add(helpLabel,BorderLayout.NORTH);
	    this.add(helpLabel1,BorderLayout.EAST);
	    this.add(helpLabel2,BorderLayout.WEST);
	    this.add(helpLabel3,BorderLayout.SOUTH);
	    this.add(centerlabel,BorderLayout.CENTER);
	    
	    
	    
	   image = Toolkit.getDefaultToolkit().getImage("imgs/主界面112.png"); 
	  // image = new ImageIcon(image).getImage();
	   
	   this.startButton.addActionListener(
			   new ActionListener()
			   {
				   public void actionPerformed(ActionEvent e)
				   {
					   boolean flag = true;
					   MainPanel.this.setIsStart(flag);
					  // System.out.println(MainPanel.this.getIsStart());
					   MainPanel.this.obs.notifyObservers(MainPanel.this);
					  // System.out.println("isStart");
				   }
			   }
			   );
	   
	   this.exitButton.addActionListener(
			   new ActionListener()
			   {
				   public void actionPerformed(ActionEvent e)
				   {
					  MainPanel.this.setExit(true);
				   }
			   }
			   );
	

	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;

	
	    g2.drawImage(image,0,0, this.getWidth(), this.getHeight(),this);
		
		super.paintComponents(g);
		
	}
	
	public boolean getIsStart()
	{
		return this.isStart;
	}
	
	public void setIsStart(boolean isStart)
	{
		this.isStart = isStart;
	}
	
	public boolean isExit() {
		return isExit;
	}

	public void setExit(boolean isExit) {
		this.isExit = isExit;
	}
		
}


