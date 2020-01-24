package Minos;

import FrameComponent.Field;
import FrameComponent.Mino;

public class TMino extends Mino{
	public TMino(Field field) {
		super(field);

		mino[1][1] = 1;
		mino[2][1] = 1;
		mino[2][2] = 1;
		mino[3][1] = 1;

		imageNo = Mino.TMino;
	}

}
