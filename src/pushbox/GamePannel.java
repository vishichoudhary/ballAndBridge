package pushbox;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePannel extends JPanel {

	/*
	 * new -1 ==null 0==greenball 1== red ball 2==blue ball,3==blackBall,4=pink
	 * ball 22==gray 23==green 24==brown 25==yellow
	 */

	JButton[][] box;
	int[][] boxvalue;
	int ball;
	int temp, temp1, temp2, temp3, temp4, temp5;
	int prevb1i, prevb2i, prevb3i, prevb4i, prevb5i;
	int prevb1j, prevb2j, prevb3j, prevb4j, prevb5j;
	int n, leveli, levelj, levelno;
	KeyAdapter hello;

	public GamePannel() {
		initComponents();
		myinit();
		showInfo();
	}

	public void myinit() {
		setSize(820, 620);// our standard game pannel size
		setOpaque(false);// to make buttons transparent
		
		hello = new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				keyInput(e);
			}
		};
		
		levelno = 1;
		
		controlPannel.setBounds(10, 70, 700, 480);
		if (levelno == 1)
			level1();
		if (levelno == 2)
			level2();
		controlPannel.setFocusable(true);
		controlPannel.setOpaque(false);
		controlPannel.setFocusTraversalKeysEnabled(false);
		createBox();// creates boxes
	}// my init

	public void level1() {
		ball = 0;
		temp = temp1 = temp2 = temp3 = temp4 = temp5 = 0;
		prevb1i = prevb2i = prevb3i = prevb4i = prevb5i = 0;
		prevb1j = prevb2j = prevb3j = prevb4j = prevb5j = 0;

		levelno = 1;
		n = 3;

		leveli = 11;
		levelj = 16;
		box = new JButton[leveli][levelj];
		boxvalue = new int[leveli + 1][levelj + 1];

		for (int i = 1; i < leveli; i++) {
			for (int j = 1; j < levelj; j++) {
				boxvalue[i][j] = (i == 1 || i == 10 || j == 1 || j == 15 || j == 5 || (j == 10 && i != 3)
						|| ((i == 6 && j != 2) && (i == 6 && j != 7) && (i == 6 && j != 12))) ? 22 : -1;
			}
		}

		boxvalue[9][2] = 0;
		boxvalue[2][9] = 1;
		boxvalue[9][13] = 2;
		boxvalue[2][2] = 23;
		boxvalue[5][6] = 25;
		boxvalue[5][9] = 25;
		boxvalue[9][4] = 25;
		boxvalue[9][9] = 25;
		boxvalue[6][2] = 24;
		boxvalue[6][7] = 24;
		boxvalue[6][12] = 24;
		boxvalue[3][10] = 24;
		updateBall();
	}

	public void level2() {
		ball = 0;
		temp = temp1 = temp2 = temp3 = temp4 = temp5 = 0;
		prevb1i = prevb2i = prevb3i = prevb4i = prevb5i = 0;
		prevb1j = prevb2j = prevb3j = prevb4j = prevb5j = 0;

		levelno = 2;
		n = 5;

		leveli = 11;
		levelj = 21;
		box = new JButton[leveli][levelj];
		boxvalue = new int[leveli + 1][levelj + 1];

		for (int i = 1; i < leveli; i++) {
			for (int j = 1; j < levelj; j++) {
				boxvalue[i][j] = (i == 1 || i == 10 || j == 1 || j == 20 || j == 7 || (i == 6 && j < 7)
						|| (i < 7 && j == 16) || (i == 7 && j > 7) || (i == 4 && (j > 7 && j < 16))) ? 22 : -1;
			}
		}

		boxvalue[8][6] = 0;
		boxvalue[5][4] = 1;
		boxvalue[2][12] = 2;
		boxvalue[5][12] = 3;
		boxvalue[8][19] = 4;
		boxvalue[9][2] = 25;
		boxvalue[7][2] = 25;
		boxvalue[2][2] = 25;
		boxvalue[2][19] = 23;
		boxvalue[3][15] = 25;
		boxvalue[6][8] = 25;
		boxvalue[8][9] = 24;
		boxvalue[9][15] = 25;
		boxvalue[2][16] = 24;
		boxvalue[2][7] = 24;
		boxvalue[8][7] = 24;
		boxvalue[7][11] = 24;
		boxvalue[5][2] = 25;
		boxvalue[5][7] = 24;
		boxvalue[3][8] = 25;
		boxvalue[5][15] = 25;
		boxvalue[8][8] = 24;
		updateBall();
	}

	public void createBox() {
		controlPannel.setLayout(new GridLayout(10, 15, 0, 0));
		for (int i = 1; i < leveli; i++) {
			for (int j = 1; j < levelj; j++) {
				box[i][j] = new JButton();
				box[i][j].setSize(45, 45);
				box[i][j].setBackground(Color.white);
				box[i][j].setFocusable(false);
				controlPannel.add(box[i][j]);
			}
		}
		controlPannel.grabFocus();
		/*
		 * controlPannel.addKeyListener(new KeyAdapter(){ public void
		 * keyTyped(KeyEvent e){ keyInput(e); } });
		 */
		controlPannel.addKeyListener(hello);

		drawbox();
	}

	public void drawbox() {
		// JOptionPane.showMessageDialog(null,"");
		for (int i = 1; i < leveli; i++) {
			for (int j = 1; j < levelj; j++) {
				switch (boxvalue[i][j]) {
				case 0:
					box[i][j].setIcon(new ImageIcon(getClass().getResource("greenball.png")));
					break;
				case 1:
					box[i][j].setIcon(new ImageIcon(getClass().getResource("redball.png")));
					break;
				case 2:
					box[i][j].setIcon(new ImageIcon(getClass().getResource("blueball.png")));
					break;
				case 3:
					box[i][j].setIcon(new ImageIcon(getClass().getResource("blackball.png")));
					break;
				case 4:
					box[i][j].setIcon(new ImageIcon(getClass().getResource("pinkball.png")));
					break;
				case 22:
					box[i][j].setIcon(new ImageIcon(getClass().getResource("graybox.png")));
					break;
				case 23:
					box[i][j].setIcon(new ImageIcon(getClass().getResource("greenbox.png")));
					break;
				case 24:
					box[i][j].setIcon(new ImageIcon(getClass().getResource("brownbox.png")));
					break;
				case 25:
					box[i][j].setIcon(new ImageIcon(getClass().getResource("yellowbox.png")));
					break;
				default:
					box[i][j].setIcon(null);
				}
			}
		}
	}

	public void keyInput(KeyEvent e) {
		char input = e.getKeyChar();

		if (input == 'a' || input == 'A') {

			level1: for (int i = 1; i < leveli; i++)
				for (int j = 1; j < levelj; j++) {
					if (boxvalue[i][j - 1] != 0 && boxvalue[i][j - 1] != 1 && boxvalue[i][j - 1] != 2
							&& boxvalue[i][j - 1] != 3 && boxvalue[i][j - 1] != 4) {
						if (boxvalue[i][j] == ball && j > 1 && boxvalue[i][j - 1] != 22 && boxvalue[i][j - 1] != 24) {
							temp = 111;
							int re = wincheck(i, j - 1);
							if (re == 1)
								return;
							if (boxvalue[i][j - 1] == 25) {
								if (ball == 0) {
									temp1++;
									prevb1i = i;
									prevb1j = j - 1;
								}
								if (ball == 1) {
									temp2++;
									prevb2i = i;
									prevb2j = j - 1;
								}
								if (ball == 2) {
									temp3++;
									prevb3i = i;
									prevb3j = j - 1;
								}
								if (ball == 3) {
									temp4++;
									prevb4i = i;
									prevb4j = j - 1;
								}
								if (ball == 4) {
									temp5++;
									prevb5i = i;
									prevb5j = j - 1;
								}

							}
							boxvalue[i][j] = -1;
							boxvalue[i][j - 1] = ball;
							if (levelno == 1)
								level1open(i, j - 1);
							if (levelno == 2)
								level2open(i, j - 1);
							break level1;
						}
					}

				}
			if (temp1 == 0 && ball == 0 && temp == 111)
				prev(prevb1i, prevb1j);
			if (temp2 == 0 && ball == 1 && temp == 111)
				prev(prevb2i, prevb2j);
			if (temp3 == 0 && ball == 2 && temp == 111)
				prev(prevb3i, prevb3j);
			if (temp4 == 0 && ball == 3 && temp == 111)
				prev(prevb4i, prevb4j);
			if (temp5 == 0 && ball == 4 && temp == 111)
				prev(prevb5i, prevb5j);
			drawbox();
			if (ball == 0 && temp == 111)
				temp1 = 0;
			if (ball == 1 && temp == 111)
				temp2 = 0;
			if (ball == 2 && temp == 111)
				temp3 = 0;
			if (ball == 3 && temp == 111)
				temp4 = 0;
			if (ball == 4 && temp == 111)
				temp5 = 0;
			temp = 0;

		}
		if (input == 'w' || input == 'W') {

			level1: for (int i = 1; i < leveli; i++)
				for (int j = 1; j < levelj; j++) {
					if (boxvalue[i - 1][j] != 0 && boxvalue[i - 1][j] != 1 && boxvalue[i - 1][j] != 2
							&& boxvalue[i - 1][j] != 3 && boxvalue[i - 1][j] != 4) {
						if (boxvalue[i][j] == ball && i > 1 && boxvalue[i - 1][j] != 22 && boxvalue[i - 1][j] != 24) {
							temp = 222;
							int re = wincheck(i - 1, j);
							if (re == 1)
								return;
							if (boxvalue[i - 1][j] == 25) {
								if (ball == 0) {
									temp1++;
									prevb1i = i - 1;
									prevb1j = j;
								}
								if (ball == 1) {
									temp2++;
									prevb2i = i - 1;
									prevb2j = j;
								}
								if (ball == 2) {
									temp3++;
									prevb3i = i - 1;
									prevb3j = j;
								}
								if (ball == 3) {
									temp4++;
									prevb4i = i - 1;
									prevb4j = j;
								}
								if (ball == 4) {
									temp5++;
									prevb5i = i - 1;
									prevb5j = j;
								}

							}

							boxvalue[i][j] = -1;
							boxvalue[i - 1][j] = ball;
							if (levelno == 1)
								level1open(i - 1, j);
							if (levelno == 2)
								level2open(i - 1, j);
							break level1;
						}
					}

				}
			if (temp1 == 0 && ball == 0 && temp == 222)
				prev(prevb1i, prevb1j);
			if (temp2 == 0 && ball == 1 && temp == 222)
				prev(prevb2i, prevb2j);
			if (temp3 == 0 && ball == 2 && temp == 222)
				prev(prevb3i, prevb3j);
			if (temp4 == 0 && ball == 3 && temp == 222)
				prev(prevb4i, prevb4j);
			if (temp5 == 0 && ball == 4 && temp == 222)
				prev(prevb5i, prevb5j);
			drawbox();
			if (ball == 0 && temp == 222)
				temp1 = 0;
			if (ball == 1 && temp == 222)
				temp2 = 0;
			if (ball == 2 && temp == 222)
				temp3 = 0;
			if (ball == 3 && temp == 222)
				temp4 = 0;
			if (ball == 4 && temp == 222)
				temp5 = 0;
			temp = 0;

		}
		if (input == 's' || input == 'S') {

			level1: for (int i = 0; i < leveli; i++)
				for (int j = 1; j < levelj; j++) {
					if (boxvalue[i + 1][j] != 0 && boxvalue[i + 1][j] != 1 && boxvalue[i + 1][j] != 2
							&& boxvalue[i + 1][j] != 3 && boxvalue[i + 1][j] != 4) {
						if (boxvalue[i][j] == ball && i < 10 && boxvalue[i + 1][j] != 22 && boxvalue[i + 1][j] != 24) {
							temp = 333;
							int re = wincheck(i + 1, j);
							if (re == 1)
								return;
							if (boxvalue[i + 1][j] == 25) {
								if (ball == 0) {
									temp1++;
									prevb1i = i + 1;
									prevb1j = j;
								}
								if (ball == 1) {
									temp2++;
									prevb2i = i + 1;
									prevb2j = j;
								}
								if (ball == 2) {
									temp3++;
									prevb3i = i + 1;
									prevb3j = j;
								}
								if (ball == 3) {
									temp4++;
									prevb4i = i + 1;
									prevb4j = j;
								}
								if (ball == 4) {
									temp5++;
									prevb5i = i + 1;
									prevb5j = j;
								}

							}

							boxvalue[i][j] = -1;
							boxvalue[i + 1][j] = ball;
							if (levelno == 1)
								level1open(i + 1, j);
							if (levelno == 2)
								level2open(i + 1, j);
							break level1;
						}
					}

				}

			if (temp1 == 0 && ball == 0 && temp == 333)
				prev(prevb1i, prevb1j);
			if (temp2 == 0 && ball == 1 && temp == 333)
				prev(prevb2i, prevb2j);
			if (temp3 == 0 && ball == 2 && temp == 333)
				prev(prevb3i, prevb3j);
			if (temp4 == 0 && ball == 3 && temp == 333)
				prev(prevb4i, prevb4j);
			if (temp5 == 0 && ball == 4 && temp == 333)
				prev(prevb5i, prevb5j);
			drawbox();
			if (ball == 0 && temp == 333)
				temp1 = 0;
			if (ball == 1 && temp == 333)
				temp2 = 0;
			if (ball == 2 && temp == 333)
				temp3 = 0;
			if (ball == 3 && temp == 333)
				temp4 = 0;
			if (ball == 4 && temp == 333)
				temp5 = 0;
			temp = 0;
		}
		if (input == 'd' || input == 'D') {
			level1: for (int i = 1; i < leveli; i++)
				for (int j = 1; j < levelj; j++) {
					if (boxvalue[i][j + 1] != 0 && boxvalue[i][j + 1] != 1 && boxvalue[i][j + 1] != 2
							&& boxvalue[i][j + 1] != 3 && boxvalue[i][j + 1] != 4) {
						if (boxvalue[i][j] == ball && j < levelj - 1 && boxvalue[i][j + 1] != 22
								&& boxvalue[i][j + 1] != 24) {
							temp = 444;
							int re = wincheck(i, j + 1);
							if (re == 1)
								return;
							if (boxvalue[i][j + 1] == 25) {
								if (ball == 0) {
									temp1++;
									prevb1i = i;
									prevb1j = j + 1;
								}
								if (ball == 1) {
									temp2++;
									prevb2i = i;
									prevb2j = j + 1;
								}
								if (ball == 2) {
									temp3++;
									prevb3i = i;
									prevb3j = j + 1;
								}
								if (ball == 3) {
									temp4++;
									prevb4i = i;
									prevb4j = j + 1;
								}
								if (ball == 4) {
									temp5++;
									prevb5i = i;
									prevb5j = j + 1;
								}

							}

							boxvalue[i][j] = -1;
							boxvalue[i][j + 1] = ball;
							if (levelno == 1)
								level1open(i, j + 1);
							if (levelno == 2)
								level2open(i, j + 1);
							break level1;
						}
					}
				}
			if (temp1 == 0 && ball == 0 && temp == 444)
				prev(prevb1i, prevb1j);
			if (temp2 == 0 && ball == 1 && temp == 444)
				prev(prevb2i, prevb2j);
			if (temp3 == 0 && ball == 2 && temp == 444)
				prev(prevb3i, prevb3j);
			if (temp4 == 0 && ball == 3 && temp == 444)
				prev(prevb4i, prevb4j);
			if (temp5 == 0 && ball == 4 && temp == 444)
				prev(prevb5i, prevb5j);
			drawbox();
			if (ball == 0 && temp == 444)
				temp1 = 0;
			if (ball == 1 && temp == 444)
				temp2 = 0;
			if (ball == 2 && temp == 444)
				temp3 = 0;
			if (ball == 3 && temp == 444)
				temp4 = 0;
			if (ball == 4 && temp == 444)
				temp5 = 0;
			temp = 0;

		}
		if (input == KeyEvent.VK_SPACE) {
			ball = (ball + 1) % n;
			updateBall();
		}

	}

	public void prev(int i, int j) {
		if (boxvalue[i][j] != 0 && boxvalue[i][j] != 1 && boxvalue[i][j] != 2 && boxvalue[i][j] != 3
				&& boxvalue[i][j] != 4)
			boxvalue[i][j] = 25;

	}

	public void level1open(int i, int j) {
		if (ball == 0 && i == 9 && j == 4)
			boxvalue[6][12] = -1;
		if (ball == 0 && (i != 9 || j != 4))
			boxvalue[6][12] = 24;
		if (ball == 1 && i == 5 && j == 9)
			boxvalue[3][10] = -1;
		if (ball == 1 && (i != 5 || j != 9))
			boxvalue[3][10] = 24;
		if (ball == 2 && i == 5 && j == 6)
			boxvalue[6][7] = -1;
		if (ball == 2 && (i != 5 || j != 6))
			boxvalue[6][7] = 24;
		if (ball == 1 && i == 9 && j == 9)
			boxvalue[6][2] = -1;
		if (ball == 1 && (i != 9 || j != 9))
			boxvalue[6][2] = 24;
	}

	public void level2open(int i, int j) {
		if (ball == 2 && i == 3 && j == 15)
			boxvalue[2][16] = -1;
		if (ball == 2 && (i != 3 || j != 15))
			boxvalue[2][16] = 24;
		if (ball == 1 && i == 2 && j == 2)
			boxvalue[2][7] = -1;
		if (ball == 1 && (i != 2 || j != 2))
			boxvalue[2][7] = 24;
		if (ball == 3 && (i == 6 && j == 8))
			boxvalue[5][7] = -1;
		if (ball == 3 && (i != 6 || j != 8))
			boxvalue[5][7] = 24;
		if (ball == 1 && i == 5 && j == 2)
			boxvalue[8][7] = -1;
		if (ball == 1 && (i != 5 || j != 2))
			boxvalue[8][7] = 24;
		if (ball == 2 && i == 3 && j == 8)
			boxvalue[8][9] = -1;
		if (ball == 2 && (i != 3 || j != 8))
			boxvalue[8][9] = 24;
		if (ball == 4 && i == 9 && j == 15)
			boxvalue[7][11] = -1;
		if (ball == 4 && (i != 9 || j != 15))
			boxvalue[7][11] = 24;
		if (ball == 3 && i == 5 && j == 15)
			boxvalue[8][8] = -1;
		if (ball == 3 && (i != 5 || j != 15))
			boxvalue[8][8] = 24;
		if (ball == 0 && i == 9 && j == 2)
			boxvalue[8][8] = -1;
		if (ball == 0 && (i != 9 || j != 2) && boxvalue[5][15] != 3)
			boxvalue[8][8] = 24;
		if (ball == 0 && i == 7 && j == 2)
			boxvalue[8][9] = -1;
		if (ball == 0 && (i != 7 || j != 2) && boxvalue[3][8] != 2)
			boxvalue[8][9] = 24;
	}

	public int wincheck(int i, int j) {
		if (boxvalue[i][j] == 23) {

			if (levelno == 1) {
				JOptionPane.showMessageDialog(null, "Yeah level 1 cleared, Go to next level");
				levelno = 2;
				controlPannel.removeAll();
				controlPannel.repaint();
				controlPannel.removeKeyListener(hello);
				level2();
				createBox();
				return 1;
			}
			if (levelno == 2) {
				JOptionPane.showMessageDialog(null, "Yeah you win this game go and play other games");
			}
		}
		return 0;
	}

	public void updateBall() {
		String str = "";
		switch (ball) {
		case 0: {
			str = "SkyBlue";
			break;
		}
		case 1: {
			str = "Red";
			break;
		}
		case 2: {
			str = "Blue";
			break;
		}
		case 3: {
			str = "Black";
			break;
		}
		case 4: {
			str = "Pink";
			break;
		}
		}
		BallLabel.setText("Current Ball is :- " + str);
	}

	public void showInfo() {
		String str = "1) Use W to move ball up\n" + "2) Use A to move ball left\n" + "3) Use S to move ball down\n"
				+ "4) Use D to move ball right\n" + "5) Use SPACE to move to other ball\n"
				+ "6) To win make AnyColor Ball to reach green box\n"
				+ "7) Put specific ball on specific Yellow box that will open a specific Brown box\n"
				+ "8) Current ball shows the current ball in movement\n" + "9) RESET button to reset the game\n"
				+ "10) RULES Button to show instruction\n";
		JOptionPane.showMessageDialog(null, str, "INSTRUCTIONS", HEIGHT);
	}

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		setLayout(null);

		// set layout
		controlPannel = new JPanel();
		GroupLayout controlPannelLayout = new GroupLayout(controlPannel);
		controlPannel.setLayout(controlPannelLayout);
		controlPannelLayout.setHorizontalGroup(
				controlPannelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));
		controlPannelLayout.setVerticalGroup(
				controlPannelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

		add(controlPannel);
		controlPannel.setBounds(20, 60, 560, 440);
		controlPannel.getAccessibleContext().setAccessibleName("");

		// set Reset button
		jButton1 = new JButton("Reset");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		add(jButton1);
		jButton1.setBounds(720, 200, 90, 32);

		// set Rules button
		jButton2 = new JButton("Rules");
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});
		add(jButton2);
		jButton2.setBounds(720, 270, 90, 32);

		// set current ball label
		BallLabel = new JLabel("Current Ball is :- ");
		BallLabel.setFont(new Font("Cantarell", 0, 18)); // NOI18N
		BallLabel.setForeground(Color.white);
		add(BallLabel);
		BallLabel.setBounds(260, 560, 220, 20);

		// set label
		jLabel1 = new JLabel("                       Ball And Bridge");
		jLabel1.setBackground(Color.white);
		jLabel1.setFont(new Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
		jLabel1.setForeground(Color.white);
		add(jLabel1);
		jLabel1.setBounds(120, 20, 420, 29);

		// set background image
		background_Image = new JLabel();
		background_Image.setFocusable(false);
		background_Image.setIcon(new ImageIcon(getClass().getResource("/pushbox/try1.jpg"))); // NOI18N
		add(background_Image);
		background_Image.setBounds(0, 0, 820, 620);
	}// </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed

		if (levelno == 1) {
			levelno = 1;
			controlPannel.removeAll();
			controlPannel.repaint();
			controlPannel.removeKeyListener(hello);
			level1();
			createBox();
		}
		if (levelno == 2) {
			levelno = 2;
			controlPannel.removeAll();
			controlPannel.repaint();
			controlPannel.removeKeyListener(hello);
			level2();
			createBox();
		}
		controlPannel.grabFocus();
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton1ActionPerformed

	private void jButton2ActionPerformed(ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		showInfo();
		controlPannel.grabFocus();// TODO add your handling code here:
	}// GEN-LAST:event_jButton2ActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JLabel BallLabel;
	private JLabel background_Image;
	private JPanel controlPannel;
	private JButton jButton1;
	private JButton jButton2;
	private JLabel jLabel1;
	// End of variables declaration//GEN-END:variables
}
