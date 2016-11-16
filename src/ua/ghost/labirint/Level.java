package ua.ghost.labirint;

import java.awt.Graphics;

import ua.ghost.labirint.gfx.FloorTile;
import ua.ghost.labirint.gfx.Tile;
import ua.ghost.labirint.gfx.WallTile;

public class Level {
	
	private Tile[] storage;
	private int width=25, height=18;
	
	public Level(){
		
		storage=new Tile[width*height];
		
		loadLevel();
		
		GameState.setCurrentLevel(this);
	}
	
	private void loadLevel(){
		
		int tileCount=0;
		
		for(int yInTiles=0; yInTiles<height; yInTiles++){
			for(int xInTiles=0; xInTiles<width; xInTiles++){
				
				
				
				if(xInTiles==12 && yInTiles==9){
					storage[tileCount]=new WallTile(xInTiles*GameState.TILE_W, yInTiles*GameState.TILE_H);
					tileCount++;
					continue;
					
				}else if(yInTiles==0 || yInTiles==height-1 || xInTiles==0 || xInTiles==width-1){
					storage[tileCount]=new WallTile(xInTiles*GameState.TILE_W, yInTiles*GameState.TILE_H);
				}else{
					storage[tileCount]=new FloorTile(xInTiles*GameState.TILE_W, yInTiles*GameState.TILE_H);
				}
				
				tileCount++;
				
			}
			
			
		}
		
		
		
		
		
	}
	
	public void render(Graphics g){
		
		for(int i=0; i<storage.length; i++){
			if(storage[i]!=null) storage[i].render(g);
			
		}
		
	}
	
	public Tile getTileIn(int x, int y){
		
		int tileX=x/GameState.TILE_W;
		int tileY=y/GameState.TILE_H;
		
		int index = tileY*width+tileX;
		
		
		return storage[index];
	}
	
	
	

}
