package engine.graphics.rendering.scene;

import engine.graphics.math.geometry.ProjectionSettings;
import engine.graphics.rendering.DrawType;
import engine.graphics.rendering.Camera;
import engine.models.entity.Entity;

import java.awt.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Scene {
	private Color backgroundColor;
	private final ArrayList<Entity> entities;
	private LightSource lightSource;

	private final ProjectionSettings projectionSettings;

	public Scene(
			Color backgroundColor, LightSource lightSource,
			ProjectionSettings projectionSettings, Entity... entities
	) {
		this.backgroundColor = backgroundColor;

		this.entities = new ArrayList<>(Arrays.asList(entities));

		this.lightSource = lightSource;

		this.projectionSettings = projectionSettings;
	}

	public void renderScene(Graphics2D g, DrawType drawType, Camera camera) {
		// Render the entities
		for (Entity entity: entities) {
			entity.render(g, drawType, lightSource, camera, projectionSettings);
		}
	}

	public void addEntitiesToScene(Entity... entities) {
		this.entities.addAll(Arrays.asList(entities));
	}

	@Override
	public Scene clone() {
		Entity[] entities = new Entity[this.entities.size()];
		for (int i = 0; i < entities.length; i++) {
			entities[i] = this.entities.get(i).clone();
		}

		return new Scene(
				new Color(backgroundColor.getRGB()), lightSource.clone(),
				projectionSettings.clone(), entities
		);
	}

	// Getters and Setters
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public LightSource getLightSource() {
		return lightSource;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setLightSource(LightSource lightSource) {
		this.lightSource = lightSource;
	}

	public ProjectionSettings getProjectionValues() {
		return projectionSettings;
	}
}
