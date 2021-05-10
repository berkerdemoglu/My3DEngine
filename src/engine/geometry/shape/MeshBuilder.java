package engine.geometry.shape;

import engine.geometry.Mesh;
import engine.geometry.Point3D;
import engine.geometry.Polygon3D;

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
	public static Mesh constructCube(Color color, double size, Point3D center) {
		double centerX = center.x;
		double centerY = center.y;
		double centerZ = center.z;

		double s = size / 2;
		Point3D p1 = new Point3D(centerX - s, centerY + -s, centerZ + s);
		Point3D p2 = new Point3D(centerX + s, centerY + -s, centerZ + s);
		Point3D p3 = new Point3D(centerX + s, centerY + s, centerZ + s);
		Point3D p4 = new Point3D(centerX - s, centerY + s, centerZ + s);
		Point3D p5 = new Point3D(centerX + -s, centerY + -s, centerZ + -s);
		Point3D p6 = new Point3D(centerX + s, centerY + -s, centerZ + -s);
		Point3D p7 = new Point3D(centerX + s, centerY + s, centerZ + -s);
		Point3D p8 = new Point3D(centerX + -s, centerY + s, centerZ + -s);

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
	public static Mesh constructRectangularPrism(Color color,double a, double b, double c, Point3D center) {
		double centerX = center.x;
		double centerY = center.y;
		double centerZ = center.z;

		double halfA = a / 2;
		double halfB = b / 2;
		double halfC = c / 2;

		Point3D p1 = new Point3D(centerX + -halfA, centerY + -halfB, centerZ + halfC);
		Point3D p2 = new Point3D(centerX + halfA, centerY + -halfB, centerZ + halfC);
		Point3D p3 = new Point3D(centerX + halfA, centerY + halfB, centerZ + halfC);
		Point3D p4 = new Point3D(centerX + -halfA, centerY + halfB, centerZ + halfC);
		Point3D p5 = new Point3D(centerX + -halfA, centerY + -halfB, centerZ + -halfC);
		Point3D p6 = new Point3D(centerX + halfA, centerY + -halfB, centerZ + -halfC);
		Point3D p7 = new Point3D(centerX + halfA, centerY + halfB, centerZ + -halfC);
		Point3D p8 = new Point3D(centerX + -halfA, centerY + halfB, centerZ + -halfC);

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
	 * @return The mesh from the file
	 * @throws IOException Throws an <code>IOException</code> if there is an error with reading the file.
	 */
	public static Mesh fromObjFile(String filename, Color color) throws IOException {
		Path filePath = Paths.get(filename);

		List<String> lines = Files.readAllLines(filePath);

		List<Polygon3D> triangles = new ArrayList<>();
		List<Point3D> vertices = new ArrayList<>();
		String[] line;
		for (String l: lines) {
			line = l.split("\\s+");
			if (l.isEmpty()) continue;

			if (l.charAt(0) == 'v') {
				Point3D v = new Point3D(
						Double.parseDouble(line[1]),
						Double.parseDouble(line[2]),
						Double.parseDouble(line[3]));
				vertices.add(v);
			}

			if (l.charAt(0) == 'f') {
				int[] f = new int[3];
				f[0] = Integer.parseInt(line[1]);
				f[1] = Integer.parseInt(line[2]);
				f[2] = Integer.parseInt(line[3]);
				triangles.add(new Polygon3D(color, vertices.get(f[0] - 1), vertices.get(f[1] - 1), vertices.get(f[2] - 1)));
			}
		}

		Polygon3D[] polygonArray = new Polygon3D[triangles.size()];
		for (int i = 0; i < polygonArray.length; i++) {
			polygonArray[i] = triangles.get(i);
		}

		Mesh mesh = new Mesh(polygonArray);

		return mesh;
	}
}
