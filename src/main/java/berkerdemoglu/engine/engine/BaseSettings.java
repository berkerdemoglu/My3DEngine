package berkerdemoglu.engine.engine;

import berkerdemoglu.engine.graphics.display.Settings;

public class BaseSettings implements Settings {

	public BaseSettings() {}

	@Override
	public int SCREEN_WIDTH() {
		return 600;
	}

	@Override
	public int SCREEN_HEIGHT() {
		return 600;
	}

	@Override
	public int FPS() {
		return 60;
	}

	@Override
	public String TITLE() {
		return "3D Engine";
	}
}
