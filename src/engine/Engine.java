package engine;

import engine.geometry.Mesh;
import engine.geometry.Point3D;
import engine.geometry.entity.Entity;
import engine.geometry.shape.MeshBuilder;
import engine.graphics.Display;
import engine.graphics.renderer.LightSource;
import engine.graphics.renderer.Scene;

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
				new LightSource(new Point3D(-100, 0, 0))
		);

		eng.renderer.setScene(scene);
		eng.renderer.camera.x = 10;

		Mesh[] meshes = {
				MeshBuilder.fromObjFile(
						"D:\\kodlama\\java_projects\\My3DEngine\\resources\\gourd.obj",
						new Color(50, 194, 217), 20, new Point3D(50, 10, 10)
				)
		};

		Entity entity = new Entity(meshes);
		eng.addEntitiesToScene(entity);
		eng.start();
	}

	@Override
	protected void updateDisplay() {
//		renderer.camera.moveCamera(1, 0, 0);
	}
}
