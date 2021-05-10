package engine.input.keyboard.keys;

import engine.graphics.Renderer;

public abstract class Key {
	protected int keyCode;
	protected boolean isKeyPressed;
	protected final Renderer renderer;

	protected Key(Renderer renderer, int keyCode) {
		this.renderer = renderer;
		this.isKeyPressed = false;
		this.keyCode = keyCode;
	}

	abstract public void pressKey();

	public void setKeyPressed(boolean keyPressed) {
		isKeyPressed = keyPressed;
	}

	public boolean isKeyPressed() {
		return isKeyPressed;
	}

	public int getKeyCode() {
		return keyCode;
	}
}
