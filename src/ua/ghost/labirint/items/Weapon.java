package ua.ghost.labirint.items;

public class Weapon extends Item {
	
	private int cast=3;
	private int dise = 6;
	
	
	public Weapon(String name, int cast, int dise, int imageIndex){
		type=ItemType.weapon;
		
		this.name=name;
		this.cast=cast;
		this.dise=dise;
		this.imageIndex=imageIndex;
		
		
	}

	public int getAttack(){
		int attac = 0;
		for (int i=0; i<cast; i++){
			attac+=1 + (int)(Math.random() * dise);
		}
		
		return attac;
	}
	
	
	@Override
	public String getInfo(){
		return " атака: "+cast+"d"+dise;
	}
	
	
}
