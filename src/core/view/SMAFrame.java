package core.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import core.model.MultiAgentSystem;
import core.utils.Parameters;

public class SMAFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMAFrame(MultiAgentSystem multiAgentSystem,  Parameters params) {

		GridView gridPanel = new GridView(multiAgentSystem, params);
		multiAgentSystem.getEnvironment().addObserver(gridPanel);
		gridPanel.setPreferredSize(new Dimension(params.getCellSize() * params.getGridWidth(), params.getCellSize() * params.getGridHeight()));
		
		JScrollPane scrollPane = new JScrollPane(gridPanel);
		scrollPane.setSize(new Dimension(params.getWidth(), params.getHeight()));
		this.setSize(new Dimension(params.getWidth(), params.getHeight()));
		this.setContentPane(scrollPane);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
