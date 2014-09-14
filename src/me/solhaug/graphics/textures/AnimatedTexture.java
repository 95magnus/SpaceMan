package me.solhaug.graphics.textures;

import java.awt.image.BufferedImage;

import me.solhaug.Game;

public class AnimatedTexture {
	
	private int frameTime, previousTime = 0;

	private SpriteSheet spriteSheet;
	private BufferedImage currentImage;
	
	public AnimatedTexture(SpriteSheet spriteSheet, int frameTime){
		this.spriteSheet = spriteSheet;
		this.frameTime = frameTime;
		
		resetAnim();
	}
	
	public BufferedImage getSprite(int index){
		return spriteSheet.get(index);
	}

	public BufferedImage getNext(){
		if (Game.updateTimes - previousTime > frameTime){
			currentImage = spriteSheet.getNext();
			previousTime = Game.updateTimes;
		}
		return currentImage;
	}
	
	public BufferedImage setCurrentImage(int index){
		currentImage = getSprite(index);
		return currentImage;
	}
	
	public void resetAnim(){
		currentImage = getSprite(0);
	}
	
	public int getWidth(){
		return getSprite(0).getWidth();
	}
	
	public int getHeight(){
		return getSprite(0).getHeight();
	}
}
