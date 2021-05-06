package engine.input.keyboard;

import engine.geometry.Axis;
import engine.geometry.Mesh;
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

	public Keyboard(Renderer renderer) {
		this.renderer = renderer;
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

		// Press the keys
		if (numpad7Pressed)
			for (Mesh mesh: renderer.getMeshes()) {
				mesh.rotate(Axis.zAxis, 1, false);
			}

		if (numpad9Pressed)
			for (Mesh mesh: renderer.getMeshes()) {
				mesh.rotate(Axis.zAxis, 1, true);
			}

		if (numpad4Pressed)
			for (Mesh mesh: renderer.getMeshes()) {
				mesh.rotate(Axis.yAxis, 1, true);
			}

		if (numpad6Pressed)
			for (Mesh mesh: renderer.getMeshes()) {
				mesh.rotate(Axis.yAxis, 1, false);
			}

		if (numpad2Pressed)
			for (Mesh mesh: renderer.getMeshes()) {
				mesh.rotate(Axis.xAxis, 1, true);
			}

		if (numpad8Pressed)
			for (Mesh mesh: renderer.getMeshes()) {
				mesh.rotate(Axis.xAxis, 1, false);
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_NUMPAD7:
				System.out.println("called release numpad7");
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
