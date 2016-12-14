package ua.ghost.labirint.entities;

import java.awt.Graphics;

import ua.ghost.mylibrary.Log;

public abstract class Alive extends Entity{

	protected int hits, baseDamage, maxHits;
	
	public int getHits(){
		return hits;
	}

}
