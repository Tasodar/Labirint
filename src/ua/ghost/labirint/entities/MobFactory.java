package ua.ghost.labirint.entities;

import ua.ghost.labirint.GameState;
import ua.ghost.mylibrary.Log;

public class MobFactory {

	public static Alive creatMob(String str){
		
		Alive res=null;
		
		String[] param =str.split(",");
		
		if(param.length==3){
			int index=Integer.valueOf(param[2]);
			int mobX=Integer.valueOf(param[0])*GameState.TILE_W;
			int mobY=Integer.valueOf(param[1])*GameState.TILE_H;
			
			
			switch(index){
			case 1:
				res = new TestMob(mobX, mobY);
				break;
			case 2:
				res = new Ghost(mobX, mobY);
				break;
			}
			
		}else{
			Log.e("Создание монстра (фабрика)", "Неверные параметры");
		}
		
		
		return res;
	}
	
}
