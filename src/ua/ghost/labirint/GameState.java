package ua.ghost.labirint;

import ua.ghost.labirint.gfx.TileStorage;

public class GameState {
	
	public static final int TILE_W=32, TILE_H=32;

	public static KeyboardState keyMap = new KeyboardState();
	public static TileStorage tileStorage=null;
	
	public static Level currentLevel=null;
	
	public static void setTileStorage(TileStorage is){
		tileStorage = is;
	}
	
	public static void setCurrentLevel(Level level){
		currentLevel = level;
	}
	
	
}
