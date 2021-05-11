package engine.math;

import engine.geometry.Point3D;

public class Vector3D {
	public double x, y, z;

	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3D(Point3D p1, Point3D p2) {
		x = p2.x - p1.x;
		y = p2.y - p1.y;
		z = p2.z - p1.z;
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
}
