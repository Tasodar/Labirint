package ua.ghost.labirint;

import ua.ghost.labirint.gfx.ImageStorage;

public class GameState {
	
	public static final int TILE_W=32, TILE_H=32;

	public static KeyboardState keyMap = new KeyboardState();
	public static ImageStorage imageStorage=null;
	
	public static void setImageStorage(ImageStorage is){
		imageStorage = is;
	}
	
	
}
