package ua.ghost.labirint;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame();
		
		frame=new JFrame("Лабиринт");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(830,  780);
		frame.getContentPane().setBackground(Color.black);
		
		
		
		Game game = new Game();
		frame.add(game);
		
//		JLabel info = new JLabel("eeeeeeeee");
//		info.setBounds(5, 5+game.HEIGHT+5, game.WIDTH, 50);
//		info.setBorder(BorderFactory.createLineBorder(Color.gray));
//		info.setVerticalAlignment(JLabel.CENTER);
//		info.setHorizontalAlignment(JLabel.CENTER);
//		info.setForeground(Color.green);
//		frame.add(info);
		
		//
		
		InfoPanel info=new InfoPanel();
		frame.add(info);
		game.setInfoPane(info);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		
		game.requestFocus();
		game.startGame();
		
	}

}
