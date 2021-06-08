package berkerdemoglu.engine.engine;

import berkerdemoglu.engine.graphics.display.Display;
import berkerdemoglu.engine.graphics.display.Settings;

public abstract class BaseEngine extends Display {
	protected BaseEngine(Settings settings) {
		super(settings);
	}

	protected BaseEngine() {
		super(new BaseSettings());
	}

	abstract public void init();
}
