package ua.ghost.labirint.entities;

import java.awt.Graphics;

public abstract class Alive extends Entity{

	protected int hits, baseDamage, maxHits;
	
	public int getHits(){
		return hits;
	}

}
