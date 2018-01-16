package model;

import java.util.Observable;

public class Grid extends Observable {


	private int gridSize = 130;
	private Agent gridAgent[][];
	private boolean isTorique = false;

	public Grid() {
		gridAgent = new Agent[gridSize][gridSize];

	}

	public void setAgentToPos(Agent agent, int x, int y){
		gridAgent[y][x] = agent;
		
		if(agent != null)
			notifyObservers();

	}

	public Agent getAgentToPos(int x, int y){
		return gridAgent[y][x];
	}

	public boolean isEmpty(int x, int y){
		return getAgentToPos(x, y) == null;
	}

	public boolean isTorique() {
		return isTorique;
	}

	public void setTorique(boolean isTorique) {
		this.isTorique = isTorique;
	}

	public int getSize(){
		return gridSize;
	}

	@Override
	public void notifyObservers() {
		this.setChanged();
		super.notifyObservers();
	}

	public int nbAgents() {
		int count = 0;
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if(gridAgent[i][j] != null){
					count++;
				}
			}
		}
		return count;
	}

	public void update() {
		notifyObservers();
	}

}
