package ua.ghost.labirint.entities;

import java.awt.Graphics;

public abstract class Entity {

	protected int x, y;
	protected float velX=0, velY=0;
	protected float speed=2;
	protected final int WIDTH=32, HEIGHT=32;
	
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	
}
