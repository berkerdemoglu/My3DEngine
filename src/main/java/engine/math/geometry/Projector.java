package engine.math.geometry;

import engine.rendering.world.Camera;
import engine.math.Matrix;
import engine.math.Vector3D;

import java.awt.Point;

import static java.lang.Math.*;

/**
 * Used to project to 2D, rotate around an axis, and scale 3D points.
 */
public abstract class Projector {
	public static double scale = 1;

	/**
	 * Project 3D point to 2D.
	 * @param v3d A {@link Vector3D} to be projected
	 * @return A projected 2D {@link Point}
	 */
	public static Point project3DPoint(
			Vector3D v3d, Camera camera,
			double width, double height, double fov, double near, double far
	) {
		double aspectRatio = height / width;
		double fovRad = 1.0 / tan(fov * 0.5 / 180.0 * PI);

		Matrix projectionMatrix = new Matrix(
				new double[][]{
						{aspectRatio * fovRad, 0, 0, 0},
						{0, fovRad, 0, 0},
						{0, 0, far / (far - near), 1},
						{0, 0, (-far * near) / (far - near), 0}
				}
		);
		Matrix pointMatrix = v3d.asQuaternion();

		Matrix result = Matrix.multiplyMatrices(pointMatrix, projectionMatrix);

		Vector3D o = new Vector3D(result.matrix[0][0], result.matrix[0][1], result.matrix[0][2]);
		double w = result.matrix[0][3];
//		if (w != 0) {
//			o.x /= w;
//			o.y /= w;
//			o.z /= w;
//		}

		o.x *= scale;
		o.y *= scale;
		o.z *= scale;

		return new Point((int) (o.x + width/2), (int) (o.y + height/2));

//		double x3d = (v3d.x - camera.position.x) * scale;
//		double y3d = (v3d.y - camera.position.y) * scale;
//		double depth = (v3d.z + camera.position.z) * scale;
//		double[] newValues = scalePoint(x3d, y3d, depth);
//
//		// Add half the screen width and height so that the point is centered in the middle
//		int x2d = (int) (Engine.SCREEN_WIDTH / 2 + newValues[0]);
//		int y2d = (int) (Engine.SCREEN_HEIGHT / 2 - newValues[1]);
//
//		return new Point(x2d, y2d);
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
	 * @param v3d The 3D point to rotate.
	 * @param axis The axis to rotate around.
	 * @param theta How much to rotate in radians.
	 */
	public static void rotateVector(Vector3D v3d, Axis axis, double theta) {
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

		Matrix result = Matrix.multiplyMatrices(rotationMatrix, v3d.asMatrix());
		if (result == null) { // if matrix multiplication failed
			return;
		}

		v3d.x = result.matrix[0][0];
		v3d.y = result.matrix[1][0];
		v3d.z = result.matrix[2][0];
	}
}
