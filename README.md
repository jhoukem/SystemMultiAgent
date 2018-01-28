# Multi Agent System

This project goal is to offer the possibility to study some very basic 2d based simulations.  
From the [wa-tor](https://en.wikipedia.org/wiki/Wa-Tor) system to a simple particles bouncing system.  

## Add a new system

Since all the systems that are meant to be simulated are all grid 2d based, this code should be easily extendable. To create a new simulation system, you will have three steps to follow:
- First you will need to create a new package for your system.
- Then you will need create all the agents you need in this system (making them extends from the base **Agent** class).
- Finally you will create a new method in the **SMA** class to add all your agents to the simulation and call it into the switch case in the *createAgents()* method from the **SMA**.

## Configuration 

There is a lot of parameters that can be configured for any simulation. Be sure to check the config.cfg file into the source folder and to tweak some parameters.

## Demo

#### Wa-tor

**Legend:**
- **Yellow:** new born fish  
- **Green:**  fish  
- **Pink:** new born predator  
- **Red:** predator  
Note: This implementation of the Wa-Tor system is not exactly the same as the original system because here, a wa-tor agent can move around the height cells around it (North, East, South, West, North-East, North-West, South-East, South-West), when in the original system, it can only move to the four adjacent cells around (North, East, South, West).

![wator](https://user-images.githubusercontent.com/9862039/35482001-fe2ba44c-042e-11e8-85bc-a7c8efff61d7.png)

#### Particles

**Legend:**  
- **Gray:** particles default color.
- **Blue:** particles that have collided with the border.
- **Red:** particles that have collided with another one.

![particles](https://user-images.githubusercontent.com/9862039/35482003-ffb439a0-042e-11e8-922f-dae066af90a0.png)