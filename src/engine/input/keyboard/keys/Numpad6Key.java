package engine.input.keyboard.keys;

import engine.geometry.Axis;
import engine.geometry.entity.Entity;
import engine.graphics.Renderer;

import java.awt.event.KeyEvent;

import static engine.input.keyboard.Keyboard.degreeChangeSpeed;

public class Numpad6Key extends Key {

	public Numpad6Key(Renderer renderer) {
		super(renderer, KeyEvent.VK_NUMPAD6);
	}

	@Override
	public void pressKey() {
		if (!isKeyPressed) return;

		for (Entity entity: renderer.getEntities()) {
			entity.rotate(Axis.xAxis, degreeChangeSpeed, false);
		}
	}
}
