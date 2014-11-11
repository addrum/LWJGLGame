package com.adamshort.base;

import org.lwjgl.opengl.GL11;

public class Quad {
	
	protected float x, y;
	
	private float rotation = 0;
	private int width, height;
	
	public enum Direction {
		LEFT, RIGHT, UP, DOWN;
	}
	
	protected Quad(float x, float y) {
		this.x = x;
		this.y = y;
		width = 100;
		height = 100;
	}

	public void drawQuad() {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(rotation, 0f, 0f, 1f);
		GL11.glTranslatef(-x, -y, 0);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x - 50, y - 50);
		GL11.glVertex2f(x + 50, y - 50);
		GL11.glVertex2f(x + 50, y + 50);
		GL11.glVertex2f(x - 50, y + 50);
		GL11.glEnd();
		GL11.glPopMatrix();
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
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

}