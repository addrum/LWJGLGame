package com.adamshort.base;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.adamshort.base.Entity.Direction;

public class Control {

	private int delta;
	private PlayerEntity player;
	private View view;

	public Control(View view, Entity player, int delta) {
		this.view = view;
		this.delta = delta;
		try {
			Keyboard.create();
			Keyboard.enableRepeatEvents(true);
			keyboardEvents();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.out.println("Couldn't create keyboard!");
		}
	}

	public void keyboardEvents() {
		// polled input - movement, keys that need to be held
		if (Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.isRepeatEvent()) {
					player.move(Direction.UP, delta);
				}
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.isRepeatEvent()) {
					player.move(Direction.LEFT, delta);
				}
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.isRepeatEvent()) {
					player.move(Direction.DOWN, delta);
				}
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.isRepeatEvent()) {
					player.move(Direction.RIGHT, delta);
				}
			}
		}
		// event driven input - power up or menu click
		while (Keyboard.next()) {
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