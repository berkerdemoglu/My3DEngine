import unittest

from src.api.geom import Vector


class VectorTestCase(unittest.TestCase):
	"""Tests the Vector class."""
	
	def setUp(self):
		self.vertices = list()
		for i in range(10):
			vertex = Vector(i, i, i)
			self.vertices.append(vertex)

	def test_coordinates(self):
		"""Test that the coordinates have been set correctly."""
		for i in range(len(self.vertices)):
			self.assertEqual(self.vertices[i].x, i)
			self.assertEqual(self.vertices[i].y, i)
			self.assertEqual(self.vertices[i].z, i)

	def test_as_dict(self):
		"""Test the as_dict() method of the Vector class."""
		v = self.vertices[5]
		d = v.as_dict()
		self.assertEqual(d['x'], v.x)
		self.assertEqual(d, v.__dict__)

	def tearDown(self):
		del self.vertices


if __name__ == '__main__':
	unittest.main()
