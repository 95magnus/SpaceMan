package me.solhaug.graphics.textures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	private static final String TEXTURE_PATH = "res/textures/";

	public static BufferedImage getImage(String name) {
		try {
			return ImageIO.read(new File(TEXTURE_PATH + name));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static BufferedImage getImage(String dir, String name) {
		try {
			return ImageIO.read(new File(TEXTURE_PATH + dir + "/" + name));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}