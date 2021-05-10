package engine;

import engine.geometry.Mesh;
import engine.geometry.entity.Entity;
import engine.geometry.shape.MeshBuilder;
import engine.graphics.Display;

import java.awt.Color;
import java.io.IOException;

public class Engine extends Display {
	public Engine(String title, Color backgroundColor) {
		super(title, backgroundColor);
	}

	public static void main(String[] args) throws IOException {
		Engine eng = new Engine("3D Engine", Color.BLACK);

		Mesh[] meshes = {
//				ShapeBuilder.constructCube(Color.CYAN, 50, new Point3D(0, 0, 200)),
//				ShapeBuilder.constructCube(Color.GREEN, 50, new Point3D(200, 200, 200)),
//				ShapeBuilder.constructRectanglePrism(new Color(1.0f, 1.0f, 1.0f, 0.5f), new Point3D(0,0,0), 50, 100, 200)
				MeshBuilder.fromObjFile("D:\\kodlama\\java_projects\\My3DEngine\\resources\\gourd.obj", Color.GREEN)
		};

		Entity entity = new Entity(meshes);

		eng.addEntitiesToRender(entity);
		eng.start();
	}

	@Override
	protected void updateDisplay() {

	}
}
