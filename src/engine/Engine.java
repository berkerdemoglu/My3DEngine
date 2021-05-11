package engine;

import engine.geometry.Mesh;
import engine.geometry.Point3D;
import engine.geometry.entity.Entity;
import engine.geometry.shape.MeshBuilder;
import engine.graphics.Display;
import engine.graphics.renderer.AmbientLightSource;
import engine.graphics.renderer.Scene;
import engine.math.Vector3D;

import java.awt.*;
import java.io.IOException;

public class Engine extends Display {
	public Engine(String title) {
		super(title);
	}

	public static void main(String[] args) throws IOException {
		Engine eng = new Engine("3D Engine");

		Scene scene = new Scene(
				SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK,
				new AmbientLightSource(new Vector3D(100, 0, 0))
		);

		eng.renderer.setScene(scene);

		Mesh[] meshes = {
				MeshBuilder.fromObjFile(
						"D:\\kodlama\\java_projects\\My3DEngine\\resources\\sphere.obj",
						new Color(50, 194, 217), 20, new Point3D(0, 0, 0)
				)
		};

		Entity entity = new Entity(meshes);
		eng.addEntitiesToScene(entity);
		eng.start();
	}

	@Override
	protected void updateDisplay() {

	}
}
