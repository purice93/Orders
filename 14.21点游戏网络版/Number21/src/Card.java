import java.util.ArrayList;
import java.util.Random;
/*
 * 纸牌类
 */
public class Card {
	String str = "";
	String[] huase = { "红桃", "黑桃", "梅花", "方块" };
	String[] number = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	
	// 用于保存52张纸牌
	ArrayList<String> list = new ArrayList<String>();
	// 随机数生成器
	Random random = new Random();

	// 构造一副牌：包括52张纸牌
	public Card() {
		int i = 0;//
		// 向list中增加数字
		for (i = 0; i < 52; i++) {
			// 生成牌的点数，以字符串类型保存，如：红桃5
			list.add("" + huase[i % 4] + number[i / 4]);
		}
	}

	// 从52张牌中抽取一张牌，并将这张牌从list中移除
	public String getCard() {
		// 随机生成数组下标
		int num = random.nextInt(list.size());
		// 将数字从数组中移除
		list.remove(num);
		// 取出数字
		return list.get(num);
	}
}
