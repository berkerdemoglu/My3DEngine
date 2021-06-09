import unittest

from src.api import Settings


class SettingsTestCase(unittest.TestCase):
	"""Tests the Settings class."""

	def setUp(self):
		self.settings = Settings(800, 600, 60, "3D Engine", use_antialiasing=False)

	def test_keyword_arguments(self):
		"""Check that the keyword arguments are being parsed correctly."""
		self.assertTrue(hasattr(self.settings, 'use_antialiasing'))

	def test_as_dict(self):
		"""Check that the as_dict() method is working correctly."""
		self.assertEqual(self.settings.as_dict(), self.settings.__dict__)

	def tearDown(self):
		del self.settings


if __name__ == '__main__':
	unittest.main()
