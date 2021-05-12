package engine.graphics.renderer;

import engine.geometry.Point3D;
import engine.graphics.camera.Camera;
import engine.math.Vector3D;

public class LightSource {
	private Point3D source;

	public LightSource(Point3D source) {
		this.source = source;
	}

	public Vector3D getLightVectorTo(Point3D p, Camera camera) {
		return Vector3D.normalize(new Vector3D(
				Point3D.cameraAdjustedPoint(source, camera),
				Point3D.cameraAdjustedPoint(p, camera))
		);
	}

	public Point3D getSource() {
		return source;
	}

	public void setSource(Point3D source) {
		this.source = source;
	}
}
