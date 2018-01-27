package core.model;

public class Cell {

	private int x,y;
	private Agent agent;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
		if(agent != null){
			agent.x = x;
			agent.y = y;
		}
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public boolean isEmpty() {
		return this.agent == null;
	}

}
