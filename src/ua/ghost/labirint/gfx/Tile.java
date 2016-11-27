package ua.ghost.labirint.gfx;

import java.awt.image.BufferedImage;

public class Tile {

	public boolean solid=false;
	public BufferedImage img;
	
	public Tile(BufferedImage img, boolean solid){
		
		this.img=img;
		this.solid=solid;
	}
	
	public BufferedImage getImg(){
		return img;
	}
	
	
	
}
