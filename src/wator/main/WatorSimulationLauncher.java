package wator.main;

import core.model.MultiAgentSystem;
import core.view.SMAFrame;
import wator.model.WatorEnvironment;
import wator.utils.WatorParameters;

public class WatorSimulationLauncher {

	public static void main(String[] args) {

		if(args.length < 1){
			System.err.println("You need to supply the properties file name.");
			return;
		}
		
		WatorParameters parameters = new WatorParameters(args[0]);
		WatorEnvironment environment = new WatorEnvironment(parameters);
		
		MultiAgentSystem multiAgentSimulation = new MultiAgentSystem(environment, parameters);
		new SMAFrame(multiAgentSimulation, parameters);
		multiAgentSimulation.run();
	}
}
