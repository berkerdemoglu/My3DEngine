package engine.input.keyboard.keys;

import engine.geometry.Axis;
import engine.geometry.entity.Entity;
import engine.graphics.renderer.Renderer;

import java.awt.event.KeyEvent;

import static engine.input.keyboard.Keyboard.degreeChangeSpeed;

public class Numpad9Key extends Key {

	public Numpad9Key(Renderer renderer) {
		super(renderer, KeyEvent.VK_NUMPAD9);
	}

	@Override
	public void pressKey() {
		if (!isKeyPressed) return;

		for (Entity entity: renderer.getScene().getEntities()) {
			entity.rotate(Axis.zAxis, degreeChangeSpeed, true);
		}
	}
}
