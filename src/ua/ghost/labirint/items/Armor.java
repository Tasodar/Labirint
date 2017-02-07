package ua.ghost.labirint.items;

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

}
