package core.scheduler;

import java.util.List;

import core.model.Agent;

public class SequentialScheduler implements Scheduler{

	@Override
	public void schedule(List<Agent> agents) {
		for(Agent agent : agents){
			agent.decide();
			agent.update();
		}
	}
}
