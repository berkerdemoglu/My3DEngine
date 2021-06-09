from typing import Sequence

from ..base import BaseAPIClass
from .mesh import Mesh


class Entity(BaseAPIClass):
	"""Represents berkerdemoglu.engine.models.entity.Entity"""

	def __init__(self, *meshes: Sequence[Mesh]):
		self.meshes = meshes

	def as_dict(self):
		meshes_list = list()
		for mesh in self.meshes:
			meshes_list.append(mesh.as_dict())

		return {'meshes': meshes_list}
