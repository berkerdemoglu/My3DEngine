package engine.geometry.entity;

import engine.geometry.Axis;
import engine.geometry.DrawType;
import engine.geometry.Mesh;

import java.awt.Graphics;
import java.util.ArrayList;

public class Entity {
	private final ArrayList<Mesh> meshes;
	private final MeshComparator meshComparator;

	public Entity(Mesh... meshes) {
		this.meshes = new ArrayList<>();
		for (Mesh mesh : meshes) {
			this.meshes.add(mesh.cloneMesh());
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
		meshes.sort(meshComparator);
	}

	public ArrayList<Mesh> getMeshes() {
		return meshes;
	}
}
