package com.stj.views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
/*
 潜艇发射的鱼雷击中战舰后爆炸的效果对象,原理：绘制多个从小到大然后从大到小的圆显示爆炸效果
 */
public class Blast implements Runnable
{
	private int X;  
	private int Y;
	private int[] r = {2,3,5,8,12,15,18,20,25,30,33,25,17,15,13,9,5}; //圆的半径集合
	private int   step = 0; //集合中圆的位置标记
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
