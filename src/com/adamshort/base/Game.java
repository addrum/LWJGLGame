package com.adamshort.base;

import java.util.ArrayList;

public class Game {
	
	public static boolean gameRunning = true;
	
	private static View view;
	private boolean logicRequiredThisLoop;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity> removeList = new ArrayList<Entity>();
	private static PlayerEntity player;

	public static void main(String[] argv) {
		// create a new view to see all the magic!
		view = new View(800, 600);
		view.start();
		Control control = new Control(view, player, view.getDelta());
	}
	
	private void init() {
		
	}

	private void startGame() {
		entities.clear();
		initEntities();
	}
	
	private void initEntities() {
		// create player and place them in the centre of the screen
		player = new PlayerEntity((view.getWidth() / 2), (view.getHeight() / 2), 100);
		player.setVerticalMovement(300);
		player.setHorizontalMovement(300);
		
		entities.add(player);
	}
	
	// the entity will be removed or no longer drawn
	// @param entity the entity to be removed
	public void removeEntities(Entity entity) {
		removeList.add(entity);
	}
	
	public void updateLogic() {
		logicRequiredThisLoop = true;
	}
	
	// notification that the player has died
	public void notifyDeath() {
	}

}
