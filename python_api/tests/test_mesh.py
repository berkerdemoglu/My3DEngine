import unittest

import os

from src.api.geom import Polygon, Vector
from src.api import libraries as c
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
			polygon = Polygon(c.ORANGE, *vertices)
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

	def test_cube(self):
		"""Test the cube constructing method."""
		cube = Mesh.construct_cube(c.BLUE, 100, Vector(0, 0, 0))

		self.assertTrue(len(cube.polygons), 6)
		self.assertTrue(cube.polygons[0].color, c.BLUE)

	def test_rectangular_prism(self):
		"""Test the rectangular prism constructing method."""
		cuboid = Mesh.construct_rectangular_prism(
				c.MAGENTA, 20, 10, 30, Vector(10, 20, -25)
			)

		self.assertEqual(type(cuboid.as_dict()), dict)
		self.assertTrue(len(cuboid.polygons), 6)
		self.assertTrue(cuboid.polygons[0].color, c.BLUE)

	def test_obj_file(self):
		"""Construct a mesh from an OBJ file and test that it works."""
		sphere_path = os.path.realpath("../src/main/resources/sphere.obj")

		mesh = Mesh.construct_from_obj_file(
				c.PINK, 
				sphere_path, 
				10, 
				Vector(50, 50, 50)
			)

		self.assertEqual(type(mesh.as_dict()), dict)

	def tearDown(self):
		del self.mesh


if __name__ == '__main__':
	unittest.main()
