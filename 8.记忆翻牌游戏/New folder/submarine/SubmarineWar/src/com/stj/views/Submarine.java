package com.stj.views;

import java.awt.*;
import java.util.Random;
import java.awt.Graphics2D;
import javax.swing.*;

/*
 * Ǳͧ����
 */
public class Submarine implements Runnable
{
	private int X;  //λ��x,y
	private int Y;
	private int dx; //�ƶ�����
	private int m;  //����:0�������� 1��������
	private WarShip ship; 
	private MyPanel panel;
	private int weight = 65; //Ĭ�ϳ��ȺͿ�ȣ���������ͼƬ��С
	private int height = 20;
	public boolean flag = false; //���б��
	private Image image;  //ͼƬ����
	//private static int   num = 0;
	
	public  Submarine(WarShip ship,MyPanel panel)
	{
		
		this.ship = ship;
		this.panel = panel;
		
		this.dx = 1;
		//�������ǱͧͼƬ���˶�����
		this.m = (int) (Math.random() * 2);
	    if(this.m == 0)
	    {
	    	Random r = new Random();
			int  num = r.nextInt(3);
			if(num == 0)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ1.png");//Toolkit.getDefaultToolkit().createImage( "C:\\1.JPG ")�����첽�ķ�ʽ����ͼƬ�����߳�ִ�е�_img.getWidth(this)���ʱ������ͼƬ���̻߳�û׼����ͼƬ���Ի᷵��-1��
				image = new ImageIcon(image).getImage();

			}
			else if(num == 1)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ2.png");
				image = new ImageIcon(image).getImage();

			}
			else if(num == 2 )
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ8.png");
				image = new ImageIcon(image).getImage();

			}
	    }
	    if(this.m == 1)
	    {
	    	Random r1 = new Random();
			int  num = r1.nextInt(4);
	    	if(num == 0 )
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ3.png");
				image = new ImageIcon(image).getImage();

			}
			else if(num == 1 )
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ4.png");
				image = new ImageIcon(image).getImage();
	
			}
			else if(num == 2 )
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ6.png");
				image = new ImageIcon(image).getImage();

			}
			else if(num == 3 )
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ7.png");
				image = new ImageIcon(image).getImage();

			}
	    }
	
	
		
		this.weight =  image.getWidth(panel);
		this.height =  image.getHeight(panel);
		
		
		if(m == 0)
		{
			this.X = this.panel.getWidth() - this.weight;

		}
		if(m == 1)
		{
			this.X = 0;

		}
		
		Random ry = new Random();
		int y1 = ry.nextInt(panel.getHeight()) + 180;

		while((y1+this.getHeight())  >= panel.getHeight())
		{
			y1 = ry.nextInt(panel.getHeight()) + 180;

		}
		this.Y = y1;
	
		//��ʱ��ÿ��һ��ʱ��������׶���
		TimeManager2 tm2 = new TimeManager2(this,this.panel,this.ship,this.panel.getTorpedoArray());
		Thread  t = new Thread(tm2);
		t.start();

	}
	
	public void drawSubmarine(Graphics2D g) 
	{
		g.drawImage(image,this.X, this.Y, panel); 
	}
	
	public void moveLeft()
	{
		//System.out.println("Ǳˮͧ�˶�");
		this.X -= dx;
		//System.out.println(this.X);
		this.panel.repaint();
		if(this.X < 0)
		{
			this.flag = true;
		}
		
	}
	
	public void moveright()
	{
		this.X += dx;
		//this.panel.repaint();
		if(this.X > this.panel.getWidth())
		{
			this.flag = true;
		}
	}
	public  void run() 
	{
		//System.out.println("�̼߳���");
		
		
			while(!flag)
			{
				//System.out.println("222");
				if(this.m == 0)
				{
					this.moveLeft();
				}
				if(this.m == 1)
				{
					this.moveright();
				}
				
				if(this.panel.isStop())
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

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
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


	
	
	
	
}
