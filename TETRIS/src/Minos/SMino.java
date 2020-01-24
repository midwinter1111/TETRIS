package Minos;

import FrameComponent.Field;
import FrameComponent.Mino;

public class SMino extends Mino{
	public SMino(Field field) {
		super(field);

		mino[1][1] = 1;
		mino[2][1] = 1;
		mino[2][2] = 1;
		mino[3][2] = 1;

		imageNo = Mino.SMino;
	}

}
