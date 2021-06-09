from ..base import BaseAPIClass


class Vector(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.graphics.math.Vector3D class."""

	def __init__(self, x: int = 0, y: int = 0, z: int = 0):
		self.x = x
		self.y = y
		self.z = z

	def as_dict(self):
		return self.__dict__
