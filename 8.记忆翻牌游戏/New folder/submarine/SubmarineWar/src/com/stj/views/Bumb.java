package com.stj.views;

import java.awt.*;

import javax.swing.ImageIcon;


public class Bumb implements Runnable
{
	private WarShip ship;
	private int  beginX;
	private int  beginY;
	private int  width = 5;
	private int  height = 15;
	public  boolean flag = false;
	private  MyPanel panel;
	private Image image;

	
	public Bumb(MyPanel p,WarShip ship)
	{
		this.beginX= ship.getBeginX()+20;
		this.beginY = ship.getBeginY()+20;
		this.panel = p;
		this.ship  = ship;
		image = Toolkit.getDefaultToolkit().getImage("imgs/Õ¨µ¯.png");
		image = new ImageIcon(image).getImage();
		this.width = image.getWidth(panel);
		this.height = image.getHeight(panel);
		//System.out.println("bumb width:"+this.width);
		//System.out.println("bumb height:"+this.height);
		
	}
	public void run() 
	{
		
		
			while(!flag)
			{
				this.moveDown();
				
				
				if(this.panel.isStop() == true)
				{
						synchronized(MyPanel.subLock)
						{
							try
							{
								MyPanel.subLock.wait();
							}
							catch(Exception e)
							{
								e.printStackTrace();
								this.flag = true;
							}
						}
					
				}
				
			
				try
				{
					Thread.sleep(10);
				}
				catch(Exception event)
				{
					event.printStackTrace();
					this.flag = true;
				}
			}
		}
	

		
	
	

	
	public void drawBumb(Graphics2D g)
	{
		//g.setColor(Color.BLACK);
		//g.fillOval(this.beginX, this.beginY, this.radius, this.radius);
		g.drawImage(image, this.beginX, this.beginY,  this.panel);
		
	}

	
	public void moveDown()
	{
		
		this.beginY += 1;
		if(this.beginY > (this.panel.getHeight() - this.height / 2))
		{
			flag = true;
		}
		//this.panel.repaint();
	}
	

	
	public WarShip getShip() {
		return ship;
	}
	public void setShip(WarShip ship) {
		this.ship = ship;
	}
	public int getBeginX() {
		return beginX;
	}
	public void setBeginX(int beginX) {
		this.beginX = beginX;
	}
	public int getBeginY() {
		return beginY;
	}
	public void setBeginY(int beginY) {
		this.beginY = beginY;
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



}
