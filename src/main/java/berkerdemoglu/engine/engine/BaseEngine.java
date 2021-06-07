package engine.engine;

import engine.graphics.display.Display;
import engine.graphics.display.Settings;

public abstract class BaseEngine extends Display {
	protected BaseEngine(Settings settings) {
		super(settings);
	}

	abstract public void init();
}
