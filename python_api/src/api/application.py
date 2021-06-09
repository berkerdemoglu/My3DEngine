import subprocess
import os
from pathlib import Path

from typing import List

import json

# Import packages from the API
from .settings import Settings
from .scene import Scene


class Application:

	def __init__(self, settings: Settings, scene: Scene):
		self.obj_dict = {
			'settings': settings.as_dict(),
			'scene': scene.as_dict(),
		}

	def _dump_json(self, filename):
		"""Dump the object dictionary to a JSON file for the engine to use."""
		with open(filename, 'w') as f:
			json.dump(self.obj_dict, f)

	def run(self, filename: str = 'start') -> None:
		"""Start the engine with the provided file name without the .json extension."""
		if not filename.endswith('.json') and '.' not in filename:
			filename += '.json'

		self._dump_json(filename)

		subprocess.run(self._generate_commands(filename))

	def _generate_commands(self, filename) -> List:
		commands = []

		# Java
		commands.append(self._get_java())
		commands.append('-jar')

		# Command line argument - JSON file
		commands.append(filename)
		
		return commands

	def _get_java(self) -> str:
		java_home = Path(os.getenv('JAVA_HOME'))
		java_bin = java_home / 'bin'
		return str(java_bin / 'java.exe')
