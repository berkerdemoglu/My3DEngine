from __future__ import annotations

from typing import Sequence

from ..base import BaseAPIClass
from .. import geom as g 
from ..libraries import awt
from ..utils import to_dict_seq


class Mesh(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.models.mesh.Mesh class."""

	def __init__(self, *polygons: Sequence[g.Polygon]):
		self.polygons = polygons

	def as_dict(self):
		d = {
			'polygons': to_dict_seq(self.polygons)
		}

		return d

	# MeshBuilder methods
	@classmethod
	def construct_cube(cls, color: awt.Color, size: float, center: g.Vector) -> Mesh:
		"""Construct a cube."""
		s = size / 2
		p1 = g.Vector(center.x - s, center.y - s, center.z + s);
		p2 = g.Vector(center.x + s, center.y - s, center.z + s);
		p3 = g.Vector(center.x + s, center.y + s, center.z + s);
		p4 = g.Vector(center.x - s, center.y + s, center.z + s);
		p5 = g.Vector(center.x - s, center.y - s, center.z - s);
		p6 = g.Vector(center.x + s, center.y - s, center.z - s);
		p7 = g.Vector(center.x + s, center.y + s, center.z - s);
		p8 = g.Vector(center.x - s, center.y + s, center.z - s);

		mesh = Mesh(
				g.Polygon(color, p5, p6, p7, p8),
				g.Polygon(color, p1, p2, p6, p5),
				g.Polygon(color, p1, p5, p8, p4),
				g.Polygon(color, p2, p6, p7, p3),
				g.Polygon(color, p4, p3, p7, p8),
				g.Polygon(color, p1, p2, p3, p4)
			)

		return mesh

	@classmethod
	def construct_rectangular_prism(
			cls, color: awt.Color, a: float, b: float, c: float, center: g.Vector
		) -> Mesh:
		"""Construct a rectangular prism (cuboid)."""

		half_a = a / 2
		half_b = b / 2
		half_c = c / 2

		p1 = g.Vector(center.x - half_a, center.y - half_b, center.z + half_c)
		p2 = g.Vector(center.x + half_a, center.y - half_b, center.z + half_c)
		p3 = g.Vector(center.x + half_a, center.y + half_b, center.z + half_c)
		p4 = g.Vector(center.x - half_a, center.y + half_b, center.z + half_c)
		p5 = g.Vector(center.x - half_a, center.y - half_b, center.z - half_c)
		p6 = g.Vector(center.x + half_a, center.y - half_b, center.z - half_c)
		p7 = g.Vector(center.x + half_a, center.y + half_b, center.z - half_c)
		p8 = g.Vector(center.x - half_a, center.y + half_b, center.z - half_c)

		mesh = Mesh(
				g.Polygon(color, p5, p6, p7, p8),
				g.Polygon(color, p1, p2, p6, p5),
				g.Polygon(color, p1, p5, p8, p4),
				g.Polygon(color, p2, p6, p7, p3),
				g.Polygon(color, p4, p3, p7, p8),
				g.Polygon(color, p1, p2, p3, p4)
		)

		return mesh


	@classmethod
	def construct_from_obj_file(
			cls, color: awt.Color, filename: str, scale: float, center: Vector
		) -> Mesh:
		"""Construct a mesh from a .obj file."""
		vertices = []
		polygons = []

		with open(filename, 'r') as f:
			lines = f.readlines()

		for line in lines:
			line_array = line.split(r'\\s+')
			if not line_array:
				continue

			char = line_array[0]
			if char == 'v':
				vertex = g.Vector(
						float(line_array[1] * scale) + center.x,
						float(line_array[2] * scale) + center.y,
						float(line_array[3] * scale) + center.z,
					)
				vertices.append(vertex)
				break
			if char == 'f':
				faces = [
					int(line_array[1]),
					int(line_array[2]),
					int(line_array[3])
				]
				polygons.append(
						g.Polygon(
								color,
								vertices[faces[0] - 1],
								vertices[faces[1] - 1],
								vertices[faces[2] - 1]
							)
					)


		mesh = Mesh(*polygons)
		return mesh
