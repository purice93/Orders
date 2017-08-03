package org.lxh.demo;
import java.io.*;
import jxl.*;
import jxl.write.*;

public class ReviseOneInfo
{	

       	public static void revise(double absent,int time_Column,int rowNum,String clsxls,int sumStudentNum)
    	{
    		try
    		{
    			Workbook wb=Workbook.getWorkbook(new File(clsxls));
    		    WritableWorkbook book=Workbook.createWorkbook(new File(clsxls),wb);
    			WritableSheet sheet=book.getSheet(0);
    			
    			jxl.write.Number number = new jxl.write.Number(time_Column,rowNum,absent);
    			sheet.addCell(number);
    			
    			Cell cell1=sheet.getCell(14,rowNum);
    			double cell1_num=Double.parseDouble(cell1.getContents());
    			cell1_num+=absent;
    			if(rowNum==(14-1))
    				cell1_num*=100/((14-4)*2);
    			jxl.write.Number sum = new jxl.write.Number(14,rowNum,cell1_num);
    			sheet.addCell(sum); 
    			book.write();
    			book.close();   				
    		}
    		catch(Exception e)
    		{
    			System.out.println(e);
    			
    		}
    	}    
}
    
 