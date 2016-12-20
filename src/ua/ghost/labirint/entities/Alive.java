package ua.ghost.labirint.entities;

import java.awt.Graphics;

import ua.ghost.labirint.GameState;
import ua.ghost.mylibrary.Log;

public abstract class Alive extends Entity{

	public int hits, baseDamage, maxHits, armor;
	
	public int getHits(){
		return hits;
	}
	
	public void hit(int damage){
		hits-=damage;
	}

}
