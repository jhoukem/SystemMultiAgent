package core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import core.scheduler.EquitableScheduler;
import core.scheduler.RandomScheduler;
import core.scheduler.Scheduler;

public class Parameters {

	protected boolean isTorus;// Done
	protected int width, height; // Done
	protected int cellSize; // Done
	protected int gridWidth, gridHeight;// Done
	protected int delay;// Done
	protected int refreshRate;// Done
	protected Scheduler scheduler;// Done
	protected int ticks;// Done
	protected boolean displayGrid; // Done
	protected boolean trace;
	protected int seed;// Done
	protected int agentCount;// Done
	protected String logFile; //Done
	protected Properties properties;

	public Parameters(String paramPath){
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("src/"+paramPath+".properties")));
			setParams();
		} catch (IOException e) {
			setDefaultParams();
			e.printStackTrace();
		}

	}

	protected void setParams() {
		isTorus = Boolean.parseBoolean(properties.getProperty("isTorus"));
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

		boolean defaultAgentCount = false;
		
		try{
			agentCount = Integer.parseInt(properties.getProperty("agentCount"));
			if(agentCount <= 0){
				defaultAgentCount = true;
			} else if(agentCount > gridWidth*gridHeight){
				defaultAgentCount = true;
				System.err.println("The number of agents is superior of the number of cell ( "+gridWidth * gridHeight+" agents max)");
			}
		} catch(NumberFormatException e){
			defaultAgentCount = true;
			e.printStackTrace();
		}

		if(defaultAgentCount){
			agentCount = (int) (gridWidth * gridHeight * 0.2f);
		}
		
		String schedulerType = properties.getProperty("scheduler");
		if(schedulerType != null){

			switch(schedulerType){
			case "sequential": scheduler = new Scheduler(); break;
			case "random": scheduler = new RandomScheduler(); break;
			default: scheduler = new EquitableScheduler(); break;
			}

		} else {
			scheduler = new EquitableScheduler();
		}
	}

	protected void setDefaultParams() {
		isTorus = false;
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
	
	public boolean isTorus() {
		return isTorus;
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

	public int getTicksToSimulate() {
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

}
