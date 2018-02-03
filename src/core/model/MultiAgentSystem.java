package core.model;

import core.scheduler.Scheduler;
import core.utils.FPSManager;
import core.utils.Logger;
import core.utils.Parameters;

public class MultiAgentSystem {

	// The enviroment where the agent can evoluate.
	private Environment environment;
	// A timer to get fixed fps rate.
	private FPSManager fpsManager = new FPSManager();
	// The number of tick to simulate.
	private int tickToSimulate = 0;
	// The minimum time between each frame.
	private int sleepTime;
	// When to refresh the view.
	private int refreshRate;
	// The scheduling system.
	private Scheduler scheduler;
	// Whether the simulation is paused.
	private boolean paused = false;

	public MultiAgentSystem(Environment enviroment, Parameters param) {
		this.environment = enviroment;
		loadMultiAgentSystemParameters(param);
	}

	private void loadMultiAgentSystemParameters(Parameters parameters) {

		sleepTime = parameters.getDelay();
		scheduler = parameters.getScheduler();
		environment.setTorique(parameters.isTorus());
		tickToSimulate = parameters.getTicksToSimulate();
		refreshRate = parameters.getRefreshRate();

		if(parameters.isTrace()){
			Logger.initialize(parameters.getLogFile());
		}

	}

	public void run(){

		while(needToSimulate()){
			if(!paused){

				fpsManager.updateTimer();
				if(fpsManager.canUpdate(sleepTime)){
					fpsManager.resetTimer();

					if(environment.isTrace()){
						Logger.log("Tick;"+environment.getTick());
					}
					simulateTick();
				} else {
					sleep(fpsManager.getTimeToSleep(sleepTime));
				}
			} else {
				// To allow the paused boolean to be reconsidered.
				sleep(50);
			}
		}
		Logger.close();
	}

	public void simulateTick() {

		if(environment.getTick() % refreshRate == 0){
			environment.notifyObservers();
		}

		scheduler.schedule(environment);
	}

	private boolean needToSimulate() {
		return tickToSimulate < 0  || environment.getTick() < tickToSimulate;
	}

	private void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void pause() {
		paused = !paused;
	}
	
	public boolean isPaused(){
		return paused;
	}

	public void increaseSleepTime() {
		this.sleepTime /= 2;
	}
	
	public void decreaseSleepTime() {
		this.sleepTime *= 2;
	}

}
