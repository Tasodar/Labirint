package ua.ghost.labirint;

import java.awt.Point;

import ua.ghost.labirint.entities.Player;
import ua.ghost.labirint.gfx.EntityStorage;
import ua.ghost.labirint.gfx.TileStorage;

public class GameState {
	
	public static final int TILE_W=32, TILE_H=32;

	public static KeyboardState keyMap = new KeyboardState();
	public static TileStorage tileStorage=null;
	public static Player player=null;
	public static EntityStorage mobs=null;
	public static Game game=null;
	
	public static Level currentLevel=null;
	
	public static void setTileStorage(TileStorage is){
		tileStorage = is;
	}
	
	public static void setCurrentLevel(Level level){
		currentLevel = level;
	}
	
	public static void setPlaye(Player newPlayer){
		player=newPlayer;
	}
	
	
	public static void setMobStorage(EntityStorage storage){
		mobs = storage;
	}
	
	public static void setGame(Game newGame){
		game=newGame;
	}
	
	
	public static Point levelToScreen(Point pos){

		Point res = new Point();
		res.x=pos.x-currentLevel.getShiftX();
		res.y=pos.y-currentLevel.getShiftY();
		return res;
		
	}
}
