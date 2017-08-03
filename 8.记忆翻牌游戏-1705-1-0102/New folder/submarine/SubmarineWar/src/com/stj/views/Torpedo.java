package com.stj.views;

import java.awt.*;
import javax.swing.*;

/*
 * 潜艇发射的鱼雷对象 
 */

public class Torpedo implements Runnable 
{
	private MyPanel panel;
	private WarShip ship;
	private Submarine sm;
	private int     weight = 5;  //默认长度和宽度 参数来自图片
	private int     height = 5;
	private int     X;   //位置x,y
	private int     Y;
	private int     dy = 1;  //移动距离
	public   boolean flag = false;  //运行标记
	//public  static boolean  blastFlag = false;
	private Image image;
	private Blast blast;
	
	public Torpedo(MyPanel p, WarShip ws,Submarine sm)
	{
		this.panel = p;
		this.ship  = ws;
		this.sm    = sm;
		
		image = Toolkit.getDefaultToolkit().getImage("imgs/鱼雷.png");
		image = new ImageIcon(image).getImage();
		this.weight = image.getWidth(panel);
		this.height = image.getHeight(panel);
		
		this.X     = this.sm.getX() + this.weight/ 2;
		this.Y     = this.sm.getY();

	}
	public void drawTorpedo(Graphics2D g)
	{
		g.drawImage(image, this.X, this.Y, panel);
	}
	
	public void upMove()
	{
		this.Y -= this.dy;
		if(this.Y <= 150)
		{
			this.hitting();
			this.flag = true;
		}
		if(this.sm.flag == true)
		{
			if(this.Y < 150)
			{
				this.flag = true;
			}
		}
	}
	
	public void run() 
	{
		
		
			while(!flag)
			{
				this.upMove();
				
				
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
				catch(Exception e)
				{
					
					e.printStackTrace();
					this.flag = true;
				}
			}
		}
	

	
	//判断是否击中战舰
	public void hitting()
	{
		if(this.X > (this.ship.getBeginX() - this.weight) && this.X < (this.ship.getBeginX() + this.ship.getWidth()-this.getWeight()))
		{
		
			int num = this.panel.getLiveNum();
			
			blast  = new Blast(this.ship.getBeginX() + this.ship.getWidth() /2, this.ship.getBeginY() + this.ship.getHeight() / 2);
			Thread t = new Thread(blast);
			this.panel.getBlastArray().add(blast);
			t.start();
			num --;
			this.panel.setLiveNum(num);
			this.panel.loseGmae();

		}
	}
	public MyPanel getPanel() {
		return panel;
	}
	public void setPanel(MyPanel panel) {
		this.panel = panel;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	public int getDy() {
		return dy;
	}
	public void setDy(int dy) {
		this.dy = dy;
	}
	public Blast getBlast() {
		return blast;
	}
	public void setBlast(Blast blast) {
		this.blast = blast;
	}

	
}
