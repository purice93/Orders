package com.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Person_Draw extends JPanel{
	private int x,y,w,h;
	private Image image;
	public Person_Draw(int x,int y,int w,int h,Image image)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.image=image;
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.drawImage(image, x,y, w,h,null);
	}

}
