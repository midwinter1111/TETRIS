package FrameComponent;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public class Mino {
	// テトリミノのサイズ
	public static final int ROW = 4;
	public static final int COL = 4;

	// 1マスのサイズ
	private static final int TILE_SIZE = Field.TILE_SIZE;

	// 移動方向
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int HARDDROP = 3;

	// テトリミノの名前
	public static final int IMino = 0;
	public static final int ZMino = 1;
	public static final int SMino = 2;
	public static final int LMino = 3;
	public static final int JMino = 4;
	public static final int TMino = 5;
	public static final int OMino = 6;

	// 壁
	public static final int WALL = 7;

	// テトリミノの形を格納
	protected int[][] mino = new int[ROW][COL];

	// テトリミノイメージ番号
	protected int imageNo;

	// 位置
	protected Point pos;

	// フィールドへの参照
	protected Field field;

	public Mino(Field field) {
		this.field = field;
		init();

		imageNo = 6;

		pos = new Point(4, -4);
	}

	/**
	 * テトリミノの初期化
	 */
	public void init() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				mino[i][j] = 0;
			}
		}
	}

	/**
	 * テトリミノの描画
	 *
	 * @param g 描画オブジェクト
	 * @param minoImage テトリミノイメージ
	 */
	public void draw(Graphics g, Image minoImage) {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (mino[i][j] == 1) {
					g.drawImage(minoImage, (pos.x + j) * TILE_SIZE, (pos.y + i) * TILE_SIZE,
							(pos.x + j) * TILE_SIZE + TILE_SIZE, (pos.y + i) * TILE_SIZE + TILE_SIZE,
							imageNo * TILE_SIZE, 0, imageNo * TILE_SIZE + TILE_SIZE, TILE_SIZE, null);
				}
			}
		}
	}

	/**
	 * ネクストを描画
	 * @param g
	 * @param minoImage
	 */
	public void drawInPanel(Graphics g, Image minoImage) {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (mino[i][j] == 1) {
					g.drawImage(minoImage, (j + 1) * TILE_SIZE, (i + 1) * TILE_SIZE,
							(j + 1) * TILE_SIZE + TILE_SIZE, (i + 1) * TILE_SIZE + TILE_SIZE,
							imageNo * TILE_SIZE, 0, imageNo * TILE_SIZE + TILE_SIZE, TILE_SIZE, null);
				}
			}
		}
	}

	/**
	 * dirの方向にテトリミノを移動
	 *
	 * @param dir 移動方向
	 * @return ロックダウンされたらtrueを返す
	 */
	public boolean move(int dir) {
		switch (dir) {
		case LEFT:
			Point newPos = new Point(pos.x - 1, pos.y);
			if (field.isMovable(newPos, mino)) { // 壁と衝突しなければ位置を更新
				pos = newPos;
			}
			break;
		case RIGHT:
			newPos = new Point(pos.x + 1, pos.y);
			if (field.isMovable(newPos, mino)) {
				pos = newPos;
			}
			break;
		case DOWN:
			newPos = new Point(pos.x, pos.y + 1);
			if (field.isMovable(newPos, mino)) {
				pos = newPos;
			} else {
				field.lockDown(pos, mino, imageNo);
				return true;
			}
			break;
		case HARDDROP:
			while(field.isMovable(new Point(pos.x, pos.y+1),  mino)) {
				pos.y += 1;
			}
			field.lockDown(new Point(pos.x, pos.y), mino, imageNo);
			return true;
		}
		return false;
	}

	/**
	 * テトリミノを回転させる
	 */
	public void spin() {
		int[][] spinnedMino = new int[ROW][COL];

		// 回転したミノ
		for(int i=0; i<ROW; i++) {
			for(int j=0; j<COL; j++) {
				spinnedMino[j][ROW-1-i] = mino[i][j];
			}
		}

		// 回転可能か調べる
		if(field.isMovable(pos, spinnedMino)) {
			mino = spinnedMino;
		}
	}

	public void setNewPosForHold() {
		pos = new Point(4, -4);
	}
}
