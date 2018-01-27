package core.model;

import java.util.Observable;
import java.util.Random;

import core.scheduler.Scheduler;
import core.utils.Logger;
import core.utils.Parameters;
import particules.ParticuleAgent;
import wator.PredatorAgent;
import wator.PreyAgent;

public class SMA extends Observable{

	private static final String CONFIG_FILE = "config.cfg";
	private int tick = 0;
	public static Random rd;
	private Parameters params;
	private int sleepTime;
	private Environment grid;
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
		grid = new Environment(params.getGridWidth(), params.getGridHeight());
		sleepTime = params.getDelay();
		scheduler = params.getScheduler();
		grid.setTorique(params.isTorique());

		if(params.isTrace()){
			Logger.initialize(params.getLogFile());
		}
	}

	private void createParticlesSimulation(){

		for(int i = 0; i < params.getAgentCount(); i++){
			ParticuleAgent agent = new ParticuleAgent(grid);
			agent.initializeRandomPositionAndVelocity();
			grid.add(agent);

		}
	}

	private void createWatorSimulation(){
		
		for(int i = 0; i < params.getAgentCount(); i++){
			Agent agent;

			if(SMA.rd.nextInt(101) < 70){
				agent = new PreyAgent(grid);
			} else {
				agent = new PredatorAgent(grid);
			}
			agent.initializeRandomPositionAndVelocity();
			agent.setTrace(params.isTrace());
			grid.add(agent);
		}
		
	}

	private void createAgents() {

		switch(params.getSimulationType()){
		case "wator": createWatorSimulation(); break;
		default: createParticlesSimulation(); break;
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
			scheduler.schedule(grid.getAgents());
			grid.removeDeadAgents();
			grid.addNewAgents();
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

	public Environment getGrid() {
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
