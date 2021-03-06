package ua.ghost.labirint.entities;

import java.awt.Graphics;

import ua.ghost.mylibrary.Log;

public abstract class Entity {

	protected int x, y;
	protected float velX=0, velY=0;
	protected float speed=2;
	public final int WIDTH=32, HEIGHT=32;
	
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void touch(){
		Log.d("Alive", "Touch!");
	}
	
}
