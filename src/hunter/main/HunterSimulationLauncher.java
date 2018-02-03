package hunter.main;

import core.model.MultiAgentSystem;
import core.view.SMAFrame;
import hunter.model.HunterEnvironment;
import hunter.utils.HunterParameters;
import hunter.view.HunterGridView;

public class HunterSimulationLauncher {

	public static void main(String[] args) {

		if(args.length < 1){
			System.err.println("You need to supply the properties file name.");
			return;
		}
		
		HunterParameters parameters = new HunterParameters(args[0]);
		HunterEnvironment environment = new HunterEnvironment(parameters);
		
		MultiAgentSystem multiAgentSystem = new MultiAgentSystem(environment, parameters);
		
		HunterGridView hunterGridView = new HunterGridView(multiAgentSystem, parameters, environment.getPlayerAvatar());
		
		new SMAFrame(multiAgentSystem, parameters, hunterGridView);
		multiAgentSystem.run();
	}
}
