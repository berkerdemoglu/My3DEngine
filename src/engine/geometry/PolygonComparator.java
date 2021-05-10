package engine.geometry;

import java.util.Comparator;

/**
 * Used to sort polygons according to their average Z coordinate.
 */
public class PolygonComparator implements Comparator<Polygon3D> {

	@Override
	public int compare(Polygon3D p1, Polygon3D p2) {
		if (p2.getAverageZ() == p1.getAverageZ()) return 0;

		return p2.getAverageZ() - p1.getAverageZ() < 0 ? 1 : -1;
	}
}
