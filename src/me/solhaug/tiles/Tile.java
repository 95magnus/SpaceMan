package me.solhaug.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import me.solhaug.Game;
import me.solhaug.graphics.Render2D;
import me.solhaug.utiliy.Side;

public abstract class Tile {

	protected int x, y, size;
	protected BufferedImage texture;
	protected Rectangle bounds;

	public Tile(int x, int y, BufferedImage texture) {
		this.x = x;
		this.y = y;
		this.texture = texture;

		size = Game.TILE_SIZE;
		bounds = new Rectangle(x, y, size, size);
	}

	public void render(Graphics g) {
		Render2D.renderImage(g, texture, x, y, Game.SCALE);
	}

	public abstract void update();

	public boolean rectCollision(Rectangle rect) {
		return bounds.intersects(rect);
	}

	public ArrayList<Side> pointIntersects(float x, float y) {
		ArrayList<Side> sides = new ArrayList<Side>();

		if ((this.y - y) < size / 2)
			sides.add(Side.UP);
		else if ((y - this.y) < size / 2)
			sides.add(Side.DOWN);
		if ((this.x - x) < size / 2)
			sides.add(Side.LEFT);
		else if ((x - this.x) < size / 2)
			sides.add(Side.RIGHT);

		return sides;
	}

	public Side axisCollision(float x, float y) {
		if (Math.abs((double) (this.y - y)) < size / 2) {
			System.out.println("Colliding y!");
			return Side.DOWN;
		}

		if (Math.abs((double) (this.x - x)) < size / 2) {
			System.out.println("Colliding x!");
			return Side.LEFT;
		}

		return null;
	}

	public boolean isSolid() {
		return true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
