package core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import core.scheduler.EquitableScheduler;
import core.scheduler.RandomScheduler;
import core.scheduler.Scheduler;
import core.scheduler.SequentialScheduler;

public class Parameters {

	private boolean isTorique;// Done
	private int width, height; // Done
	private int cellSize; // Done
	private int gridWidth, gridHeight;// Done
	private int delay;// Done
	private int refreshRate;// Done
	private Scheduler scheduler;// Done
	private int ticks;// Done
	private boolean displayGrid; // Done
	private boolean trace;
	private int seed;// Done
	private int agentCount;// Done
	private String logFile; //Done
	private String simulationType; //Done
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
		isTorique = Boolean.parseBoolean(properties.getProperty("isTorus"));
		displayGrid = Boolean.parseBoolean(properties.getProperty("displayGrid"));
		trace = Boolean.parseBoolean(properties.getProperty("trace"));
		logFile = properties.getProperty("logFile");
		
		if(logFile.isEmpty()){
			logFile = "log.csv";
		}
		
		try{
			width = Integer.parseInt(properties.getProperty("width"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			width = 900;
		}
		
		try{
			height = Integer.parseInt(properties.getProperty("height"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			height = 700;
		}
		
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
			cellSize = Integer.parseInt(properties.getProperty("cellSize"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			cellSize = 5;
		}

		try{
			delay = Integer.parseInt(properties.getProperty("delay"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			delay = 100;
		}
		
		try{
			refreshRate = Integer.parseInt(properties.getProperty("refreshRate"));
			if(refreshRate < 0){
				refreshRate = 1;
			}
		} catch(NumberFormatException e){
			e.printStackTrace();
			refreshRate = 1;
		}

		try{
			ticks = Integer.parseInt(properties.getProperty("ticks"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			ticks = 0;
		}

		try{
			seed = Integer.parseInt(properties.getProperty("seed"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			seed = 1;
		}

		boolean defaultParticles = false;
		
		try{
			agentCount = Integer.parseInt(properties.getProperty("agentCount"));
			if(agentCount <= 0){
				defaultParticles = true;
			} else if(agentCount > gridWidth*gridHeight){
				defaultParticles = true;
				System.err.println("The number of agents is superior of the number of cell ( "+gridWidth * gridHeight+" agents max)");
			}
		} catch(NumberFormatException e){
			defaultParticles = true;
			e.printStackTrace();
		}

		simulationType = properties.getProperty("simulationType");
		
		if(defaultParticles){
			agentCount = (int) (gridWidth * gridHeight * 0.2f);
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
		ticks = 0;
		seed = 1;
		agentCount = (int) (gridWidth * gridHeight * 0.2f);
	}

	public String getLogFile() {
		return logFile;
	}
	
	public boolean isTorique() {
		return isTorique;
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public int getDelay() {
		return delay;
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public int getTicks() {
		return ticks;
	}

	public boolean isDisplayGrid() {
		return displayGrid;
	}

	public boolean isTrace() {
		return trace;
	}

	public int getSeed() {
		return seed;
	}

	public int getAgentCount() {
		return agentCount;
	}

	public int getRefreshRate() {
		return refreshRate;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getCellSize() {
		return cellSize;
	}

	public String getSimulationType() {
		return simulationType;
	}


}