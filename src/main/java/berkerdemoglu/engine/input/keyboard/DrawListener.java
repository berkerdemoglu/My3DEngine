package berkerdemoglu.engine.input.keyboard;

import berkerdemoglu.engine.graphics.rendering.DrawType;
import berkerdemoglu.engine.graphics.rendering.Renderer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class implements {@link KeyListener} and is used to switch between different draw types.
 */
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

	// The following methods do not need implementations.
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
}
