package berkerdemoglu.engine.graphics.rendering.scene;

import berkerdemoglu.engine.graphics.math.Vector3D;
import berkerdemoglu.engine.graphics.rendering.Camera;

public class LightSource {
	private Vector3D source;

	public LightSource(Vector3D source) {
		this.source = source;
	}

	public Vector3D getLightVectorTo(Vector3D p, Camera camera) {
		return Vector3D.normalize(new Vector3D(
				Vector3D.cameraAdjustedVector(source, camera),
				Vector3D.cameraAdjustedVector(p, camera))
		);
	}

	@Override
	public LightSource clone() {
		return new LightSource(source.clone());
	}

	public Vector3D getSource() {
		return source;
	}

	public void setSource(Vector3D source) {
		this.source = source;
	}
}
