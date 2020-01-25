package MainComponent;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import FrameComponent.HoldMinoPanel;
import FrameComponent.HoldZoneTag;
import FrameComponent.NextMinoPanel;
import FrameComponent.NextZoneTag;
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
		NextZoneTag nextZoneTag = new NextZoneTag();
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(2, 1));
		infoPanel.add(scorePanel);
		infoPanel.add(nextZoneTag);

		// ネクスト
		NextMinoPanel nextMinoPanel_1 = new NextMinoPanel();
		NextMinoPanel nextMinoPanel_2 = new NextMinoPanel();
		NextMinoPanel nextMinoPanel_3 = new NextMinoPanel();
		NextMinoPanel nextMinoPanel_4 = new NextMinoPanel();
		JPanel nextPanel = new JPanel();
		nextPanel.setLayout(new GridLayout(4, 1));
		nextPanel.add(nextMinoPanel_1);
		nextPanel.add(nextMinoPanel_2);
		nextPanel.add(nextMinoPanel_3);
		nextPanel.add(nextMinoPanel_4);

		rightPanel.add(infoPanel, BorderLayout.NORTH);
		rightPanel.add(nextPanel, BorderLayout.CENTER);

		// 左側パネル
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		HoldZoneTag holdZoneTag = new HoldZoneTag();

		// ホールドしたミノを表示するパネル
		HoldMinoPanel holdMinoPanel = new HoldMinoPanel();
		leftPanel.add(holdZoneTag, BorderLayout.NORTH);
		leftPanel.add(holdMinoPanel, BorderLayout.CENTER);

		// メインパネルを作成してフレームに追加
		// メインパネルからスコア表示パネルを操作するため
		// scorePanel, nextMinoPanelを渡す必要あり！
		MainPanel mainPanel = new MainPanel(scorePanel, nextMinoPanel_1,
				nextMinoPanel_2, nextMinoPanel_3, nextMinoPanel_4, holdMinoPanel);
		contentPane.add(mainPanel, BorderLayout.CENTER);
		contentPane.add(rightPanel, BorderLayout.EAST);
		contentPane.add(leftPanel, BorderLayout.WEST);

		// パネルサイズに合わせてフレームサイズを自動設定
		pack();
	}
}
