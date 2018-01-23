package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class SMA extends Observable{

	private int ticks = 0;
	public static Random rd;
	private Parameters params;
	private int sleepTime;
	private Grid grid;
	private List<Agent> agents = new ArrayList<Agent>();
	private Scheduler scheduler;

	public SMA() {
		params = new Parameters("config.cfg");
		loadParameters();
		createAgents();
	}

	private void loadParameters() {
		rd = new Random(params.getSeed());
		grid = new Grid(params.getGridWidth(), params.getGridHeight());
		sleepTime = params.getDelay();
		scheduler = params.getScheduler();
		grid.setTorique(params.isTorique());
	}

	private void createAgents() {

		//		for(int i = 0; i < 10; i++){
		//			Agent agent = new Agent(grid);
		//			agents.add(agent);
		//			agent.setVelocityX(1);
		//			agent.setVelocityY(0);
		//			agent.setPosition(i, 0);
		//		 }

		for(int i = 0; i < params.getNbParticules(); i++){
			Agent agent = new Agent(grid, i);
			agents.add(agent);
			agent.setTrace(params.isTrace());
		}
	}

	public void run(){
		while(ticks < params.getNbTicks() || params.getNbTicks() == 0){
			Logger.log("Tick;"+ticks);
			notifyObservers();
			sleep(sleepTime);
			scheduler.schedule(agents);
			ticks++;
		}
		Logger.close();
	}

	private void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

	public Grid getGrid() {
		return grid;
	}

	@Override
	public void notifyObservers() {
		this.setChanged();
		super.notifyObservers();
	}

	public Parameters getParameters() {
		return params;
	}

}
