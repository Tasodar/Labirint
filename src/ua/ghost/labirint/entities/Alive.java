package ua.ghost.labirint.entities;

import java.awt.Graphics;

import ua.ghost.labirint.GameState;
import ua.ghost.mylibrary.Log;

public abstract class Alive extends Entity{

	public int hits, baseDamage, maxHits, armor;
	
	protected final int spawnX, spawnY;
	
	public Alive(int x, int y){
		
		spawnX=x;
		spawnY=y;
		
		this.x=x;
		this.y=y;
		
	}
	
	public int getHits(){
		return hits;
	}
	
	public void hit(int damage){
		Log.d("кто-то", "Получил по щщам на "+damage);
		hits-=damage;
		
		if(hits<=0) iAmDead();
		
	}
	
	public abstract void onPlayerStep();
	
	protected void iAmDead(){
		
		Log.d("Смерть", "Я умер");
	}

}
