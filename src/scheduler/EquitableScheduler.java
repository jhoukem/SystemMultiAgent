package scheduler;

import java.util.Collections;
import java.util.List;

import model.Agent;
import model.SMA;

public class EquitableScheduler implements Scheduler{

	@Override
	public void schedule(List<Agent> agents) {
		Collections.shuffle(agents, SMA.rd);
		for(Agent agent : agents){
			agent.decide();
			agent.update();
		}
	}

}
