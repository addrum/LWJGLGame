package com.adamshort.base;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

public class Control {
	
	private Quad quad1;
	private int delta;
	
	public Control(Quad quad1, int delta) {
		try {
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.out.println("Couldn't create keyboard!");
		}
		this.quad1 = quad1;
		this.delta = delta;
	}
	
	public void keyboardEvents() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP) {
				System.out.println("W or UP is pressed.");
				float quad1Y = quad1.getY();
				quad1.setY(quad1Y += 0.35f * delta);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_A || Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
				System.out.println("A or LEFT is pressed.");
				float quad1X = quad1.getX();
				quad1.setX(quad1X -= 0.35f * delta);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_S || Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
				System.out.println("S or DOWN is pressed.");
				float quad1Y = quad1.getY();
				quad1.setY(quad1Y -= 0.35f * delta);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_D || Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
				System.out.println("D or RIGHT is pressed.");
				float quad1X = quad1.getX();
				quad1.setX(quad1X += 0.35f * delta);
			}
		}
	}
}