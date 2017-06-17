import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/*
 * 服务器端
 */
public class Server {
	// 一下相似内容同客户端类似
	// 网络数据读取和和发送
	BufferedReader reader;
	PrintWriter writer;
	Socket sock;
	ArrayList<PrintWriter> clientOutputStreams;
	
	// 记录客户端玩家数量：启动了多少个玩家，用于开始发牌时的控制
	// 当所有玩家都点击开始按钮后，开始发牌
	static int number = 0;
	// 记录玩家数量最大值
	static int mixNumber = 0;
	// 记录玩家结束游戏的数量，
	// 当所有玩家都点击了结束按钮后，服务器对游戏输赢情况进行计算
	static int endNumber = 0;
	// 是否开始发牌的标志：当开始发牌后，flag=true
	boolean flag = false;
	// 初始化一副牌，
	// 说明，由于这是计算机操作，虽然每次初始化的一副牌都是顺序的，而且都是一样的
	// 当时，由于发牌完全是随机的，所以这也是一种洗牌，也就不需要一个洗牌功能
	private Card card = new Card();

	// 庄家的总点数
	int makerTotal = 0;
	// 每一个玩家的纸牌list，如[红桃3，梅花5，方块7]
	List<String> numList = new LinkedList<String>();
	// 每一局的玩家纸牌list，如[list1,list2,list3],其中list1即为上面一行的numList
	List<List<String>> twoNumList = new LinkedList<List<String>>();

	// 启动服务器
	public static void main(String[] args) {
		new Server().go();
	}

