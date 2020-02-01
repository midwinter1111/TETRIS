package FrameComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import MainComponent.MainPanel;

public class Field {
	// フィールドのサイズ
	public static final int COL = 12;
	public static final int ROW = 26;

	// マスのサイズ
	public static final int TILE_SIZE = 16;

	// フィールド
	private int[][] field;
	// フィールドのイメージ
	private int[][] fieldImage;

	public Field() {
		field = new int[ROW][COL];
		fieldImage = new int[ROW][COL];

		// フィールドを初期化
		init();
	}

	/**
	 * フィールドを初期化する
	 */
	public void init() {
		for (int y = 0; y < ROW; y++) {
			for (int x = 0; x < COL; x++) {
				// 壁を作る
				if (x == 0 || x == COL - 1) {
					field[y][x] = 1;
					fieldImage[y][x] = Mino.WALL;
				} else if (y == ROW - 1) {
					field[y][x] = 1;
					fieldImage[y][x] = Mino.WALL;
				} else {
					field[y][x] = 0;
				}
			}
		}
	}

	/**
	 * フィールドを描画
	 */
	public void draw(Graphics g, Image minoImage) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MainPanel.WIDTH, MainPanel.HEIGHT);

		g.setColor(Color.LIGHT_GRAY);
		for (int y = 0; y < ROW; y++) {
			for (int x = 0; x < COL; x++) {
				if (field[y][x] == 1) {
					g.drawImage(minoImage, x * TILE_SIZE, y * TILE_SIZE, x
							* TILE_SIZE + TILE_SIZE, y * TILE_SIZE + TILE_SIZE,
							fieldImage[y][x] * TILE_SIZE, 0, fieldImage[y][x]
									* TILE_SIZE + TILE_SIZE,
							TILE_SIZE, null);
				}
			}
		}
	}

	/**
	 * テトリミノを移動できるか調べる
	 *
	 * @param newPos ミノの移動先座標
	 * @param mino ミノ
	 * @return 移動できたらtrue
	 */
	public boolean isMovable(Point newPos, int[][] mino) {
		// block=1のマスすべてについて衝突しているか調べる
		// どれか1マスでも衝突してたら移動できない
		for (int i = 0; i < Mino.ROW; i++) {
			for (int j = 0; j < Mino.COL; j++) {
				if (mino[i][j] == 1) { // 4x4内でブロックのあるマスのみ調べる
					if (newPos.y + i < 0) { // そのマスが画面の上端外のとき
						// ブロックのあるマスが壁のある0列目以下または
						// COL-1列目以上に移動しようとしている場合は移動できない
						if (newPos.x + j <= 0 || newPos.x + j >= COL - 1) {
							return false;
						}
					} else if ((newPos.x + j) < -1) {
						return false;
					} else if (field[newPos.y + i][newPos.x + j] == 1) { // フィールド内で
						// 移動先にすでにブロック（壁含む）がある場合は移動できない
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * ロックダウンを判定してミノを固定する
	 *
	 * @param pos
	 * @param mino
	 * @param imageNo
	 */
	public void lockDown(Point pos, int[][] mino, int imageNo) {
		for (int i = 0; i < Mino.ROW; i++) {
			for (int j = 0; j < Mino.COL; j++) {
				if (mino[i][j] == 1) {
					if (pos.y + 1 < 0) {
						continue;
					}
					field[pos.y + i][pos.x + j] = 1;
					fieldImage[pos.y + i][pos.x + j] = imageNo;
				}
			}
		}
	}

	/**
	 * ライン消去を行う
	 *
	 * @return deleteLine 消した行数
	 */
	public int deleteLine() {
		int deleteLine = 0;

		for (int y = 0; y < ROW - 1; y++) {
			int count = 0;
			for (int x = 1; x < COL - 1; x++) {
				// ブロックがある列の数を数える
				if (field[y][x] == 1) {
					count++;
				}
			}
			// そろった行が見つかった！
			if (count == Field.COL - 2) {
				deleteLine++;
				// その行を消去
				for (int x = 1; x < COL - 1; x++) {
					field[y][x] = 0;
				}
				// それより上の行を落とす
				for (int ty = y; ty > 0; ty--) {
					for (int tx = 1; tx < COL - 1; tx++) {
						field[ty][tx] = field[ty - 1][tx];
						fieldImage[ty][tx] = fieldImage[ty - 1][tx];
					}
				}
			}
		}
		return deleteLine;
	}

	public boolean isStacked() {
		for (int x = 1; x < COL - 1; x++) {
			if (field[0][x] == 1) {
				return true;
			}
		}
		return false;
	}

	public boolean isFilled(Point pos, int x, int y) {
		return (field[(pos.y + x + 2)][(pos.x + y)] == 1);
	}

}
