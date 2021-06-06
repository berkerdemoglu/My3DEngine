package engine.graphics.rendering;

import engine.graphics.display.Settings;
import engine.graphics.rendering.scene.Scene;

import java.awt.*;

/**
 * The <code>Renderer</code> is responsible for rendering scenes.
 */
public class Renderer {
	private DrawType drawType;
	private Scene scene;
	public final Camera camera;

	/**
	 * Create a new <code>Renderer</code>.
	 */
	public Renderer(Scene scene) {
		this.scene = scene;

		drawType = DrawType.FILL;

		camera = new Camera();
	}

	/**
	 * Renders all entities.
	 * @param g Graphics object used for rendering
	 */
	public void render(Graphics2D g, Settings settings) {
		g.setColor(scene.getBackgroundColor());
		g.fillRect(0, 0, settings.SCREEN_WIDTH(), settings.SCREEN_HEIGHT());

		scene.renderScene(g, drawType, camera);
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
