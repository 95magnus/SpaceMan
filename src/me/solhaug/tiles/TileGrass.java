package me.solhaug.tiles;

import me.solhaug.graphics.textures.ImageRegistry;
import me.solhaug.utiliy.ImageType;

public class TileGrass extends Tile {

	public TileGrass(int x, int y) {
		super(x, y, ImageRegistry.get(ImageType.TILE, "grass"));
	}

	@Override
	public void update() {

	}
}
