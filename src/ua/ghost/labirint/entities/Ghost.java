package ua.ghost.labirint.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import ua.ghost.labirint.GameState;
import ua.ghost.mylibrary.Log;

public class Ghost extends Alive {

	public static final int LOOK_LEFT=1, LOOK_RIGHT=2, LOOK_UP=3, LOOK_DOWN=4;
	public final int MAX_DIST=4; // в тайлах;
	private final int LOOK_DIST=5; //в тайлах
	
	private int cast=2;
	private int dise = 6;
	
	private BufferedImage img;
	private final Player player=GameState.player;
	private boolean inStep = false;
	private int lookTo = 1;
	
	public Ghost(int x, int y){
		
		super(x, y);
		
		img=GameState.img.getImageById(9);
		maxHits=20;
		hits=maxHits;
		baseDamage=1;
		
		velX=1;
		
	}
	
	
	@Override
	public void tick() {
		if(inStep){
			switch(lookTo){
			case(LOOK_LEFT):
				x-=2;
				break;
			case(LOOK_RIGHT):
				x+=2;
				break;
			case(LOOK_UP):
				y-=2;
				break;
			case(LOOK_DOWN):
				y+=2;
				break;
			}
			
			if((x%GameState.TILE_W==0) && (y%GameState.TILE_H==0)){
				inStep=false;
				if(isPlayerClose()) hitPlayer();
			} 
			
		}
		
		
		
		
	}

	@Override
	public void render(Graphics g) {
		Point screenPos = GameState.levelToScreen(x, y);
		g.drawImage(img, screenPos.x, screenPos.y, null);
		int healthInd = hits*32/maxHits;
		if(healthInd<0) healthInd=0;
		g.setColor(Color.green);
		g.fillRect(screenPos.x, screenPos.y, healthInd, 3);
		
		
		
	}
	
	@Override
	public void touch(){
		GameState.player.hit(baseDamage);
		int damageFromPlayer = GameState.player.getDamage();
		this.hit(damageFromPlayer);
		
	}
	
	@Override
	public void onPlayerStep(){
		
		if(inStep) return;
		
		if(isPlayerClose()){
			hitPlayer();
			return;
		}
		
		if(isPlayerNear()){
			
			Log.d("", "Персонаж рядом");
			
			hunt();
			return;
		}
		
		patrol();
		
	}
	
	private boolean isPlayerClose(){
		
		Player p = GameState.player;
		if((p.x==x && (p.y==y-32 || p.y==y+32)) ||
				(p.y==y && (p.x==x-32 || p.x==x+32))
					){
				
				return true;
			}
		return false;
	}
	
	private boolean isPlayerNear(){
		
		Player p = GameState.player;
		
		int dX=  Math.abs(x-p.x)/GameState.TILE_W;
		int dY= Math.abs(y-p.y)/GameState.TILE_H;
		if(dX<=LOOK_DIST && dY<=LOOK_DIST) return true;
		else return false;
		
	}
	
	
	private boolean checkStep(){
		Point testPoint = null;
		switch(lookTo){
		case LOOK_LEFT:
			testPoint = new Point( x-2 , y);
			break;
		case LOOK_RIGHT:
			testPoint= new Point(x+GameState.TILE_W+2, y);
			break;
		case LOOK_UP:
			testPoint= new Point(x, y-2);
			break;
		case LOOK_DOWN:
			testPoint= new Point(x, y+GameState.TILE_W+2);
			break;
		}
		
		boolean res = !GameState.currentLevel.getTileIn(testPoint.x, testPoint.y).solid;
		return res;
	}
	
	private void patrol(){
		
		Log.d("", "Патрулирую!");
		if(y!=spawnY){
			Log.d("", "Срочно домой!");
			if(y>spawnY) lookTo = LOOK_UP;
			else lookTo=LOOK_DOWN;
			inStep=true;
			return;
		}
		
		
		if(lookTo==LOOK_LEFT){
			int dist = (spawnX-x)/GameState.TILE_W;
			if(dist>=MAX_DIST){
				lookTo=LOOK_RIGHT;
				
			}
			inStep=true;
			if(!checkStep()){
				inStep=false;
				lookTo=LOOK_RIGHT;
			}
			
		}
		
		else if(lookTo==LOOK_RIGHT){
			int dist = (x-spawnX)/GameState.TILE_W;
			if(dist>=MAX_DIST){
				lookTo=LOOK_LEFT;
				
			}
			inStep=true;
			if(!checkStep()){
				inStep=false;
				lookTo=LOOK_LEFT;
			}
		} else lookTo=LOOK_LEFT;
		

	}
	
	private void hunt(){
		
		Player p = GameState.player;
		int dX=  Math.abs(x-p.x)/GameState.TILE_W;
		int dY= Math.abs(y-p.y)/GameState.TILE_H;
		
		
		if(dX==0){
			huntY(p);
			return;
		}
		else if(dY==0){
			huntX(p);
			return;
		} 
		else if(dX>dY) huntY(p);
		else if(dY>dX) huntX(p);
		else if(dX==dY){
			int rand = (int)(Math.random()*100);
			
			if(rand%2==0)huntX(p);
			else huntY(p);
		}
		
	}
	
	private void huntY(Player p){
		Log.d("Преследуем", "По оси Y ближе");
		if((y-p.y)>0) lookTo=LOOK_UP;
		else lookTo=LOOK_DOWN;
		
		if(checkStep()) inStep=true;
		
	}
	
	private void huntX(Player p){
		Log.d("Преследуем", "По оси X ближе");
		if((x-p.x)>0) lookTo=LOOK_LEFT;
		else lookTo=LOOK_RIGHT;
		
		if(checkStep()) inStep=true;
	}
	
	
	private void hitPlayer(){
		
		int attac = 0;
		for (int i=0; i<cast; i++){
			attac+=1 + (int)(Math.random() * dise);
		}
		
		GameState.player.hit(attac);
		
		Log.d("Монстрик", "Пробил на  "+attac+ " хитов");
		
	}
	
	@Override
	protected void iAmDead(){
		GameState.currentLevel.remuveMonster(this);
	}

}
