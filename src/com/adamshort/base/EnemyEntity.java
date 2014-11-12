package com.adamshort.base;

public class EnemyEntity extends Entity {

	private Game game;
	
	protected EnemyEntity(int x, int y, int health) {
		super(x, y, health);
	}

	public void move(Direction direction, long delta) {
		super.move(direction, delta);
	}

	// notification that this entity collided with another
	// @param other the entity with which this entity collided with
	@Override
	public void collidedWith(Entity other) {
		if (other instanceof EnemyEntity) {
			game.notifyDeath();
		}
	}
	
}
