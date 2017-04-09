package ua.ghost.labirint;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import ua.ghost.labirint.entities.Alive;
import ua.ghost.labirint.entities.MobFactory;
import ua.ghost.labirint.entities.Player;
import ua.ghost.labirint.entities.TestMob;
import ua.ghost.labirint.gfx.Tile;
import ua.ghost.labirint.items.Item;
import ua.ghost.labirint.items.ItemFactory;
import ua.ghost.labirint.items.Weapon;
import ua.ghost.mylibrary.Log;


public class Level {
	
	private int[] levelData;
	private Item[] items;
	
	
	private  ArrayList<Alive>  monsters;
	private ArrayList<Alive> visibleMonsters;
	
	
	private int width=0, height=0;
	private int shiftX=0, shiftY=0;
	
	private Point playerSpawn= new Point(0, 0);
	
	private Player player ;
	
	private boolean isLoaded=false;
	
	public ArrayList<Alive> getVisibleMonstars(){
		return visibleMonsters;
	}
	
	public int getShiftX() {
		return shiftX;
	}

	public int getShiftY() {
		return shiftY;
	}

	public Level(){
		
		
		try {
			loadLevel();
		} catch (IOException e) {
			Log.e("Загрузка уровня", "Не смогли прочитать файл");
			e.printStackTrace();
		}
		
		Log.d("Спаун игрока", "x="+playerSpawn.x+", y="+playerSpawn.y);
		player = new Player(playerSpawn.x*GameState.TILE_W, playerSpawn.y*GameState.TILE_H);
		
		GameState.setCurrentLevel(this);
	}
	
	private void loadLevel() throws IOException {
		
		//URL levelUrl=this.getClass().getResource("/levels/level01.lvl");
		InputStream is = this.getClass().getResourceAsStream("/levels/level01.lvl");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String str="";
		str=br.readLine();
		width = Integer.valueOf(str);
		str=br.readLine();
		height=Integer.valueOf(str);
		Log.d("прочитали размеры", "width="+width+" height="+height);
		str=br.readLine();
		playerSpawn.x=Integer.valueOf(str);
		str=br.readLine();
		playerSpawn.y=Integer.valueOf(str);
		
		levelData=new int[width*height];
		
		int ldIndex = 0;
		
		for(int i=0; i<height; i++){
			str=br.readLine();
			String [] split = str.split(" ");
			if(split.length!=width) Log.e("Загрузка уровня", "Неверный формат файла");
			for(String num : split){
				int tileNum = Integer.valueOf(num);
				levelData[ldIndex++]=tileNum;
				//ldIndex++;
			} 
			
		}
		
		if(ldIndex!=levelData.length){
			Log.e("Загрузка уровня", "Где-то мы налажали");
			return;
		} 
		
		loadItems(br);
		loadMonsters(br);
		
		isLoaded=true;
		/*
		this.width=40;
		this.height=20;
		levelData=new int[width*height];
		//monsters = new Alive[width*height];
		monsters=new ArrayList<Alive>();
		visibleMonsters=new ArrayList<Alive>();
		items = new Item[width*height];
		
		
		items[165] = new Weapon("Бандитский нож", 3, 6, 10);
		
		addMonster(10, 5);
		addMonster(30, 7);
		
		addMonster(2, 3);
		
		//visibleTiles = new int[(GameState.SCREEN_W+2)*(GameState.SCREEN_H+2)];
		
		int tileCount=0;
		for(int yInTiles=0; yInTiles<height; yInTiles++){
			for(int xInTiles=0; xInTiles<width; xInTiles++){
				if(xInTiles==12 && yInTiles==9){
					levelData[tileCount]=2;
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
		
		checkVisibleMonsters();
		*/
		
	}
	
