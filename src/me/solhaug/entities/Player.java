package me.solhaug.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.solhaug.graphics.Render2D;
import me.solhaug.graphics.textures.AnimatedTexture;
import me.solhaug.graphics.textures.ImageRegistry;
import me.solhaug.level.Level;
import me.solhaug.utiliy.ImageType;

public class Player extends Mob {

	public float playerSpeed = 5.0f, walkingSpeed = 3.0f;
	private float gravity = 0.25f, terminalVelocity = 10.0f;
	private boolean renderBounds = false;

	private Rectangle top, bottom, left, right;

	public Player(Level level, int x, int y) {
		super(level, x, y, new AnimatedTexture(ImageRegistry.getSpriteSheet("player"), 4));
	}

	@Override
	public void render(Graphics g) {
		switch (state) {
			case MOVING_LEFT:
				Render2D.renderImageFlippedY(g, animTexture.getNext(), x, y);
				break;
			case MOVING_RIGHT:
				Render2D.renderImage(g, animTexture.getNext(), x, y);
				break;
			default:
				Render2D.renderImage(g, ImageRegistry.get(ImageType.SPRITE, "player_idle"), x, y);
				break;
		}

		if (renderBounds) {
			renderBounds(g, bounds, Color.lightGray);
			renderBounds(g, top, Color.red);
			renderBounds(g, bottom, Color.green);
			renderBounds(g, left, Color.magenta);
			renderBounds(g, right, Color.orange);
		}
	}

	@Override
	public void update() {
		setBoundsLocation();
		collision();
		
//		if (dy < terminalVelocity)
//			dy += gravity;
	}

	@Override
	protected void setBounds() {
		bounds = new Rectangle(animTexture.getWidth() - 5, animTexture.getHeight() - 5);
		width = bounds.width;
		height = bounds.height;

		top = new Rectangle(width / 2, height / 5);
		bottom = new Rectangle(width / 2, height / 5);
		left = new Rectangle(width / 4, height - 2 * (height / 5));
		right = new Rectangle(width / 4, height - 2 * (height / 5));
		setBoundsLocation();
	}

	private void setBoundsLocation() {
		bounds.setLocation(x, y);
		top.setLocation(x + (width / 2) - width / 4, y);
		bottom.setLocation(x + (width / 2) - width / 4, y + height - height / 5);
		left.setLocation(x, y + height / 5);
		right.setLocation(x + width - width / 4, y + height / 5);
	}
}
