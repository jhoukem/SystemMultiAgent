package core.scheduler;

import java.util.List;

import core.model.Agent;
import core.model.SMA;

public class RandomScheduler implements Scheduler{

	@Override
	public void schedule(List<Agent> agents) {

		
		for(int i = 0; i < agents.size(); i++){
			int index = SMA.rd.nextInt(agents.size());
			agents.get(index).decide();
			agents.get(index).update();
		}

	}

}