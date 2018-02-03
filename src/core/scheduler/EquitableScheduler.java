package core.scheduler;

import java.util.Collections;
import java.util.List;

import core.model.Agent;
import core.model.Environment;

public class EquitableScheduler implements Scheduler{

	@Override
	public void schedule(Environment environment) {
		
		List<Agent<?>> agents = environment.getAgents();
		Collections.shuffle(agents, environment.random);
		environment.setScheduling(true);
		for(Agent<?> agent : agents){
			agent.decide();
			agent.update();
		}
		environment.setScheduling(false);
		environment.processPendingOperations();
	}

}
