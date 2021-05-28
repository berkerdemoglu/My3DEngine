package engine.math;

import engine.rendering.world.Camera;

public class Vector3D {
	public double x, y, z;

	public Vector3D() {
		x = 0;
		y = 0;
		z = 0;
	}

	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3D(Vector3D v1, Vector3D v2) {
		x = v2.x - v1.x;
		y = v2.y - v1.y;
		z = v2.z - v1.z;
	}

	public double getMagnitude() {
		return Math.sqrt(x*x + y*y + z*z);
	}

	public static double dotProduct(Vector3D v1, Vector3D v2) {
		return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
	}

	public static Vector3D crossProduct(Vector3D b, Vector3D c) {
		Vector3D a = new Vector3D(
				b.y*c.z - b.z*c.y,
				b.z*c.x - b.x*c.z,
				b.x*c.y - b.y*c.x
		);

		return a;
	}

	public static Vector3D normalize(Vector3D v) {
		double m = v.getMagnitude();
		return new Vector3D(v.x/m, v.y/m, v.z/m);
	}

	public static Vector3D cameraAdjustedVector(Vector3D vector, Camera camera) {
		return new Vector3D(vector.x + camera.position.x, vector.y + camera.position.y, vector.z + camera.position.z);
	}

	public Matrix asMatrix() {
		return new Matrix(new double[][] {{x}, {y}, {z}});
	}

	public Matrix asQuaternion() {
		return new Matrix(new double[][]{
				{x, y, z, 1}
		});
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}

	@Override
	public Vector3D clone() {
		return new Vector3D(x, y, z);
	}
}
