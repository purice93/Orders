package com.csms;

import java.io.*;  
import java.util.*;  

public class IP{  
	private List<String> ipList = new ArrayList<>(); //ping 后的结果集  
	
	// 获取可连接的IP号
	public List<String> getPing(String index, int first, int last){ //用来得到ping后的结果集  
		for(int i=first;i <=last;i++) { //对所有局域网Ip  
			String iip=index+i;  
			Ping(iip);  
		}
		return ipList;
	}  

	// 尝试连接IP（通过命令行"ping ip"）
	private void Ping(String iip) {
		try{  
			Process p= Runtime.getRuntime().exec ("ping "+iip);  
			InputStreamReader ir = new InputStreamReader(p.getInputStream());  
			LineNumberReader input = new LineNumberReader (ir);  
			//读取结果行  
			for (int i=1 ; i <3; i++) {
			  input.readLine();  
			}
			String line= input.readLine();  
			System.out.println(line);
			if (line.contains("请求超时"))  {
				System.out.println(iip+"不在网中");
			}
			else if(line.contains("TTL")){ 
				ipList.add(iip); 
			}
		}
		catch (IOException e){
		
		}
	}

}