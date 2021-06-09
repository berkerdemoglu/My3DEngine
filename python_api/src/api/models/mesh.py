from typing import Sequence

from ..base import BaseAPIClass
from ..geom import Polygon


class Mesh(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.models.mesh.Mesh class."""

	def __init__(self, *polygons: Sequence[Polygon]):
		self.polygons = polygons

	def as_dict(self):
		polygons_list = list()
		for polygon in self.polygons:
			polygons_list.append(polygon.as_dict())

		return {'polygons': polygons_list}
