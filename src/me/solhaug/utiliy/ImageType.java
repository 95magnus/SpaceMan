package me.solhaug.utiliy;

public enum ImageType {
	TILE("TILE_"), SPRITE("SPRITE_"), SPRITESHEET("SPRITESHEET_");
	
	private String name;
	
	private ImageType(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
