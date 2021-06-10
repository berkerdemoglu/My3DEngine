"""
This module is used to test the whole package.
"""

import os

from src.api import (
		Settings,
		models as m,
		geom as g,
		awt,
		Scene,
		Application
	)


# Create settings
settings = Settings(600, 600, 60, "3D Engine Demo")


# Create objects for the scene
s = 50;
p1 = g.Vector(-2*s, -s, s);
p2 = g.Vector(2*s, -s, s);
p3 = g.Vector(2*s, s, s);
p4 = g.Vector(-2*s, s, s);
p5 = g.Vector(-2*s, -s, -s);
p6 = g.Vector(2*s, -s, -s);
p7 = g.Vector(2*s, s, -s);
p8 = g.Vector(-2*s, s, -s);

mesh = m.Mesh(
		g.Polygon(awt.BLUE, p5, p6, p7, p8),
		g.Polygon(awt.WHITE, p1, p2, p6, p5),
		g.Polygon(awt.YELLOW, p1, p5, p8, p4),
		g.Polygon(awt.GREEN, p2, p6, p7, p3),
		g.Polygon(awt.ORANGE, p4, p3, p7, p8),
		g.Polygon(awt.RED, p1, p2, p3, p4)
	)

entity = m.Entity(mesh)

# Create the scene
scene = Scene(awt.BLACK, None, g.ProjectionSettings(settings, 90, 0.1, 1000), entity)

# Create the application object
app = Application(settings, scene)

app.run(f'{os.getcwd()}/tests/start', start=False, pretty_output=True)
