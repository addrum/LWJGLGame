package com.adamshort.base;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class View {

	// time at last frame
	private long lastFrame;
	// last fps time
	private long lastFPS;
	// frames per second
	private int fps;
	// is vsync enabled
	private boolean vsync;

	Quad quad1 = new Quad();

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// init OpenGL here
		initGL();
		// call once before loop to initialise lastFrame
		getDelta();
		// call before loop to initialise fps timer
		lastFPS = getTime();

		while (!Display.isCloseRequested()) {
			int delta = getDelta();

			update(delta);
			renderGL();

			Control control = new Control(quad1, delta, this);
			control.keyboardEvents();

			Display.update();
			// change parameter to cap frames to that number
			Display.sync(60);
		}

		Display.destroy();
	}

	public void update(int delta) {

		float quad1Y = quad1.getY();
		float quad1X = quad1.getX();
		// keep quad on the screen
		if (quad1Y < 0)
			quad1.setY(0);
		if (quad1X < 0)
			quad1.setX(0);
		if (quad1Y > 600)
			quad1.setY(600);
		if (quad1X > 800)
			quad1.setX(800);

		updateFPS();
	}

	// calculate how many milliseconds have passed since last frame
	// @return milliseconds passed since last frame
	public int getDelta() {
		/* love you */long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	// get the accurate system time
	// @return the system time in milliseconds
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	// calculate the fps and set it in the title bar
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("" + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	private void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	public void renderGL() {
		// clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		quad1.setColour(0.5f, 0.5f, 1.0f);
		quad1.drawQuad();
	}

	// @param width the width of the display required
	// @param height the height of the display required
	// @param fullscreen true if we want fullscreen mode
	public void setDisplayMode(int width, int height, boolean fullscreen) {
		// return if requested DisplayMode is already set
		if ((Display.getDisplayMode().getWidth() == width) && (Display.getDisplayMode().getHeight() == height) && (Display.isFullscreen() == fullscreen)) {
			return;
		}
		
		try {
			DisplayMode targetDisplayMode = null;
			
			if (fullscreen) {
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;
				
				for (int i = 0; i < modes.length; i++) {
					DisplayMode current = modes[i];
					
					if ((current.getWidth() == width) && (current.getHeight() == height)) {
						if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
							targetDisplayMode = current;
							freq = targetDisplayMode.getFrequency();
						}
					}
					
					// if we've found a match for bpp and frequence against the
					// original display mode then it's probably best to go for this one
					// since it's most likely compatible with the monitor
					if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) && (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
						targetDisplayMode = current;
						break;
					}
				}
			} else {
				targetDisplayMode = new DisplayMode(width, height);
			}
			
			if (targetDisplayMode == null) {
				System.out.println("Failed to find value mode: " + width + "x" + height + " fs=" + fullscreen);
				return;
			}
			
			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);
		} catch (LWJGLException e) {
			System.out.println("Unable to setup mode " + width + "x" + height + "fullscreen=" + fullscreen + e);
		}
	}
	
	public void setVsync(boolean vsync) {
		this.vsync = vsync;
	}
	
	public boolean isVsync() {
		return vsync;
	}
	
}