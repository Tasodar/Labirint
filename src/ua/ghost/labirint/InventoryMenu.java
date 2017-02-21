package ua.ghost.labirint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import ua.ghost.labirint.items.Item;
import ua.ghost.mylibrary.Log;

public class InventoryMenu {
	
	private final int W=14, H=14; //размер в тайлах
	private final int GRID=GameState.TILE_W;
	private final int X, Y; //в пикселях
	
	private int inUseX=18*GRID;
	private int inUseY=2*GRID;
	
	private int inUseW=5, inUseH=14;
	
	private final Point SLOT_WEAPON=new Point(19*GRID, 6*GRID);  
	private final Point SLOT_ARMOR=new Point(20*GRID, 8*GRID);
	
	public int cursorX=0, cursorY=0; // в тайлах
	
	public InventoryMenu(){
		
		//X=(GameState.SCREEN_W-W)/2*GRID;
		//Y=(GameState.SCREEN_H-H)/2*GRID;
		X=2*GRID;
		Y=2*GRID;
		
		
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
		g.fillRect(inUseX, inUseY, inUseW*GRID, inUseH*GRID);
		
		
		
		int tempX, tempY;
		
		for(int i=0; i<GameState.player.inventory.size(); i++){
			
			tempX=i%W;
			tempY=i/W;
			
			BufferedImage icon=GameState.img.getImageById(GameState.player.inventory.get(i).getImageIndex());
			g.drawImage(icon, X+tempX*GRID, Y+tempY*GRID, null);
		}
		
		g.setColor(new Color(255, 0, 0));
		g.drawRect(cursorX*GRID+X, cursorY*GRID+Y, GRID, GRID);
		
		if(GameState.player.inUse.getWeapon()!=null){
			Item weapon=GameState.player.inUse.getWeapon();
			g.drawImage(GameState.img.getImageById(weapon.getImageIndex()), SLOT_WEAPON.x, SLOT_WEAPON.y, null);
		}
		
		if(GameState.player.inUse.getArmor()!=null){
			Item armor = GameState.player.inUse.getArmor();
			g.drawImage(GameState.img.getImageById(armor.getImageIndex()), SLOT_ARMOR.x, SLOT_ARMOR.y, null);
		}
		
		g.setColor(new Color(102, 205, 170));
		g.drawRect(SLOT_WEAPON.x, SLOT_WEAPON.y, GRID, GRID);
		g.drawRect(SLOT_ARMOR.x, SLOT_ARMOR.y, GRID, GRID);
		
	}
	
	public void dropItem(){
		Item selected = getSelectedItem();
		String message = "";
		int pX=GameState.player.getX()/GRID;
		int pY=GameState.player.getY()/GRID;
		
		if(GameState.currentLevel.getItemIn(pX, pY)!=null){
			message = "Не могу выбросить, на чем-то стою";
		}else{
			
			GameState.currentLevel.putItemTo(selected, pX, pY);
			
			GameState.player.inventory.remove(selected);
			message="Успешно выбросили "+selected.getName();
		}
		
		GameState.setGameInfo(message);
		Log.d("", "Бросаем предмет "+selected.getName());
		
		
		
		
	}
	
	public void useItem(){
		Item selected = getSelectedItem();
		Log.d("", "используем предмет "+selected.getName());
		selected.use();
		
	}
	
	private Item getSelectedItem(){
		int index = cursorY*W+cursorX;
		return GameState.player.inventory.get(index);
	}

}
