package engine.geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Point;

public class Polygon3D {
	private Point3D[] points;
	private Color color;

	public Polygon3D(Color color, Point3D... points) {
		this.points = new Point3D[points.length];
		for (int i = 0; i < points.length; i++) {
			this.points[i] = points[i].copyPoint();
		}

		this.color = color;
	}

	public Polygon3D(Point3D... points) {
		this(Color.RED, points);
	}

	public void render(Graphics g) {
		Polygon polygon = new Polygon();
		Point point;

		for (Point3D point3D: points) {
			point = Projector.project3DPoint(point3D);
			polygon.addPoint(point.x, point.y);
		}

		g.setColor(color);
		g.fillPolygon(polygon);
	}

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