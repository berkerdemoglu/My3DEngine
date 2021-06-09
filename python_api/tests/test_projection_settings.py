import unittest

from src.api import Settings
from src.api.geom import ProjectionSettings


class ProjectionSettingsTestCase(unittest.TestCase):
	"""Tests the ProjectionSettings class."""

	def setUp(self):
		self.ps = ProjectionSettings(
				Settings(1900, 1080, 30, "CS:GO") , 90, 0.1, 1000
			)

	def test_as_dict(self):
		"""Test the as_dict() method of the ProjectionSettings class."""
		d = self.ps.as_dict()

		self.assertEqual(type(d), dict)
		self.assertNotIn('settings', d.keys())

		self.assertIn('fov', d.keys())

	def test_width_variable(self):
		"""Test that the screen width variable has been correctly initialized."""
		self.assertTrue(hasattr(self.ps, 'width'))
		self.assertFalse(hasattr(self.ps, 'screen_width'))
		self.assertEqual(self.ps.width, 1900)

	def test_height_variable(self):
		"""Test that the screen height variable has been correctly initialized."""
		self.assertTrue(hasattr(self.ps, 'height'))
		self.assertFalse(hasattr(self.ps, 'screen_height'))
		self.assertEqual(self.ps.height, 1080)

	def tearDown(self):
		del self.ps


if __name__ == '__main__':
	unittest.main()
