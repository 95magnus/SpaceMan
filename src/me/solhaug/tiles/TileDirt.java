package me.solhaug.tiles;

import me.solhaug.graphics.textures.ImageRegistry;
import me.solhaug.utiliy.ImageType;

public class TileDirt extends Tile {

	public TileDirt(int x, int y) {
		super(x, y, ImageRegistry.get(ImageType.TILE, "dirt"));
	}

	@Override
	public void update() {

	}
}
