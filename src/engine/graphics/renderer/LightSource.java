package engine.graphics.renderer;

import engine.math.Vector3D;
import engine.graphics.camera.Camera;

public class LightSource {
	private Vector3D source;

	public LightSource(Vector3D source) {
		this.source = source;
	}

	public Vector3D getLightVectorTo(Vector3D p, Camera camera) {
		return Vector3D.normalize(new engine.math.Vector3D(
				Vector3D.cameraAdjustedVector(source, camera),
				Vector3D.cameraAdjustedVector(p, camera))
		);
	}

	public Vector3D getSource() {
		return source;
	}

	public void setSource(Vector3D source) {
		this.source = source;
	}
}
