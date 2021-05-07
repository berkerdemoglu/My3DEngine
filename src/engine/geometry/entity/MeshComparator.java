package engine.geometry.entity;

import engine.geometry.Mesh;

import java.util.Comparator;

public class MeshComparator implements Comparator<Mesh> {
	@Override
	public int compare(Mesh m1, Mesh m2) {
		return m2.getAverageZ() - m1.getAverageZ() < 0 ? 1 : -1;
	}
}
