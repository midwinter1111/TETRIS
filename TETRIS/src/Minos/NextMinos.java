package Minos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import FrameComponent.Field;
import FrameComponent.Mino;

public class NextMinos {

	// フィールド
	private Field field;
	// ネクスト
	private List<Mino> nextMinos = new ArrayList<>();
	// ミノの種類
	private List<Integer> minoList = new ArrayList<>(
			Arrays.asList(Mino.IMino, Mino.ZMino, Mino.SMino,
					Mino.LMino, Mino.JMino, Mino.TMino,
					Mino.OMino));

	public NextMinos(Field field) {
		// フィールド情報を取得
		this.field = field;
		// ネクストを初期化(14個挿入する)
		createSevenMinos();
		createSevenMinos();
	}

	/**
	 *
	 * @param field
	 * @param minoNo
	 * @return
	 */
	private Mino createMino(int minoNo) {
		switch (minoNo) {
		case Mino.IMino:
			return new IMino(field);
		case Mino.ZMino:
			return new ZMino(field);
		case Mino.SMino:
			return new SMino(field);
		case Mino.LMino:
			return new LMino(field);
		case Mino.JMino:
			return new JMino(field);
		case Mino.TMino:
			return new TMino(field);
		case Mino.OMino:
			return new OMino(field);
		}
		return null;
	}

	private void createSevenMinos() {
		Collections.shuffle(minoList);
		for (int i = 0; i < minoList.size(); i++) {
			nextMinos.add(createMino(minoList.get(i)));
		}
	}

	public Mino popNextMinoAndSupply() {
		Mino retMino = null;
		// ネクストを取り出す
		retMino = nextMinos.get(0);
		nextMinos.remove(0);
		if(nextMinos.size() < 7) {
			createSevenMinos();
		}
		return retMino;
	}

	public Mino refferNextMino(int no) {
		return nextMinos.get(no);
	}

}
