package com.adamshort.base;

public class EnemyEntity extends Entity {

	private Game game;
	
	protected EnemyEntity(Game game, float x, float y, int health) {
		super(x, y, health);
		this.game = game;
	}

	public void move(long delta) {
		super.move(delta);
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
