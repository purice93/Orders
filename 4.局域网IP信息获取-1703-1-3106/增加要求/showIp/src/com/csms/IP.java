package com.csms;

import java.io.*;  
import java.util.*;  

public class IP{  
	private List<String> ipList = new ArrayList<>(); //ping ��Ľ����  
	
	// ��ȡ�����ӵ�IP��
	public List<String> getPing(String index, int first, int last){ //�����õ�ping��Ľ����  
		for(int i=first;i <=last;i++) { //�����о�����Ip  
			String iip=index+i;  
			Ping(iip);  
		}
		return ipList;
	}  

	// ��������IP��ͨ��������"ping ip"��
	private void Ping(String iip) {
		try{  
			Process p= Runtime.getRuntime().exec ("ping "+iip);  
			InputStreamReader ir = new InputStreamReader(p.getInputStream());  
			LineNumberReader input = new LineNumberReader (ir);  
			//��ȡ�����  
			for (int i=1 ; i <3; i++) {
			  input.readLine();  
			}
			String line= input.readLine();  
			System.out.println(line);
			if (line.contains("����ʱ"))  {
				System.out.println(iip+"��������");
			}
			else if(line.contains("TTL")){ 
				ipList.add(iip); 
			}
		}
		catch (IOException e){
		
		}
	}

}