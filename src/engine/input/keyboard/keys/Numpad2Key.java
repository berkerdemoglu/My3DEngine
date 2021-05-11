package engine.input.keyboard.keys;

import engine.geometry.Axis;
import engine.geometry.entity.Entity;
import engine.graphics.renderer.Renderer;

import java.awt.event.KeyEvent;

import static engine.input.keyboard.Keyboard.degreeChangeSpeed;

public class Numpad2Key extends Key {

	public Numpad2Key(Renderer renderer) {
		super(renderer, KeyEvent.VK_NUMPAD2);
	}

	@Override
	public void pressKey() {
		if (!isKeyPressed) return;

		for (Entity entity: renderer.getScene().getEntities()) {
			entity.rotate(Axis.xAxis, degreeChangeSpeed, false);
		}
	}
}
