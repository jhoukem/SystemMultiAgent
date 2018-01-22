package model;

import java.util.Collections;
import java.util.List;

public class EquitableScheduler implements Scheduler{

	@Override
	public void schedule(List<Agent> agents) {
		Collections.shuffle(agents, SMA.rd);
		for(Agent agent : agents){
			agent.decide();
		}
	}

}
