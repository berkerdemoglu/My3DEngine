package engine.input.keyboard;

import engine.graphics.Renderer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WireframeDrawListener implements KeyListener {
	private final Renderer renderer;

	public WireframeDrawListener(Renderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
			renderer.setWireframeDraw(!renderer.isWireframeDraw());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
}
