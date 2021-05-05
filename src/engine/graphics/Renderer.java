package engine.graphics;

import engine.geometry.Polygon3D;

import java.awt.Graphics;
import java.util.ArrayList;

public class Renderer {
	private final int width;
	private final int height;

	private final ArrayList<Polygon3D> polygons;

	public Renderer(int width, int height) {
		this.width = width;
		this.height = height;

		polygons = new ArrayList<>();
	}

	public void render(Graphics g) {
		for (Polygon3D polygon3D: polygons) {
			polygon3D.render(g);
		}
	}

	public ArrayList<Polygon3D> getPolygons() {
		return polygons;
	}

	public void addPolygon(Polygon3D polygon3D) {
		polygons.add(polygon3D);
	}
}
