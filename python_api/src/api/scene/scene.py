from typing import Sequence

from ..base import BaseAPIClass
from ..geom import Vector, ProjectionSettings


class Scene(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.graphics.rendering.scene.Scene class."""

	def __init__(self, 
			background_color: Color, light_source: LightSource,
			projection_values: ProjectionValues, *entities: Sequence[Entity]
		):
		self.background_color = background_color
		self.light_source = light_source
		self.projection_values = projection_values
		self.entities = entities

	def as_dict(self):
		d = {
			'background_color': self.background_color,
			'light_source': self.light_source.as_dict(),
			'projection_values': self.projection_values.as_dict(),
			'entities': [entity.as_dict() for entity in self.entities]
		}
		
		return d


class LightSource(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.graphics.rendering.scene.LightSource class."""

	def __init__(self, source: Vector):
		self.source = source

	def as_dict(self):
		return self.__dict__
