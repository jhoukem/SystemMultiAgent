package model;

import java.util.List;

public class RandomScheduler implements Scheduler{

	@Override
	public void schedule(List<Agent> agents) {

		
		for(int i = 0; i < agents.size(); i++){
			int index = SMA.rd.nextInt(agents.size());
			agents.get(index).decide();
		}

	}

}
