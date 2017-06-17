import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
/*
 * 客户端
 */
public class ClientWindow {
	// 窗口信息显示
	JTextArea incoming;
	// 读取客户端信息内容
	BufferedReader reader;
	// 向客户端发送信息内容
	PrintWriter writer;
	// 网络连接
	Socket sock;
	// 客户端玩家姓名
	static String clientName;
	// 玩家序号：即第几个玩家，初始化为0
	int playerNumber = 0;
	// 从服务器端读取信息线程
	Thread readerThread;
	// 客户端界面
	JFrame frame;
	
	// 1.启动客户端
	public static void main(String[] args) {
		// 初始化客户端类
		ClientWindow clientWindow = new ClientWindow();
		// 输入玩家姓名
		clientName = JOptionPane.showInputDialog("请输入玩家姓名 :");
		// 初始化客户端界面定义
		clientWindow.go();
	}

	public void go() {
		// 初始化界面玩家名
		frame = new JFrame(clientName + "'s Chat Client");
		// 主面板
		JPanel mainPanel = new JPanel();
		// 文本域：消息展示框，用于显示游戏过程的消息提示
		incoming = new JTextArea(25, 20);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		// 将文本域incoming添加到面板中
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// 定义四个按钮，分别是开始游戏、继续要牌、结束要牌、退出游戏
		JButton startButton = new JButton("开始");
		JButton getButton = new JButton("要牌");
		JButton endButton = new JButton("结束");
		JButton exitButton = new JButton("退出");
		// 对按钮进行监听
		startButton.addActionListener(new startButtonListener());
		getButton.addActionListener(new getButtonListener());
		endButton.addActionListener(new endButtonListener());
		exitButton.addActionListener(new exitButtonListener());

		// 向主面板中添加消息面板和四个按钮
		mainPanel.add(qScroller);
		mainPanel.add(startButton);
		mainPanel.add(getButton);
		mainPanel.add(endButton);
		mainPanel.add(exitButton);

		// 连接服务器
		setUpNetworking();
		// 启动消息读取线程，用于实时获取服务器的数据
		readerThread = new Thread(new IncomingReader());
		readerThread.start();

		// 设置主面板界面属性：布局位置，窗口大小，是否可调动
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(280, 500);
		frame.setVisible(true);

	}

	// 启动网络连接，连接服务器
	private void setUpNetworking() {
		try {
			// 网络ip地址和端口
			sock = new Socket("127.0.0.1", 5000);
			// 从连接中读取数据
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			// 向服务器传输数据
			writer = new PrintWriter(sock.getOutputStream());

			System.out.println("networking established");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// 按钮1："开始"按钮,开始按钮的逻辑：初始化玩家信息，庄家和每个玩家随机发两张纸牌，玩家只能看到庄家一张纸牌
	public class startButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				// 提示玩家游戏开始
				writer.println("玩家："+clientName+" 开始:");
				// 玩家是否开始游戏标志，当服务器接收到/##/标志时，表明游戏开始，
				// 并对所有玩家计数,当一局游戏的所有玩家都开始时，服务器开始随机发牌
				writer.println("/##/");
				// 将数据刷进网络中
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// 按钮2："发牌"按钮，当选择继续发牌时，服务器向玩家再发送一张牌
	public class getButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				// 玩家的纸牌数量+1
				writer.println(playerNumber+"++");
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// 按钮3："结束"按钮，当选择结束按钮时，结束发牌，本玩家等待其他玩家都结束。
	// 当服务器判断所有玩家都结束要牌后，结算玩家输赢情况
	public class endButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				writer.println("游戏结束，正在等待其他玩家...");
				// "/end/"字符串用于服务器判断玩家是否结束游戏
				writer.println("/end/");
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	// 按钮4："退出"按钮，当点击退出按钮后，客户端关闭所有进程，结束游戏
	// 这里需要注意的是，再次进行游戏时，服务器需要重新启动
	public class exitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				//关闭客户端窗口
				frame.dispose();
				// 停止数据读取线程
				readerThread.stop();
				// 关闭网络连接
				sock.close();
				// 退出
				System.exit(0);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// 与服务器网络数据交互，多线程方式
	public class IncomingReader implements Runnable {
		public void run() {
			String message = null;
			try {
				// 从网络数据流reader中依次读取字符串数据（按行）
				while ((message = reader.readLine()) != null) {
					// 服务器开始发牌标志
					if (message.indexOf("/00/") != -1) {
						System.out.println("开始发牌了，这是牌" + message);
						continue;
					}
					// 判断服务器数据信息，由于有多个客户端的数据，客户端需要识别自己的信息
					// ##index是识别标志，将玩家序号赋值给客户端playerNumber（最后一个数字）
					// 如服务器数据是：##index0，则玩家序号是0
					if (message.indexOf("##index") != -1) {
						System.out.println(message.substring(message.indexOf("##index")));
						playerNumber = Integer.parseInt(message.substring(message.indexOf("##index")+7));
						continue;
					}
					// 跳过服务器发送的无用数据
					// 这里的数据其实是客户端发送给服务器的数据，但是服务器又把这些数据反送给客户端，所以需要客户端跳过这些无用数据
					if(message.indexOf("/##/") != -1||message.indexOf("++") != -1||message.indexOf("/end/") != -1) {
						continue;
					}
					System.out.println("read " + message);
					// 将读取的网络数据显示在客户端界面上
					incoming.append(message + "\n");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
}