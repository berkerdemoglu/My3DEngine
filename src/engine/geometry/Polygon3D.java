package engine.geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Point;
import java.util.Arrays;

/**
 * A polygon in 3D space.
 * A <code>Polygon3D</code> is made up of numerous {@link Point3D}s and a color.
 */
public class Polygon3D {
	private Point3D[] points;
	private Color color;

	/**
	 * Constructs a new polygon in 3D space.
	 * @param color Color of the polygon
	 * @param points Points in 3D plane that make up the polygon
	 */
	public Polygon3D(Color color, Point3D... points) {
		this.points = new Point3D[points.length];
		for (int i = 0; i < points.length; i++) {
			this.points[i] = points[i].clonePoint();
		}

		this.color = color;
	}

	/**
	 * Fallback method for constructing a polygon without a color.
	 * @param points Points in 3D plane that make up the polygon
	 */
	public Polygon3D(Point3D... points) {
		this(Color.RED, points);
	}

	/**
	 * Render the polygon to the screen.
	 * @param g Graphics object used to draw polygons
	 * @param drawType Signifies which draw type should be used to render this polygon
	 */
	public void render(Graphics g, DrawType drawType) {
		Polygon polygon = new Polygon();
		Point point;

		for (Point3D point3D: points) {
			point = Projector.project3DPoint(point3D);
			polygon.addPoint(point.x, point.y);
		}

		switch (drawType) {
			case FILL:
				g.setColor(color);
				g.fillPolygon(polygon);
				break;
			case WIREFRAME_DRAW:
				g.setColor(Color.LIGHT_GRAY);
				g.drawPolygon(polygon);
				break;
			case FILL_N_HIGHLIGHT:
				g.setColor(Color.DARK_GRAY);
				g.drawPolygon(polygon);
				g.setColor(color);
				g.fillPolygon(polygon);
				break;
		}
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
				for (Point3D p: points) {
					p.x += inc;
				}
				break;
			case yAxis:
				for (Point3D p: points) {
					p.y += inc;
				}
				break;
			case zAxis:
				for (Point3D p: points) {
					p.z += inc;
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
		for (Point3D p: points) {
			Projector.rotatePoint(p, axis, degrees, clockwise);
		}
	}

	/**
	 * Get the average Z coordinate of the polygon's points.
	 * @return The average Z coordinate of the polygon
	 */
	public double getAverageZ() {
		double sum = 0;
		for (Point3D p: points) {
			sum += p.z;
		}
		return sum / points.length;
	}

	/**
	 * @return The polygon as a string.
	 */
	@Override
	public String toString() {
		return Arrays.toString(points) + ", Color: " + color;
	}

	/**
	 * Used to clone a polygon.
	 * <p>
	 *     Note: This will be replaced by <code>Object.clone()</code> in the future.
	 * </p>
	 * @return A new polygon object that has the same data as this polygon.
	 */
	public Polygon3D clonePolygon() {
		return new Polygon3D(color, points);
	}

	// Getters and Setters
	public Point3D[] getPoints() {
		return points;
	}

	public void setPoints(Point3D[] points) {
		this.points = points;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
