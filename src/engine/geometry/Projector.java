package engine.geometry;

import engine.Engine;

import java.awt.Point;

public abstract class Projector {
	private static int d = 1; // distance to objects

	public static Point project3DPoint(Point3D p3d) {
		// Add half the screen width and height so that the point is centered in the middle
		int x2d = (int) (Engine.SCREEN_WIDTH / 2 + d*p3d.x / p3d.z);
		int y2d = (int) (Engine.SCREEN_HEIGHT / 2 - d*p3d.y / p3d.z);

		return new Point(x2d, y2d);
	}
}
