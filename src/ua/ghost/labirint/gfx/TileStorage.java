package ua.ghost.labirint.gfx;

import java.util.ArrayList;

import ua.ghost.labirint.GameState;
import ua.ghost.mylibrary.ImageLoader;

public class TileStorage {
	
	private ArrayList<Tile> storage;
	
	public TileStorage(){
		storage = new ArrayList<Tile>();
		
		Tile temp = new Tile(ImageLoader.loadImage("/tile.png"), false);
		storage.add(temp);
		temp = new Tile(ImageLoader.loadImage("/brick.png"), true);
		storage.add(temp);
		
		GameState.setTileStorage(this);
		
	}
	
	public Tile getTile(int index){
		return storage.get(index);
	}

}
