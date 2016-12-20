package ua.ghost.labirint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ua.ghost.labirint.entities.Player;

public class PlayerInfo extends JPanel {

	private Font font=new Font("Dialog", Font.BOLD, 24 ); 
	
	private Player player = GameState.player;
	
	
	public PlayerInfo(){
		
		setBounds(10, 10, 300, 130);
		setBackground(Color.black);
		setBorder(BorderFactory.createLineBorder(Color.white));
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		if(player==null){
			player=GameState.player;
			if(player==null) return;
		}
		
		g.setColor(Color.white);
		g.setFont(font);
		
		g.drawString("����: "+player.getHits(), 5, 25);
		g.drawString("�����: "+player.baseDamage, 5, 50);
		g.drawString("�����: "+player.getArmor(), 5, 75);
		
	}
	
	
	
	
	
}
