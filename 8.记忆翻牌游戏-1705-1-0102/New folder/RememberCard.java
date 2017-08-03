package com.cn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

/**
 * @author jqs 主要实现记忆翻牌功能
 */
public class RememberCard extends JFrame {

	/**
	 * 初始化游戏的行列数，行列数成绩必须为偶数
	 */
	private static final int ROWS = 4;
	private static final int COLUMNS = 4;
	private static final long serialVersionUID = -8908268719780973221L;
	private JTextField txt_Time;
	private boolean isRunning = false;
	/**
	 * 存放图片的目录，简单起见，存放图片的目录中图片个数为初始化的行列数乘积的一半
	 */
	private String picDir = "pic";
	private String[] picture;
	protected boolean isStart;
	private PicPanel preOne = null;
	/**
	 * 用于标示已找到的对数
	 */
	private int count;
	private JPanel panel_Pic;

	public RememberCard() {
		setTitle("My Game");

		JPanel panel_top = new JPanel();
		getContentPane().add(panel_top, BorderLayout.NORTH);

		JLabel lbl_player0 = new JLabel("*** Player 0,");
		JLabel lbl_player1 = new JLabel("*** Player 1,");
		panel_top.add(lbl_player0);

		JLabel lbl_scoreName = new JLabel("score:");
		panel_top.add(lbl_scoreName);

		JLabel ldl1 = new JLabel("- Choose your first square");
		JLabel lbl2 = new JLabel("- Choose your second square");
		JLabel lbl3 = new JLabel("- well done - click a square to continue");
		JLabel lbl4 = new JLabel("- failed :(-click a square to end turn)");
		JLabel lbl5 = new JLabel("- Player 0's turn.Please wait");
		JLabel lbl6 = new JLabel("- Player 1's turn.Please wait");

		panel_top.add(ldl1);
		panel_Pic = new JPanel();
		getContentPane().add(panel_Pic, BorderLayout.CENTER);

		if (isRunning) {
			return;
		}
		setRunning(true);
		startGame();

		initPicPanels();
	}

	/**
	 * 初始化图片面板
	 */
	private void initPicPanels() {
		panel_Pic.setLayout(new GridLayout(ROWS, COLUMNS, 0, 0));
		initPictureIndex();
		for (int i = 0; i < ROWS * COLUMNS; i++) {
			PicPanel panel_1 = new PicPanel(this, picture[i]);
			panel_Pic.add(panel_1);
		}
	}

	/**
	 * 开始游戏
	 */
	protected void startGame() {
		new Thread() {
			@Override
			public void run() {
				while (count < ROWS * COLUMNS / 2) {

				}
				JOptionPane.showMessageDialog(null, "成功！共耗时");
				// 结束后重新初始化一下面板以便于下一次的运行
				count = 0;
				panel_Pic.removeAll();
				initPicPanels();
				panel_Pic.validate();
				isRunning = false;
			}
		}.start();
	}

	/**
	 * 初始化图片的索引并赋值每个图片的路径
	 */
	private void initPictureIndex() {
		picture = new String[ROWS * COLUMNS];
		List<ColorLabel> colorLabelList = createColorLabel(picture.length);// 16个
	}

	private List<ColorLabel> createColorLabel(int length) {
		Random rand = new Random();
		List<ColorLabel> colorList = new LinkedList<>();
		int num=0;
		while (num<16) {
			int red = rand.nextInt(255);
			int green = rand.nextInt(255);
			int blue = rand.nextInt(255);
			Color clr = new Color(red, green, blue);
			if (!colorList.contains(clr)) {
				ColorLabel colorLabel= new ColorLabel(40, 40, clr);
				colorList.add(colorLabel);
				num++;
			}
		}
		return colorList;
	}


	/**
	 * 将map中的key转换成一个list，其中每个key的value表示该key出现的次数，转换中如果次数多于1需要重复添加key到list中
	 * 
	 * @param map
	 * @return
	 */
	private List<Integer> mapKeyToList(HashMap<Integer, Integer> map) {
		List<Integer> list = new ArrayList<Integer>();
		Iterator<Integer> keyIt = map.keySet().iterator();
		Integer key = 0;
		while (keyIt.hasNext()) {
			key = keyIt.next();
			if (map.get(key) == 1) {
				list.add(key);
			} else {
				for (int i = 0; i < map.get(key); i++) {
					list.add(key);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				RememberCard remCard = new RememberCard();
				remCard.setSize(400, 300);
				remCard.setLocationRelativeTo(null);
				remCard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				remCard.setVisible(true);
			}
		});
	}

	public PicPanel getPreOne() {
		return preOne;
	}

	public void setPreOne(PicPanel preOne) {
		this.preOne = preOne;
	}

	public void addCount() {
		count++;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}

/**
 * @author jqs
 * 
 *         图片面板，主要实现了图片的显示与图片相同判断
 */
class PicPanel extends JPanel {
	private static final long serialVersionUID = 2172162568449349737L;
	private String picPath;
	private JLabel lbl_Pic = new JLabel();
	private ImageIcon bgIcon = null;
	private boolean isShow = false;
	private RememberCard parent;
	private boolean finished = false;

	public PicPanel(RememberCard rememberCard, String picPath) {
		this.picPath = picPath;
		this.parent = rememberCard;
		this.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 2)));
		this.setLayout(new BorderLayout());
		this.add(lbl_Pic, BorderLayout.CENTER);
		this.addMouseListener(mouseAdapter);
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	/**
	 * 图片面板的鼠标事件监听，配对过程在此完成
	 */
	private MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			new Thread() {
				public void run() {
					if (!parent.isRunning() || finished) {
						return;
					}
					isShow = !isShow;
					if (isShow) {
						if (bgIcon == null) {
							initLabelImage();
						}
						PicPanel curOne = (PicPanel) lbl_Pic.getParent();
						PicPanel preOne = parent.getPreOne();
						if (preOne == null) {
							parent.setPreOne(curOne);
						} else {
							boolean right = checkRight(curOne, preOne);
							if (right) {
								parent.setPreOne(null);
								curOne.setFinished(true);
								preOne.setFinished(true);
								parent.addCount();
							} else {
								lbl_Pic.setIcon(bgIcon);
								repaint();
								try {
									Thread.sleep(50);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								lbl_Pic.setIcon(null);
								isShow = !isShow;
								repaint();
								preOne.getMouseListeners()[0].mouseClicked(null);
								parent.setPreOne(null);
								return;
							}
						}
						lbl_Pic.setIcon(bgIcon);
					} else {
						lbl_Pic.setIcon(null);
					}
					repaint();

				};
			}.start();
		}

		/**
		 * 检查两个面板显示的图片是否一致，根据图片的路径来判断，同时要保证两个面板不是同一个面板
		 * 
		 * @param curOne
		 * @param preOne
		 * @return
		 */
		private boolean checkRight(PicPanel curOne, PicPanel preOne) {
			return curOne.getPicPath().equals(preOne.getPicPath()) && !curOne.equals(preOne);
		}
	};

	/**
	 * 初始化Label对象的image
	 */
	private void initLabelImage() {
		try {
			Image image = ImageIO.read(new File(picPath));
			if (image != null) {
				int lblWidth = this.getWidth();
				int lblHeight = this.getHeight();
				bgIcon = new ImageIcon(image.getScaledInstance(lblWidth, lblHeight, Image.SCALE_DEFAULT));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 当找到配对的图片面板后设置完成状态为true，此时点击图片面板已经无效了。
	 * 
	 * @param b
	 */
	protected void setFinished(boolean b) {
		finished = b;
	}
}
