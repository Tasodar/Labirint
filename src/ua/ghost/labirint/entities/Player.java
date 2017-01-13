package ua.ghost.labirint.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ua.ghost.labirint.Game;
import ua.ghost.labirint.GameState;
import ua.ghost.labirint.gfx.Animation;
import ua.ghost.labirint.gfx.Tile;
import ua.ghost.labirint.items.Item;
import ua.ghost.labirint.items.ItemType;
import ua.ghost.labirint.items.Weapon;
import ua.ghost.mylibrary.ImageLoader;
import ua.ghost.mylibrary.Log;

public class Player extends Alive{
	
	private BufferedImage set;
	private BufferedImage img;
	
	private Animation animLeft, animRight, animUp, animDown, animCurrent;
	
	
	public static final int LOOK_DOWN=0, LOOK_LEFT=1, LOOK_RIGHT=2, LOOK_UP=3;
	private int lookTo=LOOK_DOWN;
	private boolean inStep=false;
	private int steps=0;
	
	private InUse inUse=new InUse();
	
	
	public Player(int x, int y){
		
		inUse.setWeapon(new Weapon("Финка", 3, 6));
		
		this.x=x;
		this.y=y;
		
		set = ImageLoader.loadImage("/char01.png");
		img=set.getSubimage(0, 0, 32, 32);
		
		initAnimation();
		
		maxHits=100;
		hits=maxHits;
		baseDamage=1;
		armor=10;
		
		GameState.setPlaye(this);
		GameState.game.info.refreshPlayerInfo();
	}
	
	
	private void initAnimation() {
		
		BufferedImage[] frames=new BufferedImage[3];
		frames[0]=set.getSubimage(0, 0, 32, 32);
		frames[1]=set.getSubimage(32, 0, 32, 32);
		frames[2]=set.getSubimage(64, 0, 32, 32);
		animDown=new Animation(frames);
		
		frames=new BufferedImage[3];
		frames[0]=set.getSubimage(0, 32, 32, 32);
		frames[1]=set.getSubimage(32, 32, 32, 32);
		frames[2]=set.getSubimage(64, 32, 32, 32);
		animLeft=new Animation(frames);
		
		frames=new BufferedImage[3];
		frames[0]=set.getSubimage(0, 64, 32, 32);
		frames[1]=set.getSubimage(32, 64, 32, 32);
		frames[2]=set.getSubimage(64, 64, 32, 32);
		animRight=new Animation(frames);
		
		frames=new BufferedImage[3];
		frames[0]=set.getSubimage(0, 96, 32, 32);
		frames[1]=set.getSubimage(32, 96, 32, 32);
		frames[2]=set.getSubimage(64, 96, 32, 32);
		animUp=new Animation(frames);
		
		
		animCurrent = animDown;
		
	}


	@Override
	public void tick() {
		
		if(inStep){
			steps++;
			switch(lookTo){
			case (LOOK_LEFT):
				if(checkStep(x-2, y+16)) x-=2;
				break;
			case (LOOK_RIGHT):
				if(checkStep(x+2+GameState.TILE_W, y+16)) x+=2;
				break;
			case (LOOK_UP):
				if(checkStep(x+16, y-2)) y-=2;
				break;
			case (LOOK_DOWN):
				if(checkStep(x+16, y+2+GameState.TILE_H)) y+=2;
				break;
			}
			if(steps>=16){
				inStep=false;
				steps=0;
				animCurrent.stop();
			}
			
		}
		
		
		
		animCurrent.tick();
		
		if(x<0) x=0;
		if(x>Game.WIDTH-this.WIDTH) x=Game.WIDTH-this.WIDTH;
		if(y<0) y=0;
		if(y>Game.HEIGHT-HEIGHT) y=Game.HEIGHT-HEIGHT;
		
		
	}

	
	private boolean checkStep(int newX, int newY){
		
		Entity mob=GameState.mobs.getEntityIn(newX, newY);
		if(mob!=null){
			inStep=false;
			animCurrent.stop();
			steps=0;
			
			mob.touch();
			return false;
		} 
		
		Tile stepTo=GameState.currentLevel.getTileIn(newX, newY);
		return !stepTo.solid;
		
	}
	
	
	@Override
	public void render(Graphics g) {
		Point screenPos = GameState.currentLevel.levelToScreen(new Point(x, y));
		
		g.drawImage(animCurrent.getFrame(), screenPos.x, screenPos.y, null);
	}
	
	private void setLookTo(int newLook){
		
		lookTo=newLook;
		switch(newLook){
		case(LOOK_DOWN):
			img=set.getSubimage(0, 0, 32, 32);
			animCurrent = animDown;
			break;
		case(LOOK_LEFT):
			img=set.getSubimage(0, 32, 32, 32);
			animCurrent = animLeft;
			break;
		case(LOOK_RIGHT):
			img=set.getSubimage(0, 64, 32, 32);
			animCurrent = animRight;
			break;
		case(LOOK_UP):
			img=set.getSubimage(0, 96, 32, 32);
			animCurrent = animUp;
			break;
		}
		//animCurrent.start();
		
	}
	
	public void step(int direct){
		
		if(inStep) return;
		
		if(lookTo!=direct){
			setLookTo(direct);
			return;
		}
		
		inStep=true;
		animCurrent.start();
		steps=0;
		
	}
	
	@Override
	public void hit(int damage){
		super.hit(damage);
		GameState.game.info.refreshPlayerInfo();
		Log.d("Player", "Меня укусили на "+damage+" хитов");
	}
	
	
	public int getDamage(){
		//Здесь будет происходить рассчет
		
		int res = (int)baseDamage+ inUse.getWeapon().getAttack(); 
		Log.d("Player", "Атака: "+res);
		return res;
		
	}
	
	public int getArmor(){
		return armor;
	}
	
	public int getHits(){
		return hits;
	}
	
	
	

}
