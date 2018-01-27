package core.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import core.model.SMA;
import core.utils.Parameters;

public class SMAFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private SMA sma;

	public SMAFrame() {
		sma = new SMA();
		Parameters params = sma.getParameters();

		GridView gridPanel = new GridView(sma.getGrid(), sma.getParameters());
		sma.addObserver(gridPanel);
		gridPanel.setPreferredSize(new Dimension(params.getCellSize() * params.getGridWidth(), params.getCellSize() * params.getGridHeight()));
		
		JScrollPane scrollPane = new JScrollPane(gridPanel);
		scrollPane.setSize(new Dimension(params.getWidth(), params.getHeight()));
		this.setSize(new Dimension(params.getWidth(), params.getHeight()));
		this.setContentPane(scrollPane);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	public void run() {
		sma.run();
	}


}
