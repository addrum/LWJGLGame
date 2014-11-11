package com.adamshort.base;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;

public abstract class Entity {
	
	protected float x, y, dx, dy;
	
	private float rotation = 0;
	private int width, height, health;
	
	// this object's bounds for collisions
	private Rectangle me = new Rectangle();
	// other object's bounds for collisions
	private Rectangle him = new Rectangle();
	
	public enum Direction {
		LEFT, RIGHT, UP, DOWN;
	}
	
	protected Entity(float x, float y, int health) {
		this.x = x;
		this.y = y;
		this.health = health;
		width = 100;
		height = 100;
	}
	
	public void move(long delta) {
		// update location of entity based on move speeds
		x += (delta * dx) / 1000;
		y += (delta * dy) / 1000;
	}

	public void draw() {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x - 50, y - 50);
		GL11.glVertex2f(x + 50, y - 50);
		GL11.glVertex2f(x + 50, y + 50);
		GL11.glVertex2f(x - 50, y + 50);
		GL11.glEnd();
	}
	
	public void doLogic() {
		
	}

	public void setColour(float r, float g, float b) {
		// set colour of quad (rgb)
		GL11.glColor3f(r, g, b);
	}
	
	public void rotateQuad(float rotation, int delta, Direction direction) {
		switch (direction) {
			case LEFT: {
				this.rotation += rotation * delta;
				break;
			}
			case RIGHT: {
				this.rotation -= rotation * delta;
				break;
			}
			default: {
				throw new IllegalArgumentException("Waah! Can't process: " + direction);
			}
		}
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(rotation, 0f, 0f, 1f);
		GL11.glTranslatef(-x, -y, 0);
		GL11.glPopMatrix();
	}
	
	// check if this entity collided with another
	// @param other the other entity to check collision against
	// @return true if the entities collide with each other
	public boolean collidesWith(Entity other) {
		me.setBounds((int) x, (int) y, getWidth(), getHeight());
		him.setBounds((int) other.x, (int) other.y, other.getWidth(), other.getHeight());
		
		return me.intersects(him);
	}
	
	// notification that this entity collided with another
	// @param other the entity with which this entity collided with
	public abstract void collidedWith(Entity other);

	// set the horizontal speed of this entity
	// @param dx the horizontal speed of this entity (pixels/sex)
	public void setHorizontalMovement(float dx) {
		this.dx = dx;
	}
	
	// set the vertical speed of this entity
	// @param dy the vertical speed of this entity (pixels/sec)
	public void setVerticalMovement(float dy) {
		this.dy = dy;
	}
	
	// @return the x location of this entity
	public float getX() {
		return x;
	}
	
	// @return the horizontal speed of this entity (pixels/sec)
	public float getHorizontalMovement() {
		return dx;
	}
	
	// @return the y location of this entity
	public float getY() {
		return y;
	}
	
	// @return the vertical speed of this entity (pixels/sec)
	public float getVerticalMovement() {
		return dy;
	}
	
	// @return the width of this entity
	public int getWidth() {
		return width;
	}
	
	// @return the height of this entity
	public int getHeight() {
		return height;
	}

}