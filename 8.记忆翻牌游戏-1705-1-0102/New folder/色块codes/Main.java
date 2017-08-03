import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main {

	JFrame guiFrame = new JFrame();
	ColorLabel[] arraylabels = new ColorLabel[64];
	Random rand = new Random();

	public static void setDrawColor(Color drawColor) {

	}

	public void createGUI() {
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Label demo");
		guiFrame.pack();
		guiFrame.setVisible(true);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		panel2.setLayout(new BorderLayout(0, 0));

		guiFrame.add(panel1, BorderLayout.CENTER);
		guiFrame.add(panel2, BorderLayout.SOUTH);

		panel1.setLayout(new FlowLayout());
		panel1.setLayout(new GridLayout(8, 8));
		panel2.setLayout(new FlowLayout());

		for (int i = 0; i < 64; i++) {
			int red = rand.nextInt(255);
			int green = rand.nextInt(255);
			int blue = rand.nextInt(255);

			Color clr = new Color(red, green, blue);
			arraylabels[i] = new ColorLabel(40, 40, clr);
			panel1.add(arraylabels[i]);

		}

		class ButtonClicked implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 64; i++) {
					int red = rand.nextInt(255);
					int green = rand.nextInt(255);
					int blue = rand.nextInt(255);

					Color clr = new Color(red, green, blue);
					arraylabels[i].setDrawColor(clr);
				}
				guiFrame.repaint();
			}
		}

		JButton button = new JButton("Press me to refresh labels");

		button.addActionListener(new ButtonClicked());
		panel2.add(button);

	}
}
