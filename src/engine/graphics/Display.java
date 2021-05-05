package engine.graphics;

import engine.geometry.Polygon3D;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;

public abstract class Display extends Canvas implements Runnable, DisplayConstants {
	protected JFrame window;
	protected Color backgroundColor;
	protected final Renderer renderer;

	protected Thread thread;

	protected String title;
	protected boolean isRunning;

	public Display(String title, Color backgroundColor) {
		this.title = title;
		this.backgroundColor = backgroundColor;

		window = new JFrame();
		window.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

		renderer = new Renderer(SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	public Display(String title) {
		this(title, Color.BLACK);
	}

	protected void manageWindowSettings() {
		window.setTitle(title);
		window.add(this);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null); // start in the middle of the screen
		window.setResizable(false);

		window.setVisible(true);
	}

	public synchronized void start() {
		if (isRunning) {return;} // guard clause so that another display can't be started

		// Manage window settings
		manageWindowSettings();

		// Start the thread
		isRunning = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	protected synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		final double nanoSeconds = 1000000000.0 / 60;

		long lastTime = System.nanoTime();
		long now;

		long timer = System.currentTimeMillis();
		double delta = 0;
		int drawnFrames = 0;

		// Main loop
		while (isRunning) {
			now = System.nanoTime();
			delta += (now - lastTime) / nanoSeconds;
			lastTime = now; // reset last time

			while (delta >= 1) {
				updateDisplay();
				delta--;
				render();
				drawnFrames++; // we drew a frame
			}

			// If one second has passed
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				window.setTitle(title + " - " + drawnFrames + "fps");
				drawnFrames = 0; // reset drawn frames to 0
			}
		}

		stop();
	}

	abstract protected void updateDisplay();

	protected void render() {
		// Create buffers if null
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3); // front-middle-back buffers
			return;
		}

		Graphics g = bs.getDrawGraphics(); // Get graphics object to draw on

		// Draw background
		g.setColor(backgroundColor);
		g.fillRect(0,0, SCREEN_HEIGHT * 2, SCREEN_HEIGHT * 2);

		// Call the render method of the renderer using the graphics object from the buffer strategy
		renderer.render(g);

		// Delete graphics object and show next buffer
		g.dispose();
		bs.show();
	}

	public void addPolygonToRender(Polygon3D polygon3D) {
		renderer.addPolygon(polygon3D);
	}
}