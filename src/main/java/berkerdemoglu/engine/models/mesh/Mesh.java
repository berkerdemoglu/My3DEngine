package berkerdemoglu.engine.models.mesh;

import berkerdemoglu.engine.graphics.math.geometry.Axis;
import berkerdemoglu.engine.graphics.math.geometry.Polygon3D;
import berkerdemoglu.engine.graphics.math.geometry.PolygonComparator;
import berkerdemoglu.engine.graphics.math.geometry.ProjectionSettings;
import berkerdemoglu.engine.graphics.rendering.DrawType;
import berkerdemoglu.engine.graphics.rendering.Camera;
import berkerdemoglu.engine.graphics.rendering.scene.LightSource;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that holds a list of polygons.
 * Meshes are then used to form entities.
 */
public class Mesh {
	private final ArrayList<Polygon3D> polygons;
	private final PolygonComparator polygonComparator;

	/**
	 * Create a new mesh from an arbitrary number of polygons
	 * @param polygons Polygons that make up the mesh
	 */
	public Mesh(Polygon3D... polygons) {
		this.polygons = new ArrayList<>();
		for (Polygon3D polygon : polygons) {
			this.polygons.add(polygon.clone());
		}

		polygonComparator = new PolygonComparator();
		sortPolygons();
	}

	/**
	 * Fallback method for creating a mesh from a list of polygons
	 * @param polygons Polygons that make up the mesh
	 */
	public Mesh(List<Polygon3D> polygons) {
		this.polygons = new ArrayList<>();
		for (Polygon3D polygon : polygons) {
			this.polygons.add(polygon.clone());
		}

		polygonComparator = new PolygonComparator();
	}

	/**
	 * Render the mesh to the screen by rendering every polygon of the mesh
	 * @param g Graphics object used to draw polygons
	 * @param drawType Signifies which draw type should be used to render this mesh
	 * @param lightSource A source of light illuminating the mesh
	 */
	public void render(
			Graphics2D g, DrawType drawType,
			LightSource lightSource, Camera camera,
			ProjectionSettings projectionSettings
	) {
		for (Polygon3D polygon: polygons) {
			polygon.render(g, drawType, lightSource, camera, projectionSettings);
		}
	}

	/**
	 * Currently unused.
	 * @param axis
	 * @param inc
	 */
	public void translate(Axis axis, double inc) {
		for (Polygon3D poly: polygons) {
			poly.translate(axis, inc);
		}
	}

	/**
	 * Rotate the mesh around an axis.
	 * @param axis Which axis to rotate around.
	 * @param degrees How much should the mesh be rotated in degrees.
	 * @param clockwise Which direction (clockwise or counterclockwise) the mesh should be rotated.
	 */
	public void rotate(Axis axis, double degrees, boolean clockwise) {
		for (Polygon3D poly: polygons) {
			poly.rotate(axis, degrees, clockwise);
		}
		sortPolygons();
	}

	/**
	 * Fallback method for rotate. Used when the <code>clockwise</code> parameter is not provided.
	 * @param axis Which axis should the mesh be rotated.
	 * @param degrees How much should the mesh be rotated.
	 */
	public void rotate(Axis axis, double degrees) {
		rotate(axis, degrees, false);
	}

	/**
	 * Sort polygons according to their average Z coordinate.
	 */
	public void sortPolygons() {
		polygons.sort(polygonComparator);
	}

	/**
	 * Used to sort meshes in an <code>Entity</code>.
	 * Meshes are sorted according to their polygons' average Z coordinate.
	 * @return Average Z coordinate of the mesh
	 */
	public double getAverageZ() {
		double sum = 0;
		for (Polygon3D polygon: polygons) {
			sum += polygon.getAverageZ();
		}
		return sum / polygons.size();
	}

	/**
	 * <code>toString</code> method for a mesh. Returns its list of polygons.
	 * @return The mesh as a string
	 */
	@Override
	public String toString() {
		return polygons.toString();
	}

	/**
	 * Used to clone a mesh.
	 * <p>
	 *     Note: This will be replaced by <code>Object.clone()</code> in the future.
	 * </p>
	 * @return A new mesh object that has the same data as this mesh.
	 */
	@Override
	public Mesh clone() {
		Polygon3D[] polygons = new Polygon3D[this.polygons.size()];
		for (int i = 0; i < polygons.length; i++) {
			polygons[i] = this.polygons.get(i).clone();
		}

		return new Mesh(polygons);
	}

	/**
	 * Get the list of polygons of this mesh.
	 * @return <code>ArrayList</code> of polygons of the mesh
	 */
	public ArrayList<Polygon3D> getPolygons() {
		return polygons;
	}
}
