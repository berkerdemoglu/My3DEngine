package engine.geometry;

import java.awt.Graphics;

public class Mesh {
	private Polygon3D[] polygons;

	public Mesh(Polygon3D... polygons) {
		this.polygons = new Polygon3D[polygons.length];
		for (int i = 0; i < polygons.length; i++) {
			this.polygons[i] = polygons[i].copyPolygon();
		}
	}

	public void render(Graphics g) {
		for (Polygon3D polygon: polygons) {
			polygon.render(g);
		}
	}

	public void translate(Axis axis, double inc) {
		for (Polygon3D poly: polygons) {
			poly.translate(axis, inc);
		}
	}

	public void rotate(Axis axis, double degrees) {
		for (Polygon3D poly: polygons) {
			poly.rotate(axis, degrees);
		}
	}
}
