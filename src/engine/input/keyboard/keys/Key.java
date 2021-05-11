package engine.input.keyboard.keys;

import engine.graphics.renderer.Renderer;

/**
 * Key is an abstract class that provides extensive customization for handling key presses and is used along with
 * {@link engine.input.keyboard.Keyboard}.
 */
public abstract class Key {
	protected int keyCode;
	protected boolean isKeyPressed;
	protected final Renderer renderer;

	/**
	 * Create a new <code>Key</code> object with a {@link Renderer} and a key code.
	 * @param renderer The renderer object
	 * @param keyCode The key code of the key
	 */
	protected Key(Renderer renderer, int keyCode) {
		this.renderer = renderer;
		this.isKeyPressed = false;
		this.keyCode = keyCode;
	}

	/**
	 * This function is called in {@link engine.input.keyboard.Keyboard} and should provide what to when the key is
	 * actually pressed.
	 */
	abstract public void pressKey();

	// Getters and Setters
	public void setKeyPressed(boolean keyPressed) {
		isKeyPressed = keyPressed;
	}

	public int getKeyCode() {
		return keyCode;
	}
}
