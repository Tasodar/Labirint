package ua.ghost.labirint.items;

public class ItemFactory {
	
	public static Item createItemByCode(int code){
		Item res=null;
		
		switch(code){
		case 1:
			res = new Weapon("Бандитский нож", 3, 6, 10);
			break;
		case 2:
			res =new Weapon("Меч тысячи истин", 4, 6, 5);
			break;
		case 3:
			res = new Armor("Кожаная броня", 5, 6);
			break;
		case 4:
			res = new Food("еда мужская", 5, 7);
			break;
			
		default:
			//res= new Magic("Неведома хрень", 12);	
		break;
		}
		
		
		
		return res;
	}
}
