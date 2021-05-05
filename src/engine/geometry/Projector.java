package engine.geometry;

import engine.graphics.Display;

import java.awt.Point;

public abstract class Projector {
	private static final int d = 1;

	public static Point project3DPoint(Point3D p3d) {
		int x2d = (int) (Display.SCREEN_WIDTH / 2 + d*p3d.x / p3d.z);
		int y2d = (int) (Display.SCREEN_HEIGHT / 2 - d*p3d.y / p3d.z);

		return new Point(x2d, y2d);
	}
}
