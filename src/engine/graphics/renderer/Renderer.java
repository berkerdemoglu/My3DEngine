package engine.graphics.renderer;

import engine.geometry.DrawType;

import java.awt.Graphics;

/**
 * The <code>Renderer</code> is responsible for rendering scenes.
 */
public class Renderer {
	private DrawType drawType;
	private Scene scene;

	/**
	 * Create a new <code>Renderer</code>.
	 */
	public Renderer(Scene scene) {
		drawType = DrawType.FILL;

		this.scene = scene;
	}

	/**
	 * Renders all entities.
	 * @param g Graphics object used for rendering
	 */
	public void render(Graphics g) {
		scene.renderScene(g, drawType);
	}

	// Getters and Setters
	public void setDrawType(DrawType drawType) {
		this.drawType = drawType;
	}

	public DrawType getDrawType() {
		return drawType;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Scene getScene() {
		return scene;
	}
}