	// 处理客户端连接
	public class ClientHandler implements Runnable {
		// 定义连接
		BufferedReader reader;
		Socket sock;
		public ClientHandler(Socket clientSocket) {
			try {
				sock = clientSocket;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
				System.out.println(isReader);
				reader = new BufferedReader(isReader);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		@Override
		public void run() {
			String message;
			try {
				// 从客户端读取数据，并判断
				while ((message = reader.readLine()) != null) {
					// 识别客户端是否开始游戏
					if (message.indexOf("/##/") != -1) {
						number--;
					}
					// 识别客户端是否结束游戏
					if (message.indexOf("/end/") != -1) {
						endNumber--;
					}
					// 识别客户端是否继续要牌
					if (message.indexOf("++") != -1) {
						int indexTemp = Integer.parseInt(message.substring(0, message.indexOf("++")));
						getNextCard(indexTemp);
					}
					// 判断是否所有的客户端都开始游戏
					if (number == 0 && flag == false) {
						System.out.println("开始发牌");
						String cardTwo = "/00/发牌给你";
						// 服务器开始发牌（发两张）
						outputCard(cardTwo);
						flag = true;
					}
					// 判断客户端是否都点击结束按钮，如果是，结束发牌，服务器进行输赢计算
					if (endNumber == 0) {
						System.out.println("结束发牌");
						endPutCard();
					}
					// 处理单个客户端结束游戏按钮，此时跳过，不处理
					if (message.indexOf("游戏结束") != -1&&endNumber != 0) {
						continue;
					}
					System.out.println("read " + message);
					// 向客户端传送数据
					tellEveryone(message);
				}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 结束所有的发牌，游戏结束
	public void endPutCard() {
		// 遍历读取客户端数据
		Iterator<PrintWriter> it = clientOutputStreams.iterator();
		int index = 0;
		while (it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();
				if (makerTotal < 17) {
					//writer.println("庄家继续要牌：");
				}
				// 庄家点数小于17，则庄家继续要牌
				while (makerTotal < 17) {
					// 随机发牌
					String cardTemp = card.getCard();
					// 将牌的字符串转为点数int
					int numberTemp = transToint(cardTemp);
					makerTotal += numberTemp;
				}
				writer.println("庄家的总点数是：" + makerTotal + "\n");
				System.out.println(twoNumList.get(index).toString());
				int numberTotal = 0;
				// 计算客户端（玩家）总点数
				for (String str : twoNumList.get(index)) {
					numberTotal += transToint(str);
				}
				
				// 判断玩家的胜负（比较客户端点数和庄家点数）
				/*
				 * 游戏规则是：
				 * 庄家点数<21时：
				 * 		1.玩家点数<21,比较点数大小，如果玩家点数大，则赢；否则玩家输
				 * 		2.玩家点数=21，玩家赢
				 * 		3.玩家点数》21，玩家输
				 * 
				 * 庄家点数=21时：
				 * 		1.无论怎样，玩家输
				 * 
				 * 庄家点数>21时：
				 * 		1.玩家点数<=21时，玩家赢；否则玩家输
				 */
				
				if (makerTotal > 21) {
					if (numberTotal > 21) {
						writer.println(
								"你的牌为：" + twoNumList.get(index).toString() + "\n总点数为：" + numberTotal + "\n你输了");
					} else {
						writer.println(
								"你的牌为：" + twoNumList.get(index).toString() + "\n总点数为：" + numberTotal + "\n你赢了");
					}
				} else if (makerTotal == 21) {
					writer.println("你的牌为：" + twoNumList.get(index).toString() + "\n总点数为：" + numberTotal + "\n你输了");
				} else {
					if (numberTotal > 21) {
						writer.println(
								"你的牌为：" + twoNumList.get(index).toString() + "\n总点数为：" + numberTotal + "\n你输了");
					} else if (numberTotal == 21) {
						writer.println(
								"你的牌为：" + twoNumList.get(index).toString() + "\n总点数为：" + numberTotal + "\n你赢了");
					} else {
						if (numberTotal > makerTotal) {
							writer.println(
									"你的牌为：" + twoNumList.get(index).toString() + "\n总点数为：" + numberTotal + "\n你赢了");
						} else {
							writer.println(
									"你的牌为：" + twoNumList.get(index).toString() + "\n总点数为：" + numberTotal + "\n你输了");
						}
					}
				}
				writer.println("\n");
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			index++;
		}
	}

	// 要牌，继续发牌
	public void getNextCard(int indexTemp) {
		Iterator<PrintWriter> it = clientOutputStreams.iterator();
		for (int i = 0; i < twoNumList.size(); i++) {
			// 对客户端判断，对点击发牌按钮的所在玩家进行发牌，其他玩家不发牌
			if (indexTemp == i) {
				String nextCard = card.getCard();
				twoNumList.get(i).add(nextCard);
			}
		}
		int index = 0;
		while (it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();
				if (index == indexTemp) {
					System.out.println(twoNumList.get(index).toString());
					int numberTotal = 0;
					for (String str : twoNumList.get(index)) {
						numberTotal += transToint(str);
					}
					if (numberTotal < 21) {
						writer.println(
								"你的牌为：" + twoNumList.get(index).toString() + "\n总点数为：" + numberTotal + "\n可以继续要牌");
					} else if (numberTotal == 21) {
						writer.println("你的牌为：" + twoNumList.get(index).toString() + "\n总点数为21 " + "\n无法要牌了");
					} else {
						writer.println(
								"你的牌为：" + twoNumList.get(index).toString() + "\n总点数为：" + numberTotal + "\n无法要牌了");
					}
					writer.println("\n");
					writer.flush();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			index++;
		}
	}

	// 游戏开始，发前两张牌
	public void outputCard(String cardTwo) {
		// for (int playerNum = 0; playerNum < mixNumber; playerNum++) {
		Iterator<PrintWriter> it = clientOutputStreams.iterator();
		String firstCard = "";
		
		// 对每位玩家随机生成两张牌，并保存在twoNumList中
		for (int i = 0; i < mixNumber; i++) {
			firstCard = card.getCard();
			String secondCard = card.getCard();
			numList = new LinkedList<>();
			try {
				numList.add(firstCard);
				numList.add(secondCard);
				twoNumList.add(numList);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		// 遍历客户端，对每位客户端发送数据
		int index = 0;
		for (int playerNum = 0; playerNum < mixNumber; playerNum++) {
			while (it.hasNext()) {
				try {
					PrintWriter writer = (PrintWriter) it.next();
					writer.println("player" + playerNum);
					writer.println("##index" + (index));
					System.out.println("---" + index);
					writer.println("庄家第一张牌是：" + firstCard + "\n");
					writer.println(cardTwo);
					System.out.println(twoNumList.get(index).toString());
					int numberTotal = 0;
					for (String str : twoNumList.get(index)) {
						numberTotal += transToint(str);
					}
					if (numberTotal < 21) {
						writer.println(
								"你的牌为：" + twoNumList.get(index).toString() + "\n总点数为：" + numberTotal + "\n可以继续要牌");
					} else if (numberTotal == 21) {
						writer.println("你的牌为：" + twoNumList.get(index).toString() + "\n总点数为21");
					} else {
						writer.println(
								"你的牌为：" + twoNumList.get(index).toString() + "\n总点数为：" + numberTotal + "\n无法要牌了");
					}
					writer.println("\n");
					writer.flush();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				index++;
			}
		}
	}

	// 将纸牌信息转为数字信息，如"红桃5"-->5
	private int transToint(String card) {
		int num = 0;
		String endStr = card.substring(2, card.length());
		if (endStr.equals("J") || endStr.equals("Q") || endStr.equals("K")) {
			num = 10;
		} else if (endStr.equals("A")) {
			num = 1;
		} else {
			num = Integer.parseInt(endStr);
		}
		return num;
	}

	// 向所有客户端发送数据
	public void tellEveryone(String message) {
		Iterator<PrintWriter> it = clientOutputStreams.iterator();
		while (it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(message);
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void go() {
		clientOutputStreams = new ArrayList<PrintWriter>();
		System.out.println("服务器已启动");
		// 初始化网络连接，接受网络客户端数据
		try {
			ServerSocket serverSock = new ServerSocket(5000);
			while (true) {
				Socket clientSocket = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				clientOutputStreams.add(writer);
				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();
				// 判断有多少个客户端启动，有多少个就表明有多少个玩家
				number++;
				if (number > mixNumber) {
					mixNumber = number;
					endNumber = mixNumber;
				}
				System.out.println("number is :" + number);
				System.out.println("got a connection");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
