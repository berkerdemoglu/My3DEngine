package berkerdemoglu.engine.graphics.math.geometry;

import berkerdemoglu.engine.graphics.math.Vector3D;
import berkerdemoglu.engine.graphics.rendering.DrawType;
import berkerdemoglu.engine.graphics.rendering.Camera;
import berkerdemoglu.engine.graphics.rendering.scene.LightSource;

import java.awt.*;
import java.util.Arrays;

import static java.lang.Math.toRadians;

/**
 * A polygon in 3D space.
 * A <code>Polygon3D</code> is made up of numerous {@link Vector3D}s and a color.
 */
public class Polygon3D {
	private Vector3D[] vertices;
	private Color color;
	private Color lightAdjustedColor;
	private double lightingRatio;

	/**
	 * Constructs a new polygon in 3D space.
	 * @param color Color of the polygon
	 * @param vertices Points in 3D plane that make up the polygon
	 */
	public Polygon3D(Color color, Vector3D... vertices) {
		if (vertices.length <= 2) {
			throw new IllegalArgumentException("2 or more vertices must be provided to make a polygon");
		}

		this.vertices = new Vector3D[vertices.length];
		for (int i = 0; i < vertices.length; i++) {
			this.vertices[i] = vertices[i].clone();
		}

		this.color = color;
	}

	/**
	 * Fallback method for constructing a polygon without a color.
	 * @param vertices Points in 3D plane that make up the polygon
	 */
	public Polygon3D(Vector3D... vertices) {
		this(Color.RED, vertices);
	}

	/**
	 * Render the polygon to the screen.
	 * @param g Graphics object used to draw polygons
	 * @param drawType Signifies which draw type should be used to render this polygon
	 */
	public void render(
			Graphics2D g, DrawType drawType,
			LightSource lightSource, Camera camera,
			ProjectionSettings projectionSettings
	) {
//		if (!shouldBeDrawn(camera)) return;

		updateLighting(lightSource, camera);

		Polygon polygon = new Polygon();
		Point point2D;

		for (Vector3D vector3D: vertices) {
			point2D = Projector.project3DPoint(vector3D, camera,
					projectionSettings.width, projectionSettings.height,
					projectionSettings.fov, projectionSettings.near, projectionSettings.far
			);
			polygon.addPoint(point2D.x, point2D.y);
		}

		switch (drawType) {
			case FILL:
				g.setColor(lightAdjustedColor);
				g.fillPolygon(polygon);
				break;
			case WIREFRAME_DRAW:
				g.setColor(Color.LIGHT_GRAY);
				g.drawPolygon(polygon);
				break;
			case FILL_N_HIGHLIGHT:
				g.setColor(Color.DARK_GRAY);
				g.drawPolygon(polygon);
				g.setColor(lightAdjustedColor);
				g.fillPolygon(polygon);
				break;
		}
	}

	public boolean shouldBeDrawn(Camera camera) {
		Vector3D point = new Vector3D(
				vertices[0].x - camera.position.x,
				vertices[0].y - camera.position.y,
				vertices[0].z - camera.position.z
		);

		double dotProduct = Vector3D.dotProduct(getNormalVector(), point);

		return dotProduct < 0;
	}

	private void updateLighting(LightSource lightSource, Camera camera) {
		// Check if a light source was provided.
		if (lightSource == null) {
			lightAdjustedColor = new Color(color.getRGB());
			return;
		}

		// Calculate the light vector to the polygon.
		Vector3D lightVector = lightSource.getLightVectorTo(getCentroid(), camera);

		// Get lighting ratio for the polygon.
		double dotProduct = Vector3D.dotProduct(getNormalVector(), lightVector);
		int sign = (dotProduct < 0 ? -1: 1);
		lightingRatio = (dotProduct*dotProduct*sign + 1) / 2;

		// Update the lit color of the polygon.
		int red = (int) (color.getRed() * lightingRatio);
		int green = (int) (color.getGreen() * lightingRatio);
		int blue = (int) (color.getBlue() * lightingRatio);

		lightAdjustedColor = new Color(red, green, blue);
	}

	public Vector3D getNormalVector() {
		Vector3D line1 = new Vector3D(vertices[0], vertices[1]);
		Vector3D line2 = new Vector3D(vertices[1], vertices[2]);

		Vector3D normalVector = Vector3D.normalize(Vector3D.crossProduct(line2, line1));
		return normalVector;
	}

	/**
	 * Currently unused.
	 * @param axis
	 * @param inc
	 */
	public void translate(Axis axis, double inc) {
		// Move the polygon by the increment amount in the specified axis.
		switch (axis) {
			case xAxis:
				for (Vector3D v: vertices) {
					v.x += inc;
				}
				break;
			case yAxis:
				for (Vector3D v: vertices) {
					v.y += inc;
				}
				break;
			case zAxis:
				for (Vector3D v: vertices) {
					v.z += inc;
				}
				break;
		}
	}

	/**
	 * Rotate the polygon around an axis.
	 * @param axis Which axis to rotate around.
	 * @param degrees How much should the polygon be rotated in degrees.
	 * @param clockwise Which direction (clockwise or counterclockwise) the polygon should be rotated.
	 */
	public void rotate(Axis axis, double degrees, boolean clockwise) {
		double rotationTheta;
		for (Vector3D v: vertices) {
			rotationTheta = toRadians(degrees * (clockwise ? -1: 1));
			Projector.rotateVector(v, axis, rotationTheta);
		}
	}

	public Vector3D getCentroid() {
		double numPoints = vertices.length;;

		double sumX = 0;
		double sumY = 0;
		double sumZ = 0;
		for (Vector3D v: vertices) {
			sumX += v.x;
			sumY += v.y;
			sumZ += v.z;
		}

		Vector3D centroid = new Vector3D(sumX/numPoints, sumY/numPoints, sumZ/numPoints);

		return centroid;
	}

	/**
	 * Get the average Z coordinate of the polygon's points.
	 * @return The average Z coordinate of the polygon
	 */
	public double getAverageZ() {
		double sum = 0;
		for (Vector3D p: vertices) {
			sum += p.z;
		}
		return sum / vertices.length;
	}

	/**
	 * @return The polygon as a string.
	 */
	@Override
	public String toString() {
		return Arrays.toString(vertices) + ", Color: " + color;
	}

	/**
	 * Used to clone a polygon.
	 * @return A new polygon object that has the same data as this polygon.
	 */
	@Override
	public Polygon3D clone() {
		Vector3D[] vertices = new Vector3D[this.vertices.length];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = this.vertices[i].clone();
		}

		return new Polygon3D(new Color(color.getRGB()), vertices);
	}

	// Getters and Setters
	public Vector3D[] getPoints() {
		return vertices;
	}

	public void setPoints(Vector3D[] vertices) {
		this.vertices = vertices;
		for (int i = 0; i < vertices.length; i++) {
			this.vertices[i] = vertices[i].clone();
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
