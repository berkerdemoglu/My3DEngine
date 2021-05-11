package engine.graphics.renderer;

import engine.math.Vector3D;

public class AmbientLightSource {
	private Vector3D lightVector;

	public AmbientLightSource(Vector3D lightVector) {
		this.lightVector = Vector3D.normalize(lightVector);
	}

	public Vector3D getLightVector() {
		return lightVector;
	}

	public void setLightVector(Vector3D lightVector) {
		this.lightVector = Vector3D.normalize(lightVector);
	}
}
