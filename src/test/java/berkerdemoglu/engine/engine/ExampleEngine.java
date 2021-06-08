package berkerdemoglu.engine.engine;

import berkerdemoglu.engine.graphics.display.Settings;
import berkerdemoglu.engine.graphics.math.geometry.ProjectionSettings;
import berkerdemoglu.engine.models.mesh.Mesh;
import berkerdemoglu.engine.graphics.math.Vector3D;
import berkerdemoglu.engine.models.entity.Entity;
import berkerdemoglu.engine.models.mesh.MeshBuilder;
import berkerdemoglu.engine.graphics.display.Display;
import berkerdemoglu.engine.graphics.rendering.scene.LightSource;
import berkerdemoglu.engine.graphics.rendering.scene.Scene;

import java.awt.Color;
import java.io.IOException;

public class ExampleEngine extends Display {
	public ExampleEngine(Settings settings) {
		super(settings);
	}

	public static void main(String[] args) throws IOException {
		ExampleEngine eng = new ExampleEngine(new BaseSettings());

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
	protected void update() {}
}
