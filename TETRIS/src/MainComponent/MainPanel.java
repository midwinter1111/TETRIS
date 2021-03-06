package MainComponent;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import FrameComponent.Field;
import FrameComponent.HoldMinoPanel;
import FrameComponent.Mino;
import FrameComponent.NextMinoPanel;
import FrameComponent.ScorePanel;
import Minos.IMino;
import Minos.JMino;
import Minos.LMino;
import Minos.NextMinos;
import Minos.OMino;
import Minos.SMino;
import Minos.TMino;
import Minos.NextMinos;

public class MainPanel extends JPanel implements KeyListener, Runnable {
	// パネルサイズ
	public static final int WIDTH = 192;
	public static final int HEIGHT = 416;

	private static final int USER_DROP = 1;
	private static final int ONE_LINE = 100;
	private static final int TWO_LINE = 200;
	private static final int THREE_LINE = 1000;
	private static final int TETRIS = 2000;

	// フィールド
	private Field field;
	// テトリミノ
	private Mino mino;
	// ネクスト
	private NextMinos nextMinos;
	// ホールドしたミノ
	private Mino holdMino;
	// ホールドしたかどうか
	private boolean isHold;

	private boolean isB2B = false;

	// テトリミノのイメージ
	private Image minoImage;
	// ゲームループ用スレッド
	private Thread gameLoop;
	private Random rand;

	// スコアパネルへの参照
	private ScorePanel scorePanel;

	// ネクストへの参照
	private NextMinoPanel nextMinoPanel_1;
	private NextMinoPanel nextMinoPanel_2;
	private NextMinoPanel nextMinoPanel_3;
	private NextMinoPanel nextMinoPanel_4;

	// ホールドしたミノへの参照
	private HoldMinoPanel holdMinoPanel;

	public MainPanel(ScorePanel scorePanel, NextMinoPanel nextMinoPanel_1,
			NextMinoPanel nextMinoPanel_2, NextMinoPanel nextMinoPanel_3,
			NextMinoPanel nextMinoPanel_4, HoldMinoPanel holdMinoPanel) {
		// パネルの推奨サイズを設定、pack()するときに必要
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		// パネルがキー入力を受け付けるようにする
		setFocusable(true);

		this.scorePanel = scorePanel;
		this.nextMinoPanel_1 = nextMinoPanel_1;
		this.nextMinoPanel_2 = nextMinoPanel_2;
		this.nextMinoPanel_3 = nextMinoPanel_3;
		this.nextMinoPanel_4 = nextMinoPanel_4;
		this.holdMinoPanel = holdMinoPanel;

		// ブロックのイメージをロード (Eclipse仕様を想定)
		loadImage("img/mino2.gif");
		System.out.println();

		rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		field = new Field();
		nextMinos = new NextMinos(field);
		goNextMino();
		holdMino = null;
		isHold = false;

		addKeyListener(this);

		// ゲームループ開始
		gameLoop = new Thread(this);
		gameLoop.start();
	}

