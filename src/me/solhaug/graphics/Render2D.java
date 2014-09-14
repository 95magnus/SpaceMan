package me.solhaug.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Render2D {

	public static void renderImage(Graphics g, BufferedImage img, int x, int y) {
		g.drawImage(img, x, y, img.getWidth(), img.getHeight(), null);
	}

	public static void renderImageFlippedX(Graphics g, BufferedImage img, int x, int y) {
		g.drawImage(img, x, y + img.getHeight(), img.getWidth(), -img.getHeight(), null);
	}

	public static void renderImageFlippedY(Graphics g, BufferedImage img, int x, int y) {
		g.drawImage(img, x + img.getWidth(), y, -img.getWidth(), img.getHeight(), null);
	}

	public static void renderImage(Graphics g, BufferedImage img, int x, int y, int width, int height) {
		g.drawImage(img, x, y, width, height, null);
	}

	public static void renderImageFlippedX(Graphics g, BufferedImage img, int x, int y, int width, int height) {
		g.drawImage(img, x, y + height, width, -height, null);
	}

	public static void renderImageFlippedY(Graphics g, BufferedImage img, int x, int y, int width, int height) {
		g.drawImage(img, x + width, y, -width, height, null);
	}

	public static void renderImage(Graphics g, BufferedImage img, int x, int y, float scale) {
		g.drawImage(img, x, y, (int) (img.getWidth() * scale), (int) (img.getHeight() * scale), null);
	}

	public static void renderImageFlippedX(Graphics g, BufferedImage img, int x, int y, float scale) {
		g.drawImage(img, x, y + (int) (img.getHeight() * scale), (int) (img.getWidth() * scale), (int) (-img.getHeight() * scale), null);
	}

	public static void renderImageFlippedY(Graphics g, BufferedImage img, int x, int y, float scale) {
		g.drawImage(img, x + (int) (img.getWidth() * scale), y, (int) (-img.getWidth() * scale), (int) (img.getHeight() * scale), null);
	}
}
