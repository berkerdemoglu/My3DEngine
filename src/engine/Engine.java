package engine;

import engine.geometry.Point3D;
import engine.geometry.Polygon3D;
import engine.graphics.Display;

public class Engine {
	public static void main(String[] args) {
		Display d = new Display("3D Engine");

		d.addPolygonToRender(new Polygon3D(
				new Point3D(0, 0, 100),
				new Point3D(200,200,100),
				new Point3D(0,500,100)
		));

		d.start();
	}
}
