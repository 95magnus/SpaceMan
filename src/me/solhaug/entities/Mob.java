package me.solhaug.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.solhaug.Game;
import me.solhaug.graphics.textures.AnimatedTexture;
import me.solhaug.level.Level;
import me.solhaug.tiles.Tile;

public abstract class Mob extends Entity implements IMoveable {

	public enum State {
		IDLE, MOVING_LEFT, MOVING_RIGHT
	}

	protected float dx, dy, maxWalkingSpeed = 3.0f;
	protected boolean onGround, hittingWall;
	protected State state = State.IDLE;
	protected Rectangle top, bottom, left, right;

	public Mob(Level level, int x, int y, BufferedImage texture) {
		super(level, x, y, texture);
		setBounds();
	}

	public Mob(Level level, int x, int y, AnimatedTexture animTexture) {
		super(level, x, y, animTexture);
		setBounds();
	}

	public void accelerate(float dx, float dy) {
		// if ((this.dx + dx) < maxWalkingSpeed)
		this.dx += dx;
		this.dy += dy;
	}

	public void move(float dx, float dy) {
		this.x += dx;
		this.y += dy;
	}

	protected void collision() {
		Tile[][] tiles = level.getTiles();
		onGround = false;
		hittingWall = false;

		for (int y = 0; y < level.getHeight(); y++) {
			for (int x = 0; x < level.getWidth(); x++) {
				if (tiles[x][y] == null)
					return;

				if (tiles[x][y].isSolid() && tiles[x][y].rectCollision(bounds)) {
					if (tiles[x][y].rectCollision(bottom)) {
						onGround = true;
						stopMoving();
						this.y = (y * Game.TILE_SIZE) + level.getYOffset() - height;
					}

					if (tiles[x][y].rectCollision(top)) {
						stopMoving();
						this.y = (y * Game.TILE_SIZE) + level.getYOffset();
					}

					if (tiles[x][y].rectCollision(left)) {
						hittingWall = true;
						this.x = (x * Game.TILE_SIZE) + level.getXOffset();
					}

					if (tiles[x][y].rectCollision(right)) {
						hittingWall = true;
						this.x = (x * Game.TILE_SIZE) + level.getXOffset() - width;
					}
				}
			}
		}

		if (!hittingWall)
			x += dx;
		if (!onGround)
			y += dy;
	}

	protected void renderBounds(Graphics g, Rectangle rect, Color color) {
		g.setColor(color);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
	}

	public void setState(State state) {
		this.state = state;
	}
	
	protected void stopMoving() {
		dy = 0;
	}
}