	public void run() {
		while (true) {
			// ミノを下方向へ移動する
			boolean isLockDown = mino.move(Mino.DOWN);
			if (isLockDown) {
				lockDownProcess();
			}
			repaint();
			mino.setActionWithFloor(false);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void lockDownProcess() {
		// ミノがそろった行を消す
		int deleteLine = field.deleteLine();

		// 消した行数に応じてスコアをプラスする
		if (deleteLine == 1) {
			isB2B = false;
			if(mino.getIsTMino()) {
				if(mino.checkTSpinAttack()) {
					System.out.println("T-Spin Mini!");
					isB2B = true;
				}
			}
			scorePanel.plusScore(ONE_LINE);
		} else if (deleteLine == 2) {
			if(isB2B) {
				System.out.println("Back-to-Back!");
			}
			isB2B = false;
			if(mino.getIsTMino()) {
				if(mino.checkTSpinAttack()) {
					System.out.println("T-Spin Double!!");
					isB2B = true;
				}
			}
			scorePanel.plusScore(TWO_LINE);
		} else if (deleteLine == 3) {
			if(isB2B) {
				System.out.println("Back-to-Back!");
			}
			isB2B = false;
			if(mino.getIsTMino()) {
				if(mino.checkTSpinAttack()) {
					System.out.println("T-Spin Triple!!");
					isB2B = true;
				}
			}
			scorePanel.plusScore(THREE_LINE);
		} else if (deleteLine == 4) {
			if(isB2B) {
				System.out.println("Back-to-Back!");
			}
			System.out.println("TETRIS!!");
			scorePanel.plusScore(TETRIS);
			isB2B = true;
		} else {
			// delete 0 line
			isB2B = false;
		}

		// ゲームオーバーか
		if (field.isStacked()) {
			System.out.println("Game Over");
			// スコアをリセット
			scorePanel.setScore(0);
			// フィールドをリセット
			field = new Field();
			mino = createMino(field);
			nextMinoPanel_1.set(nextMinos.refferNextMino(0), minoImage);
			nextMinoPanel_2.set(nextMinos.refferNextMino(1), minoImage);
			nextMinoPanel_3.set(nextMinos.refferNextMino(2), minoImage);
			nextMinoPanel_4.set(nextMinos.refferNextMino(3), minoImage);
			holdMinoPanel.set(null, minoImage);
		}
		goNextMino();
		isHold = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// フィールドを描画
		field.draw(g, minoImage);
		// テトリミノを描画
		mino.drawShadow(g, minoImage);
		mino.draw(g, minoImage);
	}

	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		if (key == 'q' && !isHold) {
			if (holdMino == null) {
				holdMino = mino;
				goNextMino();
				holdMinoPanel.set(holdMino, minoImage);
			} else {
				Mino tmpMino = holdMino;
				holdMino = mino;
				mino = tmpMino;
				holdMinoPanel.set(holdMino, minoImage);
			}
			mino.setNewPosForHold();
			repaint();
			isHold = true;
		} else if (key == 'a') {
			mino.spin();
		} else if (key == 'd') {
			mino.reverseSpin();
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) { // ブロックを左へ移動
			mino.move(Mino.LEFT);
		} else if (key == KeyEvent.VK_RIGHT) { // ブロックを右へ移動
			mino.move(Mino.RIGHT);
		} else if (key == KeyEvent.VK_DOWN) { // ブロックを下へ移動
			mino.move(Mino.DOWN);
			scorePanel.plusScore(USER_DROP);
		} else if (key == KeyEvent.VK_UP) {
			mino.move(Mino.HARDDROP);
			lockDownProcess();
		} else if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) { // ブロックを回転
			mino.spin();
		}

		repaint();
	}

	public void keyReleased(KeyEvent e) {
		// do nothing
	}

	public void goNextMino() {
		mino = nextMinos.popNextMinoAndSupply();
		SRSMinoCheck(mino);
		nextMinoPanel_1.set(nextMinos.refferNextMino(0), minoImage);
		nextMinoPanel_2.set(nextMinos.refferNextMino(1), minoImage);
		nextMinoPanel_3.set(nextMinos.refferNextMino(2), minoImage);
		nextMinoPanel_4.set(nextMinos.refferNextMino(3), minoImage);
	}

	/**
	 * Load mino image
	 *
	 * @param filename
	 */
	private void loadImage(String filename) {
		// ブロックのイメージを読み込む
		ImageIcon icon = new ImageIcon(filename);
		minoImage = icon.getImage();
	}

	/**
	 * Check whether TMino (IMino) or not
	 *
	 * @param mino
	 */
	public void SRSMinoCheck(Mino mino) {
		mino.setIsTMino(false);
		mino.setIsIMino(false);
		if (mino instanceof TMino) {
			mino.setIsTMino(true);
		}
		if (mino instanceof IMino) {
			mino.setIsIMino(true);
		}
		mino.initState();
	}

}
