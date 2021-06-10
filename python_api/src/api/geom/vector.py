from ..base import BaseAPIClass


class Vector(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.graphics.math.Vector3D class."""

	def __init__(self, x: float = 0, y: float = 0, z: float = 0):
		self.x = float(x)
		self.y = float(y)
		self.z = float(z)

	def as_dict(self):
		return self.__dict__
