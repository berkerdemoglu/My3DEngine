from ..base import BaseAPIClass
from ..settings import Settings


class ProjectionSettings(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.graphics.math.geometry.ProjectionSettings class."""

	def __init__(self, settings: Settings, fov: float, near: float, far: float):
		self.width = settings.screen_width
		self.height = settings.screen_height
		self.fov = float(fov)
		self.near = float(near)
		self.far = float(far)

	def as_dict(self):
		return self.__dict__
