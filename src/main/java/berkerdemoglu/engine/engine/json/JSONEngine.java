package berkerdemoglu.engine.engine.json;

import berkerdemoglu.engine.graphics.display.Display;
import berkerdemoglu.engine.graphics.display.Settings;
import berkerdemoglu.engine.graphics.rendering.scene.Scene;

public class JSONEngine extends Display {
	public JSONEngine(Settings settings, Scene scene) {
		super(settings, scene);
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("A JSON file must be provided");
		}

		String filename = args[0];

		JSONConverter converter = new JSONConverter(filename);
		Settings settings = converter.readSettings();
	}

	@Override
	protected void update() {

	}
}
