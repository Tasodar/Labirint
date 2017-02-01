package ua.ghost.labirint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JTextPane;

public class GameInfo extends JTextPane {

	private String message="Тестовое сообщение Тестовое сообщение Тестовое сообщение Тестовое сообщение";
	
	private Font font=new Font("Dialog", Font.BOLD, 24 );
	
	public GameInfo(){
		setBounds(320, 10, 470, 130);
		setBackground(Color.black);
		setBorder(BorderFactory.createLineBorder(Color.white));
		
		this.setMargin(new Insets(10, 10, 10, 10));
		
		this.setFont(font);
		this.setText(message);
		this.setEditable(false);
		
	}
	
	
	
}
