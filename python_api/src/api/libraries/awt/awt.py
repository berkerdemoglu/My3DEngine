from ...base import BaseAPIClass


class Color(BaseAPIClass):
	"""Represents the java.awt.Color class."""

	def __init__(self, r: int, g: int, b: int):
		"""Initialize the color in RGB format."""
		self.r = r
		self.g = g
		self.b = b

	def as_dict(self):
		return self.__dict__


# Color constants
WHITE = Color(256, 256, 256)
LIGHT_GRAY = Color(192, 192, 192)
GRAY = Color(128, 128, 128)
DARK_GRAY = Color(64, 64, 64)
BLACK = Color(0, 0, 0)

RED = Color(256, 0, 0)
GREEN = Color(0, 256, 0)
BLUE = Color(0, 0, 256)

YELLOW = Color(255, 255, 0)
MAGENTA = Color(255, 0, 255)
CYAN = Color(0, 255, 255)

PINK = Color(255, 175, 175)
ORANGE = Color(255, 200, 0)
