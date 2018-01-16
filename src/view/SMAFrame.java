package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.SMA;

public class SMAFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 900;
	public static final int HEIGHT = 700;

	private SMA sma;


	public SMAFrame() {
		sma = new SMA();

		JPanel gridPanel = new GridView(sma.getGrid());

		this.setLayout(new BorderLayout());
		this.add(gridPanel, BorderLayout.CENTER);

		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	public void run() {
		sma.run();
	}


}
