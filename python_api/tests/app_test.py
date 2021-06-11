"""
This module is used to test the whole package.
"""

import os

from src.api import (
		Settings,
		models as m,
		geom as g,
		awt,
		Scene, LightSource,
		Application
	)


# Create settings
settings = Settings(600, 600, 60, "3D Engine Demo")


# Create objects for the scene
mesh = m.Mesh.construct_cube(awt.BLUE, 30, g.Vector(-50, 0, 0))

entity = m.Entity(mesh)

# Create the scene
light_source = LightSource(g.Vector(100, 0, 0))
scene = Scene(awt.BLACK, light_source, g.ProjectionSettings(settings, 90, 0.1, 1000), entity)

# Create the application object
app = Application(settings, scene)

app.run(f'{os.getcwd()}/tests/start', start=True, pretty_output=False)
