package org.lxh.demo;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.Box;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

import com.iflytek.speech.SynthesizerPlayer;

import java.io.*;
import jxl.*;
import jxl.write.*;
import java.awt.Graphics;
import com.iflytek.speech.SpeechError;
import com.iflytek.speech.SynthesizerPlayer;
import com.iflytek.speech.SynthesizerPlayerListener;
import com.iflytek.util.Version;
import com.iflytek.speech.SpeechError;
import com.iflytek.speech.SynthesizerPlayer;
import com.iflytek.speech.SynthesizerPlayerListener;
import com.iflytek.util.Version;

public class NameStudent {
	
	public JTextField[] SInfo;
	private JLabel[] InfoLabel;
    public Container c;
	public JRadioButton[] Absent;
	public double absent;//返回出席情况
	public int clickTime=4,time_Column;
	public String clsxls;
    public String SNO[]=new String[100];
    public String SDEPT[]=new String[100];
    public String SNAME[]=new String[100];
    public double SABSENT[]=new double[100];
    public JLabel aLabel;
    public JButton prior;
    public JButton next;
    public int sumStudent;
    
    public  void create(int tc,String classxls,int sumStudentNum)
     {
    	   
    	 
        
     	sumStudent=sumStudentNum;	
     	clsxls=classxls;
     	time_Column=tc;
     	try
     	{
     	    Workbook book=Workbook.getWorkbook(new File(clsxls));
     	    Sheet sheet=book.getSheet(0);
     	    for(int k=3;k<sumStudent;k++)
     	    {           
                Cell cell_1=sheet.getCell(1,k);  
                SNO[k]=cell_1.getContents();
    	        Cell cell_2=sheet.getCell(2,k);
    	        SNAME[k]=cell_2.getContents();
    	        Cell cell_3=sheet.getCell(3,k);
    	        SDEPT[k]=cell_3.getContents();                       	                      
     	    }
        	book.close();
     	}
     	catch(Exception eee)
    	{
    	    eee.printStackTrace();   			
    	}    

		if (SynthesizerPlayer.getSynthesizerPlayer() == null)
			SynthesizerPlayer.createSynthesizerPlayer("appid="
					+ Version.getAppid());
     	SynthesizerPlayer synthesizer = SynthesizerPlayer.getSynthesizerPlayer();
		
		 synthesizer.setVoiceName("xiaoyu");
			// 设置朗读速度为50
			synthesizer.setSpeed(50);
			// 合成文本为TEXT_CONTENT的句子，设置监听器为mSynListener
			synthesizer.playText(SNAME[3].trim(), null,
					mSynListener);
		
     	
  	     //创建点名窗口
    	JFrame app=new JFrame("点名");
    	app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	app.setSize(500,600);
    	app.setLocationRelativeTo(null);
     	c=app.getContentPane();
    	c.setLayout(new FlowLayout(FlowLayout.CENTER));
    		
    	ImageIcon image=new ImageIcon("头像\\1.jpg");
        aLabel=new JLabel(image);
        c.add(aLabel); 
        c.add(Box.createHorizontalStrut(1000));
        c.add(Box.createHorizontalStrut(1000));
    	SInfo=new JTextField[3];
    	SInfo[0]=new JTextField("",10);
    	SInfo[1]=new JTextField("",10);
    	SInfo[2]=new JTextField("",10);
    	SInfo[0].setForeground(Color.RED);
    	SInfo[0].setFont(new Font("宋体",Font.BOLD,20));
    	SInfo[1].setFont(new Font("宋体",Font.BOLD,20));
    	SInfo[2].setFont(new Font("宋体",Font.BOLD,20));
        SInfo[1].setForeground(Color.red);
        SInfo[2].setForeground(Color.red);

    	SInfo[0].setEditable(false);
    	SInfo[1].setEditable(false);
    	SInfo[2].setEditable(false);
    	
    	InfoLabel=new JLabel[4];
    	InfoLabel[0]=new JLabel("学号:");
    	InfoLabel[0].setFont(new Font("宋体",Font.BOLD,20));
    	InfoLabel[0].setForeground(Color.green);
    	InfoLabel[1]=new JLabel("姓名:");
    	InfoLabel[1].setFont(new Font("宋体",Font.BOLD,20));
    	InfoLabel[1].setForeground(Color.green);
    	InfoLabel[2]=new JLabel("专业:");
    	InfoLabel[2].setFont(new Font("宋体",Font.BOLD,20));
    	InfoLabel[2].setForeground(Color.green);
    	InfoLabel[3]=new JLabel("出席情况:");
    	InfoLabel[3].setFont(new Font("宋体",Font.BOLD,20));
    	InfoLabel[3].setForeground(Color.green);
        System.out.println("hello4");
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
    				absent=2;
    			}
    			if(radio==Absent[1])
    			{
    				absent=0;
    			}
    			if(radio==Absent[2])
    			{
    				absent=0;
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
    				absent=0;
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
    	
    	
    	SInfo[0].setText(SNO[3]);                  		
        SInfo[1].setText(SNAME[3]);
        SInfo[2].setText(SDEPT[3]);
    		
        c.add(Box.createHorizontalStrut(1000));
        c.add(Box.createHorizontalStrut(1000)); 
        c.add(Box.createHorizontalStrut(1000)); 
       	c.add(Box.createHorizontalStrut(1000)); 
        System.out.println("hello5");
        prior=new JButton("上一个");
        prior.addFocusListener(new FocusListener()
        {
        	public void focusGained(FocusEvent e)
        	{}
        	public void focusLost(FocusEvent e)
        	{}
        }
        
        );   
        System.out.println("hello6");
        if (SynthesizerPlayer.getSynthesizerPlayer() == null)
			SynthesizerPlayer.createSynthesizerPlayer("appid="
					+ Version.getAppid());
    	 
        prior.addKeyListener(new KeyAdapter()   
        {      
     
            public  void  keyPressed(KeyEvent e)   
            {    
                if((e.getKeyChar())=='b' )                       
                {
           
                	clickTime--;
                	if(clickTime==(sumStudent-1)) next.setEnabled(true);
                	if(clickTime==3)
                	{    
                     	JOptionPane.showMessageDialog(null,"已经是第一个");
                	}
                	else
                	{  	                   			                    
                        String ss=String.format("%1$d",clickTime)+".jpg";
                        String s="头像\\"+ss;
                        ImageIcon image=new ImageIcon(s);
                        aLabel.setIcon(image);
                        SInfo[0].setText(SNO[clickTime]);                  		
                        SInfo[1].setText(SNAME[clickTime]);
                        System.out.println("SNAME[clickTime]");
                        System.out.println("上一个");
                       
                        
                        SInfo[2].setText(SDEPT[clickTime]);      	
                    }  
                 }                
           }
            public void keyReleased(KeyEvent e){}  
      }             
    );    
        System.out.print("hello7");
        prior.addActionListener(new ActionListener()
    		{
    		 	public void actionPerformed(ActionEvent e)
    			{   SynthesizerPlayer synthesizer = SynthesizerPlayer.getSynthesizerPlayer();     
    				if (e.getSource() instanceof JButton)
    				{
    					clickTime--;
    					if(clickTime==(sumStudent-1)) next.setEnabled(true);
    					if(clickTime==2)
    					{
    						prior.setEnabled(false);
    						JOptionPane.showMessageDialog(null,"已经是第一个");
    					}
    					else
    					{
    						if(clickTime==3)prior.setEnabled(true);    
                    	    String ss=String.format("%1$d",clickTime)+".jpg";
                    	    String s="头像\\"+ss;
                 	    	ImageIcon image=new ImageIcon(s);
                 	    	aLabel.setIcon(image);                 		
                          	SInfo[0].setText(SNO[clickTime]);
                            SInfo[1].setText(SNAME[clickTime]);
                            synthesizer.setVoiceName("xiaoyu");
                			// 设置朗读速度为50
                			synthesizer.setSpeed(50);
                			// 合成文本为TEXT_CONTENT的句子，设置监听器为mSynListener
                			synthesizer.playText(SNAME[clickTime].trim(), null,
                					mSynListener);
                            SInfo[2].setText(SDEPT[clickTime]);
    					}   	
    				}
    			}
    		}  		
        );
        prior.setForeground(Color.red);
        prior.setBackground(Color.green); 
        prior.setEnabled(false);
        prior.setFont(new Font("宋体",Font.BOLD,20)); 		
        c.add(prior,BorderLayout.CENTER);
        c.add(Box.createHorizontalStrut(50));
        next=new JButton("下一个");
        System.out.print("hello8");
        next.setSize(20,50); 
    	if (SynthesizerPlayer.getSynthesizerPlayer() == null)
			SynthesizerPlayer.createSynthesizerPlayer("appid="
					+ Version.getAppid());
        next.addKeyListener(new KeyAdapter()
        {
        	public void keyTyped(KeyEvent e)
        	{
        		if(e.getKeyChar()=='n')
        		prior.setEnabled(true);
        	}
        }
        );
        next.addActionListener(new ActionListener()
    	{  
    		public void actionPerformed(ActionEvent e)
    		{   SynthesizerPlayer synthesizer = SynthesizerPlayer.getSynthesizerPlayer();         				   	    				     				   	      
                String ss=String.format("%1$d",clickTime)+".jpg"; 
                String s="头像\\"+ss;                      		
                ImageIcon image=new ImageIcon(s);
                aLabel.setIcon(image);
                System.out.print("hello9");
                SInfo[0].setText(SNO[clickTime]);
                SInfo[1].setText(SNAME[clickTime]);
               synthesizer.setVoiceName("xiaoyu");
    			// 设置朗读速度为50
    			synthesizer.setSpeed(50);
    			// 合成文本为TEXT_CONTENT的句子，设置监听器为mSynListener
    			if(clickTime==2) clickTime++;
    			synthesizer.playText(SNAME[clickTime].trim(), null,
    					mSynListener);
    	     
                System.out.print(SNAME[clickTime]);
                SInfo[2].setText(SDEPT[clickTime]);
                SABSENT[clickTime]=absent;
                clickTime++;
                prior.setEnabled(true);
                if(clickTime==sumStudent)
                {
                	next.setEnabled(false);
                	JOptionPane.showMessageDialog(null,"点名结束");
                	Updatexls ud=new Updatexls();
                	ud.update(SABSENT,time_Column,clsxls,sumStudent);
                }				   	    		
    		}
    	}  		
       );
       
       next.addKeyListener(new KeyAdapter()  
        {   
            public  void  keyPressed(KeyEvent ee)   
            {   
                if((ee.getKeyChar())=='n')                       
                {
                	prior.setEnabled(true);
                	if(clickTime==sumStudent)
                	{
                		next.setEnabled(false);    
                     	JOptionPane.showMessageDialog(null,"点名结束");
                     	Updatexls ud=new Updatexls();
                	    ud.update(SABSENT,time_Column,clsxls,sumStudent);
                	}
                	else
                	{                           	                   			                    
                        String ss=String.format("%1$d",clickTime)+".jpg";
                        String s="头像\\"+ss;
                        ImageIcon image=new ImageIcon(s);
                        aLabel.setIcon(image);
                        SInfo[0].setText(SNO[clickTime]);                  		
                        SInfo[1].setText(SNAME[clickTime]);
                        SInfo[2].setText(SDEPT[clickTime]);
                        clickTime++;      	
                    }  
                 }      
           }
            public void keyReleased(KeyEvent ee){}  
      }             
    );
       next.setForeground(Color.red);
       next.setBackground(Color.green);
       next.setFont(new Font("宋体",Font.BOLD,20));    		
       c.add(next,BorderLayout.CENTER);
       app.setResizable(false);
       app.setVisible(true);	
    }
    private static SynthesizerPlayerListener mSynListener = new SynthesizerPlayerListener() {

		 
		public void onEnd(SpeechError error) {
		}

	 
		public void onBufferPercent(int percent, int beginPos, int endPos,
				String args) {

		}

		public void onPlayBegin() {

		}

		public void onPlayPaused() {

		}

		public void onPlayPercent(int percent, int beginPos, int endPos) {
		}

		public void onPlayResumed() {

		}
	};
       
}

