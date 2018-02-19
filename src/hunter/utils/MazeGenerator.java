package hunter.utils;

import java.util.ArrayList;
import java.util.Stack;

import core.model.Cell;
import hunter.model.HunterEnvironment;
import hunter.model.MovingWallAgent;
import hunter.model.WallAgent;

public class MazeGenerator {

	public static int MAZE_SEGMENT_SIZE = 2;
	// 										 LEFT,	  RIGHT,   UP,       DOWN.
	private final static int DIRECTION[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private static final int X = 0;
	private static final int Y = 1;


	private final static ArrayList<Cell> visited = new ArrayList<Cell>();
	private final static Stack<Cell> stack = new Stack<Cell>();

	private static int distance = 0;

	public static void generateMaze(HunterEnvironment environment){

		fillGridWithWalls(environment);
		createMazePath(environment);
		openMazePath(environment);
	}

	private static void openMazePath(HunterEnvironment environment) {
		for(int i = 0; i < environment.getHeight(); i++){
			for(int j = 0; j < environment.getWidth(); j++){
				float alea = environment.random.nextFloat();
				Cell cell = environment.getCell(j, i);
				if(doesCellHavePathAround(environment, cell)){
					if(alea < environment.getParameters().getSpacedAreaPercent()){
						environment.remove(cell.getAgent());
						cell.setAgent(null);
					}
				}
			}
		}
	}

	private static void createMazePath(HunterEnvironment environment) {
		Cell startingCell = environment.getCell(environment.getWidth()/2, environment.getHeight()/2);

		visited.clear();
		stack.clear();

		visited.add(startingCell);
		stack.push(startingCell);

		while(!stack.isEmpty()){

			Cell current = stack.peek();

			int[] nextDirection = null;
			int index = environment.random.nextInt(DIRECTION.length);
			Cell nextCell = null;
			boolean pathFound = false;
			for(int i = 0; i < 4 ; i++){
				nextDirection = DIRECTION[index];
				nextCell = environment.getCell(current.getX() + nextDirection[X]*MAZE_SEGMENT_SIZE, current.getY() +  + nextDirection[Y]*MAZE_SEGMENT_SIZE);

				if(nextCell != null && !visited.contains(nextCell) && !doesCellHavePathAround(environment, nextCell)){
					createPathFromTo(environment, current, nextDirection);
					pathFound = true;
					break;
				}
				else {
					index = (index + 1)%DIRECTION.length;
				}
			}

			// Havent found any path so we go back.
			if(!pathFound){
				stack.pop();
			}
		}
	}

	private static void createPathFromTo(HunterEnvironment environment, Cell current, int[] direction) {

		Cell cell = null;

		for(int i = 0; i < MAZE_SEGMENT_SIZE; i++){

			int x = current.getX() + direction[X]*i;
			int y = current.getY() + direction[Y]*i;

			cell = environment.getCell(x, y);	

			environment.remove(cell.getAgent());
			cell.setAgent(null);
			visited.add(cell);
			//			if(!environment.getPathMap().containsKey(cell)){
			//				environment.getPathMap().put(cell, distance++);
			//			}
		}

		// Only add the last cell to the frontier.
		stack.add(cell);

	}

	private static boolean doesCellHavePathAround(HunterEnvironment environment, Cell nextCell) {

		for(Cell cell : environment.getNeighbors(nextCell)){
			if(cell != null && cell.isEmpty()){
				return true;
			}
		}
		return false;
	}

	private static void fillGridWithWalls(HunterEnvironment environment) {
		for(int i = 0; i < environment.getHeight(); i++){
			for(int j = 0; j < environment.getWidth(); j++){
				WallAgent wall;
				if(environment.getParameters().isMovingWall()){
					wall = new MovingWallAgent(environment);
				} else {
					wall = new WallAgent(environment);
				}
				environment.add(wall);
				Cell cell = environment.getCell(j, i);
				cell.setAgent(wall);
			}
		}
	}
}
