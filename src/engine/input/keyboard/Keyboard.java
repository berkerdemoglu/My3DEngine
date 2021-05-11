package engine.input.keyboard;

import engine.Engine;
import engine.graphics.renderer.Renderer;
import engine.input.keyboard.keys.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

/**
 * The <code>Keyboard</code> class is used to detect key presses and act on them. It works sympathetically with the
 * abstract {@link Key} class.
 */
public class Keyboard implements KeyListener {
	private final Key[] keys;

	public final static double degreeChangeSpeed = 45.0 / Engine.FPS;

	/**
	 * Create a new <code>Keyboard</code> with a <code>Renderer</code> and an array of keys
	 * @param renderer The <code>Renderer</code> object
	 * @param keys Array of <code>Key</code>s to press
	 */
	public Keyboard(Renderer renderer, Key... keys) {
		this.keys = Arrays.copyOf(keys, keys.length);
	}

	/**
	 * Fallback method that provides a default list of keys to press. The default keys are used for rotation.
	 *
	 * The {@link Numpad2Key} is used for counterclockwise rotations on the x-axis
	 * The {@link Numpad8Key} is used for clockwise rotations on the x-axis
	 * The {@link Numpad4Key} is used for clockwise rotations on the y-axis
	 * The {@link Numpad6Key} is used for counterclockwise rotations on the y-axis
	 * The {@link Numpad7Key} is used for counterclockwise rotations on the z-axis
	 * The {@link Numpad9Key} is used for counterclockwise rotations on the z-axis
	 *
	 * @param renderer The <code>Renderer</code> object
	 */
	public Keyboard(Renderer renderer) {
		this.keys = new Key[]{
				new Numpad2Key(renderer), new Numpad8Key(renderer),
				new Numpad4Key(renderer), new Numpad6Key(renderer),
				new Numpad7Key(renderer), new Numpad9Key(renderer),
		};
	}

	/**
	 * Calls the <code>pressKey()</code> method for all keys in its array of <code>Key</code>s.
	 */
	public void pressKeys() {
		// Press all keys
		for (Key key: keys) {
			key.pressKey();
		}
	}

	/**
	 * Sets the <code>isKeyPressed</code> value of the pressed key to <code>true</code> by looping around its
	 * array of keys, just like a switch statement.
	 * @param e A KeyEvent passed by {@link java.awt.Component}.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// Make the pressed button's boolean value true
		int keyCode = e.getKeyCode();
		for (Key key: keys) {
			if (key.getKeyCode() == keyCode) {
				key.setKeyPressed(true);
				break;
			}
		}
	}

	/**
	 * Sets the <code>isKeyPressed</code> value of the pressed key to <code>false</code> by looping around its
	 * array of keys, just like a switch statement.
	 * @param e A KeyEvent passed by {@link java.awt.Component}.
	 */
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
	public void keyTyped(KeyEvent e) {} // not required, no actual implementation
}
