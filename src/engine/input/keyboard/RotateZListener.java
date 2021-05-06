package engine.input.keyboard;

import engine.geometry.Axis;
import engine.geometry.Mesh;
import engine.graphics.Renderer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RotateZListener implements KeyListener {
	private final Renderer renderer;

	public RotateZListener(Renderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
			case KeyEvent.VK_NUMPAD7:
				for (Mesh mesh: renderer.getMeshes()) {
					mesh.rotate(Axis.zAxis, 1, false);
				}
				break;
			case KeyEvent.VK_NUMPAD9:
				for (Mesh mesh: renderer.getMeshes()) {
					mesh.rotate(Axis.zAxis, 1, true);
				}
				break;
		}
	}

	// Empty implementations
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
}
