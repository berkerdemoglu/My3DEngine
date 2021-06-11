import unittest

from src.api import awt


class AWTTestCase(unittest.TestCase):
	"""Tests the AWT package."""

	def setUp(self):
		self.color = awt.Color(157, 12, 78)

	def test_color(self):
		"""Test that the Color class initializes correctly."""
		self.assertEqual(self.color.r, 157)
		self.assertTrue(hasattr(self.color, 'g'))

	def test_color_as_dict(self):
		"""Test the as_dict() method of the Color class."""
		d = self.color.as_dict()

		self.assertEqual(type(d), dict)
		self.assertIn(78, d.values())
		self.assertIn('b', d.keys())

	def test_constants(self):
		"""Test that the AWT package has color constants defined."""
		self.assertTrue(hasattr(awt, 'WHITE'))
		self.assertTrue(hasattr(awt, 'LIGHT_GRAY'))
		self.assertTrue(hasattr(awt, 'GRAY'))
		self.assertTrue(hasattr(awt, 'DARK_GRAY'))
		self.assertTrue(hasattr(awt, 'BLACK'))

		self.assertTrue(hasattr(awt, 'RED'))
		self.assertTrue(hasattr(awt, 'GREEN'))
		self.assertTrue(hasattr(awt, 'BLUE'))

		self.assertTrue(hasattr(awt, 'YELLOW'))
		self.assertTrue(hasattr(awt, 'MAGENTA'))
		self.assertTrue(hasattr(awt, 'CYAN'))
		self.assertTrue(hasattr(awt, 'PINK'))
		self.assertTrue(hasattr(awt, 'ORANGE'))

	def test_black(self):
		"""Test the black color of the Color class."""
		self.assertEqual(awt.BLACK.r, 0)
		self.assertEqual(awt.BLACK.g, 0)
		self.assertEqual(awt.BLACK.b, 0)

	def test_red(self):
		"""Test the black color of the Color class."""
		self.assertEqual(awt.RED.r, 255)
		self.assertEqual(awt.RED.g, 0)
		self.assertEqual(awt.RED.b, 0)

	def tearDown(self):
		del self.color


if __name__ == '__main__':
	unittest.main()
