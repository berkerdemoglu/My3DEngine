package engine.engine;

import engine.graphics.display.Settings;
import engine.graphics.math.geometry.ProjectionSettings;
import engine.models.mesh.Mesh;
import engine.graphics.math.Vector3D;
import engine.models.entity.Entity;
import engine.models.mesh.MeshBuilder;
import engine.graphics.display.Display;
import engine.graphics.rendering.scene.LightSource;
import engine.graphics.rendering.scene.Scene;

import java.awt.Color;
import java.io.IOException;

public class ExampleEngine extends Display {
	public ExampleEngine(Settings settings) {
		super(settings);
	}

	public static void main(String[] args) throws IOException {
		ExampleEngine eng = new ExampleEngine(new Settings() {
			public int SCREEN_WIDTH() {
				return 600;
			}
			public int SCREEN_HEIGHT() {
				return 600;
			}
			public int FPS() {
				return 60;
			}
			public String TITLE() {
				return "3D Engine";
			}
		});

		Scene scene = new Scene(
				Color.BLACK,
				new LightSource(new Vector3D(-100, 0, 0)),
				new ProjectionSettings(
						eng.settings.SCREEN_WIDTH(), eng.settings.SCREEN_HEIGHT(), 90, 0.1, 1000
				)
		);

		eng.renderer.setScene(scene);

		Mesh[] meshes = {
//				MeshBuilder.fromObjFile(
//						"D:\\kodlama\\java_projects\\My3DEngine\\resources\\sphere.obj",
//						new Color(50, 194, 217), 20, new Vector3D(50, 10, 10)
//				),
				MeshBuilder.constructCube(Color.CYAN, 100, new Vector3D(50, 10, 10))
		};

		Entity entity = new Entity(meshes);
		eng.addEntitiesToScene(entity);
		eng.start();
	}

	@Override
	protected void update() {

	}
}
