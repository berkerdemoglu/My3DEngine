from .base import BaseAPIClass


class Settings(BaseAPIClass):
	"""Represents the berkerdemoglu.engine.graphics.display.Settings class."""
	possible_kwargs = [
		'use_antialiasing', 'degree_change_speed', 'camera_move_speed'
	]

	def __init__(self, screen_width: int, screen_height: int, fps: int, title: str, **kwargs):
		"""Initialize settings."""
		self.screen_width = int(screen_width)
		self.screen_height = int(screen_height)
		self.fps = int(fps)
		self.title = title

		self._parse_kwargs(kwargs)

	def as_dict(self):
		return self.__dict__
