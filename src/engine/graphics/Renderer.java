package engine.graphics;

import engine.geometry.Mesh;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

public class Renderer {
	private final int width;
	private final int height;

	private final ArrayList<Mesh> meshes;
	private boolean wireframeDraw;

	public Renderer(int width, int height) {
		this.width = width;
		this.height = height;

		meshes = new ArrayList<>();
		wireframeDraw = false;
	}

	public void render(Graphics g) {
		for (Mesh mesh: meshes) {
			mesh.render(g, wireframeDraw);
		}
	}

	public void addMesh(Mesh[] meshes) {
		this.meshes.addAll(Arrays.asList(meshes));
	}

	@Override
	public String toString() {
		return meshes.toString();
	}

	public ArrayList<Mesh> getMeshes() {
		return meshes;
	}

	public void setWireframeDraw(boolean wireframeDraw) {
		this.wireframeDraw = wireframeDraw;
	}

	public boolean isWireframeDraw() {
		return wireframeDraw;
	}
}