	private void loadItems(BufferedReader br) throws IOException{
		this.items = new Item[width*height];
		String str;
		int count=0;
		while(true){
			str=br.readLine();
			if( str==null || str.equals("+++")) break;
			String[] res = str.split(",");
			if(res.length!=3) Log.e("Ошибка", "Неверно загружена вещ");
			else{
				int index=positionToIndex(Integer.valueOf(res[0]), Integer.valueOf(res[1]));
				Item tmp = ItemFactory.createItemByCode(Integer.valueOf(res[2]));
				if(tmp!=null){
					items[index]=tmp;
					count++;
				} 				
			}
			
		}
		
		Log.d("Загрузка вещей", "Загружено вещей: "+count);
		
	}
	
	private void loadMonsters(BufferedReader br) throws IOException{
		monsters = new ArrayList<Alive>();
		String str;
		int count=0;
		while(true){
			str = br.readLine();
			if(str==null || str.equals("+++")) break;
			Alive mob = MobFactory.creatMob(str);
			if(mob !=null){
				count++;
				monsters.add(mob);
			}
		}
		
		Log.d("Загрузка монстров", "Загружено "+count+" монстров");
		checkVisibleMonsters();
		
		
	}
	
	
	public void render(Graphics g){
		
		if(!isLoaded) return;
		
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
		
//		for(int i=0; i<monsters.size(); i++){
//				monsters.get(i).render(g);
//				
//		}
		
		for(Alive monster : visibleMonsters){
			monster.render(g);
		}
		
		
	}
	
	public void tick(){
		
		if(!isLoaded) return;
		
		player.tick();
		
		for(Alive monster : visibleMonsters){
			monster.tick();
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
		
		checkVisibleMonsters();
	}
	
	public void shiftW(int shift){
		
		int maxShift=width*GameState.TILE_W-Game.WIDTH;
		shiftX+=shift;
		if(shiftX<0) shiftX=0;
		if(shiftX>maxShift) shiftX=maxShift;
		
		checkVisibleMonsters();
	}
	
	private void selectVisibleTiles(){
		
		
		
	}
	
	public Player getPlayer(){
		return player;
	}
	
	
	private void addMonster(int monsterX, int monsterY){
		//Координаты -в тайлах!!! И в системе координат уровня.
		//int index = positionToIndex(monsterX, monsterY);
		//monsters[index] = new TestMob(monsterX*GameState.TILE_W, monsterY*GameState.TILE_H);
		
		monsters.add(new TestMob(monsterX*GameState.TILE_W, monsterY*GameState.TILE_H));
		
	}
	
	public Item getItemIn(int x, int y){
		//приходят координаты в тайлах, в системе координат уровня
		int index = y*width+x;
		return items[index];
	}
	
	public Alive getMobIn(int x, int y){
	
		for(Alive mob: visibleMonsters){
			if(mob.getX()==x && mob.getY()==y) return mob;
		}
		
		return null;
	}
	
	public void remuveItemFrom(int x, int y){
		int index = y*width+x;
		items[index]=null;
	}
	
	public void putItemTo(Item item, int x, int y){
		int index =y*width+x;
		items[index]=item;
		
		
	}
	
	private void checkVisibleMonsters(){
		
		//visibleMonsters.clear();
		visibleMonsters=new ArrayList<Alive>();
		int minX=shiftX;
		int maxX=shiftX+Game.WIDTH-GameState.TILE_W;
		
		int minY=shiftY;
		int maxY=shiftY+Game.HEIGHT-GameState.TILE_H;
		
		for(Alive monster : monsters){
			if(monster.getX()>=minX && monster.getX()<=maxX && monster.getY()>=minY && monster.getY()<=maxY){
				visibleMonsters.add(monster);
			}
			
		}
		
		//Log.d("Проверка монстров", " Мы видим "+ visibleMonsters.size()+" монстриков");
		
	}
	
	public void remuveMonster(Alive mob){
		Log.d("СМЕРТЬ", "монстрик побежден");
		
		monsters.remove(mob);
		visibleMonsters.remove(mob);
	}

}
