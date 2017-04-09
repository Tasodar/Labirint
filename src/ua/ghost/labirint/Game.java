package ua.ghost.labirint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import ua.ghost.labirint.entities.Player;
import ua.ghost.labirint.gfx.ImageLib;
import ua.ghost.labirint.gfx.TileStorage;
import ua.ghost.mylibrary.Log;

public class Game extends Canvas implements Runnable  {
	
	public static final int WIDTH=25*GameState.TILE_W, HEIGHT=18*GameState.TILE_H;
	private InventoryMenu iMenu;
	
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
				
				if(openInventory){
					
					if(e.getKeyCode()==KeyEvent.VK_C){						//бросить предмет
						iMenu.dropItem();
					}  	
					if(e.getKeyCode()==KeyEvent.VK_X){     					//использовать
						iMenu.useItem();
					}
					
				}else{
					if(e.getKeyCode()==KeyEvent.VK_C) player.pickUpItem(); //поднять предмет
					if(e.getKeyCode()==KeyEvent.VK_X){     //открыть инвентарь
						openInventory = true;
						iMenu.showMenu();
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(openInventory){
					if(e.getKeyCode()==KeyEvent.VK_LEFT) iMenu.muveCursorX(-1);
					if(e.getKeyCode()==KeyEvent.VK_RIGHT) iMenu.muveCursorX(1);
					if(e.getKeyCode()==KeyEvent.VK_UP) iMenu.muveCursorY(-1);
					if(e.getKeyCode()==KeyEvent.VK_DOWN) iMenu.muveCursorY(1);
					
					if(e.getKeyCode()==KeyEvent.VK_ESCAPE) openInventory=false;
				}else{
					if(e.getKeyCode()==KeyEvent.VK_LEFT) player.step(Player.LOOK_LEFT);
					if(e.getKeyCode()==KeyEvent.VK_RIGHT) player.step(Player.LOOK_RIGHT);
					if(e.getKeyCode()==KeyEvent.VK_UP) player.step(Player.LOOK_UP);
					if(e.getKeyCode()==KeyEvent.VK_DOWN) player.step(Player.LOOK_DOWN);
				}
				
			}
		});
		
		
		GameState.setGame(this);
		iMenu = new InventoryMenu();
		
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				//e.
				int x=e.getX();
				int y=e.getY();
				x=(x + GameState.currentLevel.getShiftX())/GameState.TILE_W;
				y=(y + GameState.currentLevel.getShiftY())/GameState.TILE_H;
				
				
				
				Log.d("Mouse", "X="+x+" Y="+y);
			}
		});
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
		if(openInventory) iMenu.render(g);
		
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
	

	
}
