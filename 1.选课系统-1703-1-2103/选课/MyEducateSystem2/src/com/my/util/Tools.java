package com.my.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.my.beans.DataBase;
import com.my.beans.Record;

public class Tools {
	
	private static char charArr[]= {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I','J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U','V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6','7', '8', '9'};
	
	//产生验证码
	public  static String codeGen()
	{
		StringBuilder str=new StringBuilder();
		
		Random rd=new Random();
		int index=0;
		for(int i=0;i<4;i++)
		{
			index=rd.nextInt(36);
			while(str.indexOf(String.valueOf(charArr[index]))!=-1)
			{
				index=rd.nextInt(36);
			}
			str.append(charArr[index]);
		}
		return new String(str);
	}
	//解析配置文件 Basedata 类  dom解析
	
	
	public static DataBase getProperties()
	{
		 DataBase basedata=null;
		 DocumentBuilder builder;
		try {
			/*builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document=builder.parse(new File("properties.xml"));*/
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			Document document=(factory.newDocumentBuilder()).parse(new File("properties.xml"));
			Node rootNode=(Node)document.getDocumentElement();
			NodeList list=rootNode.getChildNodes();
			for(int i=0;i<list.getLength();i++)
			{
				Node element=list.item(i);
				if(element.getNodeName().equals("database"))
				{
					basedata=new DataBase();
					NamedNodeMap map=element.getAttributes();//collections of nodes that can be accessed by name
					Node nameNode=map.getNamedItem("name");
					basedata.setName(nameNode.getNodeValue().trim());
					NodeList subList=element.getChildNodes();
					for(int j=0;j<subList.getLength();j++)
					{
						Node subElement=subList.item(j);
						String subNodeName=subElement.getNodeName();
						if(subNodeName.equals("driver"))
						{
							basedata.setDriver(subElement.getTextContent().trim());
						}
						if(subNodeName.equals("protocal"))
						{
							basedata.setProtocal(subElement.getTextContent().trim());
						}
						if(subNodeName.equals("dbUrl"))
						{
							basedata.setDbUrl(subElement.getTextContent().trim());
						}
					}
					
				}
				
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		 
		 return basedata;
	}
	//连接数据库 返回Connection
	public static Connection connectDataBase(String url)
	{
		Connection conn=null;
		try {
			 conn=DriverManager.getConnection(url,"sa","123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void writeRecord(Record record)
	{
		try {
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("record.txt"));
			oos.writeObject(record);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Record  readRecord()
	{
		Record record=new Record();
		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("record.txt"));
			try {
				record=(Record)ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return record;
	}
	
}
