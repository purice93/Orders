package com.stj.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;

public class WallDialog extends JDialog 
{
	/**
	 * 玩家进入前十名之后的成绩排名信息对话框
	 */
	private static final long serialVersionUID = 1L;
	private MyPanel panel;
	private JPanel  north;
	private JPanel  south;
	private JLabel[][] labels = new JLabel[11][5];
	private JLabel  help1;
	private JLabel  help2;
	private JLabel  help3;
	private JButton replay;
	private JButton cancel;
	
	public WallDialog(Frame frame,boolean modal,MyPanel panel)
	{
		super(frame,modal);
		this.panel = panel;
		
		this.north = new JPanel(new GridLayout(11,5));
		this.north.setPreferredSize(new Dimension(500,300));
		//从文件中读出数据信息并放入面板上面
		
		String[] names = {"排  名","姓  名","得  分","通 关 数","日  期"};
		for(int m = 0; m < 11; m ++)
		{
			for(int n = 0; n < 5; n ++)
			{
				labels[m][n] = new JLabel();
				labels[m][n].setHorizontalAlignment(JLabel.CENTER);
				labels[m][n].setVerticalAlignment(JLabel.CENTER);
				labels[m][n].setPreferredSize(new Dimension(30,100));
				this.north.add(labels[m][n]);
				if(m == 0)
				{
					labels[m][n].setFont(new Font("楷体",Font.BOLD,18));
					labels[m][n].setText(names[n]);
				}
			}
		}
		
		BufferedReader br = null;
		String temp = null;
		int i = 1;
		try 
		{
			br = new BufferedReader(new FileReader("userInfo/user"));
			while((temp = br.readLine()) != null)
			{
				String[] userTemp = temp.split(" ");
				for(int j = 0; j < 5; j ++)
				{
					if(j == 0)
					{
						String sort = String.valueOf(i);
						labels[i][j].setText(sort);
					}
					else if(j > 0)
					{
						 labels[i][j].setText(userTemp[j - 1]);
					}
				   

				}
				i ++;
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(br != null)
			{
				try 
				{
					br.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}

		
		this.south = new JPanel(new FlowLayout(20));
		this.south.setPreferredSize(new Dimension(500,50));
		//this.south.setBackground(Color.blue);
		this.help1 = new JLabel();
		this.help1.setPreferredSize(new Dimension(120,50));
		this.help2 = new JLabel();
		this.help2.setPreferredSize(new Dimension(120,50));
		this.help3 = new JLabel();
		this.help3.setPreferredSize(new Dimension(120,50));
		
		this.replay = new JButton("重玩");
		this.replay.setPreferredSize(new Dimension(70,30));
		this.replay.setBackground(Color.yellow);
		
		this.cancel = new JButton("退出");
		this.cancel.setPreferredSize(new Dimension(70,30));
		this.cancel.setBackground(Color.yellow);
		
		this.south.add(this.help1);
		this.south.add(this.replay);
		this.south.add(this.help2);
		this.south.add(this.cancel);
		this.south.add(this.help3);
		
		
		Container c = this.getContentPane();

		
		c.add(north,BorderLayout.CENTER);
		c.add(south,BorderLayout.SOUTH);
		
		
		this.setContentPane(c);
		this.setLocation(frame.getBounds().x + 60,frame.getBounds().y + 150);
		this.setUndecorated(true);
		AWTUtilities.setWindowOpacity(this, 0.7F);
		this.setSize(500, 350);
		
		
		this.replay.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						WallDialog.this.panel.startGame();
						WallDialog.this.panel.setSuspendFlag(false);
						//ScoreDialog.this.panel.updateScreen();
						WallDialog.this.setVisible(false);
						WallDialog.this.panel.requestFocus();
					}
					
				}
			
			);
		
		this.cancel.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						System.exit(0);
					}
					
				}
			
			);
		
		this.setVisible(true);
	}
}
