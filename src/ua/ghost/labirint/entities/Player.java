package ua.ghost.labirint.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ua.ghost.labirint.Game;
import ua.ghost.labirint.GameState;
import ua.ghost.labirint.gfx.Animation;
import ua.ghost.labirint.gfx.Tile;
import ua.ghost.labirint.items.Armor;
import ua.ghost.labirint.items.Food;
import ua.ghost.labirint.items.Item;
import ua.ghost.labirint.items.ItemFactory;
import ua.ghost.labirint.items.ItemType;
import ua.ghost.labirint.items.Weapon;
import ua.ghost.mylibrary.ImageLoader;
import ua.ghost.mylibrary.Log;

public class Player extends Alive{
	
	private BufferedImage set;
	private BufferedImage img;
	
	private Animation animLeft, animRight, animUp, animDown, animCurrent;
	
	
	public static final int LOOK_DOWN=0, LOOK_LEFT=1, LOOK_RIGHT=2, LOOK_UP=3;
	private int lookTo=LOOK_DOWN;
	private boolean inStep=false;
	private int steps=0;
	
	public InUse inUse=new InUse();
	public ArrayList<Item> inventory = new ArrayList<Item>();
	
	public Player(int x, int y){
		
		super(x, y);
		
		inUse.setWeapon(new Weapon("�����", 3, 6, 0));
//		this.x=x;
//		this.y=y;
		set = ImageLoader.loadImage("/char01.png");
		img=set.getSubimage(0, 0, 32, 32);
		
		initAnimation();
		
		maxHits=100;
		hits=maxHits;
		baseDamage=1;
		armor=1;
		
		GameState.setPlaye(this);
		GameState.game.info.refreshPlayerInfo();
		
		addToInventory();
	}
	
	private void addToInventory(){
		inventory.add(ItemFactory.createItemByCode(2));
		inventory.add(ItemFactory.createItemByCode(3));
		inventory.add(ItemFactory.createItemByCode(4));
		

	}
	
	
	private void initAnimation() {
		
		BufferedImage[] frames=new BufferedImage[3];
		frames[0]=set.getSubimage(0, 0, 32, 32);
		frames[1]=set.getSubimage(32, 0, 32, 32);
		frames[2]=set.getSubimage(64, 0, 32, 32);
		animDown=new Animation(frames);
		
		frames=new BufferedImage[3];
		frames[0]=set.getSubimage(0, 32, 32, 32);
		frames[1]=set.getSubimage(32, 32, 32, 32);
		frames[2]=set.getSubimage(64, 32, 32, 32);
		animLeft=new Animation(frames);
		
		frames=new BufferedImage[3];
		frames[0]=set.getSubimage(0, 64, 32, 32);
		frames[1]=set.getSubimage(32, 64, 32, 32);
		frames[2]=set.getSubimage(64, 64, 32, 32);
		animRight=new Animation(frames);
		
		frames=new BufferedImage[3];
		frames[0]=set.getSubimage(0, 96, 32, 32);
		frames[1]=set.getSubimage(32, 96, 32, 32);
		frames[2]=set.getSubimage(64, 96, 32, 32);
		animUp=new Animation(frames);
		
		
		animCurrent = animDown;
		
	}


	@Override
	public void tick() {
		
		Point screenPos = GameState.currentLevel.levelToScreen(new Point(x, y)); 
		
		if(inStep){
			steps++;
			switch(lookTo){
			case (LOOK_LEFT):
				if(checkStep(x-2, y+16)) x-=2;
			if(screenPos.x<=4*GameState.TILE_W){
				GameState.currentLevel.shiftW(-2);
			}
			
				break;
			case (LOOK_RIGHT):
				if(checkStep(x+1+GameState.TILE_W, y+16)) x+=2;
				if(screenPos.x>=Game.WIDTH-4*GameState.TILE_W-this.WIDTH){
					GameState.currentLevel.shiftW(2);
				}
			
			
				break;
			case (LOOK_UP):
				if(checkStep(x+16, y-2)) y-=2;
				if(screenPos.y<=4*GameState.TILE_H){
					GameState.currentLevel.shiftH(-2);
				}
			
				break;
			case (LOOK_DOWN):
				if(checkStep(x+16, y+1+GameState.TILE_H)) y+=2;
				if(screenPos.y>=Game.HEIGHT-4*GameState.TILE_H-this.HEIGHT){
					GameState.currentLevel.shiftH(2);
				}
				break;
			}

//			if(steps>=16){
//				inStep=false;
//				steps=0;
//				animCurrent.stop();
//			}
			
			//TODO: ��������� ������� ������������ �� ������
			
			if( (lookTo==LOOK_LEFT || lookTo==LOOK_RIGHT)  && x%GameState.TILE_W==0   ){
				inStep=false;
				steps=0;
				animCurrent.stop();
				stepEvent();
			} else if( (lookTo==LOOK_UP || lookTo==LOOK_DOWN)  && y%GameState.TILE_H==0   ){
				inStep=false;
				steps=0;
				animCurrent.stop();
				stepEvent();
			}
			
			
		}
		animCurrent.tick();
		
//		if(x<0) x=0;
//		if(x>Game.WIDTH-this.WIDTH) x=Game.WIDTH-this.WIDTH;
//		if(y<0) y=0;
//		if(y>Game.HEIGHT-HEIGHT) y=Game.HEIGHT-HEIGHT;
		
		
	}

	
	private boolean checkStep(int newX, int newY){
		
//		Entity mob=GameState.mobs.getEntityIn(newX, newY);
//		if(mob!=null){
//			inStep=false;
//			animCurrent.stop();
//			steps=0;
//			
//			mob.touch();
//			return false;
//		} 
		Tile stepTo=GameState.currentLevel.getTileIn(newX, newY);
		boolean res=!stepTo.solid;
		return res;
		
	}
	
	
	@Override
	public void render(Graphics g) {
		Point screenPos = GameState.currentLevel.levelToScreen(new Point(x, y));
		
		g.drawImage(animCurrent.getFrame(), screenPos.x, screenPos.y, null);
	}
	
