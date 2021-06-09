from typing import Sequence

from ..base import BaseAPIClass
from ..geom import Polygon


class Mesh(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.models.mesh.Mesh class."""

	def __init__(self, *polygons: Sequence[Polygon]):
		self.polygons = polygons

	def as_dict(self):
		d = {
			'polygons': [polygon.as_dict() for polygon in self.polygons]
		}

		return d
