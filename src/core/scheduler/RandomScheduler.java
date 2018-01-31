package core.scheduler;

import java.util.Collections;
import java.util.List;

import core.model.Agent;
import core.model.Environment;

public class RandomScheduler implements Scheduler{


	@Override
	public void schedule(Environment environment) {

		List<Agent> agents = environment.getAgents();
		Collections.shuffle(agents, environment.random);
		environment.setScheduling(true);
		for(int i = 0; i < agents.size(); i++){
			int index = environment.random.nextInt(agents.size());
			agents.get(index).decide();
			agents.get(index).update();
		}
		environment.setScheduling(false);
		environment.processPendingOperations();
	}

}
