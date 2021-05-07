package engine.input.keyboard;

import engine.geometry.DrawType;
import engine.graphics.Renderer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DrawListener implements KeyListener {
	private final Renderer renderer;

	public DrawListener(Renderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
			DrawType drawType = renderer.getDrawType();
			drawType = DrawType.ordToDrawType(drawType.ordinal() + 1);

			renderer.setDrawType(drawType);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
}
