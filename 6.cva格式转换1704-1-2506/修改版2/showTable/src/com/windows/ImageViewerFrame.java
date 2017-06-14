package com.windows;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ImageViewerFrame extends JFrame {

	private JLabel label;
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;

	public ImageViewerFrame() {
		FlowLayout  flow=new FlowLayout( );
		this.setLayout(flow);
		
		setTitle("ImageViewer");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu menu = new JMenu("截图选择");
		menubar.add(menu);
		JMenuItem picItem1 = new JMenuItem("原始文件截图");
		menu.add(picItem1);
		JMenuItem picItem2 = new JMenuItem("操作流程表截图");
		menu.add(picItem2);
		JMenuItem picItem3 = new JMenuItem("有成绩表截图");
		menu.add(picItem3);
		JMenuItem picItem4 = new JMenuItem("无成绩表截图");
		menu.add(picItem4);

		label = new JLabel();
		add(label);
		
		picItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = "img/1.png";
				ImageIcon image = new ImageIcon(name);
				label.setIcon(image);
				setSize(image.getIconWidth(), image.getIconHeight()+20);
			}
		});
		picItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = "img/2.png";
				ImageIcon image = new ImageIcon(name);
				label.setIcon(image);
				setSize(image.getIconWidth(), image.getIconHeight()+20);
			}
		});
		picItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = "img/3.png";
				ImageIcon image = new ImageIcon(name);
				label.setIcon(image);
				setSize(image.getIconWidth(), image.getIconHeight()+20);
			}
		});
		picItem4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = "img/4.png";
				ImageIcon image = new ImageIcon(name);
				label.setIcon(image);
				setSize(image.getIconWidth(), image.getIconHeight()+20);
			}
		});
	}
}
