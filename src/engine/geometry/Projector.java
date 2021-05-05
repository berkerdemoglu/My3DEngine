package engine.geometry;

import engine.Engine;
import engine.math.Matrix;

import java.awt.Point;

import static java.lang.Math.*;

public abstract class Projector {
	private static int d = 1; // distance to objects

	public static Point project3DPoint(Point3D p3d) {
		// Add half the screen width and height so that the point is centered in the middle
		int x2d = (int) (Engine.SCREEN_WIDTH / 2 + d*p3d.x / p3d.z);
		int y2d = (int) (Engine.SCREEN_HEIGHT / 2 - d*p3d.y / p3d.z);

		return new Point(x2d, y2d);
	}

	public static void rotatePoint(Point3D p, Axis axis, double degrees) {
		double theta = toRadians(degrees);
		Matrix rotationMatrix;

		// Get required rotation matrix for the axis
		switch (axis) {
			case xAxis:
				rotationMatrix = new Matrix(new double[][] {
						{1, 0, 0},
						{0, cos(theta), -sin(theta)},
						{0, sin(theta), cos(theta)}
				});

				break;
			case yAxis:
				rotationMatrix = new Matrix(new double[][] {
						{cos(theta), 0, sin(theta)},
						{0, 1, 0},
						{-sin(theta), 0, cos(theta)},
				});

				break;
			case zAxis:
				rotationMatrix = new Matrix(new double[][] {
						{cos(theta), -sin(theta), 0},
						{sin(theta), cos(theta), 0},
						{0, 0, 1},
				});

				break;
			default:
				return;
		}

		Matrix result = Matrix.multiplyMatrices(rotationMatrix, p.asMatrix());
		if (result == null) {
			return;
		}

		p.x = result.matrix[0][0];
		p.y = result.matrix[1][0];
		p.z = result.matrix[2][0];
	}
}
