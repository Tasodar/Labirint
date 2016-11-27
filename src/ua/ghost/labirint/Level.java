package ua.ghost.labirint;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ua.ghost.labirint.gfx.Tile;


public class Level {
	
	private int[] levelData ;
	private int width=25, height=18;
	
	public Level(){
		
		levelData=new int[width*height];
		
		loadLevel();
		
		GameState.setCurrentLevel(this);
	}
	
	private void loadLevel(){
		
		int tileCount=0;
		
		for(int yInTiles=0; yInTiles<height; yInTiles++){
			for(int xInTiles=0; xInTiles<width; xInTiles++){
				
				
				
				if(xInTiles==12 && yInTiles==9){
					
					levelData[tileCount]=1;
					
					tileCount++;
					continue;
					
				}else if(yInTiles==0 || yInTiles==height-1 || xInTiles==0 || xInTiles==width-1){
					levelData[tileCount]=1;
				}else{
					levelData[tileCount]=0;
				}
				
				tileCount++;
				
			}
			
			
		}
		
		
		
		
		
	}
	
	public void render(Graphics g){
		
		for(int i=0; i<levelData.length; i++){
			int y=i/width;
			int x=i%width;
			
			int tileIndex=levelData[i];
			BufferedImage img=GameState.tileStorage.getTile(tileIndex).getImg();
			
			g.drawImage(img, x*GameState.TILE_W, y*GameState.TILE_H, null);
			
		}
		
	}
	
	public Tile getTileIn(int x, int y){
		
		int tileX=x/GameState.TILE_W;
		int tileY=y/GameState.TILE_H;
		
		int index = tileY*width+tileX;
		
		int tileIndex=levelData[index];
		
		return GameState.tileStorage.getTile(tileIndex);
	}
	
	
	

}
