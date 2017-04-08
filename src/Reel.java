import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reel extends Thread {

	static Symbol[] symbol = new Symbol[6];
	boolean spin = true;
	private int genNumber;
	private JLabel label;

	// create constructor
	public Reel(JLabel label) {

		setGenNumber(-1);
		this.label = label;

	}

	public int getGenNumber() {

		return genNumber;

	}

	public void setGenNumber(int genNumber) {

		this.genNumber = genNumber;

	}

	public void addSymbol() { // Initializing symbols

		symbol[0] = new Symbol("bell.png", 6);
		symbol[1] = new Symbol("cherry.png", 2);
		symbol[2] = new Symbol("lemon.png", 3);
		symbol[3] = new Symbol("plum.png", 4);
		symbol[4] = new Symbol("redseven.png", 7);
		symbol[5] = new Symbol("watermelon.png", 5);

	}

	Timer timer;

	public void run() {

		addSymbol();

		timer = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				startCompair(label);

			}

		});

		timer.start();

	}

	public void startCompair(JLabel lbl) {

		// generate random numbers for reels
		genNumber = (int) Math.floor(Math.random() * 5);
		// assign images for reels
		lbl.setIcon(new ImageIcon(getClass().getResource(symbol[genNumber].getImage())));

	}

	public void stopCompair() { // create stop pining method

		// stop reels
		synchronized (this) {

			spin = false;
			try {
				join();
				timer.stop();

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		}

	}

}
