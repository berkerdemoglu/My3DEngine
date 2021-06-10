from typing import Sequence

from ..base import BaseAPIClass
from .mesh import Mesh
from ..utils import to_dict_seq


class Entity(BaseAPIClass):
	"""Represents berkerdemoglu.engine.models.entity.Entity"""

	def __init__(self, *meshes: Sequence[Mesh]):
		self.meshes = meshes

	def as_dict(self):
		d = {
			'meshes': to_dict_seq(self.meshes)
		}

		return d
