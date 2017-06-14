package com.csms.windows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Information {
	// ����ȡ��Ϣ������
	private LinkedList<String> infoList = null;
	private Information information = null;

	// ��ȡ�˿ں�
	public List<String> getPorts(String ipStr) {
		Vector<Thread> threads = new Vector<Thread>();
		List<String> result = new LinkedList<String>();

		// ��20���߳�
		for (int i = 0; i < 20; i++) {
			Thread thread = new Thread(new MutliThread(ipStr, result, i * 50, i));
			thread.start();
			threads.add(thread);
		}

		// ��������һ���̴߳������µĶ˿�
		Thread thread1 = new Thread(new MutliThread(ipStr, result, 1000));
		thread1.start();
		threads.add(thread1);

		// �ȴ����е����߳̽���
		for (Thread iThread : threads) {
			try {
				// �ȴ������߳�ִ�����
				iThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// ��ȡ������
	public static String getHostnames(String ip) {

		String hostname = "null";
		Map<String, String> map = new HashMap<String, String>();
		System.out.println("������ȡhostname...");
		String command = "ping -a " + ip;
		Runtime r = Runtime.getRuntime();
		Process p;

		// ͨ�������ȡ������
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String inline;
			while ((inline = br.readLine()) != null) {
				if (inline.indexOf("[") > -1) {
					int start = inline.indexOf("Ping ");
					int end = inline.indexOf("[");
					hostname = inline.substring(start + "Ping ".length(), end - 1);
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hostname;
	}

	// ��ȡmac��ַ
	public String getMac(String ip) {
		String mac = null;
		try {
			Process p = Runtime.getRuntime().exec("arp -a");
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			input.readLine();
			input.readLine();
			input.readLine();
			p.waitFor();
			boolean flag = true;
			String ipStr = ip;
			while (flag) {
				String str = input.readLine();
				if (str != null) {
					if (str.indexOf(ipStr + "  ") > 1) {
						int temp = str.indexOf(ipStr);
						System.out.println(str);// ���ip�����ڿ���̨��ʾ����
						// ��ȡ��Ӧ��mac��ַ
						mac = str.substring(temp + 22, temp + 39);
						break;
					}
				} else
					flag = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return mac;
	}

	//���߳���
	class MutliThread implements Runnable {
		private int index = 0;
		private int sum = 0;
		private int first = 0;
		private String ipStr = null;
		private List<String> result = null;

		//���幹�췽��
		MutliThread(String ipStr, List<String> result, int first, int index) {
			this.index = index;
			this.ipStr = ipStr;
			this.result = result;
			this.sum = first + 3200;
			this.first = first;
		}

		public MutliThread(String ipStr, List<String> result, int first) {
			this.ipStr = ipStr;
			this.result = result;
			this.sum = 1024;
			this.first = first;
		}

		//ɨ��˿�
		public void run() {
			while (first < sum) {
				Socket connect = new Socket();
				try {
					System.out.println(first); // Ϊ��ʾɨ����ȣ������ڿ���̨��ʾ����ɾ����
					connect.connect(new InetSocketAddress(ipStr, first++), 100);
					result.add(String.valueOf(sum));
					System.out.println("success");
				} catch (IOException e) {
				}
			}
		}
	}
}
