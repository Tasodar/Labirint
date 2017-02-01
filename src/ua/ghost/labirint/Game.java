package ua.ghost.labirint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import ua.ghost.labirint.entities.Player;
import ua.ghost.labirint.gfx.ImageLib;
import ua.ghost.labirint.gfx.TileStorage;
import ua.ghost.mylibrary.Log;

public class Game extends Canvas implements Runnable  {
	
	public static final int WIDTH=25*GameState.TILE_W, HEIGHT=18*GameState.TILE_H;
	public static final int inventoryW=21*GameState.TILE_W, inventoryH=14*GameState.TILE_H;
	
	private boolean started = false;
	
	public InfoPanel info=null;
	
	public boolean openInventory = false;
	
	
	private Player player;
	//private Tile floor1, wall1 ;
	//private Level room;
	
	
	public Game(){
		
		setBounds(5, 5, WIDTH, HEIGHT);
		setBackground(Color.blue);
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_C) player.pickUpItem(); //поднять предмет
				
				if(e.getKeyCode()==KeyEvent.VK_X){     //открыть инвентарь
					Log.d("", "Открываем инвентарь");
					openInventory = true;
				} 
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_LEFT) player.step(Player.LOOK_LEFT);
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) player.step(Player.LOOK_RIGHT);
				if(e.getKeyCode()==KeyEvent.VK_UP) player.step(Player.LOOK_UP);
				if(e.getKeyCode()==KeyEvent.VK_DOWN) player.step(Player.LOOK_DOWN);
				
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE && openInventory) openInventory=false;
				
			}
		});
		
		
		GameState.setGame(this);
	}
	
public synchronized void startGame(){
		Log.d("Game", "Игра стартовала");
		
		new ImageLib();
		
		new TileStorage();
		new Level();
		
		player = GameState.currentLevel.getPlayer();
		
		
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
		
		
	
		
		GameState.currentLevel.render(g);
		if(openInventory) drawInventory(g);
		
		//GameState.mobs.render(g);
		
		
		//*********
		
		g.dispose();
		bs.show();
	}
	
	private void tic(){
		
		if(openInventory) return;
		GameState.currentLevel.tick();

	
	}
	
	
	public void setInfoPane(InfoPanel info){
		this.info=info;
	}
	
	private void drawInventory(Graphics g){
		
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect((WIDTH-inventoryW)/2, (HEIGHT-inventoryH)/2, inventoryW, inventoryH);
		
		for(int i=0; i<player.inventory.size(); i++){
			
			BufferedImage icon=GameState.img.getImageById(player.inventory.get(i).getImageIndex());
			g.drawImage(icon, (WIDTH-inventoryW)/2+i*32, (HEIGHT-inventoryH)/2+i*32, null);
		}
		
		
	}
	
}
