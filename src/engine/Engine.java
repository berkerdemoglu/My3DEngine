package engine;

import engine.geometry.Axis;
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

		eng.addPolygonToRender(new Polygon3D(
				new Point3D(0, 0, 100),
				new Point3D(200,200,100),
				new Point3D(0,500,100)
		));

		eng.start();
	}

	@Override
	protected void updateDisplay() {
		Polygon3D poly = renderer.getPolygons().get(0);
		poly.translate(Axis.zAxis, -1);
	}
}
