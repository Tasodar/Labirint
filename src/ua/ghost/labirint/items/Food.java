package ua.ghost.labirint.items;

public class Food extends Item{
	
	private int energy;
	
	public Food(String name, int energy, int imageIndex){
		type = ItemType.food;
		this.name = name;
		this.energy=energy;
		
		this.imageIndex=imageIndex;
	}
	
	
	
}
