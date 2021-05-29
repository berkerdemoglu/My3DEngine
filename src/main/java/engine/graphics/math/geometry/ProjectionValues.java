package engine.graphics.math.geometry;

public class ProjectionValues {
	public double width, height;

	public double fov;
	public double near;
	public double far;

	public ProjectionValues(int width, int height, double fov, double near, double far) {
		this.width = (double) width;
		this.height = (double) height;
		this.fov = fov;
		this.near = near;
		this.far = far;
	}

	@Override
	public ProjectionValues clone() {
		return new ProjectionValues((int) width, (int) height, fov, near, far);
	}
}
