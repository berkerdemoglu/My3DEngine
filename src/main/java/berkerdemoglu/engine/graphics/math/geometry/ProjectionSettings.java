package engine.graphics.math.geometry;

public class ProjectionSettings {
	public double width, height;

	public double fov;
	public double near;
	public double far;

	public ProjectionSettings(int width, int height, double fov, double near, double far) {
		this.width = (double) width;
		this.height = (double) height;
		this.fov = fov;
		this.near = near;
		this.far = far;
	}

	@Override
	public ProjectionSettings clone() {
		return new ProjectionSettings((int) width, (int) height, fov, near, far);
	}
}
