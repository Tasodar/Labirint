package ua.ghost.labirint.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import ua.ghost.labirint.GameState;
import ua.ghost.mylibrary.ImageLoader;

public class ImageStorage {

	private HashMap<String, BufferedImage> imageMap;
	
	public ImageStorage(){
		
		imageMap=new HashMap<String, BufferedImage>();
		load();
		
		GameState.setImageStorage(this);
	}
	
	
	private void load(){
		
		BufferedImage img;
		img=ImageLoader.loadImage("/tile.png");
		imageMap.put("���", img);
		
		img=ImageLoader.loadImage("/brick.png");
		imageMap.put("�����", img);
		
		
	}
	
	
	public BufferedImage getImage(String name){
		return imageMap.get(name);
	}
	
}
