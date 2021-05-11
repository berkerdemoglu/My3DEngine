package engine.graphics.camera;

public class Camera {
	public double x, y, z;

	public Camera() {
		x = 0;
		y = 0;
		z = 0;
	}

	public Camera(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void moveCamera(double dx, double dy, double dz) {
		x -= dx;
		y -= dy;
		z -= dz;
	}
}
