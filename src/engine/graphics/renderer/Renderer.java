package engine.graphics.renderer;

import engine.Engine;
import engine.geometry.DrawType;
import engine.graphics.camera.Camera;

import java.awt.Graphics;

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
	public void render(Graphics g) {
		g.setColor(scene.getBackgroundColor());
		g.fillRect(0, 0, Engine.SCREEN_WIDTH, Engine.SCREEN_HEIGHT);

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
