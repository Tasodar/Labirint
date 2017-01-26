package ua.ghost.labirint.gfx;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import ua.ghost.labirint.GameState;
import ua.ghost.labirint.entities.Entity;
import ua.ghost.labirint.entities.TestMob;

public class EntityStorage {

	private ArrayList<Entity> storage;
	
	public EntityStorage(){
		
		storage = new ArrayList<Entity>();
		
		storage.add(new TestMob(5*GameState.TILE_W, 5*GameState.TILE_H));
		storage.add(new TestMob(15*GameState.TILE_W, 5*GameState.TILE_H));
		
		//GameState.setMobStorage(this);
		
	}
	
	public Entity getEntityIn(int x, int y){

		for(Entity currentMob : storage){
			Rectangle rect =new Rectangle(currentMob.getX(), currentMob.getY(), currentMob.WIDTH, currentMob.HEIGHT) ;
			if(rect.contains(x, y)) return currentMob;
			
//			if(		x>currentMob.getX() &&
//					x<currentMob.getX()+currentMob.WIDTH && 
//					y>currentMob.getY() && 
//					y<currentMob.getY()+currentMob.HEIGHT){
//				return currentMob;
//			}
			
		}
		return null;
	}
	
	public void render(Graphics g){
		for(Entity currentMob : storage){
			currentMob.render(g);
		}
	}
	
	
	public void tick(){
		for(Entity currentMob : storage){
			currentMob.tick();
		}
	}
	
	
}
