package engine.graphics;

import engine.geometry.entity.Entity;
import engine.geometry.DrawType;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

public class Renderer {
	private final int width;
	private final int height;

	private final ArrayList<Entity> entities;
	private DrawType drawType;

	public Renderer(int width, int height) {
		this.width = width;
		this.height = height;

		entities = new ArrayList<>();
		drawType = DrawType.FILL;
	}

	public void render(Graphics g) {
		for (Entity entity: entities) {
			entity.render(g, drawType);
		}
	}

	public void addEntity(Entity... entities) {
		this.entities.addAll(Arrays.asList(entities));
	}

	@Override
	public String toString() {
		return entities.toString();
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setDrawType(DrawType drawType) {
		this.drawType = drawType;
	}

	public DrawType getDrawType() {
		return drawType;
	}
}
