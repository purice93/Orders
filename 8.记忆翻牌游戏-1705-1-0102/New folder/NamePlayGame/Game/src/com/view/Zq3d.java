package com.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Zq3d extends JPanel{
	Image img;
	public Zq3d(Image image)
	{
		this.img=image;
	}
	@Override
	public void paint(Graphics g)
	{
		g.drawImage(img, 0, 0, 55, 65, null);
	}
}
