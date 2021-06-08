import subprocess
import os
from pathlib import Path

from typing import List

import json

# Import packages from the API
from .api import Settings


class Application:

	def __init__(self, settings: Settings):
		self.obj_dict = {
			'settings': settings.as_dict()
		}

	# Run methods
	def run(self):
		commands = self._generate_commands()
		subprocess.run(commands)

	def _generate_commands(self) -> List:
		commands = []

		# Java
		commands.append(self._get_java())
		commands.append('-jar')

		# Command line argument - JSON file
		commands.append(self.filename)
		
		return commands

	def _get_java(self) -> str:
		java_home = Path(os.getenv('JAVA_HOME'))
		java_bin = java_home / 'bin'
		return str(java_bin / 'java.exe')
