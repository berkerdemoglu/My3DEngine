from ...base import BaseAPIClass


class Color(BaseAPIClass):
	"""Represents the java.awt.Color class."""

	def __init__(self, r: int, g: int, b: int):
		"""Initialize the color in RGB format."""
		self._set_color('r', r)
		self._set_color('g', g)
		self._set_color('b', b)

	def _set_color(self, name, value):
		if 0 <= value <= 255:
			setattr(self, name, int(value))
		else:
			raise ValueError("Color provided outside the possible range")

	def as_dict(self):
		return self.__dict__


# Color constants
WHITE = Color(255, 255, 255)
LIGHT_GRAY = Color(192, 192, 192)
GRAY = Color(128, 128, 128)
DARK_GRAY = Color(64, 64, 64)
BLACK = Color(0, 0, 0)

RED = Color(255, 0, 0)
GREEN = Color(0, 255, 0)
BLUE = Color(0, 0, 255)

YELLOW = Color(255, 255, 0)
MAGENTA = Color(255, 0, 255)
CYAN = Color(0, 255, 255)

PINK = Color(255, 175, 175)
ORANGE = Color(255, 200, 0)
