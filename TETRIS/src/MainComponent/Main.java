package MainComponent;

import javax.swing.JFrame;

/*
 * Created by midwinter on 2020/1/24
 *
 * This program is TETRIS Game
 * 
 * Refference: 
 * [1] Javaでゲーム作りますが何か？ http://aidiary.hatenablog.com/entry/20040918/1251373370
 * [2] てとりすちゃんねる https://tetrisch.github.io/main/rule.html
 *
 */

public class Main {

	public static void main(String[] args) {
		TetrisFrame frame = new TetrisFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
