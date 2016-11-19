package ua.ghost.labirint.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import ua.ghost.labirint.Game;
import ua.ghost.labirint.GameState;
import ua.ghost.labirint.gfx.Animation;
import ua.ghost.labirint.gfx.Tile;
import ua.ghost.mylibrary.ImageLoader;
import ua.ghost.mylibrary.Log;

public class Player extends Entity{
	
	private BufferedImage set;
	private BufferedImage img;
	
	private Animation animLeft, animRight, animUp, animDown, animCurrent;
	
	
	public static final int LOOK_DOWN=0, LOOK_LEFT=1, LOOK_RIGHT=2, LOOK_UP=3;
	private int lookTo=LOOK_DOWN;
	
	
	public Player(int x, int y){
		this.x=x;
		this.y=y;
		
		set = ImageLoader.loadImage("/char01.png");
		img=set.getSubimage(0, 0, 32, 32);
		
		initAnimation();
		
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
		
		if(x<0) x=0;
		if(x>Game.WIDTH-this.WIDTH) x=Game.WIDTH-this.WIDTH;
		if(y<0) y=0;
		if(y>Game.HEIGHT-HEIGHT) y=Game.HEIGHT-HEIGHT;
		
		
	}

	
	private void checkStep(){
		
		int nextX=(int) (x+velX);
		int nextY=(int) (y+velY);
		
		if(velX>0){
			Tile forTest=GameState.currentLevel.getTileIn(nextX+32, y);
			if(forTest.solid) velX=0;
		}
		if(velX>0){
			Tile forTest=GameState.currentLevel.getTileIn(nextX+32, y+32);
			if(forTest.solid) velX=0;
		}
		
		if(velX<0){
			Tile forTest=GameState.currentLevel.getTileIn(nextX, y);
			if(forTest.solid) velX=0;
		}
		if(velX<0){
			Tile forTest=GameState.currentLevel.getTileIn(nextX, y+32);
			if(forTest.solid) velX=0;
		}
		
		if(velY>0){
			Tile forTest=GameState.currentLevel.getTileIn(x, nextY+32);
			if(forTest.solid) velY=0;
		}
		if(velY>0){
			Tile forTest=GameState.currentLevel.getTileIn(x+32, nextY+32);
			if(forTest.solid) velY=0;
		}
		if(velY<0){
			Tile forTest=GameState.currentLevel.getTileIn(x, nextY);
			if(forTest.solid) velY=0;
		}
		if(velY<0){
			Tile forTest=GameState.currentLevel.getTileIn(x+32, nextY);
			if(forTest.solid) velY=0;
		}
		
	}
	
	
	@Override
	public void render(Graphics g) {
		g.drawImage(animCurrent.getFrame(), x, y, null);
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
		animCurrent.start();
		
	}

}
