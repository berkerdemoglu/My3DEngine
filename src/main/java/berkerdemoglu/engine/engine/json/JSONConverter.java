package berkerdemoglu.engine.engine.json;

import berkerdemoglu.engine.graphics.display.Settings;
import berkerdemoglu.engine.graphics.math.Vector3D;
import berkerdemoglu.engine.graphics.math.geometry.Polygon3D;
import berkerdemoglu.engine.graphics.math.geometry.ProjectionSettings;
import berkerdemoglu.engine.graphics.rendering.scene.LightSource;
import berkerdemoglu.engine.graphics.rendering.scene.Scene;
import berkerdemoglu.engine.models.entity.Entity;
import berkerdemoglu.engine.models.mesh.Mesh;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class JSONConverter {
	private Map<String, Object> map;

	public JSONConverter(String filename) {
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

	public Scene readScene() {
		Map<String, Object> sceneMap = (Map<String, Object>) map.get("scene");
		Color backgroundColor = readColor((Map<String, Integer>) sceneMap.get("background_color"));
		LightSource lightSource = readLightSource((Map<String, Object>) sceneMap.get("light_source"));
		ProjectionSettings projectionSettings = readProjectionSettings((Map<String, Object>) sceneMap.get("projection_settings"));
		Entity[] entities = readEntities((ArrayList<Map<String, Object>>) sceneMap.get("entities"));

		return new Scene(backgroundColor, lightSource, projectionSettings, entities);
	}

	private Entity[] readEntities(ArrayList<Map<String, Object>> entityMapList) {
		int entitiesLength = entityMapList.size();
		Entity[] entities = new Entity[entitiesLength];

		for (int i = 0; i < entitiesLength; i++) {
			entities[i] = readEntity(entityMapList.get(i));
		}

		return entities;
	}

	private Entity readEntity(Map<String, Object> entityMap) {
		ArrayList<Map<String, Object>> meshMapList = (ArrayList<Map<String, Object>>) entityMap.get("meshes");
		int meshesLength = meshMapList.size();

		Mesh[] meshes = new Mesh[meshesLength];

		for (int i = 0; i < meshesLength; i++) {
			meshes[i] = readMesh(meshMapList.get(i));
		}

		return new Entity(meshes);
	}

	private Mesh readMesh(Map<String, Object> meshMap) {
		ArrayList<Map<String, Object>> polygonMapList = (ArrayList<Map<String, Object>>) meshMap.get("polygons");
		int polygonsLength = polygonMapList.size();

		Polygon3D[] polygons = new Polygon3D[polygonsLength];

		for (int i = 0; i < polygonsLength; i++) {
			polygons[i] = readPolygon(polygonMapList.get(i));
		}

		return new Mesh(polygons);
	}

	private Polygon3D readPolygon(Map<String, Object> polygonMap) {
		ArrayList<Map<String, Double>> verticesMapList = (ArrayList<Map<String, Double>>) polygonMap.get("vertices");
		int verticesLength = verticesMapList.size();

		Vector3D[] vertices = new Vector3D[verticesLength];

		for (int i = 0; i < verticesLength; i++) {
			vertices[i] = readVector(verticesMapList.get(i));
		}

		Color color = readColor((Map<String, Integer>) polygonMap.get("color"));

		return new Polygon3D(color, vertices);
	}

	private Color readColor(Map<String, Integer> colorMap) {
		int r = colorMap.get("r");
		int g = colorMap.get("g");
		int b = colorMap.get("b");

		return new Color(r, g, b);
	}

	private Vector3D readVector(Map<String, Double> vectorMap) {
		double a = vectorMap.get("x");
		double b = vectorMap.get("y");
		double c = vectorMap.get("z");

		return new Vector3D(a, b, c);
	}

	private ProjectionSettings readProjectionSettings(Map<String, Object> projectionMap) {
		int width = (int) projectionMap.get("width");
		int height = (int) projectionMap.get("height");
		double fov  = (double) projectionMap.get("fov");
		double near = (double) projectionMap.get("near");
		double far = (double) projectionMap.get("far");

		return new ProjectionSettings(width, height, fov, near, far);
	}

	private LightSource readLightSource(Map<String, Object> light_source) {
		if (light_source == null) return null;  // guard clause

		Vector3D sourceVector = readVector((Map<String, Double>) light_source.get("source"));
		return new LightSource(sourceVector);
	}
}
