package ua.ghost.labirint.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] frames;
	private int currentFrame = 0;
	private int delay=5; // задержка в тиках
	private int tickNum=0;
	private boolean started=false;
	
	
	public Animation(BufferedImage[] frames){
		this.frames=frames;
	}
	
	public void setDelay(int newDelay){
		delay=newDelay;
	}
	
	public void start(){
		started=true;
	}
	
	public void stop(){
		started=false;
	}
	
	public BufferedImage getFrame(){
		return frames[currentFrame];
	}
	
	public void tick(){
		
		if(!started) return;
		
		tickNum++;
		if(tickNum>delay){
			currentFrame++;
			if(currentFrame>=frames.length) currentFrame=0;
			tickNum=0;
		}
		
		
	}
	
	
}
