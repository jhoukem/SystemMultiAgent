package core.scheduler;

import java.util.Collections;
import java.util.List;

import core.model.Agent;
import core.model.Environment;

public class EquitableScheduler extends Scheduler{

	@Override
	protected List<Agent<?>> getAgentToSchedule(Environment environment) {
		// Shuffle the order of the agents so they are not updated on the same sequential order.
		Collections.shuffle(environment.getAgents());
		return super.getAgentToSchedule(environment);
	}

}
