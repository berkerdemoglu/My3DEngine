package engine.graphics;

import engine.models.entity.Entity;
import engine.math.geometry.Projector;
import engine.rendering.Renderer;
import engine.rendering.world.Scene;
import engine.input.keyboard.Keyboard;
import engine.input.keyboard.DrawListener;
import engine.input.mouse.Mouse;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Perhaps the most important class of the engine, it is the window where all the magic happens.
 * It is an abstract class and must be inherited from. The {@link engine.Engine} class inherits from this class too.
 *
 * The <code>Display</code> class allows customization as it has <code>protected</code> variables.
 * It is easy to add keys to detect, entities to render, and more.
 */
public abstract class Display extends Canvas implements Runnable, DisplayConstants {
	protected JFrame window;

	protected final Renderer renderer;
	protected RenderingHints antiAliasingHints;

	protected final Keyboard keyboard;
	protected final DrawListener wireframeDrawListener;
	protected final Mouse mouse;

	protected Thread thread;

	protected String title;
	protected boolean isRunning;

	/**
	 * Create a new display object.
	 * @param title Title of the window
	 */
	public Display(String title, Scene scene) {
		this.title = title;

		window = new JFrame();
		window.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

		renderer = new Renderer(scene);
		// Create antialiasing hints
		antiAliasingHints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
		);

		keyboard = new Keyboard(renderer);
		wireframeDrawListener = new DrawListener(renderer);

		mouse = new Mouse();
	}

	public Display(String title) {
		this(title, null);
	}

	/**
	 * This function can be overridden but its main function is to manage its window's ({@link JFrame}) properties
	 * such as adding key listeners, adding mouse listeners, etc. However, it is generally recommended that
	 * this function not be overridden.
	 */
	protected void manageWindowSettings() {
		window.setTitle(title);
		window.add(this);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null); // start in the middle of the screen
		window.setResizable(false);

		// Add key listeners
		this.addKeyListener(keyboard);
		this.addKeyListener(wireframeDrawListener);
		this.requestFocus();

		// Add mouse listeners
		this.addMouseWheelListener(mouse);

		window.setVisible(true);
	}

	/**
	 * Starts the thread of the display.
	 */
	public synchronized void start() {
		if (isRunning) {return;} // guard clause so that another display can't be started

		// Manage window settings
		manageWindowSettings();

		// Start the thread
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * This method is responsible for joining the thread and stopping the display.
	 */
	private synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method comes from the {@link Runnable} interface and starts the main loop of the display.
	 */
	@Override
	public void run() {
		final double nanoSecondsPerUpdate = 1000000000.0 / FPS;

		long lastNano = System.nanoTime();
		long nowNano;

		long secondCounter = System.currentTimeMillis();
		double delta = 0;
		int drawnFrames = 0;

		// Main loop
		while (isRunning) {
			nowNano = System.nanoTime();
			delta += (nowNano - lastNano) / nanoSecondsPerUpdate;
			lastNano = nowNano; // reset last time

			while (delta >= 1) {
				keyboard.pressKeys();
				handleMouseEvents();
				updateDisplay();
				delta--;
				render();
				drawnFrames++; // we drew a frame
			}

			if (System.currentTimeMillis() - secondCounter > 1000) {
				secondCounter += 1000;
				window.setTitle(title + " - " + drawnFrames + "fps");
				drawnFrames = 0; // reset drawn frames to 0
			}
		}

		stop();
	}

	/**
	 * This method is responsible for controlling mouse events. It is generally a good idea to override this function
	 * if you are not content with its functionality.
	 */
	protected void handleMouseEvents() {
		if (mouse.isScrollingUp()) {
			Projector.scale *= Mouse.zoomFactor;
		} else if (mouse.isScrollingDown()) {
			Projector.scale /= Mouse.zoomFactor;
		}

		mouse.resetScroll();
	}

	/**
	 * <code>updateDisplay()</code> is an abstract method that must be implemented by the inheriting class
	 * and is generally used to control entities in every loop.
	 */
	abstract protected void updateDisplay();

	/**
	 * This method is very important for the display class as it is responsible for creating a {@link BufferStrategy}
	 * and getting its graphics, and then rendering entities from the {@link Renderer} class.
	 */
	private void render() {
		// Create buffers if null
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3); // front-middle-back buffers
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics(); // Get Graphics2D object to draw on
		g.setRenderingHints(antiAliasingHints);

		// Call the render method of the renderer using the graphics object from the buffer strategy
		renderer.render(g);

		// Delete graphics object and show next buffer
		g.dispose();
		bs.show();
	}

	/**
	 * This method adds entities to the list of entities of the <code>Renderer</code> class.
	 * @param entities Entities to be rendered
	 */
	public void addEntitiesToScene(Entity... entities) {
		renderer.getScene().addEntitiesToScene(entities);
	}
}