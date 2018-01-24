package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Agent;
import model.Grid;
import utils.Parameters;

public class GridView extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7811268030703839096L;

	// The grid to watch.
	protected Grid grid;
	// The size of a cell in pixel.
	protected int cellSize;
	protected int widthPadding;
	protected int heightPadding;
	private boolean displayGrid = false;
	private boolean cellSizeAuto = false;

	public GridView(Grid grid, Parameters parameters) {
		this.grid = grid;
		displayGrid = parameters.isDisplayGrid();
		cellSize = parameters.getCellSize();
		cellSizeAuto = (parameters.getCellSize() == 0);
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
			cellSize = getCorrectSize() / ((grid.getWidth() > grid.getHeight()) ? grid.getWidth() : grid.getHeight());

			widthPadding = (this.getWidth() - grid.getWidth() * cellSize) / 2;
			heightPadding = (this.getHeight() - grid.getHeight() * cellSize) / 2;
		}

		for (int i = 0; i < grid.getHeight(); i++) {
			for (int j = 0; j < grid.getWidth(); j++) {

				Agent agent = grid.getAgentToPos(j, i);

				if(agent != null){
					g.setColor(agent.getColor());
					g.fill3DRect(j * cellSize + widthPadding, i * cellSize + heightPadding, cellSize, cellSize, true);
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
		for(int i = 0; i < grid.getHeight(); i++){
			for(int j = 0; j < grid.getWidth(); j++){
				if(!grid.isEmpty(j, i)){
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

}
