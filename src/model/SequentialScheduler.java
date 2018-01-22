package model;

import java.util.List;

public class SequentialScheduler implements Scheduler{

	@Override
	public void schedule(List<Agent> agents) {
		for(Agent agent : agents){
			agent.decide();
		}
	}
}
