from typing import Sequence

from ..base import BaseAPIClass
from ..geom import Vector, ProjectionSettings
from ..libraries.awt import Color
from ..models import Entity
from ..utils import to_dict, to_dict_seq


class LightSource(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.graphics.rendering.scene.LightSource class."""

	def __init__(self, source: Vector):
		self.source = source

	def as_dict(self):
		d = {
			'source': to_dict(self.source)
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
			'background_color': to_dict(self.background_color),
			'light_source': to_dict(self.light_source),
			'projection_settings': to_dict(self.projection_settings),
			'entities': to_dict_seq(self.entities)
		}
		
		return d
