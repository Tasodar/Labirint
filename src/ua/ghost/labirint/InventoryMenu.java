package ua.ghost.labirint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class InventoryMenu {
	
	private final int W=21, H=14; //размер в тайлах
	private final int GRID;
	private final int X, Y; //в пикселях
	
	public int cursorX=0, cursorY=0;
	
	public InventoryMenu(){
		
		
		GRID=GameState.TILE_W;
		X=(GameState.SCREEN_W-W)/2*GRID;
		Y=(GameState.SCREEN_H-H)/2*GRID;
		
	}
	
	
	
	
	public void render(Graphics g){
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(X, Y, W*GRID, H*GRID);
		
		for(int i=0; i<GameState.player.inventory.size(); i++){
			
			BufferedImage icon=GameState.img.getImageById(GameState.player.inventory.get(i).getImageIndex());
			g.drawImage(icon, X+i*GRID, Y+i/W*32, null);
		}
		
		g.setColor(new Color(255, 0, 0));
		g.drawRect(cursorX*GRID+X, cursorY*GRID+Y, GRID, GRID);
		
	}
	

}
