package engine.graphics.renderer;

import engine.geometry.DrawType;
import engine.geometry.entity.Entity;
import engine.graphics.camera.Camera;

import java.awt.Graphics;
import java.awt.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class Scene {
	private Color backgroundColor;
	private final ArrayList<Entity> entities;
	private AmbientLightSource ambientLightSource;

	private final int width;
	private final int height;

	public Scene(int width, int height, Color backgroundColor, AmbientLightSource ambientLightSource, Entity... entities) {
		this.width = width;
		this.height = height;
		this.backgroundColor = backgroundColor;

		this.entities = new ArrayList<>(Arrays.asList(entities));

		this.ambientLightSource = ambientLightSource;
	}

	public void renderScene(Graphics g, DrawType drawType, Camera camera) {
		// Draw background
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);

		// Render the entities
		for (Entity entity: entities) {
			entity.render(g, drawType, ambientLightSource, camera);
		}
	}

	public void addEntitiesToScene(Entity... entities) {
		this.entities.addAll(Arrays.asList(entities));
	}

	public Scene cloneScene() {
		return new Scene(width, height, backgroundColor, ambientLightSource, entities.toArray(new Entity[0]));
	}

	// Getters and Setters
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public AmbientLightSource getLightSource() {
		return ambientLightSource;
	}
}
