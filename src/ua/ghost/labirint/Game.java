package ua.ghost.labirint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


import ua.ghost.labirint.entities.Player;
import ua.ghost.labirint.gfx.FloorTile;
import ua.ghost.labirint.gfx.ImageStorage;
import ua.ghost.labirint.gfx.Tile;
import ua.ghost.labirint.gfx.WallTile;
import ua.ghost.mylibrary.Log;

public class Game extends Canvas implements Runnable  {
	
	public static final int WIDTH=25*GameState.TILE_W, HEIGHT=18*GameState.TILE_H;
	private boolean started = false;
	
	
	private Player player;
	//private Tile floor1, wall1 ;
	private Level room;
	
	
	public Game(){
		
		setBounds(5, 5, WIDTH, HEIGHT);
		setBackground(Color.blue);
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				GameState.keyMap.setKey(e.getKeyCode(), false);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				GameState.keyMap.setKey(e.getKeyCode(), true);
				
			}
		});
	}
	
public synchronized void startGame(){
		Log.d("Game", "Игра стартовала");
		
		new ImageStorage();
		//floor1=new FloorTile(10, 10);
		//wall1=new WallTile(35, -5);
		
		room=new Level();
		player = new Player(200, 200);
		Thread gameThread = new Thread(this);
		started=true;
		gameThread.start();
		
		
		
	}
	
	public synchronized void stopGame(){
		
		started=false;
	}
	
	


	@Override
	public void run() {
		
		long startTime= System.nanoTime();
		long mills=System.currentTimeMillis();
		
		long delta = 0, delta1=0;
		long interval = 1000000000/60;
		
		int fps=0, tps=0 ;
		
		while(started){
			
			
			
			delta=System.nanoTime()-startTime;
			delta1=System.currentTimeMillis()-mills;
			
			if(delta>interval){
				
				tps++;
				tic();
				startTime+=interval;
			}
			
			if(delta1>1000){
				
				fps=0;
				tps=0;
				mills+=1000;
			}
			
			render();
			fps++;
		}
	}
	
	private void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		//Рисовать тута
		
		
		//floor1.render(g);
		//wall1.render(g);
		
		room.render(g);
		
		player.render(g);
		//g.drawImage(GameState.imageStorage.getImage("стена"), 10, 20, null);
		
		
		
		//*********
		
		g.dispose();
		bs.show();
	}
	
	private void tic(){
		player.tick();
	}
	

}
