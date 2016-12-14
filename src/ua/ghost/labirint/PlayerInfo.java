package ua.ghost.labirint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PlayerInfo extends JPanel {

	private Font font=new Font("Dialog", Font.BOLD, 24 ); 
	
	private String hits="234234";
	
	public PlayerInfo(){
		
		setBounds(10, 10, 300, 130);
		setBackground(Color.black);
		setBorder(BorderFactory.createLineBorder(Color.white));
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString("Хиты: "+hits, 5, 25);
		
	}
	
	
	public void setHits(String newValue){
		hits=newValue;
		this.repaint();
	}
	
	
}
