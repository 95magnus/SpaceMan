package me.solhaug.graphics.textures;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import me.solhaug.utiliy.ImageType;

public class ImageRegistry {

	private static HashMap<String, BufferedImage> textureMap = new HashMap<String, BufferedImage>();
	private static HashMap<String, BufferedImage[]> spriteSheetMap = new HashMap<String, BufferedImage[]>();

	public ImageRegistry() {
		registerTileTexture("stone", "tile_stone2.png");
		registerTileTexture("dirt", "tile_dirt2.png");
		registerTileTexture("grass", "tile_grass2.png");

		registerSprite("player_idle", "player_idle.png");

		registerSpriteSheet("player", "player.png", 73, 98, 6, 3, 2);
		registerSpriteSheet("player_walk", "player_walk.png", 73, 98, 11, 3, 4);
	}

	public static void registerTileTexture(String name, String fileName) {
		BufferedImage img = ImageLoader.getImage("tiles", fileName);

		textureMap.put("TILE_" + name, img);
	}

	public static void registerSprite(String name, String fileName) {
		BufferedImage img = ImageLoader.getImage("sprites", fileName);

		textureMap.put("SPRITE_" + name, img);
	}

	public static void registerSpriteSheet(String name, String fileName, int spriteWidth, int spriteHeight, int animFrameCount, int lineWidth, int lines) {
		BufferedImage img = ImageLoader.getImage("spritesheets", fileName);
		BufferedImage[] frames = new BufferedImage[animFrameCount];

		for (int i = 0; i < animFrameCount; i++) {
			int x = (spriteWidth * (i % lineWidth));
			int y = (spriteHeight * ((int)(i / lineWidth)));
			
			frames[i] = img.getSubimage(x, y, spriteWidth, spriteHeight);
		}
		spriteSheetMap.put(name, frames);
	}

	public static BufferedImage get(ImageType type, String name) {
		String typeName = type.getName();

		return textureMap.get(typeName + name);
	}

	public static SpriteSheet getSpriteSheet(String name) {
		int animFrameCount = spriteSheetMap.get(name).length;
		
		return new SpriteSheet(spriteSheetMap.get(name), animFrameCount);
	}

	public static ArrayList<String> getAllEntries() {
		ArrayList<String> list = new ArrayList<String>();
		for (String name : textureMap.keySet())
			list.add(name);

		return list;
	}

	public static ArrayList<String> getEntriesOfType(ImageType type) {
		String typeName = type.getName();
		ArrayList<String> list = new ArrayList<String>();

		for (String name : textureMap.keySet()) {
			String textureType = name.substring(typeName.length() - 1);

			if (textureType.equals(typeName))
				list.add(name);
		}

		return list;
	}
}