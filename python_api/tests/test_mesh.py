import unittest

from src.api.geom import Polygon, Vector
from src.api.libraries import ORANGE
from src.api.models import Mesh


class MeshTestCase(unittest.TestCase):
	"""Tests the Mesh class."""

	def setUp(self):
		polygons = list()
		for i in range(6):
			vertices = list()
			for j in range(4):
				vertex = Vector()
				vertices.append(vertex)
			polygon = Polygon(ORANGE, *vertices)
			polygons.append(polygon)

		self.mesh = Mesh(*polygons)

	def test_as_dict(self):
		"""Test the as_dict() method of the mesh object."""
		polygons = list()
		for polygon in self.mesh.polygons:
			polygons.append(polygon)

		d = self.mesh.as_dict()

		self.assertEqual(len(d['polygons']), len(polygons))
		self.assertNotEqual(d['polygons'], self.mesh.polygons)
		self.assertEqual(type(d), dict)

	def tearDown(self):
		del self.mesh


if __name__ == '__main__':
	unittest.main()
