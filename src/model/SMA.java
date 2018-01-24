package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import scheduler.Scheduler;
import utils.Logger;
import utils.Parameters;

public class SMA extends Observable{

	private static final String CONFIG_FILE = "config.cfg";
	private int tick = 0;
	public static Random rd;
	private Parameters params;
	private int sleepTime;
	private Grid grid;
	private List<Agent> agents = new ArrayList<Agent>();
	private Scheduler scheduler;

	public SMA() {
		params = new Parameters(CONFIG_FILE);
		loadParameters();
		createAgents();
	}

	private void loadParameters() {
		
		if(params.getSeed() > -1){
			rd = new Random(params.getSeed());
		} else {
			rd = new Random();
		}
		grid = new Grid(params.getGridWidth(), params.getGridHeight());
		sleepTime = params.getDelay();
		scheduler = params.getScheduler();
		grid.setTorique(params.isTorique());
		
		if(params.isTrace()){
			Logger.initialize(params.getLogFile());
		}
	}

	private void createAgents() {

		for(int i = 0; i < params.getNbParticules(); i++){
			Agent agent = new Agent(grid, i);
			agents.add(agent);
			agent.setTrace(params.isTrace());
		}
	}

	public void run(){
		while(tick < params.getTicks() || params.getTicks() == 0){
			if(params.isTrace()){
				Logger.log("Tick;"+tick);
			}
			if(tick % params.getRefreshRate() == 0){
				notifyObservers();
			}
			sleep(sleepTime);
			scheduler.schedule(agents);
			tick++;
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
