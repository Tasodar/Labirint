package ua.ghost.labirint.items;

import ua.ghost.labirint.GameState;

public class Armor extends Item {
	
	private int def;
	
	public Armor(String name, int def, int imageIndex){
		
		this.type=ItemType.armor;
		this.name=name;
		this.imageIndex=imageIndex;
		
		this.def=def;
		
	}
	
	public int getDef(){
		return def;
	}
	
	@Override
	public String getInfo(){
		return " защита: "+def;
	}
	
	@Override
	public void use(){
		Item old = GameState.player.reEquip(this);
		if(old!=null) GameState.player.inventory.add(old);
		GameState.player.inventory.remove(this);
		
		
	}

}
