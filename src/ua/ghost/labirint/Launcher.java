package ua.ghost.labirint;

import javax.swing.JFrame;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame();
		
		frame=new JFrame("Лабиринт");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(830,  650);
		
		Game game = new Game();
		frame.add(game);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.requestFocus();
		game.startGame();
		
	}

}
