package com.view;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.control.*;
public class MonsiterThread implements Runnable{
	JPanel jp;
	Game_Play_JFrame game_Play_JFrame;
	static int x=0;
	static int y=0;
	static int i=0;
	static int j=4;
	static int zx=8;
	static int fx=10;
	
	Game_P_con game_P_con;
	int attack=0;
	ArrayList<Image> al=new ArrayList<Image>();

	
	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public int getAttack() {
		return attack;
	}
	public MonsiterThread(JPanel jpa,Game_Play_JFrame game_Play_JFrame)
	{
		this.jp=jpa;
	
		this.game_Play_JFrame=game_Play_JFrame;
		al.add(new ImageIcon("fz_image/d.png").getImage());
		al.add(new ImageIcon("fz_image/d2.png").getImage());
		al.add(new ImageIcon("fz_image/d3.png").getImage());
		al.add(new ImageIcon("fz_image/d4.png").getImage());
		
		al.add(new ImageIcon("Image/gameP1_1.png").getImage());
		al.add(new ImageIcon("Image/gameP1_2.png").getImage());
		al.add(new ImageIcon("Image/gameP1_3.png").getImage());
		al.add(new ImageIcon("Image/gameP1_4.png").getImage());
		
		al.add(new ImageIcon("Image/008.png").getImage());
		al.add(new ImageIcon("Image/009.png").getImage());
		
		al.add(new ImageIcon("fz_image/d5.png").getImage());
		al.add(new ImageIcon("fz_image/d6.png").getImage());
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true)
		{
			Random random=new Random();
			//随机面向
			int f=random.nextInt(2);
			//如果f!=1就向前走
			if(f==1)
			{
				for(int i1=0;i1<10;i1++)
				{
					//随机攻击
					int attack=random.nextInt(4);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(attack==1)
					{
						game_Play_JFrame.Move(x,y,al.get(11),1);
						int xmy=game_Play_JFrame.getXmy();
						int xdr=game_Play_JFrame.getXdr();
						int ymy=game_Play_JFrame.getYmy();
						
						
					
						if(xmy<=xdr&&xmy>=(xdr-55))
						{
							jp.remove(game_Play_JFrame.getJpone());
							game_Play_JFrame.MoveMy(xmy, ymy-383, 12);
							jp.repaint();
						}
						else
						{
							jp.repaint();
						}
					}
					else
					{
						if(i==4)
							i=0;
						x=x-5;
						game_Play_JFrame.Move(x,y,al.get(i++),0);
						jp.repaint();
					}
					
				}
			}
			else if(f==0)
			{
				for(int i2=0;i2<10;i2++)
				{
					
					int attack2=random.nextInt(4);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}

					if(attack2==2)
					{
						
						game_Play_JFrame.Move(x,y,al.get(9),2);
						int xmy2=game_Play_JFrame.getXmy();
						int xdr2=game_Play_JFrame.getXdr();
						int ymy2=game_Play_JFrame.getYmy();
						if(xmy2>=xdr2&&xmy2<=(xdr2+55))
						{
							jp.remove(game_Play_JFrame.getJpone());
							game_Play_JFrame.MoveMy(xmy2, ymy2-383, 13);
							jp.repaint();
						}
						else
						{
							jp.repaint();
						}
					}
					
					else
					{
						if(j==8)
							j=4;
						x=x+5;
						game_Play_JFrame.Move(x,y,al.get(j++),3);
						jp.repaint();
					}
					
				}
				
			}
			
			}	
		}
}
