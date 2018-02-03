package core.scheduler;

import java.util.List;

import core.model.Agent;
import core.model.Environment;

public class Scheduler {

	public void schedule(Environment environment){
		List<Agent<?>> agents = getAgentToSchedule(environment);
		if(!environment.isStopSimulation()){
			environment.setScheduling(true);
			for(Agent<?> agent : agents){
				agent.decide();
				agent.update();
			}
			environment.setScheduling(false);
			environment.processPendingOperations();
			environment.tick();
		}
	}

	protected List<Agent<?>> getAgentToSchedule(Environment environment) {
		return environment.getAgents();
	}

}
