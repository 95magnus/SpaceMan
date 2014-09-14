package me.solhaug.tiles;

import me.solhaug.graphics.textures.ImageRegistry;
import me.solhaug.utiliy.ImageType;

public class TileStone extends Tile {

	public TileStone(int x, int y) {
		super(x, y, ImageRegistry.get(ImageType.TILE, "stone"));
	}

	@Override
	public void update() {

	}
}
