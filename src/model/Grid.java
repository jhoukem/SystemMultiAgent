package model;

public class Grid {


	private Agent gridAgent[][];
	private boolean isTorique = false;

	public Grid(int width, int height) {
		gridAgent = new Agent[height][width];

	}

	public void setAgentToPos(Agent agent, int x, int y){
		gridAgent[y][x] = agent;
	}

	public Agent getAgentToPos(int x, int y){
		return gridAgent[y][x];
	}

	public boolean isEmpty(int x, int y){
		return getAgentToPos(x, y) == null;
	}

	public boolean isTorique() {
		return isTorique;
	}

	public void setTorique(boolean isTorique) {
		this.isTorique = isTorique;
	}

	public int getWidth(){
		return gridAgent[0].length;
	}
	
	public int getHeight(){
		return gridAgent.length;
	}

	public int nbAgents() {
		int count = 0;
		for (int i = 0; i < getHeight(); i++) {
			for (int j = 0; j < getWidth(); j++) {
				if(gridAgent[i][j] != null){
					count++;
				}
			}
		}
		return count;
	}

}
