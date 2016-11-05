package ua.ghost.labirint;

import java.util.HashMap;
import java.util.Map;

import ua.ghost.mylibrary.Log;

public class KeyboardState {
	
	private HashMap<Integer, Boolean> keyMap;
	
	public KeyboardState()
	{
		keyMap=new HashMap<Integer, Boolean>();
	}
	
	public void setKey(int keyCode, boolean val){
		if(keyMap==null){
			Log.e("KeyboardState", "Map is null!!!!");
		}
		keyMap.put(keyCode, val);

	}
	
	
	public boolean getKey(int keyCode){
		if(keyMap.get(keyCode)==null) return false;
		
		return keyMap.get(keyCode);
	}
}
