package com.stj.views;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

/*
 * 潜艇被击中的爆炸效果，通过图片显示
 */
public class Hit implements Runnable 
{
	private MyPanel panel;  //主面板
	private Image   image;  //图片
	private int     liveTime =  500; //爆炸效果显示的时间默认为500毫秒
	private int     beginX   =  0; //位置 x y
	private int     beginY   =  0;
	private boolean isRunning    =  false; //游戏是否正在运行标志
	
	public Hit(int x,int y,MyPanel panel)
	{
		this.beginX = x;
		this.beginY = y;
		this.panel  = panel;
		this.image  = Toolkit.getDefaultToolkit().getImage("imgs/炸弹效果.png");
		this.image  = new ImageIcon(this.image).getImage();
	}
	
	public void drawHitting(Graphics2D g)
	{
		g.drawImage(this.image, this.beginX,this.beginY,this.panel);
	}
	
	public void run()
	{
		while(!this.isRunning)
		{
			try {
				Thread.sleep(this.liveTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.isRunning = true;
		}
		
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	
}
