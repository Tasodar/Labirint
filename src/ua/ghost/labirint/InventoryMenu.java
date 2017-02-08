package ua.ghost.labirint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ua.ghost.labirint.items.Item;

public class InventoryMenu {
	
	private final int W=21, H=14; //размер в тайлах
	private final int GRID;
	private final int X, Y; //в пикселях
	
	public int cursorX=0, cursorY=0; // в тайлах
	
	public InventoryMenu(){
		
		
		GRID=GameState.TILE_W;
		X=(GameState.SCREEN_W-W)/2*GRID;
		Y=(GameState.SCREEN_H-H)/2*GRID;
		
	}
	
	
	public void muveCursorX(int dx){
		cursorX+=dx;
		if(cursorX<0) cursorX=0;
		if(cursorX>=W){
			cursorX=W-1;
		}
		
		int index=cursorY*W+cursorX;
		if(index>=GameState.player.inventory.size()) cursorX-=dx;
		
		showItemInfo();
	}
	
	public void muveCursorY(int dy){
		cursorY+=dy;
		if(cursorY<0) cursorY=0;
		if(cursorY>=H) cursorY=H-1;
		
		int index=cursorY*W+cursorX;
		if(index>=GameState.player.inventory.size()) cursorY-=dy;
		
		showItemInfo();
		
	}
	
	
	private void showItemInfo(){
		
		Item item = GameState.player.getInventory().get(cursorY*W+cursorX);
		String message = "";
		
		String n=System.lineSeparator();
		
		
		if(item==null){
			message="Предмет не обнаружен";
		} else{
			message=" "+item.getName()+n+" тип: "+item.getType()+n+" "+item.getInfo();
		}
		
		GameState.setGameInfo(message);
	}
	
	
	public void showMenu(){
		
		int index=cursorY*W+cursorX;
		if(index>=GameState.player.inventory.size()){
			cursorX=0;
			cursorY=0;
		}
		
		showItemInfo();
	}
	
	
	
	
	public void render(Graphics g){
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(X, Y, W*GRID, H*GRID);
		
		int tempX, tempY;
		
		for(int i=0; i<GameState.player.inventory.size(); i++){
			
			tempX=i%W;
			tempY=i/W;
			
			BufferedImage icon=GameState.img.getImageById(GameState.player.inventory.get(i).getImageIndex());
			g.drawImage(icon, X+tempX*GRID, Y+tempY*GRID, null);
		}
		
		g.setColor(new Color(255, 0, 0));
		g.drawRect(cursorX*GRID+X, cursorY*GRID+Y, GRID, GRID);
		
	}
	

}
