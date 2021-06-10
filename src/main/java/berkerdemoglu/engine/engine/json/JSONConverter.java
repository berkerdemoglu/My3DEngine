package berkerdemoglu.engine.engine.json;

import berkerdemoglu.engine.graphics.display.Settings;
import berkerdemoglu.engine.graphics.rendering.scene.Scene;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class JSONConverter {
	private final String filename;
	private Map<String, Object> map;

	public JSONConverter(String filename) {
		this.filename = filename;
		try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream inputStream = new FileInputStream(filename);
			TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {};
			map = mapper.readValue(inputStream, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Settings readSettings() {
		Map<String, Object> settingsMap = (Map<String, Object>) map.get("settings");

		return new Settings() {
			@Override
			public int SCREEN_WIDTH() {
				return (int) settingsMap.get("screen_width");
			}

			@Override
			public int SCREEN_HEIGHT() {
				return (int) settingsMap.get("screen_height");
			}

			@Override
			public int FPS() {
				return (int) settingsMap.get("fps");
			}

			@Override
			public String TITLE() {
				return (String) settingsMap.get("title");
			}
		};
	}
}
