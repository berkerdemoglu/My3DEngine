package engine;

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

		double s = 50.0;
		Point3D p1 = new Point3D(-2*s, -s, s);
		Point3D p2 = new Point3D(2*s, -s, s);
		Point3D p3 = new Point3D(2*s, s, s);
		Point3D p4 = new Point3D(-2*s, s, s);
		Point3D p5 = new Point3D(-2*s, -s, -s);
		Point3D p6 = new Point3D(2*s, -s, -s);
		Point3D p7 = new Point3D(2*s, s, -s);
		Point3D p8 = new Point3D(-2*s, s, -s);

		Mesh mesh = new Mesh(/*Color.CYAN,*/
				new Polygon3D(Color.BLUE, p5, p6, p7, p8),
				new Polygon3D(Color.WHITE, p1, p2, p6, p5),
				new Polygon3D(Color.YELLOW, p1, p5, p8, p4),
				new Polygon3D(Color.GREEN, p2, p6, p7, p3),
				new Polygon3D(Color.ORANGE, p4, p3, p7, p8),
				new Polygon3D(Color.RED, p1, p2, p3, p4)
		);

		eng.addMeshesToRender(mesh);
		eng.start();
	}

	@Override
	protected void updateDisplay() {

	}
}
