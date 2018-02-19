# Multi Agent System

This project goal is to offer the possibility to study some very basic 2D based simulations.  
From the [wa-tor](https://en.wikipedia.org/wiki/Wa-Tor) system to a simple particles bouncing system. All the different systems bellow uses the same core class.

## Add a new system

Since all the systems that are meant to be simulated are all grid 2D based, this code should be easily extendable. To create a new simulation system, you will have to create your own packages and make your entities extends from the core package entities (such as the environment, the agent, the parameters etc...). Then you just have to create a Launcher class that will set up all the elements together and run the game.

## Configuration 

There is a lot of parameters that can be configured for any simulation. Be sure to check the *.properties* file for each simulation and try to tweak some parameters.

## Demo

### Wa-tor

**Legend:**
- **Yellow:** new born fish  
- **Green:**  fish  
- **Pink:** new born predator  
- **Red:** predator  

![wator](https://user-images.githubusercontent.com/9862039/36391975-4266d3da-15a9-11e8-93f9-989b280c22b4.png)

Note: This implementation of the Wa-Tor system is not exactly the same as the original system because here, a wa-tor agent can move around the height cells around it (North, East, South, West, North-East, North-West, South-East, South-West), when in the original system, it can only move to the four adjacent cells around (North, East, South, West).

### Hunter System

It is a pac-man like game. You are playing as the blue square and you have to eat 4 defenders (green square) while dodging the enemies (red square). Once you ate 4 defenders a winner agent will pop out. When you eat the winner agent, you win the game. If you get eaten by an enemy it's game over. Press escape to reload the game. You can tweak the MazeGenerator parameters in the properties file to have a different game experience.

![hunter](https://user-images.githubusercontent.com/9862039/36391929-03e628cc-15a9-11e8-915e-4a58b9d6be22.png)

### Particles

**Legend:**  
- **Gray:** particles default color.
- **Blue:** particles that have collided with the border.
- **Red:** particles that have collided with another one.

![particles](https://user-images.githubusercontent.com/9862039/36392227-8f3ae236-15aa-11e8-921e-0b36c131c9fe.png)