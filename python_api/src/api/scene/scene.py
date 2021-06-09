from typing import Sequence

from ..base import BaseAPIClass
from ..geom import Vector, ProjectionSettings
from ..libraries.awt import Color
from ..models import Entity


class LightSource(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.graphics.rendering.scene.LightSource class."""

	def __init__(self, source: Vector):
		self.source = source

	def as_dict(self):
		d = {
			'source': self.source.as_dict()
		}
		return d


class Scene(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.graphics.rendering.scene.Scene class."""

	def __init__(self, 
			background_color: Color, light_source: LightSource,
			projection_settings: ProjectionSettings, *entities: Sequence[Entity]
		):
		self.background_color = background_color
		self.light_source = light_source
		self.projection_settings = projection_settings
		self.entities = entities

	def as_dict(self):
		d = {
			'background_color': self.background_color.as_dict(),
			'light_source': self.light_source.as_dict(),
			'projection_settings': self.projection_settings.as_dict(),
			'entities': [entity.as_dict() for entity in self.entities]
		}
		
		return d
