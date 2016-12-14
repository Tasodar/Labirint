package ua.ghost.labirint.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ua.ghost.labirint.GameState;
import ua.ghost.mylibrary.ImageLoader;

public class TestMob extends Alive{

	private BufferedImage img;
	private final Player player=GameState.player;
	
	public TestMob(int x, int y){
		
		this.x=x;
		this.y=y;
		
		img=ImageLoader.loadImage("/monster01.png");
		
		maxHits=30;
		hits=maxHits;
		baseDamage=1;
		
	}
	
	
	@Override
	public void tick() {
		
		if(player.getX()<=x+32+10 && player.getX()>x && player.getY()==y ) x-=32;
		if(player.getX()<=x-32-10 && player.getX()>x && player.getY()==y ) x+=32;
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, x, y, null);
		
	}
	
	@Override
	public void touch(){
		GameState.player.hit(baseDamage);
	}

}
