package ua.ghost.labirint.gfx;

import java.awt.image.BufferedImage;

import ua.ghost.labirint.GameState;

public class Tile {

	public boolean solid=false;
	//public BufferedImage img;
	public int numInLib=0;
	
	
//	public Tile(BufferedImage img, boolean solid){
//		
//		this.img=img;
//		this.solid=solid;
//	}
	
	public Tile(int num, boolean solid){
		
		this.numInLib=num;
		this.solid=solid;
	}
	
	
	public BufferedImage getImg(){
		//return img;
		return GameState.img.getImageById(numInLib);
	}
	
	
	
}
