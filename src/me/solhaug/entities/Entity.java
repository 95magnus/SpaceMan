package me.solhaug.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.solhaug.graphics.textures.AnimatedTexture;
import me.solhaug.level.Level;

public abstract class Entity {

	protected int x, y, width, height;
	protected BufferedImage texture;
	protected AnimatedTexture animTexture;
	protected Rectangle bounds;
	protected Level level;

	public Entity(Level level, int x, int y, BufferedImage texture) {
		this.level = level;
		this.x = x;
		this.y = y;
		this.texture = texture;
		
		setBounds();
	}

	public Entity(Level level, int x, int y, AnimatedTexture animTexture) {
		this.level = level;
		this.x = x;
		this.y = y;
		this.animTexture = animTexture;
		
		setBounds();
	}

	public abstract void render(Graphics g);

	public abstract void update();

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}	
	
	protected abstract void setBounds();
}
