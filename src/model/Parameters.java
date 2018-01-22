package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Parameters {

	private boolean isTorique;// Done
	private int gridWidth, gridHeight;// Done
	private int delay;// Done
	private Scheduler scheduler;// Done
	private int nbTicks;// Done
	private boolean displayGrid;
	private boolean trace;
	private int seed;// Done
	private int nbParticules;// Done

	private Properties properties;

	public Parameters(String paramPath){
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("src/"+paramPath)));
			setParams();
		} catch (IOException e) {
			setDefaultParams();
			e.printStackTrace();
		}

	}

	private void setParams() {
		isTorique = Boolean.parseBoolean(properties.getProperty("isTorique"));
		displayGrid = Boolean.parseBoolean(properties.getProperty("displayGrid"));
		
		try{
			gridWidth = Integer.parseInt(properties.getProperty("gridWidth"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			gridWidth = 10;
		}

		try{
			gridHeight = Integer.parseInt(properties.getProperty("gridHeight"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			gridHeight = 10;
		}

		try{
			delay = Integer.parseInt(properties.getProperty("delay"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			delay = 100;
		}

		try{
			nbTicks = Integer.parseInt(properties.getProperty("nbTicks"));
		} catch(NumberFormatException e){
			e.printStackTrace();
		}

		try{
			seed = Integer.parseInt(properties.getProperty("seed"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			seed = 1;
		}

		try{
			nbParticules = Integer.parseInt(properties.getProperty("nbParticules"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			nbParticules = (int) (gridWidth * gridHeight * 0.2f);
		}


		String schedulerType = properties.getProperty("scheduler");
		if(schedulerType != null){

			switch(schedulerType){
			case "sequential": scheduler = new SequentialScheduler(); break;
			case "random": scheduler = new RandomScheduler(); break;
			default: scheduler = new EquitableScheduler(); break;
			}

		} else {
			scheduler = new EquitableScheduler();
		}

	}

	private void setDefaultParams() {
		isTorique = false;
		gridWidth = 10;
		gridHeight = 10;
		delay = 100;
		scheduler = new EquitableScheduler();
		nbTicks = 0;
		seed = 1;
		nbParticules = (int) (gridWidth * gridHeight * 0.2f);
	}

	public boolean isTorique() {
		return isTorique;
	}

	public void setTorique(boolean isTorique) {
		this.isTorique = isTorique;
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public void setGridSizeX(int gridSizeX) {
		this.gridWidth = gridSizeX;
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public void setGridSizeY(int gridSizeY) {
		this.gridHeight = gridSizeY;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setSchedule(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public int getNbTicks() {
		return nbTicks;
	}

	public void setNbTicks(int nbTicks) {
		this.nbTicks = nbTicks;
	}

	public boolean isDisplayGrid() {
		return displayGrid;
	}

	public void setDisplayGrid(boolean displayGrid) {
		this.displayGrid = displayGrid;
	}

	public boolean isTrace() {
		return trace;
	}

	public void setTrace(boolean trace) {
		this.trace = trace;
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public int getNbParticules() {
		return nbParticules;
	}

	public void setNbParticules(int nbParticules) {
		this.nbParticules = nbParticules;
	}

}
