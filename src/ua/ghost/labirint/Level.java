package ua.ghost.labirint;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import ua.ghost.labirint.gfx.Tile;


public class Level {
	
	private int[] levelData;
	private int width=0, height=0;
	
	private int shiftX=0, shiftY=0;
	
	
	
	
	public int getShiftX() {
		return shiftX;
	}

	public int getShiftY() {
		return shiftY;
	}

	public Level(){
		
		
		loadLevel();
		GameState.setCurrentLevel(this);
	}
	
	private void loadLevel(){
		
		this.width=40;
		this.height=20;
		levelData=new int[width*height];		
		
		int tileCount=0;
		for(int yInTiles=0; yInTiles<height; yInTiles++){
			for(int xInTiles=0; xInTiles<width; xInTiles++){
				if(xInTiles==12 && yInTiles==9){
					levelData[tileCount]=4;
					tileCount++;
					continue;
					
				}else if(yInTiles==0 || yInTiles==height-1 || xInTiles==0 || xInTiles==width-1){
					levelData[tileCount]=2;
				}else{
					levelData[tileCount]=1;
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
			
			g.drawImage(img, x*GameState.TILE_W-shiftX, y*GameState.TILE_H-shiftY, null);
			
		}
		
	}
	
	public Tile getTileIn(int x, int y){
		
		int tileX=x/GameState.TILE_W;
		int tileY=y/GameState.TILE_H;
		
		int index = tileY*width+tileX;
		
		int tileIndex=levelData[index];
		
		return GameState.tileStorage.getTile(tileIndex);
	}
	
	public Point screenToLeve(Point pos){
		//в пикселях
		Point res = new Point();
		res.x=pos.x-shiftX;
		res.y=pos.y-shiftY;
		return res;
	}
	
	public Point levelToScreen(Point pos){
		Point res = new Point();
		res.x=pos.x-shiftX;
		res.y=pos.y-shiftY;
		return res;
	}
	
	
	public void shiftH(int shift){
		
		int maxShift=height*GameState.TILE_H-Game.HEIGHT;
		shiftY+=shift;
		if(shiftY<0) shiftY=0;
		if(shiftY>maxShift) shiftY=maxShift;
	}
	
	public void shiftW(int shift){
		
		int maxShift=width*GameState.TILE_W-Game.WIDTH;
		shiftX+=shift;
		if(shiftX<0) shiftX=0;
		if(shiftX>maxShift) shiftX=maxShift;
	}

}
