from .base import BaseAPIClass
from .libraries import *
from .models import *


class Settings(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.graphics.display.Settings class."""
	possible_kwargs = [
		'use_antialiasing', 'degree_change_speed', 'camera_move_speed'
	]

	def __init__(self, screen_width: int, screen_height: int, fps: int, title: str, **kwargs):
		"""Initialize settings."""
		self.screen_width = screen_width
		self.screen_height = screen_height
		self.fps = fps
		self.title = title

		self._parse_kwargs(kwargs)

	def as_dict(self):
		return self.__dict__


# class Scene(BaseAPIClass):
# 	"""Represents the berkerdemoglu.engine.graphics.rendering.scene.Scene class."""

# 	def __init__(self, 
# 			background_color: Color, light_source: LightSource,
# 			projection_values: ProjectionValues, *entities: List[Entity]
# 		):
# 		pass

# 	def as_dict(self):
# 		pass
