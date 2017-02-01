package ua.ghost.labirint;

import java.awt.Point;

import ua.ghost.labirint.entities.Player;
import ua.ghost.labirint.gfx.EntityStorage;
import ua.ghost.labirint.gfx.ImageLib;
import ua.ghost.labirint.gfx.TileStorage;

public class GameState {
	
	public static final int TILE_W=32, TILE_H=32;
	public static int SCREEN_W=0, SCREEN_H=0; //в тайлах
	
	public static KeyboardState keyMap = new KeyboardState();
	public static TileStorage tileStorage=null;
	public static Player player=null;
	//public static EntityStorage mobs=null;
	public static Game game=null;
	
	public static ImageLib img=null;
	
	
	
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
	
	
//	public static void setMobStorage(EntityStorage storage){
//		mobs = storage;
//	}
	
	public static void setGame(Game newGame){
		game=newGame;
		SCREEN_W = game.WIDTH/TILE_W;
		SCREEN_H = game.HEIGHT/TILE_H;
	}
	
	public static void setImg(ImageLib images){
		img=images;
	}
	
	
	public static Point levelToScreen(Point pos){
		return currentLevel.levelToScreen(pos);
	}
	
	public static Point levelToScreen(int x, int y){
		return currentLevel.levelToScreen(new Point(x, y));
	}
	
	public static void setGameInfo(String message){
		
		if(game == null) return;
		game.info.setGameInfo(message);
	}
	
}
