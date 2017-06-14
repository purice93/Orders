package com.stj.views;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

/*
 * Ǳͧ�����еı�ըЧ����ͨ��ͼƬ��ʾ
 */
public class Hit implements Runnable 
{
	private MyPanel panel;  //�����
	private Image   image;  //ͼƬ
	private int     liveTime =  500; //��ըЧ����ʾ��ʱ��Ĭ��Ϊ500����
	private int     beginX   =  0; //λ�� x y
	private int     beginY   =  0;
	private boolean isRunning    =  false; //��Ϸ�Ƿ��������б�־
	
	public Hit(int x,int y,MyPanel panel)
	{
		this.beginX = x;
		this.beginY = y;
		this.panel  = panel;
		this.image  = Toolkit.getDefaultToolkit().getImage("imgs/ը��Ч��.png");
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
