package engine.geometry.entity;

import engine.geometry.Axis;
import engine.geometry.DrawType;
import engine.geometry.Mesh;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Just like a mesh is made up of polygons, an entity is made up of meshes. The <code>Entity</code> class handles a list
 * of meshes.
 */
public class Entity {
	private final ArrayList<Mesh> meshes;
	private final MeshComparator meshComparator;

	/**
	 * Creates a new <code>Entity</code> from a list of meshes
	 * @param meshes Meshes that make up the <code>Entity</code>
	 */
	public Entity(Mesh... meshes) {
		this.meshes = new ArrayList<>();
		for (Mesh mesh : meshes) {
			this.meshes.add(mesh.cloneMesh());
		}

		meshComparator = new MeshComparator();
		sortMeshes();
	}

	/**
	 * Renders the entity's meshes one by one.
	 * @param g Graphics object to draw on
	 * @param drawType Signifies which draw type should be used to render this entity
	 */
	public void render(Graphics g, DrawType drawType) {
		for (Mesh mesh: meshes) {
			mesh.render(g, drawType);
		}
	}

	/**
	 * Rotate the entity around an axis.
	 * @param axis Which axis to rotate around.
	 * @param degrees How much should the entity be rotated in degrees.
	 * @param clockwise Which direction (clockwise or counterclockwise) the entity should be rotated.
	 */
	public void rotate(Axis axis, double degrees, boolean clockwise) {
		for (Mesh mesh: meshes) {
			mesh.rotate(axis, degrees, clockwise);
		}
		sortMeshes();
	}

	/**
	 * Sort the meshes according to their average Z coordinate.
	 */
	public void sortMeshes() {
		meshes.sort(meshComparator);
	}

	public ArrayList<Mesh> getMeshes() {
		return meshes;
	}
}
