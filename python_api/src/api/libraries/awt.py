"""
This module defines classes that are already defined in the 
Java AWT (Abstract Window Toolkit) framework/library.
"""

from ..base import BaseAPIClass


class Color(BaseAPIClass):
	"""Represents the java.awt.Color class."""

	def __init__(self, r: int, g: int, b: int):
		"""Initialize the color in RGB format."""
		self.r = r
		self.g = g
		self.b = b

	def as_dict(self):
		return self.__dict__
