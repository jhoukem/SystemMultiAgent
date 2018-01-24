package model;

import java.util.ArrayList;
import java.util.List;

public class Grid {

	private int agentCounter;
	private Agent gridAgent[][];
	private boolean isTorique = false;
	private List<Agent> agents = new ArrayList<Agent>();
	private List<Agent> toRemove = new ArrayList<Agent>();
	private List<Agent> toAdd = new ArrayList<Agent>();

	public Grid(int width, int height) {
		gridAgent = new Agent[height][width];
		agentCounter = 0;
	}

	public void setAgentToPos(Agent agent, int x, int y){
		gridAgent[y][x] = agent;
	}

	public Agent getAgentToPos(int x, int y){
		return gridAgent[y][x];
	}

	public boolean isEmpty(int x, int y){
		return getAgentToPos(x, y) == null;
	}

	public boolean isTorus() {
		return isTorique;
	}

	public void setTorique(boolean isTorique) {
		this.isTorique = isTorique;
	}

	public int getWidth(){
		return gridAgent[0].length;
	}

	public int getHeight(){
		return gridAgent.length;
	}

	public int getNextAgentId(){
		return agentCounter++;
	}

	public int agentsCount() {
		int count = 0;
		for (int i = 0; i < getHeight(); i++) {
			for (int j = 0; j < getWidth(); j++) {
				if(gridAgent[i][j] != null){
					count++;
				}
			}
		}
		return count;
	}

	public void addAgentToRemove(Agent agent) {
		toRemove.add(agent);
	}

	public void removeDeadAgents() {
		for(Agent agent : toRemove){
			agents.remove(agent);
			setAgentToPos(null, agent.x, agent.y);
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

}
