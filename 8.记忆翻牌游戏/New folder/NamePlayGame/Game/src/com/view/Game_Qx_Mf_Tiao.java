package com.view;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Game_Qx_Mf_Tiao extends JPanel{
	private int x,y,width,height;
	public Game_Qx_Mf_Tiao(int x,int y,int width,int height)
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	@Override
	public void paint(Graphics g)
	{
		//super.paintComponent(g);
		g.fillRect(x, y, width, height);
	}

}
