package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SMA {

	private int timePerFrame = 100;
	private int sleepTime;
	private final Grid grid = new Grid();
	private List<Agent> agents = new ArrayList<Agent>();
	public static Random rd = new Random(12345);

	public SMA() {
		createAgents();
	}

	private void createAgents() {
		
		int nbAgent = (int) (0.2*grid.getSize());
		
		for(int i = 0; i < nbAgent; i++){
			Agent agent = new Agent(grid);
			agents.add(agent);
		}
		sleepTime = timePerFrame/nbAgent;
	}

	public void run(){
		while(true){
			Collections.shuffle(agents, rd);
			for(Agent agent : agents){
				agent.setColor(Color.GRAY);
				grid.update();
			}
			sleep(sleepTime);
			for(Agent agent : agents){
				agent.decide();
				sleep(sleepTime);
			}
		}
	}

	private void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

	public Grid getGrid() {
		return grid;
	}

}
