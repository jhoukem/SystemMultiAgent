package core.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import core.model.Agent;
import core.model.Environment;
import core.model.MultiAgentSystem;
import core.utils.Parameters;

public class GridView extends JPanel implements Observer, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7811268030703839096L;

	// The grid to watch.
	protected Environment environment;
	// The size of a cell in pixel.
	protected int cellSize;
	protected int widthPadding;
	protected int heightPadding;
	private boolean displayGrid = false;
	private boolean cellSizeAuto = false;
	private MultiAgentSystem multiAgentSystem;

	public GridView(MultiAgentSystem multiAgentSystem, Parameters parameters) {
		this.multiAgentSystem = multiAgentSystem;
		environment = multiAgentSystem.getEnvironment();
		displayGrid = parameters.isDisplayGrid();
		cellSize = parameters.getCellSize();
		cellSizeAuto = (parameters.getCellSize() == 0);
		this.addKeyListener(this);
		this.setFocusable(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintGrid(g);
	}

	private void paintGrid(Graphics g) {

		// Clear the screen.
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		if(cellSizeAuto){
			// Get the correct size based on the grid size and the frame size.
			cellSize = getCorrectSize() / ((environment.getWidth() > environment.getHeight()) ? environment.getWidth() : environment.getHeight());

			widthPadding = (this.getWidth() - environment.getWidth() * cellSize) / 2;
			heightPadding = (this.getHeight() - environment.getHeight() * cellSize) / 2;
		}

		for (int i = 0; i < environment.getHeight(); i++) {
			for (int j = 0; j < environment.getWidth(); j++) {

				Agent<?> agent = environment.getCell(j, i).getAgent();

				if(agent != null){
					g.setColor(agent.getColor());
					g.fillRect(j * cellSize + widthPadding, i * cellSize + heightPadding, cellSize, cellSize);
				} else {
					g.setColor(environment.getBackgroundColor());
					g.fillRect(j * cellSize + widthPadding, i * cellSize + heightPadding, cellSize, cellSize);
				}

				if(displayGrid){
					g.setColor(Color.BLACK);
					g.drawRect(j * cellSize + widthPadding, i * cellSize + heightPadding, cellSize, cellSize);
				}

			}
		}
	}

	private int getCorrectSize() {
		return this.getWidth() > this.getHeight() ? this.getHeight() : this.getWidth();
	}

	public void displayAscii() {
		for(int i = 0; i < environment.getHeight(); i++){
			for(int j = 0; j < environment.getWidth(); j++){
				if(!environment.isCellEmpty(j, i)){
					System.out.print("O");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void keyPressed(KeyEvent key) {
		switch(key.getKeyCode()){
		case KeyEvent.VK_SPACE: multiAgentSystem.pause(); break;
		case KeyEvent.VK_RIGHT: 
			if(multiAgentSystem.isPaused()){
				multiAgentSystem.simulateTick();
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
