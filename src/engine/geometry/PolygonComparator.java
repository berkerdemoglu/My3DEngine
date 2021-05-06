package engine.geometry;

import java.util.Comparator;

public class PolygonComparator implements Comparator<Polygon3D> {

	@Override
	public int compare(Polygon3D p1, Polygon3D p2) {
		return p2.getAverageZ() - p1.getAverageZ() < 0 ? 1 : -1;
	}
}
