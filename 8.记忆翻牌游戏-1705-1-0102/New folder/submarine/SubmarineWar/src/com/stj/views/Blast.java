package com.stj.views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
/*
 Ǳͧ��������׻���ս����ը��Ч������,ԭ�����ƶ����С����Ȼ��Ӵ�С��Բ��ʾ��ըЧ��
 */
public class Blast implements Runnable
{
	private int X;  
	private int Y;
	private int[] r = {2,3,5,8,12,15,18,20,25,30,33,25,17,15,13,9,5}; //Բ�İ뾶����
	private int   step = 0; //������Բ��λ�ñ��
	private boolean flag = false;
	
	public Blast(int x,int y)
	{
		this.X = x;
		this.Y = y;
	}
	
	
	
	public void drawBlast(Graphics2D g)
	{
		Random random =new Random();
		int    c = random.nextInt(2);
		if(c == 0)
		{
			g.setColor( Color.RED);
		}
		else if(c == 1)
		{
			g.setColor(Color.ORANGE);
		}

		
		if(this.step < r.length)
		{
			//System.out.println("draw");
			//g.setColor(Color.gray);
			g.fillOval(this.X, this.Y, this.r[this.step], this.r[this.step]);
			//step ++;
		}
		
	
	}



		public void run() 
		{
			while(!this.flag)
			{
				step ++;
				//System.out.println(step);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(this.step == r.length)
				{
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



		public boolean isFlag() {
			return flag;
		}



		public void setFlag(boolean flag) {
			this.flag = flag;
		}
		
		
}
