package engine.input.keyboard;

import engine.geometry.Axis;
import engine.geometry.Mesh;
import engine.graphics.Renderer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RotateXListener implements KeyListener {
	private final Renderer renderer;

	public RotateXListener(Renderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
			case KeyEvent.VK_NUMPAD2:
				for (Mesh mesh: renderer.getMeshes()) {
					mesh.rotate(Axis.xAxis, 1, true);
				}
				break;
			case KeyEvent.VK_NUMPAD8:
				for (Mesh mesh: renderer.getMeshes()) {
					mesh.rotate(Axis.xAxis, 1, false);
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
