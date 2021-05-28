package engine.engine;

import engine.graphics.display.Display;

import java.awt.RenderingHints;

public abstract class BaseEngine extends Display {
	protected BaseEngine(String name, boolean useAntiAliasing) {
		super(name);

		if (!useAntiAliasing) {
			antiAliasingHints = new RenderingHints(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_OFF
			);
		}
	}

	abstract public void init();
}
