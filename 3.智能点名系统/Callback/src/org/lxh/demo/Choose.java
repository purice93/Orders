package org.lxh.demo;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.Box;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.io.*;
import jxl.*;
import jxl.write.*;
import java.awt.Color;
import java.awt.Font;



public class Choose 
{
	public int j=0;
	public String weekNumber,timeNumber,record,classxls;//record��¼�ڼ��ܵڼ���
	private JTextField[] time;
	private JComboBox nameTime,chooseClass;
	public int column,row;
	public  int sumStudentNum;
	
    public void choose_1() 
    {
    	JFrame app=new JFrame("����ϵͳ");
    	app.setSize(360,240);
    	app.setLocationRelativeTo(null);
    	app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  	
    	Container c=app.getContentPane();
    	c.setLayout(new FlowLayout());
    	for(int i=0;i<4;i++)
    	{
    		if(i==1)c.add(Box.createHorizontalStrut(1000)); 
    		c.add(Box.createHorizontalStrut(10)); 
    	}
        c.add(Box.createHorizontalStrut(1000));
        
        JLabel cls=new JLabel("ѡ��༶:");
        cls.setForeground(Color.red);
        cls.setFont(new Font("����",Font.PLAIN,15));
        c.add(cls);
        c.add(Box.createHorizontalStrut(10));
        
        String Cls[]={"�����1�������.xls","�����2�������.xls"};
        chooseClass=new JComboBox(Cls);
        chooseClass.setBackground(Color.lightGray);
        chooseClass.setSelectedIndex(-1);
        chooseClass.setEditable(false);
        chooseClass.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent m)
        	{
            	if (m.getSource() instanceof JComboBox)
       	     	{
         			classxls=(chooseClass.getSelectedItem()).toString();
         			if(classxls.equals("�����1�������.xls"))
         			{
         				sumStudentNum=14;//��һ���༶������
         			}
         			else sumStudentNum=11;//�ڶ����༶������
        		}
        	}
        }
        );
        c.add(chooseClass); 
        c.add(Box.createHorizontalStrut(1000)); 
        
        JLabel time=new JLabel("����ʱ��:");
        time.setForeground(Color.red);
        time.setFont(new Font("����",Font.PLAIN,15));
        c.add(time);
        c.add(Box.createHorizontalStrut(10));
        String s[]={"��1�ܵ�1��","��1�ܵ�2��","��2�ܵ�1��","��2�ܵ�2��","��3�ܵ�1��","��3�ܵ�2��","��4�ܵ�1��","��4�ܵ�2��","��5�ܵ�1��","��5�ܵ�2��"};
        nameTime=new JComboBox(s);
        nameTime.setBackground(Color.lightGray);
        nameTime.setSelectedIndex(-1);
        nameTime.setMaximumRowCount(5);
        nameTime.setEditable(false);
        nameTime.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent ae)
        	{
            	if (ae.getSource() instanceof JComboBox)
       	     	{
         			record=(nameTime.getSelectedItem()).toString();
         			
        		}
        	}
        }
        );
        c.add(nameTime); 
        c.add(Box.createHorizontalStrut(1000)); 	
        final JRadioButton[] r={new JRadioButton("����"),new JRadioButton("�޸�")};
        r[0].setForeground(Color.blue);
        r[0].setFont(new Font("����",Font.PLAIN,15));
        r[1].setForeground(Color.blue);
        r[1].setFont(new Font("����",Font.PLAIN,15));
    	ButtonGroup rg = new ButtonGroup();
    	ActionListener al=new ActionListener()
    	{
    		public void actionPerformed(ActionEvent ae)
    		{
    			JRadioButton radio=(JRadioButton)ae.getSource();
    			if(radio==r[0])
    			{
    				j=0;
    			}
    			if(radio==r[1])
    			{
    				j=1;
    			}
    		}
    	};
    	r[0].addActionListener(al);
    	r[1].addActionListener(al);    
    	rg.add(r[0]);
    	rg.add(r[1]);

    	for(int i=0;i<r.length;i++)
    	{
    		c.add(r[i]);
    		rg.add(r[i]);
    		c.add(Box.createHorizontalStrut(20)); 
    	} 
    	r[0].setSelected(true);
    	r[1].setSelected(false);
    		
        c.add(Box.createHorizontalStrut(1000));
        JButton identify=new JButton("ȷ��");
        identify.setFont(new Font("����",Font.PLAIN,15));
        identify.setForeground(Color.MAGENTA);
        identify.addActionListener(new ActionListener()
    		{
    		 	public void actionPerformed(ActionEvent e)
    			{
    				if (e.getSource() instanceof JButton)
    				{
    					
    					if(j==0)
    					{
                            column=Updataxls();	
    					    NameStudent dlg=new NameStudent();
    				        dlg.create(column,classxls,sumStudentNum);
    				    }
    				   	if(j==1)
    					{
                            column=Updataxls();	
    					    Revise dlg=new Revise();
    				        dlg.create(column,classxls,sumStudentNum);
    				    }
    				}
    			}
    		}  		
    		);
        c.add(identify);
    	app.setVisible(true);
    }
    
    public int Updataxls()
    {
    	try
    	{
    		Workbook book=Workbook.getWorkbook(new File(classxls));
    		Sheet sheet=book.getSheet(0);
    		int i;
    		for(i=4;i<=13;i++)
    		{
    		    Cell cell=sheet.getCell(i,1);//��ȡ3��2��
    		    String result=cell.getContents();
    		    if(result.equals(record))
    		    {
    		    	return i;
    		    } 		    
    		}  		
    		book.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return -1;	
    	}
    	return -1;
   	}

   	
}