import unittest

from src.api.scene import Scene, LightSource
from src.api import awt, Settings
from src.api.geom import Vector, Polygon, ProjectionSettings
from src.api.models import Entity, Mesh


class SceneTestCase(unittest.TestCase):
	"""Tests the Scene class."""

	def setUp(self):
		entities = [
			Entity(
				Mesh(
					Polygon(awt.CYAN, *[Vector(i%2, i%3, i%4) for i in range(6)])
				)
			),
			Entity(
				Mesh(
					Polygon(awt.CYAN, *[Vector(-i, i%5, 0) for i in range(4)])
				),
				Mesh(
					Polygon(awt.CYAN, *[Vector(i, -i%5, i+2) for i in range(7)])
				)
			)
		]

		self.scene = Scene(
				awt.BLACK, LightSource(Vector(50, 0 ,0)), ProjectionSettings(
						Settings(800, 600, 60, "3D Engine"), 90, 0.1, 1000
					), *entities
			)

	def test_as_dict(self):
		"""Test the as_dict() method of the Scene class."""
		d = self.scene.as_dict()
		entities_key = d['entities']

		self.assertIn('entities', d.keys())
		self.assertEqual(type(entities_key), list)

		self.assertNotEqual(type(entities_key[0]), Entity)
		self.assertEqual(type(entities_key[0]), dict)

	def test_background_color(self):
		"""Test the background color of the scene."""
		self.assertTrue(hasattr(self.scene, 'background_color'))
		self.assertFalse(hasattr(self.scene, 'color'))
		self.assertEqual(self.scene.background_color, awt.BLACK)

	def test_entities(self):
		"""Test that entities have been successfully initialized."""
		self.assertTrue(hasattr(self.scene, 'entities'))
		self.assertEqual(type(self.scene.entities), tuple)
		self.assertIsNotNone(self.scene.entities)
		self.assertGreater(len(self.scene.entities), 0)

	def test_no_entities_scene(self):
		"""Test a scene with no entities."""
		s = Scene(
				awt.BLACK, LightSource(Vector(50, 0 ,0)), ProjectionSettings(
						Settings(800, 600, 60, "3D Engine"), 90, 0.1, 1000
					)
			)

		self.assertIsNotNone(s.entities)
		self.assertEqual(len(s.entities), 0)

	def tearDown(self):
		del self.scene


if __name__ == '__main__':
	unittest.main()