	private void setLookTo(int newLook){
		
		lookTo=newLook;
		switch(newLook){
		case(LOOK_DOWN):
			img=set.getSubimage(0, 0, 32, 32);
			animCurrent = animDown;
			break;
		case(LOOK_LEFT):
			img=set.getSubimage(0, 32, 32, 32);
			animCurrent = animLeft;
			break;
		case(LOOK_RIGHT):
			img=set.getSubimage(0, 64, 32, 32);
			animCurrent = animRight;
			break;
		case(LOOK_UP):
			img=set.getSubimage(0, 96, 32, 32);
			animCurrent = animUp;
			break;
		}
		//animCurrent.start();
		
	}
	
	public Alive checkMonsterIn(int direct){
		
		Alive mob=null;
		
		switch(direct){
		case(LOOK_DOWN):
			mob = GameState.currentLevel.getMobIn(x, y+GameState.TILE_H);
			break;
		case(LOOK_LEFT):
			mob = GameState.currentLevel.getMobIn(x-GameState.TILE_W, y);
			break;
		case(LOOK_RIGHT):
			mob = GameState.currentLevel.getMobIn(x+GameState.TILE_W, y);
			break;
		case(LOOK_UP):
			mob = GameState.currentLevel.getMobIn(x, y-GameState.TILE_W);
			break;
		}
		
		return mob;
		
	}
	
	public void step(int direct){
		
		if(inStep) return;
		
		if(lookTo!=direct){
			setLookTo(direct);
			return;
		}
		
		
		
		//��� ��� ���������� ������� - ��� ������
		Alive monster=checkMonsterIn(direct);
		if(monster!=null){
			//�������� ����, ��� ����� ����
			monster.hit(getDamage());
			//������������� ������� ����
			stepEvent();
			return;
		}
		
		
		//stepEvent();
		
		inStep=true;
		animCurrent.start();
		steps=0;
		
	}
	
	@Override
	public void hit(int damage){
		
//		int armor=1;
		if(inUse.getArmor()!=null){
			armor = inUse.getArmor().getDef();
			damage-=armor;
			if(damage<0) damage=0;
		}
		
		super.hit(damage);
		GameState.game.info.refreshPlayerInfo();
		Log.d("Player", "���� ������� �� "+damage+" �����");
	}
	
	
	public int getDamage(){
		//����� ����� ����������� �������
		
		int res = (int)baseDamage+ inUse.getWeapon().getAttack(); 
		Log.d("Player", "�����: "+res);
		return res;
		
	}
	
	public int getArmor(){
		
		int def = 0;
		
		if(inUse.getArmor()!=null) def = inUse.getArmor().getDef();
		
		return armor+def;
		
		
	}
	
	public int getHits(){
		return hits;
	}
	
	public void pickUpItem(){
		if(inStep) return;
		Item item = GameState.currentLevel.getItemIn(x/GameState.TILE_W, y/GameState.TILE_H);
		
		if(item==null) GameState.game.info.setGameInfo("��� ���� ������ ����");
		else{
			GameState.setGameInfo("����� ������� "+item.getName());
			inventory.add(item);
			GameState.currentLevel.remuveItemFrom(x/GameState.TILE_W, y/GameState.TILE_H);
			
		}  
	}
	
	public ArrayList<Item> getInventory(){
		return inventory;
	}
	
	public Item reEquip(Item toEquip){
		Item old = null;
		if(toEquip.getType() == ItemType.armor){
			old = inUse.getArmor();
			inUse.setArmor((Armor)toEquip);
		} else if(toEquip.getType() == ItemType.weapon){
			old = inUse.getWeapon();
			inUse.setWeapon((Weapon)toEquip);
		}
		
		GameState.game.info.refreshPlayerInfo();
		return old;
	}
	
	private void stepEvent(){
		
		 ArrayList<Alive> monsters= GameState.currentLevel.getVisibleMonstars();
		 for(Alive monster : monsters){
			 monster.onPlayerStep();
		 }
		
	}

	@Override
	public void onPlayerStep() {
			
	}
	

}
