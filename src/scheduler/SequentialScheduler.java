package scheduler;

import java.util.List;

import model.Agent;

public class SequentialScheduler implements Scheduler{

	@Override
	public void schedule(List<Agent> agents) {
		for(Agent agent : agents){
			agent.decide();
			agent.update();
		}
	}
}
