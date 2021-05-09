package engine.geometry.entity;

import engine.geometry.Axis;
import engine.geometry.DrawType;
import engine.geometry.Mesh;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Entity {
	private Mesh[] meshes;
	private final MeshComparator meshComparator;

	public Entity(Mesh... meshes) {
		this.meshes = new Mesh[meshes.length];
		for (int i = 0; i < meshes.length; i++) {
			this.meshes[i] = meshes[i].cloneMesh();
		}

		meshComparator = new MeshComparator();
	}

	public void render(Graphics g, DrawType drawType) {
		for (Mesh mesh: meshes) {
			mesh.render(g, drawType);
		}
	}

	public void rotate(Axis axis, double degrees, boolean clockwise) {
		for (Mesh mesh: meshes) {
			mesh.rotate(axis, degrees, clockwise);
		}
		sortMeshes();
	}

	public void sortMeshes() {
		List<Mesh> meshList = new ArrayList<>(List.of(meshes));
		meshList.sort(meshComparator);

		for (int i = 0; i < meshes.length; i++) {
			this.meshes[i] = meshList.get(i);
		}
	}

	public Mesh[] getMeshes() {
		return meshes;
	}
}
