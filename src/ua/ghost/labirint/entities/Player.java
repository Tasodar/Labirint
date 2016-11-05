package ua.ghost.labirint.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import ua.ghost.labirint.Game;
import ua.ghost.labirint.GameState;
import ua.ghost.mylibrary.ImageLoader;

public class Player extends Entity{
	
	private BufferedImage set;
	private BufferedImage img;
	
	public static final int LOOK_DOWN=0, LOOK_LEFT=1, LOOK_RIGHT=2, LOOK_UP=3;
	private int lookTo=LOOK_DOWN;
	
	
	public Player(int x, int y){
		this.x=x;
		this.y=y;
		
		set = ImageLoader.loadImage("/char01.png");
		img=set.getSubimage(0, 0, 32, 32);
		
	}
	
	
	@Override
	public void tick() {
		velX=0;
		velY=0;
		
		
		if(GameState.keyMap.getKey(KeyEvent.VK_A)){
			setLookTo(LOOK_LEFT);
			velX-=speed;
		}
		
		if(GameState.keyMap.getKey(KeyEvent.VK_D)){
			setLookTo(LOOK_RIGHT);
			velX+=speed;
		}
		
		if(GameState.keyMap.getKey(KeyEvent.VK_W)){
			setLookTo(LOOK_UP);
			velY-=speed;
		}
		
		if(GameState.keyMap.getKey(KeyEvent.VK_S)){
			setLookTo(LOOK_DOWN);
			velY+=speed;
		}
		
		x+=velX;
		y+=velY;
		
		if(x<0) x=0;
		if(x>Game.WIDTH-this.WIDTH) x=Game.WIDTH-this.WIDTH;
		if(y<0) y=0;
		if(y>Game.HEIGHT-HEIGHT) y=Game.HEIGHT-HEIGHT;
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.red);
		//g.fillRect(x, y, WIDTH, HEIGHT);
		g.drawImage(img, x, y, null);
		
	}
	
	private void setLookTo(int newLook){
		
		lookTo=newLook;
		switch(newLook){
		case(LOOK_DOWN):
			img=set.getSubimage(0, 0, 32, 32);
			break;
		case(LOOK_LEFT):
			img=set.getSubimage(0, 32, 32, 32);
			break;
		case(LOOK_RIGHT):
			img=set.getSubimage(0, 64, 32, 32);
			break;
		case(LOOK_UP):
			img=set.getSubimage(0, 96, 32, 32);
			break;
		}
		
	}

}
