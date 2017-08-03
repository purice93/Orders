package com.csms.windows;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PublicWindowSet {
	
	//窗口大小
	public static void windowAttribute(JFrame jfr, int wide, int high, String title) {
		windowIsCenter(wide, high, jfr);
		jfr.setTitle(title);
		jfr.setSize(wide, high);
		jfr.setVisible(true);
		jfr.setResizable(false);
	}

	//中心布局
	public static void windowIsCenter(int windowWide, int windowHigh, JFrame frame) {
		int screenWide = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHigh = Toolkit.getDefaultToolkit().getScreenSize().height;
		int x = screenWide / 2 - windowWide / 2;
		int y = screenHigh / 2 - windowHigh / 2;
		frame.setLocation(x, y);
	}

	//提示面板
	public static void promptPopUp(String content, String title, JFrame jfr) {
		JOptionPane.showMessageDialog(jfr.getContentPane(), content, title, JOptionPane.INFORMATION_MESSAGE);
	}

}
