package ua.ghost.labirint.items;

public class Item {

	protected String name;
	protected ItemType type;
	protected int imageIndex=0;
	
	public String getName() {
		return name;
	}

	public ItemType getType() {
		return type;
	}

	public void use(){
		
	}
	
	public int getImageIndex(){
		return imageIndex;
	}
	
	
	
}
