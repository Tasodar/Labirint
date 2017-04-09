package ua.ghost.labirint.items;

public class ItemFactory {
	
	public static Item createItemByCode(int code){
		Item res=null;
		
		switch(code){
		case 1:
			res = new Weapon("���������� ���", 3, 6, 10);
			break;
		case 2:
			res =new Weapon("��� ������ �����", 4, 6, 5);
			break;
		case 3:
			res = new Armor("������� �����", 5, 6);
			break;
		case 4:
			res = new Food("��� �������", 5, 7);
			break;
			
		default:
			//res= new Magic("�������� �����", 12);	
		break;
		}
		
		
		
		return res;
	}
}
