package MainComponent;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

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

		// メインパネルを作成してフレームに追加
		// メインパネルからスコア表示パネルを操作するため
		// scorePanel, nextMinoPanelを渡す必要あり！
		MainPanel mainPanel = new MainPanel(scorePanel, nextMinoPanel);
		contentPane.add(mainPanel, BorderLayout.CENTER);
		contentPane.add(rightPanel, BorderLayout.EAST);

		// パネルサイズに合わせてフレームサイズを自動設定
		pack();
	}
}
