from typing import Sequence

from ..base import BaseAPIClass
from ..libraries import Color
from .vector import Vector


class Polygon(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.graphics.math.geometry.Polygon3D class."""

	def __init__(self, color: Color, *vertices: Sequence[Vector]):
		self.color = color

		self._check_args(vertices)
		self.vertices = vertices

	def _check_args(self, vertices):
		if len(vertices) <= 2:
			print(len(vertices))
			raise ValueError('2 or more vertices must be provided to make a polygon')

		for v in vertices:
			if type(v) != Vector:
				raise ValueError('Vectors must be provided as input')

	def as_dict(self):
		vertices_list = list()
		for vertex in self.vertices:
			vertices_list.append(vertex.as_dict())

		return {'color': self.color, 'vertices': vertices_list}
