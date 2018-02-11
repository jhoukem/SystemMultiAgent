package core.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import core.utils.Logger;
import core.utils.Parameters;

public abstract class Environment extends Observable{

	protected Cell grid[][];
	// The number of agent at the start of the simulation.
	protected int agentCount = 0;
	// The id of the next agent to be created.
	protected int agentCounter = 0;
	protected boolean isTorus = false;
	// Whether to log infos.
	protected boolean isTrace = false;
	// The total number of tick for the current simulation.
	protected int tick;
	// Whether to stop the simulation.
	protected boolean stopSimulation = false;
	// Whether the environment is in the scheduling process (can the agents list be modified directly).
	protected boolean isScheduling = false;
	// The random object used everywhere we need something random.
	public final Random random;

	// The list of the agent to update.
	protected List<Agent<?>> agents = new ArrayList<Agent<?>>();
	// The list of the agent to remove after the environment finished updating.
	protected List<Agent<?>> removePending = new ArrayList<Agent<?>>();
	// The list of the agent to add after the environment finished updating.
	protected List<Agent<?>> addPending = new ArrayList<Agent<?>>();


	public Environment(Parameters parameters) {
		initGrid(parameters.getGridWidth(), parameters.getGridHeight());

		if(parameters.getSeed() > -1){
			random = new Random(parameters.getSeed());
		} else {
			random = new Random();
		}
		this.isTorus = parameters.isTorus();
		this.agentCount = parameters.getAgentCount();
	}

	protected abstract void loadAgents();


	protected void initGrid(int width, int height) {
		grid = new Cell[height][width];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Cell(j, i);
			}
		}
	}

	/**
	 * Return the cell for the given coordinates or null of the coordinate are out bounds.
	 * 
	 * @param x
	 * @param y
	 * @return the cell for the given coordinates or null of the coordinate are out bounds.
	 */
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

	/**
	 * Add the current agent to the environment agents list. If the environment is currently being
	 * scheduled, it will be added on the next frame. To avoid any concurrent modification exception.
	 * 
	 * @param agent The agent to add to the environment.
	 */
	public void add(Agent<?> agent) {
		if(isScheduling){
			addPending.add(agent);
		} else {
			agents.add(agent);	
		}
	}

	/**
	 * Remove the current agent from the environment agents list. If the environment is currently being
	 * scheduled, it will be remove on the next frame. To avoid any concurrent modification exception.
	 * 
	 * @param agent The agent to remove from the environment.
	 */
	public void remove(Agent<?> agent){
		if(isScheduling){
			removePending.add(agent);
		} else {
			agents.remove(agent);	
		}
	}

	private void removeDeadAgents() {
		for(Agent<?> agent : removePending){
			agents.remove(agent);
		}
		removePending.clear();
	}

	private void addNewAgents() {
		for(Agent<?> agent : addPending){
			agents.add(agent);
		}
		addPending.clear();
	}

	public void logInfos(){
		Logger.log(getTick()+";");
	}

	public void processPendingOperations() {
		addNewAgents();
		removeDeadAgents();
	}

	public boolean isCellEmpty(int x, int y){
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

	public List<Agent<?>> getAgents() {
		return agents;
	}

	/**
	 * Whether the agent will be deleted after the current tick.
	 * 
	 * @param agent The agent to test.
	 * @return true if the agent will be deleted after the current tick.
	 */
	public boolean isPendingForDeletion(Agent<?> agent) {
		return removePending.contains(agent);
	}

	public void setScheduling(boolean isScheduling) {
		this.isScheduling = isScheduling;
	}

	public void setTrace(boolean trace) {
		isTrace = trace;
	}
	
	public boolean isTrace() {
		return isTrace;
	}

	public Color getBackgroundColor() {
		return Color.DARK_GRAY;
	}

	public boolean isStopSimulation() {
		return stopSimulation;
	}

	public void setStopSimulation(boolean stopSimulation) {
		this.stopSimulation = stopSimulation;
	}

	@Override
	public void notifyObservers() {
		this.setChanged();
		super.notifyObservers();
	}

	public void tick(){
		tick++;
	}

	public int getTick() {
		return tick;
	}

}
