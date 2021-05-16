package engine;

import engine.models.mesh.Mesh;
import engine.math.Vector3D;
import engine.models.entity.Entity;
import engine.models.mesh.MeshBuilder;
import engine.graphics.Display;
import engine.rendering.world.LightSource;
import engine.rendering.world.Scene;

import java.awt.Color;
import java.io.IOException;

public class Engine extends Display {
	public Engine(String title) {
		super(title);
	}

	public static void main(String[] args) throws IOException {
		Engine eng = new Engine("3D Engine");

		Scene scene = new Scene(
				Color.BLACK,
				new LightSource(new Vector3D(-100, 0, 0))
		);

		eng.renderer.setScene(scene);
		eng.renderer.camera.position.x = 10;

		Mesh[] meshes = {
				MeshBuilder.fromObjFile(
						"D:\\kodlama\\java_projects\\My3DEngine\\resources\\gourd.obj",
						new Color(50, 194, 217), 20, new Vector3D(50, 10, 10)
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
