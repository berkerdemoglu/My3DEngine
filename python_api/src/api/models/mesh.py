from typing import Sequence

from ..base import BaseAPIClass
from ..geom import Polygon
from ..utils import to_dict_seq


class Mesh(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.models.mesh.Mesh class."""

	def __init__(self, *polygons: Sequence[Polygon]):
		self.polygons = polygons

	def as_dict(self):
		d = {
			'polygons': to_dict_seq(self.polygons)
		}

		return d
