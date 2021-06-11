package berkerdemoglu.engine.models.mesh;

import berkerdemoglu.engine.graphics.math.Vector3D;
import berkerdemoglu.engine.graphics.math.geometry.Polygon3D;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MeshBuilder {

	/**
	 * Constructs a cube.
	 * @param color The color of the cube
	 * @param size The size of the cube (The length of its edges)
	 * @param center The center of the cube
	 * @return A cube as a {@link Mesh}
	 */
	public static Mesh constructCube(Color color, double size, Vector3D center) {
		double centerX = center.x;
		double centerY = center.y;
		double centerZ = center.z;

		double s = size / 2;
		Vector3D p1 = new Vector3D(centerX - s, centerY + -s, centerZ + s);
		Vector3D p2 = new Vector3D(centerX + s, centerY + -s, centerZ + s);
		Vector3D p3 = new Vector3D(centerX + s, centerY + s, centerZ + s);
		Vector3D p4 = new Vector3D(centerX - s, centerY + s, centerZ + s);
		Vector3D p5 = new Vector3D(centerX + -s, centerY + -s, centerZ + -s);
		Vector3D p6 = new Vector3D(centerX + s, centerY + -s, centerZ + -s);
		Vector3D p7 = new Vector3D(centerX + s, centerY + s, centerZ + -s);
		Vector3D p8 = new Vector3D(centerX + -s, centerY + s, centerZ + -s);

		Mesh mesh = new Mesh(
				new Polygon3D(color, p5, p6, p7, p8),
				new Polygon3D(color, p1, p2, p6, p5),
				new Polygon3D(color, p1, p5, p8, p4),
				new Polygon3D(color, p2, p6, p7, p3),
				new Polygon3D(color, p4, p3, p7, p8),
				new Polygon3D(color, p1, p2, p3, p4)
		);

		return mesh;
	}

	/**
	 * Constructs a rectangular prism (a cuboid).
	 * @param color The color of the prism.
	 * @param a The length of its edge in the x-axis.
	 * @param b The length of its edge in the y-axis.
	 * @param c The length of its edge in the z-axis.
	 * @param center The center of the rectangular prism.
	 * @return A rectangular prism as a {@link Mesh}
	 */
	public static Mesh constructRectangularPrism(Color color, double a, double b, double c, Vector3D center) {
		double centerX = center.x;
		double centerY = center.y;
		double centerZ = center.z;

		double halfA = a / 2;
		double halfB = b / 2;
		double halfC = c / 2;

		Vector3D p1 = new Vector3D(centerX - halfA, centerY - halfB, centerZ + halfC);
		Vector3D p2 = new Vector3D(centerX + halfA, centerY - halfB, centerZ + halfC);
		Vector3D p3 = new Vector3D(centerX + halfA, centerY + halfB, centerZ + halfC);
		Vector3D p4 = new Vector3D(centerX - halfA, centerY + halfB, centerZ + halfC);
		Vector3D p5 = new Vector3D(centerX - halfA, centerY - halfB, centerZ - halfC);
		Vector3D p6 = new Vector3D(centerX + halfA, centerY - halfB, centerZ - halfC);
		Vector3D p7 = new Vector3D(centerX + halfA, centerY + halfB, centerZ - halfC);
		Vector3D p8 = new Vector3D(centerX - halfA, centerY + halfB, centerZ - halfC);

		Mesh mesh = new Mesh(
				new Polygon3D(color, p5, p6, p7, p8),
				new Polygon3D(color, p1, p2, p6, p5),
				new Polygon3D(color, p1, p5, p8, p4),
				new Polygon3D(color, p2, p6, p7, p3),
				new Polygon3D(color, p4, p3, p7, p8),
				new Polygon3D(color, p1, p2, p3, p4)
		);

		return mesh;
	}

	/**
	 * Constructs a {@link Mesh} from a <i>.obj</i> file.
	 * @param filename The path to the <i>.obj</i> file.
	 * @param color The color of the mesh
	 * @param scale The value to scale the points by
	 * @param center The center of the mesh
	 * @return The mesh from the file
	 * @throws IOException Throws an <code>IOException</code> if there is an error with reading the file.
	 */
	public static Mesh constructFromObjFile(
			String filename, Color color, double scale, Vector3D center
	) throws IOException {
		Path filePath = Paths.get(filename);

		List<String> lines = Files.readAllLines(filePath);

		List<Vector3D> vertices = new ArrayList<>();
		List<Polygon3D> polygons = new ArrayList<>();
		String[] line;
		for (String l: lines) {
			line = l.split("\\s+");
			if (l.isEmpty()) continue; // make sure that the line is not empty

			switch (l.charAt(0)) {
				case 'v':
					// We are reading a vertex
					Vector3D v = new Vector3D(
							(Double.parseDouble(line[1]) * scale) + center.x,
							(Double.parseDouble(line[2]) * scale) + center.y,
							(Double.parseDouble(line[3])  * scale) + center.z
					);
					vertices.add(v);
					break;
				case 'f':
					// We are reading a face (triangle)
					int[] f = new int[3];
					f[0] = Integer.parseInt(line[1]);
					f[1] = Integer.parseInt(line[2]);
					f[2] = Integer.parseInt(line[3]);
					polygons.add(new Polygon3D(
									color,
									vertices.get(f[0] - 1),
									vertices.get(f[1] - 1),
									vertices.get(f[2] - 1)
							)
					);
					break;
			}
		}

		Mesh mesh = new Mesh(polygons);
		return mesh;
	}
}
