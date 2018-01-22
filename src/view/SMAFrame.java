package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.SMA;

public class SMAFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1800;
	public static final int HEIGHT = 900;

	private SMA sma;


	public SMAFrame() {
		sma = new SMA();

		GridView gridPanel = new GridView(sma.getGrid());
		sma.addObserver(gridPanel);
		
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
