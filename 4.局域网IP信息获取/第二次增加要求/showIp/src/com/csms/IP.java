package com.csms;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;

public class IP {
	private List<String> ipList = new ArrayList<>(); // ping 后的结果集

	// 获取可连接的IP号
	public List<String> getPing(String index, int first, int last) { // 用来得到ping后的结果集
		Vector<Thread> threads = new Vector<Thread>();
		int temp = last - first + 1;// 差值
		int number = temp / 20;// 每个线程扫描的数量

		// 20个线程
		for (int i = 0; i < 20; i++) {
			Thread thread = new Thread(new MutliThread(first, index, number, i));
			thread.start();
			threads.add(thread);
		}

		//等待子线程完成
		for (Thread iThread : threads) {
			try {
				// 等待所有线程执行完毕
				iThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		int left = first + 20 * number;
		String ipStr = null;
		for (int k = left; k <= last; k++) {
			ipStr = index + k;
			Ping(ipStr);
		}

		return ipList;
	}

	// 尝试连接IP（通过命令行"ping ip"）
	private void Ping(String iip) {
		try {
			Process p = Runtime.getRuntime().exec("ping " + iip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			// 读取结果行
			for (int i = 1; i < 3; i++) {
				input.readLine();
			}
			String line = input.readLine();
			System.out.println(line);
			if (line.contains("TTL")) {
				ipList.add(iip);
			}
		} catch (IOException e) {

		}
	}

	class MutliThread implements Runnable {
		private String index = null;
		private int sum = 0;
		private int first = 0;
		private String ipStr = null;

		MutliThread(int first, String index, int number, int i) {
			this.index = index;
			this.sum = first + (i + 1) * number;
			this.first = first + i * number;
		}

		public void run() {
			while (first < sum) {
				ipStr = index + first;
				Ping(ipStr);
				first++;
			}
		}
	}
}