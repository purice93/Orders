package org.lxh.demo;

import java.io.*;
import jxl.*;
import jxl.write.*;

public class Updatexls 
{	
       	public static void update(double SABSENT[],int time_Column,String clsxls,int sumStudentNum)
    	{
    		try
    		{
    			Workbook wb=Workbook.getWorkbook(new File(clsxls));
    		    WritableWorkbook book=Workbook.createWorkbook(new File(clsxls),wb);
    			WritableSheet sheet=book.getSheet(0);
    			Double cell1_num[]=new Double[100]; 
    			double k=100/((14-4)*2); 
    			for(int i=3;i<sumStudentNum;i++)
    			{    			    
    			    jxl.write.Number number = new jxl.write.Number(time_Column,i,SABSENT[i]);
    			    sheet.addCell(number);
    			    Cell cell1=sheet.getCell(14,i);
    			    cell1_num[i]=Double.parseDouble(cell1.getContents());
    			    cell1_num[i]+=SABSENT[i];
    			    if(time_Column==(14-1))cell1_num[i]*=k;
    			    jxl.write.Number sum = new jxl.write.Number(14,i,cell1_num[i]);
    			    sheet.addCell(sum);    			                         
    			}
    			book.write();
    			book.close();   				
    		}
    		catch(Exception e)
    		{
    			System.out.println(e);
    			
    		}
    	}
    
    
}