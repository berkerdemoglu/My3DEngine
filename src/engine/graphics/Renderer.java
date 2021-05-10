package engine.graphics;

import engine.geometry.entity.Entity;
import engine.geometry.DrawType;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The <code>Renderer</code> is responsible for managing entities to render and actually rendering them.
 */
public class Renderer {
	private final ArrayList<Entity> entities;
	private DrawType drawType;

	/**
	 * Create a new <code>Renderer</code>.
	 */
	public Renderer() {
		entities = new ArrayList<>();
		drawType = DrawType.FILL;
	}

	/**
	 * Renders all entities.
	 * @param g Graphics object used for rendering
	 */
	public void render(Graphics g) {
		for (Entity entity: entities) {
			entity.render(g, drawType);
		}
	}

	/**
	 * Adds an entity to be rendered. This method can be called on its own but using the {@link Display} class's
	 * <code>addEntitiesToRender()</code> method is encouraged.
	 * @param entities Entities to be rendered
	 */
	public void addEntity(Entity... entities) {
		this.entities.addAll(Arrays.asList(entities));
	}

	@Override
	public String toString() {
		return entities.toString();
	}

	// Getters and Setters
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
