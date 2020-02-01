package FrameComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class NextZoneTag extends JPanel{
	// パネルサイズ
	public static final int WIDTH = 96;
	public static final int HEIGHT = 16;

	public NextZoneTag() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// "NEXT"と描画
		g.setColor(Color.ORANGE);
		// フォントを作成
		Font font = new Font("Ariel", Font.BOLD, 16);
		g.setFont(font);

		g.drawString("Next", 16, 12);
	}

}
