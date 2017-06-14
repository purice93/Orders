package org.lxh.demo;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.Box;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import com.iflytek.speech.SpeechError;
import com.iflytek.speech.SynthesizerPlayer;
import com.iflytek.speech.SynthesizerPlayerListener;
import com.iflytek.util.Version;

import java.io.*;
import jxl.*;
import jxl.write.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

public class Revise {
	public JTextField[] SInfo;
	private JLabel[] InfoLabel;
	public JRadioButton[] Absent;
	public double absent;//返回出席情况
	public String clsxls;
	public String sno;
	public String sname;
	public String sdept;
	public int rowNum;
	public int time_Column;
	public JLabel aLabel;
	public JButton Certain;
	public int sumStudent;
	
	
    public  void create(int column,String classxls,int sumStudentNum)
     {	
     	sumStudent=sumStudentNum;
     	clsxls=classxls;
 	    time_Column=column;
     	
    	JFrame app=new JFrame("修改记录");
    	app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	app.setSize(500,600);
    	app.setLocationRelativeTo(null);
    	Container c=app.getContentPane();
    	c.setLayout(new FlowLayout(FlowLayout.CENTER));
    	ImageIcon image=new ImageIcon("头像\\0.jpg");
        aLabel=new JLabel(image);
        c.add(aLabel);  
        c.add(Box.createHorizontalStrut(1000));
    	c.add(Box.createHorizontalStrut(1000));
    	SInfo=new JTextField[3];
    	SInfo[0]=new JTextField("",10);
    	SInfo[1]=new JTextField("",10);
    	SInfo[2]=new JTextField("",10);
    	
    	SInfo[0].setEditable(true);
    	SInfo[1].setEditable(false);
    	SInfo[2].setEditable(false);
    	
    	InfoLabel=new JLabel[4];
    	InfoLabel[0]=new JLabel("学号:");
    	InfoLabel[0].setForeground(Color.green);
    	InfoLabel[1]=new JLabel("姓名:");
    	InfoLabel[1].setForeground(Color.green);
    	InfoLabel[2]=new JLabel("专业:");
    	InfoLabel[2].setForeground(Color.green);
    	InfoLabel[3]=new JLabel("出席情况:");
    	InfoLabel[3].setForeground(Color.green);
    	InfoLabel[0].setFont(new Font("宋体",Font.BOLD,20));
    	InfoLabel[1].setFont(new Font("宋体",Font.BOLD,20));
    	InfoLabel[2].setFont(new Font("宋体",Font.BOLD,20));
    	InfoLabel[3].setFont(new Font("宋体",Font.BOLD,20));
    	
    	for(int i=0;i<3;i++)
    	{		
    		c.add(InfoLabel[i]);
    		c.add(Box.createHorizontalStrut(50));
    		c.add(SInfo[i]);
    		c.add(Box.createHorizontalStrut(1000));
    		c.add(Box.createHorizontalStrut(1000));
    		c.add(Box.createHorizontalStrut(1000));
    	}
        c.add(Box.createHorizontalStrut(1000)); 
        c.add(InfoLabel[3]);	
        Absent=new JRadioButton[6];
        Absent[0]=new JRadioButton("正常");
        Absent[0].setForeground(Color.MAGENTA); 
        Absent[0].setFont(new Font("宋体",Font.BOLD,20)); 
        
        Absent[1]=new JRadioButton("病假");
    	Absent[1].setForeground(Color.MAGENTA);
    	Absent[1].setFont(new Font("宋体",Font.BOLD,20));	
    	
    	
    	Absent[2]=new JRadioButton("事假");
    	Absent[2].setForeground(Color.MAGENTA);
    	Absent[2].setFont(new Font("宋体",Font.BOLD,20));
    	
    	Absent[3]=new JRadioButton("迟到");
    	Absent[3].setForeground(Color.MAGENTA);
    	Absent[3].setFont(new Font("宋体",Font.BOLD,20));
    	
    	Absent[4]=new JRadioButton("早退");
    	Absent[4].setForeground(Color.MAGENTA);
    	Absent[4].setFont(new Font("宋体",Font.BOLD,20));
    	
    	Absent[5]=new JRadioButton("旷课");
    	Absent[5].setForeground(Color.MAGENTA);
    	Absent[5].setFont(new Font("宋体",Font.BOLD,20));
    	
    	ButtonGroup rg = new ButtonGroup();
    	ActionListener al=new ActionListener()
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    			JRadioButton radio=(JRadioButton)ae.getSource();
    			if(radio==Absent[0])
    			{
    				absent=0.0;
    			}
    			if(radio==Absent[1])
    			{
    				absent=1.0;
    			}
    			if(radio==Absent[2])
    			{
    				absent=1.0;
    			}
    			if(radio==Absent[3])
    			{
    				absent=1.33;
    			}
    			if(radio==Absent[4])
    			{
    				absent=1.33;
    			}
    			if(radio==Absent[5])
    			{
    				absent=2.0;
    			}
    			
    		}
    	};
    	Absent[0].addActionListener(al);
    	Absent[1].addActionListener(al);
    	Absent[2].addActionListener(al);
    	Absent[3].addActionListener(al);
    	Absent[4].addActionListener(al);
    	Absent[5].addActionListener(al);
    	
    	for(int i=0;i<Absent.length;i++)
    	{
    		c.add(Absent[i]);
    		rg.add(Absent[i]);
    		if(i==2)
    		{
    			c.add(Box.createHorizontalStrut(1000));
    			c.add(Box.createHorizontalStrut(95));
    		}
    	} 
    	Absent[0].setSelected(true);
    	Absent[1].setSelected(false);
    	Absent[2].setSelected(false);
    	Absent[3].setSelected(false);
    	Absent[4].setSelected(false);
    	Absent[5].setSelected(false);
    		
        c.add(Box.createHorizontalStrut(1000));
        c.add(Box.createHorizontalStrut(1000));
        c.add(Box.createHorizontalStrut(1000));
        c.add(Box.createHorizontalStrut(1000));
        
        JButton GetInfo=new JButton("获取信息");
        GetInfo.setFont(new Font("宋体",Font.BOLD,20));
        GetInfo.setForeground(Color.blue);     
     
        GetInfo.addActionListener(new ActionListener()
    	{	
        	SynthesizerPlayer synthesizer = SynthesizerPlayer
			.getSynthesizerPlayer();
    	 	public void actionPerformed(ActionEvent e)
    		{
    			if (e.getSource() instanceof JButton)
    			{
    				try
     	            {
     	                Workbook book=Workbook.getWorkbook(new File(clsxls));
     	                Sheet sheet=book.getSheet(0);
     	                sno=SInfo[0].getText();
     	                int k;
     	               System.out.println("hello1");
     	                for(k=3;k<sumStudent;k++)
     	               {              
                           Cell cell_1=sheet.getCell(1,k);  
                           if(sno.equals(cell_1.getContents()))
                           {
    	                       Cell cell_2=sheet.getCell(2,k);
    	                       
    	                       sname=cell_2.getContents();
    	                      
    	               
    	                       
    	                       
    	                       Cell cell_3=sheet.getCell(3,k);
    	                       sdept=cell_3.getContents();
    	                       SInfo[0].setText(sno);                  		
                               SInfo[1].setText(sname);
                               SInfo[2].setText(sdept);
                               SInfo[0].setFont(new Font("宋体",Font.BOLD,20));
    	                       SInfo[1].setFont(new Font("宋体",Font.BOLD,20));
    	                       SInfo[2].setFont(new Font("宋体",Font.BOLD,20));
    	                       SInfo[0].setForeground(Color.RED);
    	                       SInfo[1].setForeground(Color.RED);
    	                       SInfo[2].setForeground(Color.RED);
                               String ss=String.format("%1$d",k)+".jpg";
                               String s="头像\\"+ss;
                               ImageIcon image=new ImageIcon(s);
                               aLabel.setIcon(image);
                               rowNum=k;
                               break; 
    	                   }                       	                      
     	               }
     	               if(k==sumStudent)JOptionPane.showMessageDialog(null,"未能找到该学生信息！");
                 	book.close();
                 	Certain.setEnabled(true);
     	           }
     	           catch(Exception eee)
    	           {
    	                eee.printStackTrace();   			
                	}         
    				
    			}
    		}
    	}  		
    	);
    	c.add(GetInfo);
    	c.add(Box.createHorizontalStrut(500)); 
        Certain=new JButton("确认更改");
        
        Certain.setFont(new Font("宋体",Font.BOLD,20));
        Certain.setForeground(Color.blue);      
        Certain.addActionListener(new ActionListener()
    	{
    	 	public void actionPerformed(ActionEvent ee)
    		{
    			if (ee.getSource() instanceof JButton)
    			{
    				ReviseOneInfo m=new ReviseOneInfo();
    				m.revise(absent,time_Column,rowNum,clsxls,sumStudent);
    				JOptionPane.showMessageDialog(null,"修改信息成功！");	
    			}
    		}
    	}  		
    	);
    	Certain.setEnabled(false);
    	c.add(Certain); 
    	app.setResizable(false);
    	app.setVisible(true);	
    }
 
}

       
