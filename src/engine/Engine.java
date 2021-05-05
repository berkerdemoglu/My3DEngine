package engine;

import engine.geometry.Axis;
import engine.geometry.Mesh;
import engine.geometry.Point3D;
import engine.geometry.Polygon3D;
import engine.graphics.Display;

import java.awt.*;

public class Engine extends Display {
	public Engine(String title, Color backgroundColor) {
		super(title, backgroundColor);
	}

	public static void main(String[] args) {
		Engine eng = new Engine("3D Engine", Color.BLACK);

		Point3D p1 = new Point3D(-200, -200, 2); // lower left
		Point3D p2 = new Point3D(-200, 200, 2); // upper left
		Point3D p3 = new Point3D(200, 200, 2); // upper right
		Point3D p4 = new Point3D(200, -200, 2); // lower right
		Mesh mesh = new Mesh(
				new Polygon3D(Color.BLUE, p1, p2, p3, p4)
		);

		eng.addMeshesToRender(mesh);
		eng.start();
	}

	@Override
	protected void updateDisplay() {
		Mesh mesh = renderer.getMeshes().get(0);
		mesh.rotate(Axis.zAxis, 2);
	}
}
