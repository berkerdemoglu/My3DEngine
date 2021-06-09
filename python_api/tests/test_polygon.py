import unittest

from src.api.geom import Vector, Polygon
from src.api.libraries import Color, CYAN


class PolygonTestCase(unittest.TestCase):
	"""Tests the Polygon class."""

	def setUp(self):
		self.test_vertex = Vector(0, 100, 0)
		vertices = [
			Vector(0, 0, 0), self.test_vertex,
			Vector(100, 0, 0), Vector(100, 100, 0)
		]

		self.polygon = Polygon(CYAN, *vertices)

	def test_vertices(self):
		"""Test that the vertices variable has been initialized."""
		self.assertTrue(hasattr(self.polygon, 'vertices'))

	def test_as_dict(self):
		"""Test the as_dict() method of the polygon."""
		d = self.polygon.as_dict()
		self.assertEqual(type(d['vertices']), list)

	def test_vertex_count(self):
		"""Test that the polygon has 4 vertices."""
		self.assertEqual(len(self.polygon.vertices), 4)

	def test_vertex_tuple(self):
		"""Test that the vertices are stored in a tuple."""
		self.assertEqual(type(self.polygon.vertices), tuple)

	def test_vertex_in_vertices(self):
		"""Test that the vertices have been correctly initialized."""
		self.assertIn(self.test_vertex, self.polygon.vertices)

	def test_args(self):
		"""Test that vertices can/cannot be provided without unpacking."""
		vertices = [
			Vector(0, 0, 0), Vector(0, 100, 0),
			Vector(100, 0, 0), Vector(100, 100, 0)
		]

		self.assertRaises(ValueError, Polygon, CYAN, vertices)

	def test_invalid_number_vertices(self):
		"""Test that a polygon cannot be initialized with 2 or less vertices."""
		self.assertRaises(ValueError, Polygon, CYAN)

	def tearDown(self):
		del self.polygon
		del self.test_vertex


if __name__ == '__main__':
	unittest.main()
