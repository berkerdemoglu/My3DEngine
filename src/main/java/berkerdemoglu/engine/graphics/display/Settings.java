package berkerdemoglu.engine.graphics.display;

/**
 * The <code>Settings</code> class is used to set and change properties for the <code>Display</code> class.
 */
public interface Settings {
	// Display settings
	int SCREEN_WIDTH();

	int SCREEN_HEIGHT();

	int FPS();

	String TITLE();

	default boolean useAntialiasing() {
		return true;
	}

	// Keyboard input settings
	default double degreeChangeSpeed() {
		return 45.0 / FPS();
	}

	default double cameraMoveSpeed() {
		return 60.0 / FPS();
	}
}
