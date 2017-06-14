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
 * @author jqs ��Ҫʵ�ּ��䷭�ƹ���
 */
public class RememberCard extends JFrame {

    /**
     * ��ʼ����Ϸ�����������������ɼ�����Ϊż��
     */
    private static final int ROWS = 3;
    private static final int COLUMNS = 4;
    private static final long serialVersionUID = -8908268719780973221L;
    private JTextField txt_Time;
    private boolean isRunning = false;
    /**
     * ���ͼƬ��Ŀ¼������������ͼƬ��Ŀ¼��ͼƬ����Ϊ��ʼ�����������˻���һ��
     */
    private String picDir = "D:\\pics";
    private String[] picture;
    protected boolean isStart;
    private PicPanel preOne = null;
    /**
     * ���ڱ�ʾ���ҵ��Ķ���
     */
    private int count;
    private JPanel panel_Pic;

    public RememberCard() {
        setTitle("\u5BFB\u627E\u76F8\u540C\u5361\u724C");

        JPanel panel_Time = new JPanel();
        getContentPane().add(panel_Time, BorderLayout.NORTH);

        JLabel lbl_Time = new JLabel("\u7528\u65F6\uFF1A");
        panel_Time.add(lbl_Time);

        txt_Time = new JTextField();
        txt_Time.setEditable(false);
        panel_Time.add(txt_Time);
        txt_Time.setColumns(10);

        JLabel lbl_Unit = new JLabel("\u79D2");
        panel_Time.add(lbl_Unit);

        JButton btn_Start = new JButton("\u5F00\u59CB");
        panel_Time.add(btn_Start);
        panel_Pic = new JPanel();
        getContentPane().add(panel_Pic, BorderLayout.CENTER);
        btn_Start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    return;
                }
                setRunning(true);
                startGame();

            }
        });
        initPicPanels();
    }

    /**
     * ��ʼ��ͼƬ���
     */
    private void initPicPanels() {
        panel_Pic.setLayout(new GridLayout(ROWS, COLUMNS, 5, 5));
        initPictureIndex();
        for (int i = 0; i < ROWS * COLUMNS; i++) {
            PicPanel panel_1 = new PicPanel(this, picture[i]);
            panel_Pic.add(panel_1);
        }
    }

    /**
     * ��ʼ��Ϸ
     */
    protected void startGame() {
        new Thread() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                while (count < ROWS * COLUMNS / 2) {
                    txt_Time.setText(((System.currentTimeMillis() - startTime) / 1000)
                            + "");
                }
                JOptionPane.showMessageDialog(null,
                        "�ɹ�������ʱ" + txt_Time.getText() + "�롣");
                // ���������³�ʼ��һ������Ա�����һ�ε�����
                count = 0;
                panel_Pic.removeAll();
                initPicPanels();
                txt_Time.setText(null);
                panel_Pic.validate();
                isRunning = false;
            }
        }.start();
    }

    /**
     * ��ʼ��ͼƬ����������ֵÿ��ͼƬ��·��
     */
    private void initPictureIndex() {
        picture = new String[ROWS * COLUMNS];

        // ����û�м��ͼƬĿ¼���ļ�����Ч�ԣ���Ҫ��֤����ͼƬ���͡�
        File file = new File(picDir);
        File[] pics = file.listFiles();

        // ��ʼ��һ��ROWS*COLUMNS��int���飬������ÿ��ͼƬ������
        int[] indexs = getIndexs(picture.length, pics.length);
        for (int i = 0; i < indexs.length; i++) {
            picture[i] = pics[indexs[i]].getAbsolutePath();
        }
    }

    /**
     * �����ṩ��ͼƬ����Ŀ������ͼƬ���ǻ�����ͬ�ģ��õ�һ������Ϊsum������������ʾÿ��ͼƬ������
     * 
     * @param sum
     *            ��Ϸ���������˻�
     * @param picNums
     *            ����Ŀ¼��ͼƬ������Ŀ
     * @return
     */
    private int[] getIndexs(int sum, int picNums) {
        int half = sum / 2;

        if (picNums < half) {
            return getIndexsByMap(sum, picNums);
        }
        int[] tmpResult = new int[sum];
        Random random = new Random(System.currentTimeMillis());
        int temp = 0;
        LinkedList<Integer> list = new LinkedList<Integer>();
        while (list.size() != half) {
            temp = random.nextInt(picNums);
            if (!list.contains(temp)) {
                list.add(temp);
            } else if (picNums < half) {
                list.add(temp);
            }
        }

        for (int i = 0; i < tmpResult.length; i++) {
            tmpResult[i] = list.get(i >= half ? i % half : i);
        }
        // ��˳����ң���������ǰ�벿�ֺͺ�벿������ȫ�ֿ������
        LinkedList<Integer> _result = new LinkedList<Integer>();
        while (_result.size() != sum) {
            temp = random.nextInt(sum);
            if (!_result.contains(temp)) {
                _result.add(temp);
            }
        }
        int[] result = new int[sum];
        for (int i = 0; i < result.length; i++) {
            result[i] = tmpResult[_result.get(i)];
        }
        return result;
    }

    /**
     * ��ͼƬ����С���ܸ�����һ��ʱ��Ҫʹ������ķ�����ȡ����֤���е�ͼƬ����ʹ����
     * 
     * @param sum
     * @param picNums
     * @return
     */
    private int[] getIndexsByMap(int sum, int picNums) {
        int half = sum / 2;
        int[] tmpResult = new int[sum];
        Random random = new Random(System.currentTimeMillis());
        int temp = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        // ��ΪͼƬ������С��sum��һ�룬����Ȱ�˳��ͼƬ��������ӵ�map���Ա�֤�õ��Ľ����ÿ��ͼƬ����ʹ�õ�
        for (int i = 0; i < picNums; i++) {
            map.put(i, 1);
        }
        int size = picNums;
        while (size != half) {
            temp = random.nextInt(picNums);
            if (!map.containsKey(temp)) {
                map.put(temp, 1);
            } else {
                map.put(temp, map.get(temp) + 1);
            }
            size++;
        }

        List<Integer> list = mapKeyToList(map);
        for (int i = 0; i < tmpResult.length; i++) {
            tmpResult[i] = list.get(i >= half ? i % half : i);
        }
        // ��˳����ң���������ǰ�벿�ֺͺ�벿������ȫ�ֿ������
        LinkedList<Integer> _result = new LinkedList<Integer>();
        while (_result.size() != sum) {
            temp = random.nextInt(sum);
            if (!_result.contains(temp)) {
                _result.add(temp);
            }
        }
        int[] result = new int[sum];
        for (int i = 0; i < result.length; i++) {
            result[i] = tmpResult[_result.get(i)];
        }
        return result;
    }

    /**
     * ��map�е�keyת����һ��list������ÿ��key��value��ʾ��key���ֵĴ�����ת���������������1��Ҫ�ظ����key��list��
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
 *         ͼƬ��壬��Ҫʵ����ͼƬ����ʾ��ͼƬ��ͬ�ж�
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
        this.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0,
                0), 2)));
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
     * ͼƬ��������¼���������Թ����ڴ����
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
                                preOne.getMouseListeners()[0]
                                        .mouseClicked(null);
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
         * ������������ʾ��ͼƬ�Ƿ�һ�£�����ͼƬ��·�����жϣ�ͬʱҪ��֤������岻��ͬһ�����
         * 
         * @param curOne
         * @param preOne
         * @return
         */
        private boolean checkRight(PicPanel curOne, PicPanel preOne) {
            return curOne.getPicPath().equals(preOne.getPicPath())
                    && !curOne.equals(preOne);
        }
    };

    /**
     * ��ʼ��Label�����image
     */
    private void initLabelImage() {
        try {
            Image image = ImageIO.read(new File(picPath));
            if (image != null) {
                int lblWidth = this.getWidth();
                int lblHeight = this.getHeight();
                bgIcon = new ImageIcon(image.getScaledInstance(lblWidth,
                        lblHeight, Image.SCALE_DEFAULT));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ���ҵ���Ե�ͼƬ�����������״̬Ϊtrue����ʱ���ͼƬ����Ѿ���Ч�ˡ�
     * 
     * @param b
     */
    protected void setFinished(boolean b) {
        finished = b;
    }
}