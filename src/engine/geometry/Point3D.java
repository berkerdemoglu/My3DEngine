package engine.geometry;

import engine.math.Matrix;

public class Point3D {
	public double x, y, z;

	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Matrix asMatrix() {
		return new Matrix(new double[][] {{x}, {y}, {z}});
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}

	public Point3D clonePoint() {
		return new Point3D(x, y, z);
	}
}
