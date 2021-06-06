package engine.input.keyboard.keys;

import engine.graphics.display.Settings;
import engine.graphics.math.geometry.Axis;
import engine.models.entity.Entity;
import engine.graphics.rendering.Renderer;

import java.awt.event.KeyEvent;

/**
 * Key is an abstract class that provides extensive customization for handling key presses and is used along with
 * {@link engine.input.keyboard.Keyboard}.
 */
public abstract class Key {
	protected int keyCode;
	protected boolean isKeyPressed;
	protected Renderer renderer;

	protected Settings settings;

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

	protected Key(Renderer renderer, Settings settings, int keyCode) {
		this(renderer, keyCode);
		this.settings = settings;
	}

	protected Key(int keyCode) {
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

	public static class KeyNumpad2 extends Key {

		public KeyNumpad2(Renderer renderer, Settings settings) {
			super(renderer, settings, KeyEvent.VK_NUMPAD2);
		}

		@Override
		public void pressKey() {
			if (!isKeyPressed) return;

			for (Entity entity: renderer.getScene().getEntities()) {
				entity.rotate(Axis.xAxis, settings.degreeChangeSpeed(), true);
			}
		}
	}

	public static class KeyNumpad4 extends Key {

		public KeyNumpad4(Renderer renderer, Settings settings) {
			super(renderer, settings, KeyEvent.VK_NUMPAD4);
		}

		@Override
		public void pressKey() {
			if (!isKeyPressed) return;

			for (Entity entity: renderer.getScene().getEntities()) {
				entity.rotate(Axis.yAxis, settings.degreeChangeSpeed(), true);
			}
		}
	}

	public static class KeyNumpad6 extends Key {

		public KeyNumpad6(Renderer renderer, Settings settings) {
			super(renderer, settings, KeyEvent.VK_NUMPAD6);
		}

		@Override
		public void pressKey() {
			if (!isKeyPressed) return;

			for (Entity entity: renderer.getScene().getEntities()) {
				entity.rotate(Axis.yAxis, settings.degreeChangeSpeed(), false);
			}
		}
	}

	public static class KeyNumpad7 extends Key {

		public KeyNumpad7(Renderer renderer, Settings settings) {
			super(renderer, settings, KeyEvent.VK_NUMPAD7);
		}

		@Override
		public void pressKey() {
			if (!isKeyPressed) return;

			for (Entity entity: renderer.getScene().getEntities()) {
				entity.rotate(Axis.zAxis, settings.degreeChangeSpeed(), true);
			}
		}
	}

	public static class KeyNumpad8 extends Key {

		public KeyNumpad8(Renderer renderer, Settings settings) {
			super(renderer, settings, KeyEvent.VK_NUMPAD8);
		}

		@Override
		public void pressKey() {
			if (!isKeyPressed) return;

			for (Entity entity: renderer.getScene().getEntities()) {
				entity.rotate(Axis.xAxis, settings.degreeChangeSpeed(), false);
			}
		}
	}

	public static class KeyNumpad9 extends Key {

		public KeyNumpad9(Renderer renderer, Settings settings) {
			super(renderer, settings, KeyEvent.VK_NUMPAD9);
		}

		@Override
		public void pressKey() {
			if (!isKeyPressed) return;

			for (Entity entity: renderer.getScene().getEntities()) {
				entity.rotate(Axis.zAxis, settings.degreeChangeSpeed(), false);
			}
		}
	}

	public static class KeyW extends Key {

		public KeyW(Renderer renderer, Settings settings) {
			super(renderer, settings, KeyEvent.VK_W);
		}

		@Override
		public void pressKey() {
			if (!isKeyPressed) return;

			renderer.camera.moveCamera(0, 0, settings.cameraMoveSpeed());
		}
	}

	public static class KeyA extends Key {

		public KeyA(Renderer renderer, Settings settings) {
			super(renderer, settings, KeyEvent.VK_A);
		}

		@Override
		public void pressKey() {
			if (!isKeyPressed) return;

			renderer.camera.moveCamera(settings.cameraMoveSpeed(), 0, 0);
		}
	}

	public static class KeyS extends Key {

		public KeyS(Renderer renderer, Settings settings) {
			super(renderer, settings, KeyEvent.VK_S);
		}

		@Override
		public void pressKey() {
			if (!isKeyPressed) return;

			renderer.camera.moveCamera(0, 0, -settings.cameraMoveSpeed());
		}
	}

	public static class KeyD extends Key {

		public KeyD(Renderer renderer, Settings settings) {
			super(renderer, settings, KeyEvent.VK_D);
		}

		@Override
		public void pressKey() {
			if (!isKeyPressed) return;

			renderer.camera.moveCamera(-settings.cameraMoveSpeed(), 0, 0);
		}
	}

	public static class KeyShift extends Key {

		public KeyShift(Renderer renderer, Settings settings) {
			super(renderer, settings, KeyEvent.VK_SHIFT);
		}

		@Override
		public void pressKey() {
			if (!isKeyPressed) return;

			renderer.camera.moveCamera(0, -settings.cameraMoveSpeed(), 0);
		}
	}

	public static class KeySpace extends Key {

		public KeySpace(Renderer renderer, Settings settings) {
			super(renderer, settings, KeyEvent.VK_SPACE);
		}

		@Override
		public void pressKey() {
			if (!isKeyPressed) return;

			renderer.camera.moveCamera(0, settings.cameraMoveSpeed(), 0);
		}
	}
}
