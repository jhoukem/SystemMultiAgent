package core.scheduler;

import java.util.ArrayList;
import java.util.List;

import core.model.Agent;
import core.model.Environment;

public class RandomScheduler extends Scheduler{

	ArrayList<Agent<?>> agentToSchedule = new ArrayList<Agent<?>>();
	
	@Override
	protected List<Agent<?>> getAgentToSchedule(Environment environment) {
		
		agentToSchedule.clear();
		int agentCount =  environment.getAgents().size();
		for(int i = 0; i < agentCount; i++){
			agentToSchedule.add(environment.getAgents().get(environment.random.nextInt(agentCount)));
		}
		return agentToSchedule;
	}
}
