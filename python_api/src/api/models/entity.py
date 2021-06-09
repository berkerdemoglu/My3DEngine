from typing import Sequence

from ..base import BaseAPIClass
from .mesh import Mesh


class Entity(BaseAPIClass):
	"""Represents berkerdemoglu.engine.models.entity.Entity"""

	def __init__(self, *meshes: Sequence[Mesh]):
		self.meshes = meshes

	def as_dict(self):
		d = {
			'meshes': [mesh.as_dict() for mesh in self.meshes]
		}

		return d
