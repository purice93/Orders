package com.stj.views;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * 进入游戏面板中用到的圆角矩形按钮对象
 */


import javax.swing.JButton;

public class MyButton extends JButton implements ActionListener,MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
	private int   width  = 250;
	private int   height = 40;
	private String name;
	private int flag ;
	
	public MyButton(String name) 
	{
		this.setWidth(230);
		this.setHeight(40);
		this.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		this.name = name;
		this.setHorizontalAlignment(JButton.CENTER);
		this.setContentAreaFilled(false);
		this.addActionListener(this);
		this.addMouseListener(this);
		this.flag = 0;
	}
	
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(flag == 0)
		{
			g2.setColor(new Color(0,0,139));
		}
		if(flag == 1)
		{
			g2.setColor(new Color(25,25,112));
		}
		if(flag == 2)
		{
			//g.setColor(new Color(175,239,255));
			g2.setColor(new Color(150,188,188));
		}
		
		g2.fillRoundRect(0, 0, this.getWidth()-1 , this.getHeight()-1, 10, 10);
		//g2.setColor(new Color(36,49,100));
		g2.setColor(new Color(218,218,0));
		Font font = new Font("楷体",Font.BOLD,28);
		g2.setFont(font);
		g2.drawString(this.name, 62, 30);
		
	}
	
	public void paintBorder(Graphics g)
	{
		g.setColor(Color.gray);
		g.drawRoundRect(0, 0,this.getWidth()-1 , this.getHeight()-1, 10, 10);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void actionPerformed(ActionEvent arg0)
	{
		//this.flag = true;
		
	}

	public void mouseClicked(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0)
	{
		this.flag = 2;
		
	}

	public void mouseExited(MouseEvent arg0) {
		this.flag = 0;
		
	}

	public void mousePressed(MouseEvent arg0)
	{
		this.flag = 1;
		
	}

	public void mouseReleased(MouseEvent arg0) 
	{
		this.flag = 0;
		
	}
	
	
	
}