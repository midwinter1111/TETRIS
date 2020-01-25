package MainComponent;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import FrameComponent.HoldMinoPanel;
import FrameComponent.NextMinoPanel;
import FrameComponent.ScorePanel;

public class TetrisFrame extends JFrame {
	public TetrisFrame() {
		// タイトル設定
		setTitle("テトリス");
		// サイズ変更を不可に設定
		setResizable(false);

		Container contentPane = getContentPane();

		// 右側パネル
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());

		// スコアパネル
		ScorePanel scorePanel = new ScorePanel();

		// ネクスト
		NextMinoPanel nextMinoPanel = new NextMinoPanel();

		rightPanel.add(scorePanel, BorderLayout.NORTH);
		rightPanel.add(nextMinoPanel, BorderLayout.CENTER);

		// 左側パネル
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		// ホールドしたミノを表示するパネル
		HoldMinoPanel holdMinoPanel = new HoldMinoPanel();
		leftPanel.add(holdMinoPanel, BorderLayout.NORTH);

		// メインパネルを作成してフレームに追加
		// メインパネルからスコア表示パネルを操作するため
		// scorePanel, nextMinoPanelを渡す必要あり！
		MainPanel mainPanel = new MainPanel(scorePanel, nextMinoPanel, holdMinoPanel);
		contentPane.add(mainPanel, BorderLayout.CENTER);
		contentPane.add(rightPanel, BorderLayout.EAST);
		contentPane.add(leftPanel, BorderLayout.WEST);

		// パネルサイズに合わせてフレームサイズを自動設定
		pack();
	}
}
