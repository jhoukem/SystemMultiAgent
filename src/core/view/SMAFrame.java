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

	public SMAFrame(MultiAgentSystem multiAgentSystem, Parameters params, GridView viewPanel) {

		
		if(viewPanel == null){
			viewPanel = new GridView(multiAgentSystem, params);
		}
		
		multiAgentSystem.getEnvironment().addObserver(viewPanel);
		viewPanel.setPreferredSize(new Dimension(params.getCellSize() * params.getGridWidth(), params.getCellSize() * params.getGridHeight()));
		
		JScrollPane scrollPane = new JScrollPane(viewPanel);
		scrollPane.setSize(new Dimension(params.getWidth(), params.getHeight()));
		this.setSize(new Dimension(params.getWidth(), params.getHeight()));
		this.setContentPane(scrollPane);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public SMAFrame(MultiAgentSystem multiAgentSystem, Parameters parameters) {
		this(multiAgentSystem, parameters, null);
	}

}
