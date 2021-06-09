from ..base import BaseAPIClass
from ..display import Settings


class ProjectionSettings(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.graphics.math.geometry.ProjectionSettings class."""

	def __init__(self, settings: Settings, fov: float, near: float, far: float):
		self.width = settings.screen_width
		self.height = settings.screen_height
		self.fov = fov
		self.near = near
		self.far = far

	def as_dict(self):
		return self.__dict__
