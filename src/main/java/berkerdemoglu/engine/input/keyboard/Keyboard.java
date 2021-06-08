package berkerdemoglu.engine.input.keyboard;

import berkerdemoglu.engine.graphics.display.Settings;
import berkerdemoglu.engine.graphics.rendering.Renderer;
import berkerdemoglu.engine.input.keyboard.keys.Key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

/**
 * The <code>Keyboard</code> class is used to detect key presses and act on them. It works sympathetically with the
 * abstract {@link Key} class.
 */
public class Keyboard implements KeyListener {
	private final Key[] keys;

	/**
	 * Create a new <code>Keyboard</code> with a <code>Renderer</code> and an array of keys
	 * @param keys Array of <code>Key</code>s to press
	 */
	public Keyboard(Key... keys) {
		this.keys = Arrays.copyOf(keys, keys.length);
	}

	/**
	 * Fallback method that provides a default list of keys to press. The default keys are used for rotation.
	 *
	 * The {@link Key.KeyNumpad2} is used for counterclockwise rotations on the x-axis
	 * The {@link Key.KeyNumpad8} is used for clockwise rotations on the x-axis
	 * The {@link Key.KeyNumpad4} is used for clockwise rotations on the y-axis
	 * The {@link Key.KeyNumpad6} is used for counterclockwise rotations on the y-axis
	 * The {@link Key.KeyNumpad7} is used for counterclockwise rotations on the z-axis
	 * The {@link Key.KeyNumpad9} is used for counterclockwise rotations on the z-axis
	 *
	 * @param renderer The <code>Renderer</code> object
	 */
	public Keyboard(Renderer renderer, Settings settings) {
		this.keys = new Key[]{
				// Entity rotation keys
				new Key.KeyNumpad2(renderer, settings), new Key.KeyNumpad8(renderer, settings),
				new Key.KeyNumpad4(renderer, settings), new Key.KeyNumpad6(renderer, settings),
				new Key.KeyNumpad7(renderer, settings), new Key.KeyNumpad9(renderer, settings),

				// Camera movement keys
				new Key.KeyW(renderer, settings), new Key.KeyA(renderer, settings),
				new Key.KeyS(renderer, settings), new Key.KeyD(renderer, settings),
				new Key.KeyShift(renderer, settings), new Key.KeySpace(renderer, settings),
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
