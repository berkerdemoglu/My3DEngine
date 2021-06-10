import subprocess
import os
from pathlib import Path

from typing import List

import json

# Import packages from the API
from .settings import Settings
from .scene import Scene
from .utils import to_dict
from .base import BaseAPIClass


class Application(BaseAPIClass):

	def __init__(self, settings: Settings, scene: Scene):
		self.settings = settings
		self.scene = scene

	def as_dict(self):
		d = {
			'settings': to_dict(self.settings),
			'scene': to_dict(self.scene)
		}
		return d

	def _dump_json(self, filename, pretty_output) -> None:
		"""Dump the object dictionary to a JSON file for the engine to use."""
		d = self.as_dict()
		
		with open(filename, 'w') as f:
			if pretty_output:
				json.dump(d, f, indent=2)
			else:
				json.dump(d, f)

	@staticmethod
	def _generate_filename(filename) -> str:
		if not filename:
			filename = f'{os.getcwd()}/start.json'
		elif not filename.endswith('.json') and '.' not in filename:
			filename += '.json'

		return filename

	def run(self, filename: str = '', start=True, pretty_output=False) -> None:
		"""
		Start the engine with the provided file name without the .json extension.

		The 'pretty_output' parameter can be used to dump the objects to the JSON
		file with indents and linebreaks. However developers should refrain from 
		doing so as it greatly increases file size. It is a parameter so 
		that one can read the file and debug, if need be.
		"""
		filename = self._generate_filename(filename)

		self._dump_json(filename, pretty_output)

		if start:
			subprocess.run(self._generate_commands(filename))
		else:
			print("Dumped JSON to file")

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
