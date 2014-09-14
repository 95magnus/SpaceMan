package me.solhaug.graphics.textures;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private int animFrameCount, currentFrame; 
	private BufferedImage[] animFrames;
	
	public SpriteSheet(BufferedImage[] animFrames, int animFrameCount){
		this.animFrameCount = animFrameCount;
		this.animFrames = animFrames;
	}
	
	public BufferedImage getNext(){
		return animFrames[(++currentFrame) % animFrameCount];
	}
	
	public BufferedImage get(int animIndex){
		try{
			return animFrames[animIndex];
		}catch (ArrayIndexOutOfBoundsException e){
			System.out.println("animeIndex out of bounds: " + getClass().getSimpleName());
			return null;
		}
	}
}
