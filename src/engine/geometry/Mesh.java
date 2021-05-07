package engine.geometry;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mesh {
	private final Polygon3D[] polygons;

	public Mesh(Polygon3D... polygons) {
		this.polygons = new Polygon3D[polygons.length];
		for (int i = 0; i < polygons.length; i++) {
			this.polygons[i] = polygons[i].copyPolygon();
		}
	}

	public void render(Graphics g, boolean wireframeDraw) {
		for (Polygon3D polygon: polygons) {
			polygon.render(g, wireframeDraw);
		}
	}

	public void translate(Axis axis, double inc) {
		for (Polygon3D poly: polygons) {
			poly.translate(axis, inc);
		}
	}

	public void rotate(Axis axis, double degrees, boolean clockwise) {
		for (Polygon3D poly: polygons) {
			poly.rotate(axis, degrees, clockwise);
		}
		sortPolygons();
	}

	public void rotate(Axis axis, double degrees) {
		rotate(axis, degrees, false);
	}

	public void sortPolygons() {
		List<Polygon3D> polygonList = new ArrayList<>(List.of(polygons));
		polygonList.sort(new PolygonComparator());

		for (int i = 0; i < polygons.length; i++) {
			polygons[i] = polygonList.get(i);
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(polygons);
	}

	public Polygon3D[] getPolygons() {
		return polygons;
	}
}
