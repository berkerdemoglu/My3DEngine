package berkerdemoglu.engine.models.mesh;

import java.util.Comparator;

/**
 * Used to sort meshes according to their average Z coordinate.
 */
public class MeshComparator implements Comparator<Mesh> {
	@Override
	public int compare(Mesh m1, Mesh m2) {
		if (m2.getAverageZ() == m1.getAverageZ()) return 0;

		return m2.getAverageZ() - m1.getAverageZ() < 0 ? 1 : -1;
	}
}
