package ua.ghost.labirint.items;

import ua.ghost.labirint.GameState;
import ua.ghost.mylibrary.Log;

public class Food extends Item{
	
	private int energy;
	
	

	public Food(String name, int energy, int imageIndex){
		type = ItemType.food;
		this.name = name;
		this.energy=energy;
		
		this.imageIndex=imageIndex;
	}
	
	
	
	@Override
	public String getInfo(){
		return " енергитическая ценность: "+energy;
	}
	
	public int getEnergy() {
		return energy;
	}

	@Override
	public void use(){
		Log.d("", "Мы едим "+name);
	}

	
}
