package ua.ghost.labirint.gfx;

import java.awt.image.BufferedImage;

import ua.ghost.labirint.GameState;
import ua.ghost.mylibrary.ImageLoader;

public class ImageLib {
	
	private BufferedImage set;
	private BufferedImage[] images;
	
	private final static int GRID=GameState.TILE_W;
	

	public ImageLib(){
		
		set = ImageLoader.loadImage("/set.png");
		
		int wInTiles = set.getWidth()/GRID;
		int hInTiles = set.getHeight()/GRID;
		
		images = new BufferedImage[wInTiles*hInTiles];
		
		for(int i=0; i<images.length; i++){
			images[i]=set.getSubimage((i%wInTiles)*GRID, (i/wInTiles)*GRID, GRID, GRID);
		}
		
		set=null;
		
		GameState.setImg(this);
	}
	
	
	
	
	public  BufferedImage getImageById(int id){
		if(id<0 || id>=images.length ) id=0;
		return images[id];		
	}
	

}
