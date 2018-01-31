package core.scheduler;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import core.model.Agent;
import core.model.Environment;
import wator.model.PreyAgent;

public class EquitableStepbyStepScheduler implements Scheduler{

	public boolean canStep = false;

	@Override
	public void schedule(Environment environment) {
		List<Agent> agents = environment.getAgents();
		Collections.shuffle(agents, environment.random);
		environment.setScheduling(true);
		System.out.println("start");
		for(Agent agent : agents){
			if(agent instanceof PreyAgent){
				agent.setColor(Color.GRAY);
			} else {
				agent.setColor(Color.MAGENTA);
			}
			environment.notifyObservers();
			while(!canStep){
				sleep(10);
			}
			canStep = false;
			agent.decide();
			agent.update();
			environment.notifyObservers();
		}
		System.out.println("end");
		environment.setScheduling(false);
		environment.processPendingOperations();
	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

}
