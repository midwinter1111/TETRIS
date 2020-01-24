package Minos;

import FrameComponent.Field;
import FrameComponent.Mino;

public class LMino extends Mino{
	public LMino(Field field) {
		super(field);

		mino[1][1] = 1;
		mino[1][2] = 1;
		mino[2][2] = 1;
		mino[3][2] = 1;

		imageNo = Mino.LMino;
	}

}
