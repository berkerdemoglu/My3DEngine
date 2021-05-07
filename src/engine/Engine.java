package engine;

import engine.geometry.Mesh;
import engine.geometry.Point3D;
import engine.geometry.entity.Entity;
import engine.geometry.shape.ShapeBuilder;
import engine.graphics.Display;

import java.awt.*;

public class Engine extends Display {
	public Engine(String title, Color backgroundColor) {
		super(title, backgroundColor);
	}

	public static void main(String[] args) {
		Engine eng = new Engine("3D Engine", Color.BLACK);

		Mesh[] meshes = {
				ShapeBuilder.constructCube(Color.CYAN, 50, new Point3D(0, 0, 200)),
				ShapeBuilder.constructCube(Color.GREEN, 50, new Point3D(200, 200, 200)),
		};

		Entity entity = new Entity(meshes);

		eng.addEntitiesToRender(entity);
		eng.start();
	}

	@Override
	protected void updateDisplay() {

	}
}
