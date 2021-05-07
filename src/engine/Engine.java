package engine;

import engine.geometry.Point3D;
import engine.geometry.shape.ShapeBuilder;
import engine.graphics.Display;

import java.awt.*;

public class Engine extends Display {
	public Engine(String title, Color backgroundColor) {
		super(title, backgroundColor);
	}

	public static void main(String[] args) {
		Engine eng = new Engine("3D Engine", Color.BLACK);

		eng.addMeshesToRender(ShapeBuilder.constructCube(Color.CYAN, 100, new Point3D(0, 0, 0)));
		eng.start();
	}

	@Override
	protected void updateDisplay() {

	}
}
