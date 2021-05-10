package engine.input.keyboard;

import engine.Engine;
import engine.graphics.Renderer;
import engine.input.keyboard.keys.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Keyboard implements KeyListener {
	private final Key[] keys;

	public final static double degreeChangeSpeed = 45.0 / Engine.FPS;

	public Keyboard(Renderer renderer, Key... keys) {
		this.keys = Arrays.copyOf(keys, keys.length);
	}

	public Keyboard(Renderer renderer) {
		this.keys = new Key[]{
				new Numpad2Key(renderer), new Numpad8Key(renderer),
				new Numpad4Key(renderer), new Numpad6Key(renderer),
				new Numpad7Key(renderer), new Numpad9Key(renderer),
		};
	}

	public void pressKeys() {
		// Press all keys
		for (Key key: keys) {
			key.pressKey();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Make the pressed button's boolean value true
		int keyCode = e.getKeyCode();
		for (Key key: keys) {
			if (key.getKeyCode() == keyCode) {
				System.out.println("Pressed key: " + keyCode);
				key.setKeyPressed(true);
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Make the released button's boolean value false
		int keyCode = e.getKeyCode();
		for (Key key: keys) {
			if (key.getKeyCode() == keyCode) {
				key.setKeyPressed(false);
				break;
			}
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {} // not required, empty implementation
}
