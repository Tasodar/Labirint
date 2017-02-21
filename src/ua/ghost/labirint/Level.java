package ua.ghost.labirint;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import ua.ghost.labirint.entities.Alive;
import ua.ghost.labirint.entities.Player;
import ua.ghost.labirint.entities.TestMob;
import ua.ghost.labirint.gfx.Tile;
import ua.ghost.labirint.items.Item;
import ua.ghost.labirint.items.Weapon;


public class Level {
	
	private int[] levelData;
	private Item[] items;
	
	
	private Alive[] monsters;
	
	private int width=0, height=0;
	private int shiftX=0, shiftY=0;
	
	
	private Player player;
	
	
	
	public int getShiftX() {
		return shiftX;
	}

	public int getShiftY() {
		return shiftY;
	}

	public Level(){
		
		
		loadLevel();
		player = new Player(10*GameState.TILE_W, 10*GameState.TILE_H);
		
		GameState.setCurrentLevel(this);
	}
	
	private void loadLevel(){
		
		this.width=40;
		this.height=20;
		levelData=new int[width*height];
		monsters = new Alive[width*height];
		items = new Item[width*height];
		
		
		items[165] = new Weapon("Бандитский нож", 3, 6, 10);
		
		addMonster(5, 5);
		addMonster(7, 7);
		addMonster(9, 9);
		
		//visibleTiles = new int[(GameState.SCREEN_W+2)*(GameState.SCREEN_H+2)];
		
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
		
		for(int i=0; i<items.length; i++){
			if(items[i]!=null){
				int y=i/width*GameState.TILE_H;
				int x=i%width*GameState.TILE_W;
				BufferedImage img = GameState.img.getImageById( items[i].getImageIndex());
				
				g.drawImage(img, x-shiftX, y-shiftY, null);
				
				
			}
		}
		
		player.render(g);
		
		for(int i=0; i<monsters.length; i++){
			
			if(monsters[i]!=null){
				monsters[i].render(g);
			}
		}
		
	}
	
	public void tick(){
		player.tick();
		
		for(int i=0; i<monsters.length; i++){
			
			if(monsters[i]!=null){
				monsters[i].tick();
			}
		}
		
	}
	
	public Tile getTileIn(int x, int y){
		
		int tileX=x/GameState.TILE_W;
		int tileY=y/GameState.TILE_H;
		
		int index = tileY*width+tileX;
		
		int tileIndex=levelData[index];
		
		return GameState.tileStorage.getTile(tileIndex);
	}
	
	private int positionToIndex(int posX, int posY){
		return posY*width+posX;
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
	
	private void selectVisibleTiles(){
		
		
		
	}
	
	public Player getPlayer(){
		return player;
	}
	
	
	private void addMonster(int monsterX, int monsterY){
		//Координаты -в тайлах!!! И в системе координат уровня.
		int index = positionToIndex(monsterX, monsterY);
		monsters[index] = new TestMob(monsterX*GameState.TILE_W, monsterY*GameState.TILE_H);
		
	}
	
	public Item getItemIn(int x, int y){
		//приходят координаты в тайлах, в системе координат уровня
		int index = y*width+x;
		return items[index];
	}
	
	public void remuveItemFrom(int x, int y){
		int index = y*width+x;
		items[index]=null;
	}
	
	public void putItemTo(Item item, int x, int y){
		int index =y*width+x;
		items[index]=item;
		
		
	}

}
