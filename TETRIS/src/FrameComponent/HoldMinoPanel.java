package FrameComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class HoldMinoPanel extends JPanel {

	public static final int WIDTH = 96;
	public static final int HEIGHT = 400;

	private Mino holdMino;
	private Image minoImage;

	/**
	 *
	 */
	public HoldMinoPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	/**
	 *
	 */
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.GREEN);
		g.drawRect(10, 10, WIDTH - 20, WIDTH - 20); // 正方形にするため

		// ホールドしたミノを描画
		if (holdMino != null) {
			holdMino.drawInPanel(g, minoImage);
		}
	}

	/**
	 *
	 * @param holdMino
	 * @param minoImage
	 */
	public void set(Mino holdMino, Image minoImage) {
		this.holdMino = holdMino;
		this.minoImage = minoImage;
		repaint();
	}

}
