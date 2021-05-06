package engine.input.keyboard;

import engine.graphics.Renderer;

import java.awt.event.KeyListener;

public class Keyboard {
	public final KeyListener[] keyListeners;

	public Keyboard(Renderer renderer) {
		keyListeners = new KeyListener[]{
				new RotateXListener(renderer), new RotateYListener(renderer), new RotateZListener(renderer)
		};
	}
}
