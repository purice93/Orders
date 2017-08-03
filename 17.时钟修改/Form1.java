import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Toolkit;

import javax.swing.JRadioButton;
import javax.swing.JTabbedPane; 
public class Form1 {
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            /*invokelater 使事件派发线程上的可运行对象排队。当可运行对象排在事件派发队列的队首时，就调用其run方法。
             * 其效果是允许事件派发线程调用另一个线程中的任意一个代码块。
             * 只有从事件派发线程才能更新组件。 */
			public void run() {
				try {
					Form1 window = new Form1();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
                /*try catch语句，抛出异常
                 /*System.out.println(e);这个方法打印出异常，并且输出在哪里出现的异常；
                 * e.printStackTrace()也是打印出异常，但是它还将显示出更深的调用信息。比如说：
                 * A extends ---> B extends---> C
                 当在创建A的过程中出现问题了，我们抛出异常。<
                 System.out.println(e)，除了标准异常外，只打印at A 然后再向外层层输出。
                 e.printStackTrace()，除了标准异常外，打印
                 at C
                 at B
                 at A
                 .......再向外层调查。
                 在向外层调查的情况下，都一样。最后都会回到com.sun.midp.main.Main.main
                 */
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public Form1() throws IOException, InterruptedException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private void initialize() throws IOException, InterruptedException {
		//每个新建JLabel请setFont(Global.MSYH)
		
		//主窗体
		frame = new JFrame();
		frame.setBounds(10, 10, 1000, 745);//坐标，长宽
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);//s设置布局，因为没有选择任何布局所以是null
		frame.setTitle("Magical Clock");
		frame.setIconImage(Toolkit.getDefaultToolkit ().getImage ("D:\\8.jpg"));
		
		//钟面
		ClockPanel clPanel = new ClockPanel();
		clPanel.setLayout(null);
		clPanel.setBounds(50, 100, 200, 200);
		frame.getContentPane().add(clPanel);
		
		//标签框
		//tabbedPane.setBounds(318, 50, 248, 474);
		
		//时间格式单选框
		ButtonGroup bg = new ButtonGroup();
		JRadioButton rB_12 = new JRadioButton("12");
		rB_12.setBounds(88, 600, 77, 23);
		rB_12.setFont(Global.MSYH);
		frame.getContentPane().add(rB_12);
		rB_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.ClockUpdate();
			}
		});
		JRadioButton rB_24 = new JRadioButton("24");
		rB_24.setBounds(158, 600, 70, 23);
		rB_24.setFont(Global.MSYH);
		frame.getContentPane().add(rB_24);
		rB_24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.ClockUpdate();
			}
		});
		bg.add(rB_12);
		bg.add(rB_24);
		Global.NRB12 = rB_12;
		rB_24.setSelected(true);
		
		//世界时间
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		panel.setBounds(420, 20, 240, 400);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		LabelsPanel[] worldPanel = new LabelsPanel[6];
		Global.TPanels = worldPanel;
		String[] cities = {"London", "Paris", "Tokyo", "New York", "Moscow", "Sydney"};

		JLabel  t1 = new JLabel ("Global Time");
		t1.setFont(new Font("Georgia", 1, 16));
		//t1.setFont(new Font("Cursive", 1, 12));
		t1.setBounds(20, 40, 200, 45);
		panel.add(t1);
		for (int i=0;i<6;i++) {
			worldPanel[i] = new LabelsPanel(1);
			worldPanel[i].setLayout(null);
			worldPanel[i].setBounds(20, 50*i+90, 200, 45);
			panel.add(worldPanel[i]);
			
			JLabel lbl = new JLabel(cities[i]);
			lbl.setFont(Global.MSYH);
			lbl.setBounds(10, 15, 60, 25);
			worldPanel[i].add(lbl);
		
			JLabel label_wt = new JLabel();
			label_wt.setFont(Global.MSYH);			
			label_wt.setBounds(80, 15, 160, 25);
			worldPanel[i].ls[0] = label_wt;
			worldPanel[i].add(label_wt);
		}
		
		//闹钟标签
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		panel_1.setBounds(700, 20, 240, 400);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		Global.alarmContainer = panel_1;	


		JLabel  t2 = new JLabel ("Alarm Clock");
		t2.setFont(new Font("Georgia", 1, 16));
		t2.setBounds(20, 40, 200, 45);
		panel_1.add(t2);
		JTextPane[] arSets = new JTextPane[2];
		for (int i=0;i<2;i++){
			arSets[i] = new JTextPane();
			arSets[i].setBounds(40+55*i, 358, 40, 21);
			arSets[i].setFont(Global.MSYH);
			arSets[i].setLayout(null);
			panel_1.add(arSets[i]);
		}
		arSets[0].setText("Hour");
		arSets[1].setText("Min");
		JButton btnAdd = new JButton("Add Alarm");//闹钟设定按钮		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Global.alarmList.size()>=6) {
					JOptionPane.showMessageDialog(new JFrame(),"6 alarms at most are supported.");
					return;
				}
				try {
					Calendar t = Calendar.getInstance();				
					t.set(Global.NCar.get(Calendar.YEAR),Global.NCar.get(Calendar.MONTH),Global.NCar.get(Calendar.DAY_OF_MONTH),Integer.parseInt(arSets[0].getText()),Integer.parseInt(arSets[1].getText()),0);
					if (!t.getTime().after(Global.NCar.getTime())) {
						t.add(Calendar.DAY_OF_MONTH, 1);
					}
					AlarmPanel.addAlarm(t.getTime());
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(new JFrame(),"Invalid value of time!");
				}
			}
		});
		btnAdd.setBounds(140, 358, 93, 23);
		panel_1.add(btnAdd);
		
		//倒计时		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(420, 400, 600, 400);
		panel_3.setLayout(null);
		JLabel  t3 = new JLabel ("CountDown");
		t3.setFont(new Font("Georgia", 1, 16));
		t3.setBounds(20, 40, 200, 45);
		panel_3.add(t3);
		//tabbedPane.addTab("Countdown", null, panel_3, null);
		frame.getContentPane().add(panel_3);
		Countdown cd = new Countdown();
		cd.setLayout(null);
		cd.setBounds(0,0,430,400);
		panel_3.add(cd);
		
		
		//主时间设定框

		JLabel  t4 = new JLabel ("Digital Clock");
		t4.setFont(new Font("Georgia", 1, 16));
		t4.setBounds(440, 440, 200, 45);
		frame.getContentPane().add(t4);	
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(Global.MSYH);
		textPane.setBounds(40, 480, 40, 21);
		frame.getContentPane().add(textPane);		
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setFont(Global.MSYH);
		textPane_1.setBounds(110, 480, 40, 21);
		frame.getContentPane().add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setFont(Global.MSYH);
		textPane_2.setBounds(180, 480, 40, 21);
		frame.getContentPane().add(textPane_2);
		

		JLabel timeTextItn1 = new JLabel("/");
		timeTextItn1.setFont(Global.MSYH);
		timeTextItn1.setBounds(95, 480, 40, 21);
		frame.getContentPane().add(timeTextItn1);
		
		JLabel timeTextItn2 = new JLabel("/");
		timeTextItn2.setFont(Global.MSYH);
		timeTextItn2.setBounds(165, 480, 40, 21);
		frame.getContentPane().add(timeTextItn2);
		
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setFont(Global.MSYH);
		textPane_3.setBounds(40, 540, 40, 21);
		frame.getContentPane().add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setFont(Global.MSYH);
		textPane_4.setBounds(110, 540, 40, 21);
		frame.getContentPane().add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setFont(Global.MSYH);
		textPane_5.setBounds(180, 540, 40, 21);
		frame.getContentPane().add(textPane_5);
		
		JLabel clockTextItn1 = new JLabel(":");
		clockTextItn1.setFont(Global.MSYH);
		clockTextItn1.setBounds(95, 540, 40, 21);
		frame.getContentPane().add(clockTextItn1);
		
		JLabel clockTextItn2 = new JLabel(":");
		clockTextItn2.setFont(Global.MSYH);
		clockTextItn2.setBounds(165, 540, 40, 21);
		frame.getContentPane().add(clockTextItn2);
		//
		
		//时间设定及初始化按钮
		JButton btnDefault = new JButton("Default");//初始化按钮
		btnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.NCar = Calendar.getInstance();
				Global.ClockUpdate();
			}
		});
		btnDefault.setBounds(173, 660, 93, 23);
		frame.getContentPane().add(btnDefault);
		
		JButton btnSetup = new JButton("Setup");//时间设定按钮
		btnSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String s1 = Global.TextY.getText();
				String s2 = Global.TextM.getText();
				String s3 = Global.TextD.getText();
				String s4 = Global.Texth.getText();
				String s5 = Global.Textm.getText();
				String s6 = Global.Texts.getText();//设定时间时输入的数据为字符串类型
				//将字符串转换为数字后实现时间的更改设定
				try {
					Global.NCar.set(Integer.parseInt(s1), Integer.parseInt(s2)-1, Integer.parseInt(s3), Integer.parseInt(s4), Integer.parseInt(s5), Integer.parseInt(s6));
				}
				catch (Exception x) {
					JOptionPane.showMessageDialog(new JFrame(),"Invalid value of time!");
				}
				finally {
					Global.ClockUpdate();
				}
			}
		});
		btnSetup.setBounds(41, 660, 93, 23);
		frame.getContentPane().add(btnSetup);
		//
		
		Global.NCar = Calendar.getInstance();
		Global.TextY = textPane;
		Global.TextM = textPane_1;
		Global.TextD = textPane_2;
		Global.Texth = textPane_3;
		Global.Textm = textPane_4;
		Global.Texts = textPane_5;
		
		Timer timer = new Timer();
		timer.schedule(new CalrUpdate(), 1, 1000);
		
	}
	

	static class CalrUpdate extends TimerTask{      
	    public void run(){	    	
	        Global.NCar.add(Calendar.SECOND,1);
	        Global.ClockUpdate();         //每秒钟刷新时间
	    }
	}    
}
