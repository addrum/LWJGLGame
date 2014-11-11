package com.adamshort.base;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Control {

	private Quad quad1;
	private int delta;
	private View view;

	public Control(Quad quad1, int delta, View view) {
		try {
			Keyboard.create();
			Keyboard.enableRepeatEvents(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.out.println("Couldn't create keyboard!");
		}
		this.quad1 = quad1;
		this.delta = delta;
		this.view = view;
	}

	public void keyboardEvents() {
		if (Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.isRepeatEvent()) {
					float quad1Y = quad1.getY();
					quad1.setY(quad1Y += 0.35f * delta);
				}
			}
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_A || Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.isRepeatEvent()) {
					float quad1X = quad1.getX();
					quad1.setX(quad1X -= 0.35f * delta);
					// quad1.rotateQuad(0.15f, delta, Direction.LEFT);
				}
			}
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_S || Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.isRepeatEvent()) {
					float quad1Y = quad1.getY();
					quad1.setY(quad1Y -= 0.35f * delta);
				}
			}
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_D || Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.isRepeatEvent()) {
					float quad1X = quad1.getX();
					quad1.setX(quad1X += 0.35f * delta);
					// quad1.rotateQuad(0.15f, delta, Direction.RIGHT);
				}
			}
		}
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP) {
					float quad1Y = quad1.getY();
					quad1.setY(quad1Y += 0.35f * delta);
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_A || Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
					float quad1X = quad1.getX();
					quad1.setX(quad1X -= 0.35f * delta);
					// quad1.rotateQuad(0.35f, delta, Direction.LEFT);
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S || Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
					float quad1Y = quad1.getY();
					quad1.setY(quad1Y -= 0.35f * delta);
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D || Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
					float quad1X = quad1.getX();
					quad1.setX(quad1X += 0.35f * delta);
					// quad1.rotateQuad(0.35f, delta, Direction.RIGHT);
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_F) {
					view.setDisplayMode(800, 600, !Display.isFullscreen());
					System.out.println("Gone fullscreen? " + Display.isFullscreen());
				} else if (Keyboard.getEventKey() == Keyboard.KEY_V) {
					view.setVsync(!view.isVsync());
					Display.setVSyncEnabled(view.isVsync());
					System.out.println("Vsync enabled? " + view.isVsync());
				}
			}
		}
	}
	
	public void move() {
		
	}
}