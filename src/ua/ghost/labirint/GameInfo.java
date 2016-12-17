package ua.ghost.labirint;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GameInfo extends JPanel {

	private String message="Тестовое сообщение";
	
	public GameInfo(){
		setBounds(320, 10, 470, 130);
		setBackground(Color.black);
		setBorder(BorderFactory.createLineBorder(Color.white));
	}
	
}
