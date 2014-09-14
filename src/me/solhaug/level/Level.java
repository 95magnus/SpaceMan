package me.solhaug.level;

import java.awt.Graphics;
import java.util.ArrayList;

import me.solhaug.Game;
import me.solhaug.entities.Entity;
import me.solhaug.entities.Player;
import me.solhaug.tiles.Tile;
import me.solhaug.tiles.TileDirt;
import me.solhaug.tiles.TileGrass;
import me.solhaug.tiles.TileStone;

public class Level {

	private int width, height, xOffset = 0, yOffset = 250;

	public Player player;
	private Tile[][] tiles;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity> removeEntities = new ArrayList<Entity>();

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
		player = new Player(this, 300, 100);

		generateLevel();
		addEntity(player);
	}

	public void render(Graphics g) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (tiles[x][y] != null)
					tiles[x][y].render(g);
			}
		}

		for (Entity e : entities)
			e.render(g);
	}

	public void update() {
		for (Entity e : entities)
			e.update();

		for (int i = 0; i < removeEntities.size(); i++) {
			entities.remove(removeEntities.get(i));
		}

		removeEntities.clear();
	}

	private void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (y == 0 && x != 7)
					tiles[x][y] = new TileGrass((x * Game.TILE_SIZE) + xOffset, (y * Game.TILE_SIZE + yOffset));
				else if (y > 0 && y < 3 && x != 7)
					tiles[x][y] = new TileDirt((x * Game.TILE_SIZE) + xOffset, (y * Game.TILE_SIZE + yOffset));
				else
					tiles[x][y] = new TileStone((x * Game.TILE_SIZE) + xOffset, (y * Game.TILE_SIZE + yOffset));
			}
		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void remove(Entity e) {
		removeEntities.add(e);
	}

	public Player getPlayer() {
		for (Entity e : entities) {
			if (e instanceof Player)
				return (Player) e;
		}
		return null;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getXOffset() {
		return xOffset;
	}

	public int getYOffset() {
		return yOffset;
	}
}
