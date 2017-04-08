import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuiClass extends JFrame {
	static int clickCount = 0;
	

	public GuiClass() {

		super("Winning or Losing game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 400);
		GridBagConstraints gridCon = new GridBagConstraints();
		JPanel panelNew = new JPanel();
		panelNew.setLayout(new GridBagLayout());
		add(panelNew);

		// create image object  for reel1
		ImageIcon img1 = new ImageIcon(getClass().getResource("bell.png"));
		JLabel lbl1 = new JLabel(img1);
		panelNew.add(lbl1);

		// create image object  for reel2
		ImageIcon img2 = new ImageIcon(getClass().getResource("cherry.png"));
		JLabel lbl2 = new JLabel(img2);
		panelNew.add(lbl2);

		// create image object  for reel3
		ImageIcon img3 = new ImageIcon(getClass().getResource("plum.png"));
		JLabel lbl3 = new JLabel(img3);
		panelNew.add(lbl3);

		// create button for add coin
		JButton btnAddCoin = new JButton("Add Coin");
		gridCon.gridx = 0;
		gridCon.gridy = 2;
		gridCon.insets = new Insets(30, 120, 10, 10);
		panelNew.add(btnAddCoin, gridCon);

		// create button for bet one
		JButton btnBetOne = new JButton("Bet One");
		gridCon.gridx = 1;
		gridCon.gridy = 2;
		gridCon.insets = new Insets(30, -160, 10, 10);
		panelNew.add(btnBetOne, gridCon);

		//create  button for bet max
		JButton btnBetMax = new JButton("Bet Max");
		gridCon.gridx = 1;
		gridCon.gridy = 2;
		gridCon.insets = new Insets(30, 30, 10, 10);
		panelNew.add(btnBetMax, gridCon);

		//create button for reset
		JButton btnReset = new JButton("Reset");
		gridCon.gridx = 2;
		gridCon.gridy = 2;
		gridCon.insets = new Insets(30, -270, 10, 10);
		panelNew.add(btnReset, gridCon);

		// this for status
		JLabel stat = new JLabel();
		gridCon.gridx = 2;
		gridCon.gridy = 2;
		gridCon.insets = new Insets(30, 10, 10, 10);
		panelNew.add(stat, gridCon);
		TestClass testC = new TestClass(stat, lbl1, lbl2, lbl3);
		testC.setText();

		// create button for Spin
		JButton btnSpin = new JButton("Spin");
		gridCon.gridx = 2;
		gridCon.gridy = 3;
		gridCon.insets = new Insets(-4, -80, 10, 10);
		panelNew.add(btnSpin, gridCon);
		Reel reel1 = new Reel(lbl1);
		Reel reel2 = new Reel(lbl2);
		Reel reel3 = new Reel(lbl3);

		// create button for statics
		JButton btnStatics = new JButton("Statictics");
		gridCon.gridx = 1;
		gridCon.gridy = 3;
		gridCon.insets = new Insets(-4, -270, 10, 10);
		panelNew.add(btnStatics, gridCon);
		
		// this for message of win or try again
		JLabel txtStatus = new JLabel(" Result");
		gridCon.gridx = 1;
		gridCon.gridy = 3;
		gridCon.insets = new Insets(-4, 40, 10, 10);
		panelNew.add(txtStatus, gridCon);

		// ---------------------------------------------------------------------------------------------------------------------------

		lbl1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				btnBetOne.setEnabled(true);
				btnBetMax.setEnabled(true);
				btnReset.setEnabled(true);
				btnAddCoin.setEnabled(true);

				super.mouseClicked(mouseEvent);
				reel1.stopCompair();

				clickCount++;
				if (clickCount == 3) {

					txtStatus.setText(testC.winning(reel1.getGenNumber(), reel2.getGenNumber(), reel3.getGenNumber()));

				}
			}
		});

		lbl2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				btnBetOne.setEnabled(true);
				btnBetMax.setEnabled(true);
				btnReset.setEnabled(true);
				btnAddCoin.setEnabled(true);

				super.mouseClicked(mouseEvent);
				reel2.stopCompair();

				clickCount++;
				if (clickCount == 3) {
					txtStatus.setText(testC.winning(reel1.getGenNumber(), reel2.getGenNumber(), reel3.getGenNumber()));

				}
			}
		});

		lbl3.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				btnBetOne.setEnabled(true);
				btnBetMax.setEnabled(true);
				btnReset.setEnabled(true);
				btnAddCoin.setEnabled(true);

				super.mouseClicked(mouseEvent);
				reel3.stopCompair();
				
				clickCount++;
				if (clickCount == 3) {

					txtStatus.setText(testC.winning(reel1.getGenNumber(), reel2.getGenNumber(), reel3.getGenNumber()));

				}
			}
		});

		btnAddCoin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				testC.setCredit();
				testC.setText();
		

			}
		});

		btnBetOne.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				testC.setBet();
				testC.setText();

			}
		});

		btnBetMax.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				testC.setMaxBet();
				testC.setText();
				// btnBetOne.setEnabled(false);

			}
		});
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testC.setReset();
				testC.setText();
			}
		});

		btnSpin.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent actionEvent) {

				if (clickCount == 3) {
					txtStatus.setText("");
					reel1.timer.start();
					reel2.timer.start();
					reel3.timer.start();

					clickCount = 0;

					btnBetOne.setEnabled(false);
					btnBetMax.setEnabled(false);
					btnReset.setEnabled(false);
					btnAddCoin.setEnabled(false);

				} else {

					if (testC.getBet() != 0) {

						reel1.start();
						reel2.start();
						reel3.start();

					} else {

						JOptionPane.showMessageDialog(null, " Hey user, Please Bet First ", "",
								JOptionPane.WARNING_MESSAGE);
					}
				}

			}

		});
		btnStatics.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				testC.viewStatics(GuiClass.this);
				

			}
		});

		setVisible(true);

	}

}
