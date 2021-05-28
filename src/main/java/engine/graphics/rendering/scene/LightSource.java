package engine.graphics.rendering.scene;

import engine.graphics.math.Vector3D;
import engine.graphics.rendering.Camera;

public class LightSource {
	private Vector3D source;

	public LightSource(Vector3D source) {
		this.source = source;
	}

	public Vector3D getLightVectorTo(Vector3D p, Camera camera) {
		return Vector3D.normalize(new engine.graphics.math.Vector3D(
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
