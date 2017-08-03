package com.stj.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.awt.AWTUtilities;


/*
 * 玩家得分进图前十名的提示对话框
 */
public class InputDialog extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton submit;  //提交按钮
	private JLabel text1;
	private JLabel text2;
	private JLabel text3;
	private JTextField field;
	private MyPanel   panel;
	private JPanel    centerPanel;
	private JPanel    northPanel;
	private JPanel    southPanel;
	
	public InputDialog(Frame frame,boolean modal,MyPanel panel)
	{
		super(frame,modal);
		this.panel = panel;
		this.submit = new JButton("提交");

		
		this.text1 = new JLabel("恭喜您进入前十!");
		this.text1.setFont(new Font("楷体",Font.BOLD,15));
		this.text1.setHorizontalAlignment(JLabel.CENTER);
		this.text1.setVerticalAlignment(JLabel.BOTTOM);
		this.text1.setPreferredSize(new Dimension(300,90));
		
		
		this.text2 = new JLabel("请输入您的姓名:");
		this.text2.setFont(new Font("楷体",Font.BOLD,12));
		this.text2.setHorizontalAlignment(JLabel.CENTER);
		this.text2.setPreferredSize(new Dimension(130,20));
		
		this.text3 = new JLabel("总分:"+ this.panel.getScore());
		this.text3.setFont(new Font("楷体",Font.BOLD,12));
		this.text3.setHorizontalAlignment(JLabel.CENTER);
		this.text3.setPreferredSize(new Dimension(300,30));
		
		
		
		this.field = new JTextField();
		this.field.setFont(new Font("楷体",Font.BOLD,15));
		this.field.setHorizontalAlignment(JLabel.CENTER);
		this.field.setPreferredSize(new Dimension(70,20));
		
		
		this.submit.setBackground(Color.orange);
		this.submit.setHorizontalAlignment(JLabel.CENTER);
		this.submit.setPreferredSize(new Dimension(80,30));
		this.submit.setForeground(new Color(61,145,64));
		this.submit.setFont(new Font(" ",0,20));
		
		this.centerPanel = new JPanel(new FlowLayout(5));
		this.centerPanel.setPreferredSize(new Dimension(200,50));
		this.centerPanel.add(this.text2);
		this.centerPanel.add(this.field);
		
		
		
		this.northPanel = new JPanel(new GridLayout(2,1));
		this.northPanel.setPreferredSize(new Dimension(300,90));
		this.northPanel.add(this.text1);
		this.northPanel.add(this.text3);
		
		

		JLabel southHelp1 = new JLabel();
		southHelp1.setPreferredSize(new Dimension(100, 50));
		
		JLabel southHelp2 = new JLabel();
		southHelp2.setPreferredSize(new Dimension(100, 50));
		
		this.southPanel = new JPanel();
		this.southPanel.setPreferredSize(new Dimension(300,70));
		this.southPanel.add(southHelp1);
		this.southPanel.add(this.submit);
		this.southPanel.add(southHelp2);
		

		 JLabel east = new JLabel();
		 east.setPreferredSize(new Dimension(20, 30));
		 JLabel west = new JLabel();
		 west.setPreferredSize(new Dimension(60, 20));
		

		Container c = this.getContentPane();

		
		c.add(this.northPanel,BorderLayout.NORTH);
		c.add(this.centerPanel,BorderLayout.CENTER);
		c.add(this.southPanel,BorderLayout.SOUTH);
		c.add(east,BorderLayout.EAST);
		c.add(west,BorderLayout.WEST);
		
		
		
		this.setContentPane(c);
		this.setLocation(frame.getBounds().x + 180,frame.getBounds().y + 200);
		this.setUndecorated(true);
		AWTUtilities.setWindowOpacity(this, 0.7F);
		this.setSize(300, 200);
		
		
		this.submit.addActionListener(
			new ActionListener()
			{

				public void actionPerformed(ActionEvent arg0)
				{
					//得到用户信息
					String name = InputDialog.this.field.getText().trim();
					String score = Integer.toString(InputDialog.this.panel.getScore());
					String pass  = Integer.toString(InputDialog.this.panel.getPass());
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					String date = df.format(new Date());// new Date()为获取当前系统时间
					String args[] = date.split(" ");
					String time  = args[0];
					
					String user = name + " " + score + " " + pass + " " + time;
					
					//将数据文件中的用户信息取出,去掉最后一名,加入玩家信息，并按降序排序
					ArrayList<String> userList = new ArrayList<String>();
					BufferedReader br = null;
					BufferedWriter bw = null;
		
					try 
					{
						 br = new BufferedReader(new FileReader("userInfo/user"));
						 String temp = null;
						 int   count = 0;
						 while((temp = br.readLine()) != null)
						 {
							 //System.out.println(temp);
							  userList.add(temp);
							  count ++;
						 }
						 
						 
						
						 for(int i = 1; i < userList.size(); i ++)
						 {
							 String[] arg = userList.get(i).split(" ");
							 int value     = Integer.parseInt(arg[1]);
							 
							 for(int position = i; position > 0; position -- )
							 {
								 String[] indexScore = userList.get(position - 1).split(" ");
								 int tempScore  = Integer.parseInt(indexScore[1]);
								 if(tempScore < value)
								 {
									 String temp1 = (String)userList.get(position);
									 userList.set(position, (String)userList.get(position - 1));
									 userList.set(position - 1, temp1);
								 }
 
							 }
						 }
						 
						 if(count >= 10)
						 {
							 userList.remove(userList.get(userList.size() - 1));
						 }
						 userList.add(user);
						 
						 for(int i = 1; i < userList.size(); i ++)
						 {
							 String[] arg = userList.get(i).split(" ");
							 int value     = Integer.parseInt(arg[1]);
							 
							 for(int position = i; position > 0; position -- )
							 {
								 String[] indexScore = userList.get(position - 1).split(" ");
								 int tempScore  = Integer.parseInt(indexScore[1]);
								 if(tempScore < value)
								 {
									 String temp1 = (String)userList.get(position);
									 userList.set(position, (String)userList.get(position - 1));
									 userList.set(position - 1, temp1);
								 }
 
							 }
						 }
						 //重新写入数据文件
						 bw = new BufferedWriter(new FileWriter("userInfo/user"));
						 for(int i = 0; i < userList.size();i ++)
						 {
							 bw.write((String)userList.get(i));
							 bw.newLine();
						 }
						 
						 
						
						
					} 
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally
					{
						if(br != null && bw != null)
						{
							try {
								br.close();
								bw.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
					
					InputDialog.this.setVisible(false);
					new WallDialog((JFrame)InputDialog.this.panel.getParent().getParent().getParent().getParent(),true,InputDialog.this.panel);
					
				}
			
			}
		);
		
		
		this.setVisiableRigeon(this.getWidth(), this.getHeight());
		this.setVisible(true);
	}
	
	public void setVisiableRigeon(int width,int height)
	{
		
		
		Shape shape = new RoundRectangle2D.Float(20,20,this.getWidth() - 40,this.getHeight()-40,15.0f,15.0f);

		AWTUtilities.setWindowShape(this,shape);
		
		
	}
}
