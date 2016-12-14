package ua.ghost.labirint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ua.ghost.mylibrary.ImageLoader;

public class InfoPanel extends JPanel {

	private BufferedImage bg;
	private PlayerInfo playerInfo;
	
	
	
	public InfoPanel(){
		
		
		setLayout(null);
		setBounds(5, 5+GameState.game.HEIGHT+5, GameState.game.WIDTH, 150);
		setBorder(BorderFactory.createLineBorder(Color.white));
		//setBackground(Color.black);
		setBackground(new Color(255, 0, 255, 0));
		bg=ImageLoader.loadImage("/bg01.png");
		
		playerInfo = new PlayerInfo();
		this.add(playerInfo);
		
		
		
		
	}
	
	@Override
	public void paint(Graphics g){
		g.drawImage(bg.getSubimage(0, bg.getHeight()-this.getHeight(), this.getWidth(), this.getHeight())  , 0, 0, null);
		super.paint(g);
		
	}
	
	public void setHits(String newValue){
		playerInfo.setHits(newValue);
	}
	
	
	
}
