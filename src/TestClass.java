import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TestClass extends JFrame {

	static private int wins = 0;
	private int credit;
	private int bet = 0;
	private int losses = 0;
	private double average;
	private JLabel lbl, lbl2, lbl3, lbl4;
	private int freePlay;

	public void setfreePlay() {
		freePlay++;
	}

	public TestClass(JLabel lbl, JLabel lbl2, JLabel lbl3, JLabel lbl4) {

		credit = 10;
		this.lbl = lbl;
		this.lbl2 = lbl2;
		this.lbl3 = lbl3;
		this.lbl4 = lbl4;

	}

	public void setCredit() {
		credit++;

	}

	public int getCredit() {
		return credit;
	}

	public int getBet() {
		return bet;
	}

	public void setBet() {
		bet++;
		credit--;
	}

	public int getWins() {
		return wins;
	}

	public void setWins() {
		wins++;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses() {
		losses++;
	}

	public double getaverage() {
		return average;
	}

	public int getfreePlay() {
		return freePlay;
	}

	public float calAverage() {
		float average = (getWins() / (float) (getWins() + getLosses())) * 100;
		return average;
	}

	public String winning(int reel1, int reel2, int reel3) {

		String status = " You Lost !";

		if (((reel1 == reel2) && (reel2 == reel3)) || reel1 == reel2 || reel2 == reel3 || reel1 == reel3) {

			// matching reels

			if ((reel1 == reel2) && (reel2 == reel3)) {
				credit = credit + (Reel.symbol[reel1].getValue() * bet) * 2;
				status = " You Win !";
				setWins();
				bet = 0;
			} else if (reel1 == reel2) {
				credit = credit + (Reel.symbol[reel1].getValue() * bet) * 2;
				status = "Try Again";
				setfreePlay();
			} else if (reel2 == reel3) {
				credit = credit + (Reel.symbol[reel2].getValue() * bet) * 2;
				status = "Try Again";
				setfreePlay();
			} else if (reel1 == reel3) {
				credit = credit + (Reel.symbol[reel1].getValue() * bet) * 3;
				status = "Try Again";
				setfreePlay();
			}

			setText();

		} else {

			setLosses();
			bet = 0;
			setText();

		}

		return status;

	}

	public void setText() {

		lbl.setText("Credit Area: " + credit);
		if (bet > 0) {
			lbl.setText("[ Credit Area: " + credit + " ]" + "      " + "  [ Bet Area : " + bet + " ]");

		}

	}

	public void setMaxBet() {

		bet = 3;
		credit = credit - 3;

	}

	public void setReset() {
		bet = 0;
		wins = 0;
		credit = 10;
		losses = 0;

	}

	public void WinAverage() {
		int times = wins + losses;
		double winAver = wins / times;
		System.out.println(winAver);
	}

	public void Writter() {

	

		File file = new File("30-01-2017.txt");
		PrintWriter pw = null;
		FileWriter fw = null;
		Scanner sc = null;

		try {
			fw = new FileWriter(file, true);
			pw = new PrintWriter(fw);

			pw.write("\tNumber of Matches:  " + (getWins() + getLosses() + getfreePlay()));
			pw.write("\tWons:  " + getWins());
			pw.write("\tLosses:  " + getLosses());
			pw.write("\tFree plays: " + getfreePlay());
			pw.write("\tCredits: " + getCredit());
			pw.write("\tWinning average: " + calAverage());

			

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error no file save yet");
		} finally {
			pw.flush();
			pw.close();
		}

	}

	public void viewStatics(JFrame frame) {

		JDialog stat = new JDialog(frame, true);
		stat.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		stat.setSize(600, 300);

		GridBagConstraints gridCon = new GridBagConstraints();
		stat.setLayout(new GridBagLayout());

		JLabel lbl1 = new JLabel("This is your Number of Winnings :    " + getWins());
		gridCon.gridy = 0;
		gridCon.gridx = 0;
		gridCon.insets = new Insets(10, 4, 10, 10);
		stat.add(lbl1, gridCon);

		JLabel lbl2 = new JLabel("This is your Number of losses :         " + getLosses());
		gridCon.gridx = 0;
		gridCon.gridy = 2;
		gridCon.insets = new Insets(10, 10, 10, 10);
		stat.add(lbl2, gridCon);

		JLabel lbl3 = new JLabel("This is your ' Credits ' and  ' winning average '  :      " + getCredit()
				+ "      &     " + calAverage());
		gridCon.gridx = 0;
		gridCon.gridy = 4;
		gridCon.insets = new Insets(10, 10, 10, 10);
		stat.add(lbl3, gridCon);

		JLabel lbl4 = new JLabel("Free matches   :      " + getfreePlay());
		gridCon.gridx = 0;
		gridCon.gridy = 6;
		gridCon.insets = new Insets(10, 28, 10, 10);
		stat.add(lbl4, gridCon);

		JButton btn = new JButton("Back");
		gridCon.gridx = -1;
		gridCon.gridy = 10;
		gridCon.insets = new Insets(30, 10, 10, 10);
		stat.add(btn, gridCon);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				stat.dispose();
			}
		});

		JButton btn2 = new JButton("Save Statictics");
		gridCon.gridx = 2;
		gridCon.gridy = 10;
		gridCon.insets = new Insets(30, 10, 10, 10);
		stat.add(btn2, gridCon);

		stat.setVisible(true);
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				Writter();

			}

		});
	}
//	}

	public static void main(String[] args) {

		GuiClass gclass = new GuiClass();

	}
}
