package berkerdemoglu.engine.input.mouse;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * The <code>Mouse</code> class implements {@link MouseWheelListener}
 * and as of now it is only used for zooming in an out.
 */
public class Mouse implements MouseWheelListener {
	private int scroll = 0;
	public static final double zoomFactor = 1.2;

	public boolean isScrollingUp() {
		return scroll == -1;
	}

	public boolean isScrollingDown() {
		return scroll == 1;
	}

	public void resetScroll() {
		scroll = 0;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll = e.getWheelRotation();
	}
}
