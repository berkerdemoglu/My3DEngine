package engine.geometry;

import engine.Engine;
import engine.math.Matrix;

import java.awt.Point;

import static java.lang.Math.*;

/**
 * Used to project to 2D, rotate around an axis, and scale 3D points.
 */
public abstract class Projector {
	public static double scale = 1;  // this will be changed in the future

	/**
	 * Project 3D point to 2D.
	 * @param p3d A {@link Point3D} to be projected
	 * @return A projected 2D {@link Point}
	 */
	public static Point project3DPoint(Point3D p3d) {
		double x3d = p3d.x * scale;
		double y3d = p3d.y * scale;
		double depth = p3d.z * scale;
		double[] newValues = scalePoint(x3d, y3d, depth);

		// Add half the screen width and height so that the point is centered in the middle
		int x2d = (int) (Engine.SCREEN_WIDTH / 2 + newValues[0]);
		int y2d = (int) (Engine.SCREEN_HEIGHT / 2 - newValues[1]);

		return new Point(x2d, y2d);
	}

	/**
	 * Scales a point accordingly. Used for perspective projection.
	 * This method is called in the <code>project3DPoint()</code> method.
	 * @param x3d The scaled X coordinate of the 3D point
	 * @param y3d The scaled Y coordinate of the 3D point
	 * @param depth The scaled Z coordinate of the 3D point which is used for depth
	 * @return A 2 element <code>double</code> array of the locally scaled X and Y coordinates.
	 */
	private static double[] scalePoint(double x3d, double y3d, double depth) {
		double depth2 = 15 - depth;
		double localScale = Math.abs(1400 / (depth2 + 1400));
		return new double[]{localScale * x3d, localScale * y3d};
	}

	/**
	 * Rotates a 3D point around an axis.
	 * This method is called in the <code>rotate()</code> method of {@link Polygon3D}.
	 * @param p The 3D point to rotate.
	 * @param axis The axis to rotate around.
	 * @param degrees How much to rotate in degrees.
	 * @param clockwise Clockwise or counterclockwise rotation.
	 */
	public static void rotatePoint(Point3D p, Axis axis, double degrees, boolean clockwise) {
		double theta = toRadians(degrees) * (clockwise ? -1: 1);
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
		if (result == null) { // if matrix multiplication failed
			return;
		}

		p.x = result.matrix[0][0];
		p.y = result.matrix[1][0];
		p.z = result.matrix[2][0];
	}
}
