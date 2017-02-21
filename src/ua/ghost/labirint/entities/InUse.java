package ua.ghost.labirint.entities;

import ua.ghost.labirint.items.Armor;
import ua.ghost.labirint.items.Weapon;

public class InUse {
	
	private Weapon weapon=null;
	private Armor armor=null;
	
	
	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	} 
	
	
}
