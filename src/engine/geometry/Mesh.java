package engine.geometry;

import java.awt.Graphics;
import java.util.ArrayList;

public class Mesh {
	private final ArrayList<Polygon3D> polygons;
	private final PolygonComparator polygonComparator;

	public Mesh(Polygon3D... polygons) {
		this.polygons = new ArrayList<>();
		for (Polygon3D polygon : polygons) {
			this.polygons.add(polygon.clonePolygon());
		}

		polygonComparator = new PolygonComparator();
	}

	public void render(Graphics g, DrawType drawType) {
		for (Polygon3D polygon: polygons) {
			polygon.render(g, drawType);
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
		polygons.sort(polygonComparator);
	}

	public double getAverageZ() {
		double sum = 0;
		for (Polygon3D polygon: polygons) {
			sum += polygon.getAverageZ();
		}
		return sum / polygons.size();
	}

	@Override
	public String toString() {
		return polygons.toString();
	}

	public Mesh cloneMesh() {
		return new Mesh(polygons.toArray(new Polygon3D[0]));
	}

	public ArrayList<Polygon3D> getPolygons() {
		return polygons;
	}
}
