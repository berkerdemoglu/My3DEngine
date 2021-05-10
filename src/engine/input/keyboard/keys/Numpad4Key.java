package engine.input.keyboard.keys;

import engine.geometry.Axis;
import engine.geometry.entity.Entity;
import engine.graphics.Renderer;

import java.awt.event.KeyEvent;

import static engine.input.keyboard.Keyboard.degreeChangeSpeed;

public class Numpad4Key extends Key {

	public Numpad4Key(Renderer renderer) {
		super(renderer, KeyEvent.VK_NUMPAD4);
	}

	@Override
	public void pressKey() {
		if (!isKeyPressed) return;

		for (Entity entity: renderer.getEntities()) {
			entity.rotate(Axis.zAxis, degreeChangeSpeed, false);
		}
	}
}
