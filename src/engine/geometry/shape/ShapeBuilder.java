package engine.geometry.shape;

import engine.geometry.Mesh;
import engine.geometry.Point3D;
import engine.geometry.Polygon3D;

import java.awt.Color;

public class ShapeBuilder {

	public static Mesh constructCube(Color color, double size, Point3D centerPoint) {
		double centerX = centerPoint.x;
		double centerY = centerPoint.y;
		double centerZ = centerPoint.z;

		double s = size / 2;
		Point3D p1 = new Point3D(centerX + -s, centerY + -s, centerZ + s);
		Point3D p2 = new Point3D(centerX + s, centerY + -s, centerZ + s);
		Point3D p3 = new Point3D(centerX + s, centerY + s, centerZ + s);
		Point3D p4 = new Point3D(centerX + -s, centerY + s, centerZ + s);
		Point3D p5 = new Point3D(centerX + -s, centerY + -s, centerZ + -s);
		Point3D p6 = new Point3D(centerX + s, centerY + -s, centerZ + -s);
		Point3D p7 = new Point3D(centerX + s, centerY + s, centerZ + -s);
		Point3D p8 = new Point3D(centerX + -s, centerY + s, centerZ + -s);

		Mesh mesh = new Mesh(/*Color.CYAN,*/
				new Polygon3D(color, p5, p6, p7, p8),
				new Polygon3D(color, p1, p2, p6, p5),
				new Polygon3D(color, p1, p5, p8, p4),
				new Polygon3D(color, p2, p6, p7, p3),
				new Polygon3D(color, p4, p3, p7, p8),
				new Polygon3D(color, p1, p2, p3, p4)
		);

		return mesh;
	}
}
