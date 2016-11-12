package ua.ghost.labirint.gfx;

import java.awt.Graphics;

import ua.ghost.labirint.GameState;

public abstract class Tile {

	protected int x, y;
	protected String name;
	
	public Tile(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void render(Graphics g){
		g.drawImage(GameState.imageStorage.getImage(name), x, y, null);
	}
	
	public abstract void tick();
	
}
