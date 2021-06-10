from typing import Sequence

from ..base import BaseAPIClass
from ..libraries import Color
from .vector import Vector
from ..utils import to_dict, to_dict_seq


class Polygon(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.graphics.math.geometry.Polygon3D class."""

	def __init__(self, color: Color, *vertices: Sequence[Vector]):
		self.color = color

		self._check_args(vertices)
		self.vertices = vertices

	def _check_args(self, vertices):
		"""Check that nothing weird was passed as an argument."""
		if not hasattr(vertices, '__iter__') and type(vertices) != str:
			raise TypeError('A sequence must be provided as input')

		for v in vertices:
			if type(v) != Vector:
				raise TypeError('Vectors must be provided as input')

		if len(vertices) <= 2:
			raise ValueError('2 or more vertices must be provided to make a polygon')

	def as_dict(self):
		d = {
			'color': to_dict(self.color),
			'vertices': to_dict_seq(self.vertices)
		}

		return d
