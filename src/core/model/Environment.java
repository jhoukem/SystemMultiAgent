package core.model;

import java.util.ArrayList;
import java.util.List;

public class Environment {

	private Cell grid[][];
	private int agentCounter;
	private boolean isTorus = false;
	private List<Agent> agents = new ArrayList<Agent>();
	private List<Agent> toRemove = new ArrayList<Agent>();
	private List<Agent> toAdd = new ArrayList<Agent>();

	public Environment(int width, int height) {
		agentCounter = 0;
		initGrid(width, height);
	}

	private void initGrid(int width, int height) {
		grid = new Cell[height][width];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Cell(j, i);
			}
		}
	}

	public Cell getCell(int x, int y) {
		
		if(isTorus) {
			if(x < 0) {
				x = this.grid[0].length - 1;
			} else {
				x = x % grid[0].length;
			}
			if(y < 0) {
				y = this.grid.length - 1;
			} else {
				y = y % grid.length;
			}	
		} else if(x < 0 || x > this.grid[0].length - 1 || y < 0 || y > this.grid.length - 1) {
			return null;
		}

		return grid[y][x];
	}

	public boolean isEmpty(int x, int y){
		return getCell(x, y).isEmpty();
	}

	public boolean isTorus() {
		return isTorus;
	}

	public void setTorique(boolean isTorique) {
		this.isTorus = isTorique;
	}

	public int getWidth(){
		return grid[0].length;
	}

	public int getHeight(){
		return grid.length;
	}

	public int getNextAgentId(){
		return agentCounter++;
	}

	public void addAgentToRemove(Agent agent) {
		toRemove.add(agent);
	}

	public void removeDeadAgents() {
		for(Agent agent : toRemove){
			Cell cell = getCell(agent.x, agent.y);
			if(cell.getAgent() == agent){
				cell.setAgent(null);
			}
			agents.remove(agent);
		}
		toRemove.clear();
	}

	public List<Agent> getAgents() {
		return agents;
	}

	public void add(Agent agent) {
		agents.add(agent);	
	}

	/**
	 * Used by agents that can create others agents.
	 * 
	 * @param agent
	 */
	public void addLater(Agent agent) {
		toAdd.add(agent);
	}

	public void addNewAgents() {
		for(Agent agent : toAdd){
			agents.add(agent);
		}
		toAdd.clear();
	}

	public boolean isToDelete(Agent agent) {
		return toRemove.contains(agent);
	}

}
