package engine.graphics.camera;

import engine.math.Vector3D;

public class Camera {
	public Vector3D position;

	public Camera() {
		position = new Vector3D(0, 0, 0);
	}

	public Camera(double x, double y, double z) {
		position = new Vector3D(x, y, z);
	}

	public void moveCamera(double dx, double dy, double dz) {
		position.x += dx;
		position.y += dy;
		position.z += dz;
	}
}
