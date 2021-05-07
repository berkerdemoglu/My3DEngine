package engine.input.keyboard;

import engine.Engine;
import engine.geometry.entity.Entity;
import engine.geometry.Axis;
import engine.graphics.Renderer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	private final Renderer renderer;

	private boolean numpad7Pressed;
	private boolean numpad9Pressed;

	private boolean numpad4Pressed;
	private boolean numpad6Pressed;

	private boolean numpad2Pressed;
	private boolean numpad8Pressed;

	private final static double degreeChangeSpeed = 45.0 / Engine.FPS;

	public Keyboard(Renderer renderer) {
		this.renderer = renderer;
	}

	public void pressKeys() {
		if (numpad7Pressed)
			for (Entity entity: renderer.getEntities()) {
				entity.rotate(Axis.zAxis, degreeChangeSpeed, false);
			}

		if (numpad9Pressed)
			for (Entity entity: renderer.getEntities()) {
				entity.rotate(Axis.zAxis, degreeChangeSpeed, true);
			}

		if (numpad4Pressed)
			for (Entity entity: renderer.getEntities()) {
				entity.rotate(Axis.yAxis, degreeChangeSpeed, true);
			}

		if (numpad6Pressed)
			for (Entity entity: renderer.getEntities()) {
				entity.rotate(Axis.yAxis, degreeChangeSpeed, false);
			}

		if (numpad2Pressed)
			for (Entity entity: renderer.getEntities()) {
				entity.rotate(Axis.xAxis, degreeChangeSpeed, false);
			}

		if (numpad8Pressed)
			for (Entity entity: renderer.getEntities()) {
				entity.rotate(Axis.xAxis, degreeChangeSpeed, true);
			}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Make the pressed button's boolean value true
		switch (e.getKeyCode()) {
			case KeyEvent.VK_NUMPAD7:
				numpad7Pressed = true;
				break;
			case KeyEvent.VK_NUMPAD9:
				numpad9Pressed = true;
				break;

			case KeyEvent.VK_NUMPAD4:
				numpad4Pressed = true;
				break;
			case KeyEvent.VK_NUMPAD6:
				numpad6Pressed = true;
				break;

			case KeyEvent.VK_NUMPAD2:
				numpad2Pressed = true;
				break;
			case KeyEvent.VK_NUMPAD8:
				numpad8Pressed = true;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_NUMPAD7:
				numpad7Pressed = false;
				break;
			case KeyEvent.VK_NUMPAD9:
				numpad9Pressed = false;
				break;

			case KeyEvent.VK_NUMPAD4:
				numpad4Pressed = false;
				break;
			case KeyEvent.VK_NUMPAD6:
				numpad6Pressed = false;
				break;

			case KeyEvent.VK_NUMPAD2:
				numpad2Pressed = false;
				break;
			case KeyEvent.VK_NUMPAD8:
				numpad8Pressed = false;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {} // not required, empty implementation
}
