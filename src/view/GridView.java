package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Agent;
import model.Grid;

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


	public GridView(Grid grid) {
		this.grid = grid;
		grid.addObserver(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintGrid(g);
	}

	private void paintGrid(Graphics g) {
		
		cellSize = getCorrectSize()/grid.getSize();
		widthPadding = (this.getWidth() - grid.getSize() * cellSize) / 2;
		heightPadding = (this.getHeight() - grid.getSize() * cellSize) / 2;

		for (int i = 0; i < grid.getSize(); i++) {
			for (int j = 0; j < grid.getSize(); j++) {

				Agent agent = grid.getAgentToPos(j, i);

				if(agent != null){
					g.setColor(agent.getColor());
					g.fill3DRect(j * cellSize + widthPadding, i * cellSize + heightPadding, cellSize, cellSize, true);

				} else {
					g.setColor(Color.WHITE);
					g.fillRect(j * cellSize + widthPadding, i * cellSize + heightPadding, cellSize, cellSize);

				}

			}
		}
	}
	private int getCorrectSize() {
		return this.getWidth() > this.getHeight() ? this.getHeight() : this.getWidth();
	}
	
	public void display() {
		for(int i = 0; i < grid.getSize(); i++){
			for(int j = 0; j < grid.getSize(); j++){
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
		repaint();	
//		display();
	}

}
