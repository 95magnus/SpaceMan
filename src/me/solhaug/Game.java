package me.solhaug;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import me.solhaug.entities.Player;
import me.solhaug.graphics.textures.ImageLoader;
import me.solhaug.graphics.textures.ImageRegistry;
import me.solhaug.input.Controller;
import me.solhaug.input.Input;
import me.solhaug.level.Level;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1000, HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 1, TILE_SIZE = 32 * SCALE;
	public static final String TITLE = "Graphics test";
	private static boolean running = false;

	public static int updateTimes = 0;

	private Thread thread;
	private JFrame frame;
	private ImageIcon icon;

	public Player player;
	private Input input;
	private Controller controller;
	private Level level;

	public Game() {
		frame = new JFrame(TITLE + " | 0 fps");
		icon = new ImageIcon("res/icon.png");
		input = new Input();
		initCanvas();

		new ImageRegistry();
		level = new Level(40, 10);
		player = level.getPlayer();
		controller = new Controller(input, level);
	}

	private synchronized void start() {
		System.out.println("Starting game..");

		running = true;

		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		System.out.println("Exiting game...");

		running = false;

		frame.dispose();
		System.exit(0);

		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		int frames = 0, fps = 0;
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int updateCount = 0;
		boolean updated = false;

		while (running) {
			long currentTime = System.nanoTime();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds += passedTime / 1000000000.0;

			while (unprocessedSeconds > secondsPerTick) {
				update();
				unprocessedSeconds -= secondsPerTick;
				updated = true;
				updateCount++;
				if (updateCount % 60 == 0) {
					fps = frames;

					System.out.println(frames + " fps");
					frame.setTitle(TITLE + " | " + fps + " fps");

					previousTime += 1000;
					frames = 0;
				}
			}

			if (updated) {
				render();
				frames++;
			}
			// render();
			// frames++;
		}
	}

	private void update() {
		input.update();
		if (input.exit)
			stop();
		controller.update();

		level.update();
		updateTimes++;
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(ImageLoader.getImage("/backgrounds", "background.png"), 0, 0, WIDTH, HEIGHT, null);

		level.render(g);

		g.dispose();
		bs.show();

	}

	private void initCanvas() {
		Dimension size = new Dimension(WIDTH, HEIGHT);

		frame.setPreferredSize(size);
		frame.add(this);
		frame.pack();
		frame.setIconImage(icon.getImage());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addKeyListener(input);
		frame.setFocusable(true);
	}

	public static void main(String[] args) {
		new Game().start();
	}
}
