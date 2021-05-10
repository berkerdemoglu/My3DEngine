package engine.geometry;

import engine.math.Matrix;

/**
 * Represents a point on a 3D plane.
 */
public class Point3D {
	public double x, y, z;

	/**
	 * Construct a point in 3D.
	 * @param x X coordinate of the point
	 * @param y Y coordinate of the point
	 * @param z Z coordinate of the point
	 */
	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Matrix representation of a 3D point
	 * @return The point as a {@link Matrix}.
	 */
	public Matrix asMatrix() {
		return new Matrix(new double[][] {{x}, {y}, {z}});
	}

	/**
	 * The point enclosed with brackets.
	 * @return The point as a string
	 */
	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}

	/**
	 * Used to clone a point.
	 * <p>
	 *     Note: This will be replaced by <code>Object.clone()</code> in the future.
	 * </p>
	 * @return A new point object that has the same data as this point.
	 */
	public Point3D clonePoint() {
		return new Point3D(x, y, z);
	}
}
